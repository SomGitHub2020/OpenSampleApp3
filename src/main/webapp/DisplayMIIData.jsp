<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MII Scheduler Jobs List</title>
</head>
<body bgcolor="#000000">
	    
	   	<div align="center" style="margin-top: 50px;">
 
         <font color="#ffffff">  Welcome! Below is list of MII Scheduler Jobs </font>
 
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
				String QueryString = "SELECT * from MIISchedulerJobs";
				rs = statement.executeQuery(QueryString);
			%>
    			<TABLE cellpadding="15" border="1" style="background-color: #ffffcc;">
    			<TR>
				<TD>ID</TD>
				<TD>Name</TD>
				<TD>BLS Path</TD>
				<TD>Pattern</TD>
				</TR>
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