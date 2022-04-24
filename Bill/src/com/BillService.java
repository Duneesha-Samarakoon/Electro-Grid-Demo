package com;

import model.Bill; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Bill") 

public class BillService {
 Bill billObj = new Bill(); 
 
 @POST
 @Path("/")
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 @Produces(MediaType.TEXT_PLAIN)
 
 public String insertBill (@FormParam("billCode") String billCode,
  @FormParam("userID") String userID,
  @FormParam("dueMonth") String dueMonth,
  @FormParam("units") int units)
 {
  String output = billObj.insertBill (billCode, userID, dueMonth, units);
 return output;
 }

 @GET
 @Path("/")
 @Produces(MediaType.TEXT_HTML)
 public String readBill()
  {
  return billObj.readBill();
 } 
 
 @PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData)
	{
		//Convert the input string to a JSON object
		 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
		 
		//Read the values from the JSON object
		 String billID = billObject.get("billID").getAsString();
		 String billCode = billObject.get("billCode").getAsString();
		 String userID = billObject.get("userID").getAsString();
		 String dueMonth = billObject.get("dueMonth").getAsString();
		 int units = billObject.get("units").getAsInt();
		 
		 String output = billObj.updateBill(billID, billCode, userID, dueMonth, units );
		 
		 return output;
	}
 
 
 @DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
	
		//Read the value from the element <billID>
		 String billID = doc.select("billID").text();
		 
		 String output = billObj.deleteBill(billID);
		 
		 return output;
	}
 
 
 @GET
	@Path("/getBillByID/{billID}")//view a specific bill
	@Produces(MediaType.TEXT_HTML)
	public String billDetails(@PathParam("billID") String billID) {

		return billObj.getBillDetails(billID);
	}
 
}