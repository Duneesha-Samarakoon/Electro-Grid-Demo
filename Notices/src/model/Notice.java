package model; 
import java.sql.*; 

public class Notice 
{ 	//A common method to connect to the DB
	private Connection connect() 
	 { 
		Connection con = null; 
		try
	 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/electrogrid", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 }
	
	
	public String insertNotice(String date, String title, String area, String desc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into notice (`noticeId`,`noticeDate`,`noticeTitle`,`noticeArea`,`noticeDesc`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, date); 
	 preparedStmt.setString(3, title); 
	 preparedStmt.setString(4, area); 
	 preparedStmt.setString(5, desc); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the notice."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	public String readNotice() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>DATE</th><th>SUBJECT</th>" +
	 "<th>EFFECTED AREA(S)</th>" + 
	 "<th>DESCRIPTION</th></tr>"; 
	 
	 String query = "select * from notice"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String noticeId = Integer.toString(rs.getInt("noticeId")); 
	 String noticeDate = rs.getString("noticeDate"); 
	 String noticeTitle = rs.getString("noticeTitle"); 
	 String noticeArea = rs.getString("noticeArea"); 
	 String noticeDesc = rs.getString("noticeDesc"); 
	 
	 // Add into the html table
	 output += "<tr><td>" + noticeDate + "</td>"; 
	 output += "<td>" + noticeTitle + "</td>"; 
	 output += "<td>" + noticeArea + "</td>"; 
	 output += "<td>" + noticeDesc + "</td>"; 
	 // buttons
	 output +=  "<input name='noticeId' type='hidden' value='" + noticeId 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the notices."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	public String updateNotice(String ID, String date, String title, String area, String desc)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE notice SET noticeDate=?,noticeTitle=?,noticeArea=?,noticeDesc=? WHERE noticeId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, date); 
		 preparedStmt.setString(2, title); 
		 preparedStmt.setString(3, area); 
		 preparedStmt.setString(4, desc); 
		 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the notice."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
		public String deleteNotice(String noticeId) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from notice where noticeId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(noticeId)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
			
		public String getNoticeDetails(String noticeId)

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
		output = "<table border='1'><tr><th>DATE</th><th>SUBJECT</th>" +
				 "<th>EFFECTED AREA(S)</th>" + 
				 "<th>DESCRIPTION</th></tr>";
		String query = "select * from notice where noticeID='"+noticeId+"'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		
		 String noticeID = Integer.toString(rs.getInt("noticeID")); 
		 String noticeDate = rs.getString("noticeDate"); 
		 String noticeTitle = rs.getString("noticeTitle"); 
		 String noticeArea = rs.getString("noticeArea"); 
		 String noticeDesc = rs.getString("noticeDesc"); 
		 
		 // Add into the html table
		 output += "<tr><td>" + noticeDate + "</td>"; 
		 output += "<td>" + noticeTitle + "</td>"; 
		 output += "<td>" + noticeArea + "</td>"; 
		 output += "<td>" + noticeDesc + "</td>"; 
		 // buttons
		 output +=  "<input name='noticeId' type='hidden' value='" + noticeID 
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
		
		
	}