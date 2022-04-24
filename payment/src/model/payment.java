package model;

import java.sql.*;

public class payment {
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid_payment", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	public String insertPayment(String paymentCode, String billID, String paymentMethod, String cardNumber, String nameOnCard, String cvc, String expireDate, String amount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into payment(`paymentID`,`paymentCode`,`billID`,`paymentMethod`,`cardNumber`,`nameOnCard`,`cvc`,`expireDate`,`amount`)"
	 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, paymentCode);
	 preparedStmt.setInt(3, Integer.parseInt(billID));
	 preparedStmt.setString(4, paymentMethod);
	 preparedStmt.setString(5, cardNumber);
	 preparedStmt.setString(6, nameOnCard);
	 preparedStmt.setString(7, cvc);
	 preparedStmt.setString(8, expireDate);
	 preparedStmt.setDouble(9, Double.parseDouble(amount));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readPayment()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>paymentCode</th><th>billID</th>" +
	 "<th>paymentMethod</th>" +
	 "<th>cardNumber</th>" +
	 "<th>nameOnCard</th>"+
	 "<th>cvc</th>"+
	 "<th>expireDate</th>"+
	 "<th>amount</th>"+
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String paymentID = Integer.toString(rs.getInt("paymentID"));
	 String paymentCode = rs.getString("paymentCode");
	 String billID = Integer.toString(rs.getInt("billID"));
	 String paymentMethod = rs.getString("paymentMethod");
	 String cardNumber = rs.getString("cardNumber");
	 String nameOnCard = rs.getString("nameOnCard");
	 String cvc = rs.getString("cvc");
	 String expireDate = rs.getString("expireDate");
	 String amount = Double.toString(rs.getDouble("amount"));
	 // Add into the html table
	 output += "<tr><td>" + paymentCode + "</td>";
	 output += "<td>" + billID + "</td>";
	 output += "<td>" + paymentMethod + "</td>";
	 output += "<td>" + cardNumber + "</td>";
	 output += "<td>" + nameOnCard + "</td>";
	 output += "<td>" + cvc + "</td>";
	 output += "<td>" + expireDate + "</td>";
	 output += "<td>" + amount + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='paymentID' type='hidden' value='" + paymentID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updatePayment(String paymentID, String paymentCode, String billID, String paymentMethod, String cardNumber, String nameOnCard, String cvc, String expireDate, String amount)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE payment SET paymentCode=?,billID=?,paymentMethod=?,cardNumber=?,nameOnCard=?,cvc=?,expireDate=?,amount=? WHERE paymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, paymentCode);
		 preparedStmt.setInt(2, Integer.parseInt(billID));
		 preparedStmt.setString(3, paymentMethod);
		 preparedStmt.setString(4, cardNumber);
		 preparedStmt.setString(5, nameOnCard);
		 preparedStmt.setString(6, cvc);
		 preparedStmt.setString(7, expireDate);
		 preparedStmt.setDouble(8, Double.parseDouble(amount));
		 preparedStmt.setInt(9, Integer.parseInt(paymentID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the payment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deletePayment(String paymentID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from payment where paymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(paymentID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the payment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String getPaymentDetails(String paymentID)

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
				output = "<table border='1'><tr><th>paymentCode</th>"
						+"<th>billID</th><th>paymentMethod</th>"
						+ "<th>cardNumber</th>"
						+ "<th>nameOnCard</th>"
						+ "<th>cvc</th>"
						+ "<th>expireDate</th>"
						+ "<th>amount</th>";
				String query = "select * from payment where paymentId='"+paymentID+"'";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{ 
					//String paymentID = Integer.toString(rs.getInt("paymentID"));
					 String paymentCode = rs.getString("paymentCode");
					 String billID = Integer.toString(rs.getInt("billID"));
					 String paymentMethod = rs.getString("paymentMethod");
					 String cardNumber = rs.getString("cardNumber");
					 String nameOnCard = rs.getString("nameOnCard");
					 String cvc = rs.getString("cvc");
					 String expireDate = rs.getString("expireDate");
					 String amount = Double.toString(rs.getDouble("amount"));
					// Add a row into the html table
					 output += "<tr><td>" + paymentCode + "</td>";
					 output += "<td>" + billID + "</td>";
					 output += "<td>" + paymentMethod + "</td>";
					 output += "<td>" + cardNumber + "</td>";
					 output += "<td>" + nameOnCard + "</td>";
					 output += "<td>" + cvc + "</td>";
					 output += "<td>" + expireDate + "</td>";
					 output += "<td>" + amount + "</td>";

					// buttons
					output += "<input name='paymentID' type='hidden' "
							+ " value='" + paymentID + "'>"
							+ "</form></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";

			}
			catch (Exception e)
			{
				output = "Error while reading the payment details";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
}
	 
