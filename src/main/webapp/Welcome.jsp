<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
</head>
<body bgcolor="#000000">
	    
	   	<div align="center" style="margin-top: 50px;">
 
         <font color="#ffffff">  Welcome! Please Enter your details below </font>
 
    	</div>
	    
	    <div align="center" style="margin-top: 50px;">
 
        <form action="EmployeeServlet">
        	<font color="#ffffff"> EmpID: </font>
        	 <input type="text" name="ID" size="20px"> <br>
        	 <font color="#ffffff"> First Name: </font> 
        	 <input type="text" name="FName" size="20px"> <br><br>
            <font color="#ffffff"> Last Name: </font> 
            <input type="text" name="LName" size="20px"> <br><br>
        <input type="Submit" value="Add">
        </form>
 
    	</div>
    	<br><br>
    	<div align="center" style="margin-top: 50px;">
    	
    	<%
			try {

				String connectionURL = "jdbc:sqlserver://192.168.1.102:1433;databasename=SOMDB;user=som_sa;password=password@12345";
				
				Connection connection = null;
			
				Statement statement = null;
				
				ResultSet rs = null;
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

				connection = DriverManager.getConnection(connectionURL);

				statement = connection.createStatement();
				// sql query to retrieve values from the secified table.
				String QueryString = "SELECT * from Employee";
				rs = statement.executeQuery(QueryString);
			%>
    			<TABLE cellpadding="15" border="1" style="background-color: #ffffcc;">
			<%
				while (rs.next()) {
				%>
				<TR>
				<TD><%=rs.getString(1)%></TD>
				<TD><%=rs.getString(2)%></TD>
				<TD><%=rs.getString(3)%></TD>
				<TD><%=rs.getString(4)%></TD>
				</TR>
			<% } %>
			<%
			// close all the connections.
				rs.close();
				statement.close();
				connection.close();
				} catch (Exception ex) {
			%>

			<%
			out.println("Unable to connect to database.");
			}
			%>
			</TABLE>
    	</div>
</body>
</html>