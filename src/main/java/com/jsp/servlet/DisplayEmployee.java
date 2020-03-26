package com.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mssqlServerConn.MSSQLServerConn;

/**
 * Servlet implementation class DisplayEmployee
 */
/*
@WebServlet("/HelloLogin")
*/

public class DisplayEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empID = request.getParameter("ID");
        String firstName = request.getParameter("FName");
        String lastName = request.getParameter("LName");
        
       
		
        MSSQLServerConn msserverCon = new MSSQLServerConn();
		String returnMsg = msserverCon.insertNewEmp(empID,firstName,lastName);
		System.out.println("New Employee recorded: "+returnMsg);
		
		response.sendRedirect(request.getContextPath() + "/Welcome.jsp");  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
