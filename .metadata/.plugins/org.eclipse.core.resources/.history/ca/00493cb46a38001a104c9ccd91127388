package test;

import java.util.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class AddXmlNode {
   

public void XmlUpload(String I_D, String Grade, String FName,String LName, String School, String Hours ) throws Exception{

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("students.xml");
        Element root = document.getDocumentElement();

        Collection<AddXmlNode> Array = new ArrayList<AddXmlNode>();
        Array.add(new AddXmlNode());

        for (@SuppressWarnings("unused") AddXmlNode upload : Array) {
            // StudentList elements
            Element newStudent = document.createElement("student");
            
            //ID
            Element ID = document.createElement("id");
            ID.appendChild(document.createTextNode(I_D));
            newStudent.appendChild(ID);
            
            //GRADE
            Element grade = document.createElement("grade");
            grade.appendChild(document.createTextNode(Grade));
            newStudent.appendChild(grade);

            //FIRSTNAME
            Element fname = document.createElement("firstname");
            fname.appendChild(document.createTextNode(FName));
            newStudent.appendChild(fname);

            //LASTNAME
            
            Element lname = document.createElement("lastname");
            lname.appendChild(document.createTextNode(LName));
            newStudent.appendChild(lname);
            
            //SCHOOL
            Element school = document.createElement("school");
            school.appendChild(document.createTextNode(School));
            newStudent.appendChild(school);
            
            //HOURS
            Element hours = document.createElement("hours");
            hours.appendChild(document.createTextNode(Hours));
            newStudent.appendChild(hours);


            root.appendChild(newStudent);
            
        }

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        StreamResult result = new StreamResult("students.xml");
        transformer.transform(source, result);

    
}
	

}