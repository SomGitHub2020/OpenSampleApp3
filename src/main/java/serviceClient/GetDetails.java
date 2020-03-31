package serviceClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;

public class GetDetails {
	public static void main(String a[]){
		
		//String url = "http://localhost:8080/RestfulWebServices/order-inventory/order/1016";
		
		//HttpURLConnection urlConn = null;
		//BufferedReader reader = null;
		
		GetDetails file = new GetDetails();
        file.readXMLFile();
		
		/*
        try {
		
		
			URL urlObj = new URL(url);
			urlConn = (HttpURLConnection) urlObj.openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setConnectTimeout(5000);
			urlConn.setReadTimeout(5000);
			urlConn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.err.println("Unable to connect to the URL...");
				return;
			}
			System.out.println("Connected to the server...");
		
			
			
			InputStream is = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader((is)));
			System.out.println("Reading data from server...");
			String tmpStr = null;
			while((tmpStr = reader.readLine()) != null){
				System.out.println(tmpStr);
			}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(reader != null) reader.close();
					if(urlConn != null) urlConn.disconnect();
				} catch(Exception ex){
					
				}
			}
		*/
	}
	
	public void readXMLFile()
	{
		
		
		try {
			
			InputStream in = new FileInputStream("C:\\Users\\SOMSARKAR\\Desktop\\IBM\\Project Work 2018\\Workspaces\\SelfLearningWS\\OSSampleApp3\\src\\main\\resources\\sample.xml");
			
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
	           System.out.println("ID: " + getCharacterDataFromElement(line));

	           NodeList name = element.getElementsByTagName("Name");
	           line = (Element) name.item(0);
	           System.out.println("Name: " + getCharacterDataFromElement(line));
	        }
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
}
