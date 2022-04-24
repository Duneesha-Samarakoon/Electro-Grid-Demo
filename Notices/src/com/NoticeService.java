package com; 
import model.Notice; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser; 
@Path("/Notices") 

public class NoticeService 
{ 
	Notice noticeObj = new Notice(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readNotice() 
	 { 
		return noticeObj.readNotice();
	 }
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String insertNotice(
	 @FormParam("noticeDate") String noticeDate, 
	 @FormParam("noticeTitle") String noticeTitle, 
	 @FormParam("noticeArea") String noticeArea, 
	 @FormParam("noticeDesc") String noticeDesc) 
	{ 
		String output = noticeObj.insertNotice(noticeDate, noticeTitle, noticeArea, noticeDesc); 
		return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String noticeData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject noticeObject = new JsonParser().parse(noticeData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String noticeId = noticeObject.get("noticeId").getAsString(); 
	 String noticeDate = noticeObject.get("noticeDate").getAsString(); 
	 String noticeTitle = noticeObject.get("noticeTitle").getAsString(); 
	 String noticeArea = noticeObject.get("noticeArea").getAsString(); 
	 String noticeDesc = noticeObject.get("noticeDesc").getAsString(); 
	 String output = noticeObj.updateNotice(noticeId, noticeDate, noticeTitle, noticeArea, noticeDesc); 
	return output; 
	}

	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String noticeData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String noticeId = doc.select("noticeId").text(); 
	 String output = noticeObj.deleteNotice(noticeId); 
	return output; 
	}
	
	
	@GET
	@Path("/getNoticebyID/{noticeID}")//view a specific user
	@Produces(MediaType.TEXT_HTML)
	public String NoticeDetails(@PathParam("noticeID") String noticeID) {

	return noticeObj.getNoticeDetails(noticeID);
	}

	
}

