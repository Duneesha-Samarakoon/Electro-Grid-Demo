package com;

import model.payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/payment")
public class paymentService {
	
	payment paymentObj = new payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment()
	 {
	 return paymentObj.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("paymentCode") String paymentCode,
	 @FormParam("billID") String billID,
	 @FormParam("paymentMethod") String paymentMethod,
	 @FormParam("cardNumber") String cardNumber,
	@FormParam("nameOnCard") String nameOnCard,
	@FormParam("cvc") String cvc,
	@FormParam("expireDate") String expireDate,
	@FormParam("amount") String amount)
	
	//inserting validations
		{
			if(paymentCode.isEmpty()||billID.isEmpty()||paymentMethod.isEmpty()||cardNumber.isEmpty()||nameOnCard.isEmpty()||cvc.isEmpty()||expireDate.isEmpty()||amount.isEmpty())
			{
				 return "payment fields must be filled out";
			}
		
			else if(cardNumber.length()!=16) {
				 return "cardNumber length must be 16 characters long";
			 }
			String output = paymentObj.insertPayment(paymentCode, billID, paymentMethod, cardNumber, nameOnCard, cvc, expireDate, amount);
			return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String paymentID = paymentObject.get("paymentID").getAsString();
	 String paymentCode = paymentObject.get("paymentCode").getAsString();
	 String billID = paymentObject.get("billID").getAsString();
	 String paymentMethod = paymentObject.get("paymentMethod").getAsString();
	 String cardNumber = paymentObject.get("cardNumber").getAsString();
	 String nameOnCard = paymentObject.get("nameOnCard").getAsString();
	 String cvc = paymentObject.get("cvc").getAsString();
	 String expireDate = paymentObject.get("expireDate").getAsString();
	 String amount = paymentObject.get("amount").getAsString();
	 String output = paymentObj.updatePayment(paymentID, paymentCode, billID, paymentMethod, cardNumber, nameOnCard, cvc, expireDate, amount);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
	
		//Read the value from the element <paymentID>
		 String paymentID = doc.select("paymentID").text();
		 
		 String output = paymentObj.deletePayment(paymentID);
		 
		 return output;
	}
	
	@GET
	@Path("/getPaymentByID/{paymentID}")//view a specific payment
	@Produces(MediaType.TEXT_HTML)
	public String paymentDetails(@PathParam("paymentID") String paymentID) {

		return paymentObj.getPaymentDetails(paymentID);
	}

}
