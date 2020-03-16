package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mssqlServerConn.MSSQLServerConn;

/**
 * Servlet implementation class HelloLogin
 */
/*
@WebServlet("/HelloLogin")
*/
public class HelloLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 
		String username = request.getParameter("username");
        String password = request.getParameter("password");
       
		
        MSSQLServerConn msserverCon = new MSSQLServerConn();
        String returnMsg = msserverCon.dbConn(username,password);
        
       //System.out.println("DB Conn is made with: "+returnMsg);
       //response.sendRedirect("/SAPUI5/index.html?U="+username+" "+password);  
       
        /*
        PrintWriter out = response.getWriter();
        out.println(request.getContextPath());
        */
               
        PrintWriter out = response.getWriter();
        out.println (
                  "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                      "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                  "<html> \n" +
                    "<head> \n" +
                      "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
                        "charset=ISO-8859-1\"> \n" +
                      "<title> You are welcome!  </title> \n" +
                    "</head> \n" +
                    "<body> <div align='center'> \n" +
                      "<style= \"font-size=\"12px\" color='black'\"" + "\">" +
                        "Hello " + username + ", Welcome! But we've noted your password: " + password + 
                        " <br> <br>" +
                        "Now, go and please submit the goals for 2020!" +
                        " <br> <br>" +
                        " MS SQL DB is connected: " + returnMsg +
                    "</font></body> \n" +
                  "</html>" 
                );      

        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
