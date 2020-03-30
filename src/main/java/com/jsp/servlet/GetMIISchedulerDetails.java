package com.jsp.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import mssqlServerConn.MSSQLServerConn;

/**
 * Servlet implementation class GetMIISchedulerDetails
 */
public class GetMIISchedulerDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String username = "som_sa";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMIISchedulerDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ip = request.getParameter("IP");
        String port = request.getParameter("PORT");
        String admin = request.getParameter("ADMIN");
        String password = request.getParameter("PASSWORD");
        
        String StringURL = "http://"+ ip + ":" + port + "/XMII/Illuminator?service=scheduler&mode=List&content-type=text/xml&IllumLoginName=" + admin + "&IllumLoginPassword=" + password;
        
        String LocalFileURL = "C:\\Users\\SOMSARKAR\\Desktop\\IBM\\Project Work 2018\\Workspaces\\SelfLearningWS\\OSSampleApp3\\src\\main\\resources\\sample.xml";
       
        try {
			
			InputStream in = new FileInputStream(LocalFileURL);
			
	        DocumentBuilderFactory dbf =
	            DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        

	        Document doc = (Document) db.parse(in);
	        NodeList nodes = ((org.w3c.dom.Document) doc).getElementsByTagName("Row");

	        // iterate the employees
	        for (int i = 0; i < nodes.getLength(); i++) {
	           Element element = (Element) nodes.item(i);

	           NodeList id = element.getElementsByTagName("ID");
	           Element line = (Element) id.item(0);
	           String ID = getCharacterDataFromElement(line);

	           NodeList name = element.getElementsByTagName("Name");
	           line = (Element) name.item(0);
	           String Name = getCharacterDataFromElement(line);
	           
	           NodeList blspath = element.getElementsByTagName("FullName");
	           line = (Element) blspath.item(0);
	           String FullName = getCharacterDataFromElement(line);
	           
	           NodeList pattern = element.getElementsByTagName("Pattern");
	           line = (Element) pattern.item(0);
	           String Pattern = getCharacterDataFromElement(line);
	           
		       MSSQLServerConn msserverCon = new MSSQLServerConn();
		      
		       msserverCon.dbConn("som_sa","password@12345");
		       String returnMsg = msserverCon.insertMIISchData(ID,Name,FullName,Pattern);
		       System.out.println("MII Scheduler for ID "+ ID +"is inserted: "+returnMsg);
	        }
	        
	        response.sendRedirect(request.getContextPath() + "/DisplayMIIData.jsp");
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
        
        
	}
	
	  public static String getCharacterDataFromElement(Element e) {
		    Node child = e.getFirstChild();
		    if (child instanceof CharacterData) {
		       CharacterData cd = (CharacterData) child;

		       return cd.getData();
		    }
		    return "?";
		  }

		
		 
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
