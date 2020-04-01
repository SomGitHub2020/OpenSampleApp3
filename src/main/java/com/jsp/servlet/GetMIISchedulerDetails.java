package com.jsp.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import mssqlServerConn.MSSQLServerConn;

/**
 * Servlet implementation class GetMIISchedulerDetails
 */
public class GetMIISchedulerDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public String outputMsg = "";
       
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
	        
	        String urlString = "http://"+ ip + ":" + port + "/XMII/Illuminator?service=scheduler&mode=List&content-type=text/xml&IllumLoginName=" + admin + "&IllumLoginPassword=" + password;
	        
	        //String LocalFileURL = "C:\\Users\\SOMSARKAR\\Desktop\\IBM\\Project Work 2018\\Workspaces\\SelfLearningWS\\OSSampleApp3\\src\\main\\resources\\sample.xml";
	    	//InputStream in = new FileInputStream(LocalFileURL);
    	
			//String urlString = "https://maps.googleapis.com/maps/api/timezone/xml?location=38.908133,-77.047119&timestamp=1458000000&key=AIzaSyB48w-yapMF-WrZYmmNOvuw3y1s2J9mZCU";
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			
	        InputStream in = conn.getInputStream();
			
	        DocumentBuilderFactory dbf =
	            DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        

	        Document doc = null;
			try {
				doc = (Document) db.parse(in);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			NodeList nodes = ((org.w3c.dom.Document) doc).getElementsByTagName("Row");
			//NodeList nodes = ((org.w3c.dom.Document) doc).getElementsByTagName("TimeZoneResponse");
	        
	        // iterate the employees
	        for (int i = 0; i < nodes.getLength(); i++) {
	           Element element = (Element) nodes.item(i);
	           
	           /*
	           NodeList status = element.getElementsByTagName("status");
	           Element line = (Element) status.item(0);
	           String STATUS = getCharacterDataFromElement(line);

	           NodeList error_message = element.getElementsByTagName("error_message");
	           line = (Element) error_message.item(0);
	           String ERR_MSG = getCharacterDataFromElement(line);
	           */
	           
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
		       //String returnMsg = msserverCon.insertDummyData(STATUS,ERR_MSG);
		       outputMsg = outputMsg + returnMsg;
		       System.out.println("Data is inserted "+returnMsg);
	        }
	        
       
	        //response.sendRedirect(request.getContextPath() + "/DisplayMIIData.jsp?returnMsg=" + outputMsg);
	        response.sendRedirect(request.getContextPath() + "/DisplayMIIData.jsp");
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
