<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Details</title>
</head>
<body bgcolor="#000000">

    <div align="center" style="margin-top: 50px;">
 
        <form action="LoginServlet">
           <font color="#ffffff"> Please Enter Server IP: </font> 
           <input type="text" name="IP" size="20px"> <br>
           <font color="#ffffff"> Please Enter Server Port:</font> 
           <input type="text" name="PORT" size="20px"> <br>
             <font color="#ffffff"> Please Enter Admin: </font> 
           <input type="text" name="ADMIN" size="20px"> <br>
             <font color="#ffffff"> Please Enter Password: </font> 
           <input type="text" name="PASSWORD" size="20px"> <br>
           <br>
			<input type="submit" value="Configure">
        </form>
 
    </div>

</body>
</html>