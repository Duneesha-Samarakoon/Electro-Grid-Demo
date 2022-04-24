package com;

import model.User; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Users") 
public class UserService 
{ 
	User UserObj = new User(); 
	
	//retrive
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	 { 
	 return UserObj.readUsers(); 
	 } 
	
	//retreive by ID
	@GET
	@Path("/getUserbyID/{userId}")//view a specific user
	@Produces(MediaType.TEXT_HTML)
	public String getUserDetails(@PathParam("userId") String userId) {

		return UserObj.getUserDetails(userId);
	}
	
	//insert
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("userCode") String userCode, 
	 @FormParam("userName") String userName, 
	 @FormParam("province") String province, 
	 @FormParam("areaOffice") String areaOffice,
	 @FormParam("userTP") String userTP,
	 @FormParam("userEmail") String userEmail,
	 @FormParam("userAccounts") String userAccounts) 
	{ 
	 String output = UserObj.insertUser(userCode, userName, province, areaOffice,userTP,userEmail,userAccounts); 
	return output; 
	}
	
	//update
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String userData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String userID = userObject.get("userID").getAsString(); 
	 String userCode = userObject.get("userCode").getAsString(); 
	 String userName = userObject.get("userName").getAsString(); 
	 String province = userObject.get("province").getAsString(); 
	 String areaOffice = userObject.get("areaOffice").getAsString(); 
	 String userTP = userObject.get("userTP").getAsString();
	 String userEmail = userObject.get("userEmail").getAsString();
	 String userAccounts = userObject.get("userAccounts").getAsString();
	 String output = UserObj.updateUser(userID, userCode, userName, province, areaOffice,userTP,userEmail,userAccounts); 
	return output; 
	}
	
	//delete
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUser(String userData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String userID = doc.select("userID").text(); 
	 String output = UserObj.deleteUser(userID); 
	return output; 
	}

}
