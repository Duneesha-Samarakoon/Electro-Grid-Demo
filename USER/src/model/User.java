package model; 

import java.sql.*; 

public class User 
{ //A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogirddemo", "root", ""); 
			} 
			catch (Exception e) 
			{e.printStackTrace();} 
			return con; 
	} 
	
	public String getUserDetails(String userID)

	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User Code</th><th>User Name</th>" +"<th>Province</th>" + 
					 "<th>Area Office</th>" +"<th>Telephone</th>" +"<th>Email</th>" +"<th>Accounts</th>" +
					 "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from user where userId='"+userID+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{ 
				 String userID1 = Integer.toString(rs.getInt("userID")); 
				 String userCode = rs.getString("userCode"); 
				 String userName = rs.getString("userName"); 
				 String province = rs.getString("province");
				 String areaOffice = rs.getString("areaOffice");
				 String userTP = rs.getString("userTP"); 
				 String userEmail = rs.getString("userEmail"); 
				 String UserAccounts = rs.getString("UserAccounts"); 
				 
				 // Add into the html table
				 output += "<tr><td>" + userCode + "</td>"; 
				 output += "<td>" + userName + "</td>"; 
				 output += "<td>" + province + "</td>"; 
				 output += "<td>" + areaOffice + "</td>"; 
				 output += "<td>" + userTP + "</td>"; 
				 output += "<td>" + userEmail + "</td>"; 
				 output += "<td>" + UserAccounts + "</td>"; 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update User' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='users.jsp'>"+ "<input name='btnRemove' type='submit' value='Delete User' class='btn btn-danger'>"
				 + "<input name='userID' type='hidden' value='" + userID1
				 + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";

		}
		catch (Exception e)
		{
			output = "Error while reading the user details";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String insertUser(String userCode, String userName, String province, String areaOffice, String userTP , String userEmail, String UserAccounts) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{return "Error while connecting to the database for inserting.."; } 
			// create a prepared statement
			String query = "INSERT INTO user(`userID`,`userCode`,`userName`,`province`,`areaOffice`,`userTP`,`userEmail`,`userAccounts`) values (?, ?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, userCode  ); 
			 preparedStmt.setString(3, userName ); 
			 preparedStmt.setString(4, province); 
			 preparedStmt.setString(5, areaOffice);
			 preparedStmt.setString(6, userTP );
			 preparedStmt.setString(7, userEmail);
			 preparedStmt.setString(8, UserAccounts); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while inserting the users."; 
			 System.err.println(e.getMessage()); 
		 } 
		return output; 
	} 
//	
	public String readUsers() 
	 { 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>User Code</th><th>User Name</th>" +"<th>Province</th>" + 
			 "<th>Area Office</th>" +"<th>Telephone</th>" +"<th>Email</th>" +"<th>Accounts</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from user"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 
			 while (rs.next()) 
			 { 
				 String userID = Integer.toString(rs.getInt("userID")); 
				 String userCode = rs.getString("userCode"); 
				 String userName = rs.getString("userName"); 
				 String province = rs.getString("province");
				 String area = rs.getString("areaOffice");
				 String UserTP = rs.getString("userTP"); 
				 String email = rs.getString("userEmail"); 
				 String accounts = rs.getString("UserAccounts"); 
				 
				 // Add into the html table
				 output += "<tr><td>" + userCode + "</td>"; 
				 output += "<td>" + userName + "</td>"; 
				 output += "<td>" + province + "</td>"; 
				 output += "<td>" + area + "</td>"; 
				 output += "<td>" + UserTP + "</td>"; 
				 output += "<td>" + email + "</td>"; 
				 output += "<td>" + accounts + "</td>"; 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update User' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='users.jsp'>"+ "<input name='btnRemove' type='submit' value='Delete User' class='btn btn-danger'>"
				 + "<input name='userID' type='hidden' value='" + userID 
				 + "'>" + "</form></td></tr>"; 
			 } 
			 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the user details."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
//
public String updateUser(String userID, String userCode, String userName, String province, String areaOffice , String userTP, String userEmail, String UserAccounts) 
	 { 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 
			 // create a prepared statement
			 String query = "UPDATE user SET userCode=?,userName=?,province=?,areaOffice=?,userTP=?,userEmail=?,userAccounts=? WHERE userID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values 
			 preparedStmt.setString(1, userCode); 
			 preparedStmt.setString(2, userName); 
			 preparedStmt.setString(3, province); 
			 preparedStmt.setString(4, areaOffice );
			 preparedStmt.setString(5, userTP);
			 preparedStmt.setString(6, userEmail);
			 preparedStmt.setString(7, UserAccounts);  
			 preparedStmt.setInt(8, Integer.parseInt(userID)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "User Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the User details."; 
			 System.err.println(e.getMessage()); 
		 } 
	 return output; 
 } 

//delete
public String deleteUser(String userID) 
 { 
	 String output = ""; 
	 
	 try
	 { 
		 Connection con = connect(); 
		 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 // create a prepared statement
		 String query = "delete from user where userID=?"; 
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(userID));
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 output = "User Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while deleting the User details."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
 } 
} 
