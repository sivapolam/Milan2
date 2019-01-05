package com.ssit.localsearch;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class GlobalSearchList {
	public static ArrayList<String> name=new ArrayList<String>();
	public static ArrayList<String> location=new ArrayList<String>();
	public static ArrayList<String> lat=new ArrayList<String>();
	public static ArrayList<String> lng =new ArrayList<String>();
	public static ArrayList<String> referenceKey =new ArrayList<String>();
	
	private static final int CONNECTION_TIMEOUT = 30000;
	
    public static String PhoneNo,sunRise,sunSet,time,placename,sunrisenew,sunsetnew;
	
	public static void getSearchList(String reqUrl){
		
		try {
			
			name.clear();
			location.clear();
			lat.clear();
			lng.clear();
			
			URL u = new URL(reqUrl);
			URLConnection c = u.openConnection();
			c.setConnectTimeout(CONNECTION_TIMEOUT);
			c.setReadTimeout(CONNECTION_TIMEOUT);
	        
	        
	        // write connection to file
            InputStream is = c.getInputStream();
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
       
          
            String nodeValue = null;
            
            NodeList nodeLst1 = doc.getElementsByTagName("PlaceSearchResponse");
            

            nodeLst1 = doc.getElementsByTagName("result");

                System.out.println("No of Results: "+nodeLst1.getLength());
                
                Constants.nodeLength=nodeLst1.getLength();
                
                for (int s = 0; s < nodeLst1.getLength(); s++) {
               	 Node fstNode = nodeLst1.item(s);
               	 
               	 if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
           		 	
               		 Element fstElmnt = (Element) fstNode;
               
               		 NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        NodeList fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                        
                        name.add(nodeValue);
                                             
                        
                        try {
						
                        	fstNmElmntLst = fstElmnt.getElementsByTagName("vicinity");
                            fstNmElmnt = (Element) fstNmElmntLst.item(0);
                            fstNm = fstNmElmnt.getChildNodes();
                            nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                            
                            location.add(nodeValue);
                        	
                        	
						} catch (Exception e) {
							location.add("No Address");
							// TODO: handle exception
						}
                        
                        fstNmElmntLst = fstElmnt.getElementsByTagName("lat");
                        fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                        
                        lat.add(nodeValue);
                        
                        
                        fstNmElmntLst = fstElmnt.getElementsByTagName("lng");
                        fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                        
                        lng.add(nodeValue);
                        
                        
                        fstNmElmntLst = fstElmnt.getElementsByTagName("reference");
                        fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                        
                        referenceKey.add(nodeValue);
                        
                       
                    
                      
               	 }
               }
			
			
		} catch (Exception e) {
			
			 
			 
			// TODO: handle exception
		}
		
		
	}

	public static void getSearchListDetails(String url) {
		// TODO Auto-generated method stub
		
		
try {

			URL u = new URL(url);
			URLConnection c = u.openConnection();
	        
	        
	        // write connection to file
            InputStream is = c.getInputStream();
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
       
          
            String nodeValue = null;
            
            NodeList nodeLst1 = doc.getElementsByTagName("PlaceDetailsResponse");
            

            nodeLst1 = doc.getElementsByTagName("result");

                System.out.println("No of Results: "+nodeLst1.getLength());
                
                Constants.nodeLength=nodeLst1.getLength();
                
                for (int s = 0; s < nodeLst1.getLength(); s++) {
               	 Node fstNode = nodeLst1.item(s);
               	 
               	 if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
           		 	
               		 Element fstElmnt = (Element) fstNode;
               
               		 NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("international_phone_number");
                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        NodeList fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                        
                        PhoneNo=nodeValue;
                       
                    
                      
               	 }
               }
                
                
			
			
		} catch (Exception e) {
			 System.out.println("..."+"hi");
			 
			 
			// TODO: handle exception
		}
		
		
		
	}

	public static void getClock(String clockurl) {
		// TODO Auto-generated method stub
		
		try {

			URL u = new URL(clockurl);
			URLConnection c = u.openConnection();
	        
	        
	        // write connection to file
            InputStream is = c.getInputStream();
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
       
          
            String nodeValue = null;
            
            NodeList nodeLst1 = doc.getElementsByTagName("geonames");
            

            nodeLst1 = doc.getElementsByTagName("timezone");

                System.out.println("No of Results: "+nodeLst1.getLength());
                
                Constants.nodeLength=nodeLst1.getLength();
                
                for (int s = 0; s < nodeLst1.getLength(); s++) {
               	 Node fstNode = nodeLst1.item(s);
               	 
               	 if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
           		 	
               		 Element fstElmnt = (Element) fstNode;
               
               		 NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("sunrise");
                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        NodeList fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                        nodeValue = nodeValue.substring(11,nodeValue.length());
                        sunRise=nodeValue;
                        
                        
                        fstNmElmntLst = fstElmnt.getElementsByTagName("sunset");
                        fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                      
                        nodeValue = nodeValue.substring(11,nodeValue.length());
                        
                        sunSet =nodeValue;
                        
                        
                        fstNmElmntLst = fstElmnt.getElementsByTagName("timezoneId");
                        fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                        
                       
                        placename =nodeValue;
                        
                        
                        
                        fstNmElmntLst = fstElmnt.getElementsByTagName("time");
                        fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        fstNm = fstNmElmnt.getChildNodes();
                        nodeValue = ((Node) fstNm.item(0)).getNodeValue();
                        
                        time =nodeValue;
                        
                       
                    
                      
               	 }
               }
			
			
		} catch (Exception e) {
			 System.out.println("..."+"hi");
			 
			 
			// TODO: handle exception
		}
		
		
	}

		
	}

