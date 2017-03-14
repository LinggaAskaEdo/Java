package itech.sample;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlAppend
{
	public static void main(String[] args)
	{
		try
		{
			File xmlFile = new File("sample.xml");
			//Create the documentBuilderFactory
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			//Create the documentBuilder
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			//Create the Document  by parsing the file
			Document document = documentBuilder.parse(xmlFile);
			//Get the root element of the xml Document;
			Element documentElement = document.getDocumentElement();
			//System.out.println("documentElement:" + documentElement.toString());
			//Get childNodes of the rootElement
			//Create a textNode element			
			Element textNode = document.createElement("TextNode");
			//Text value = document.createTextNode("2");
			textNode.setTextContent("4");
			//Create a Node element
			Element nodeElement = document.createElement("Node");
			//append textNode to Node element;
			nodeElement.appendChild(textNode);
			//append Node to rootNode element
			documentElement.appendChild(nodeElement);
			document.replaceChild(documentElement, documentElement);
			Transformer tFormer = TransformerFactory.newInstance().newTransformer();
			//  Set output file to xml
			tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
			//  Write the document back to the file
			Source source = new DOMSource(document);
			Result result = new StreamResult(xmlFile);
			tFormer.transform(source, result);
		}
		catch (TransformerException ex) 
		{
			Logger.getLogger(XmlAppend.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (SAXException ex) 
		{
			Logger.getLogger(XmlAppend.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (IOException ex)
		{
			Logger.getLogger(XmlAppend.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (ParserConfigurationException ex) 
		{
			Logger.getLogger(XmlAppend.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
