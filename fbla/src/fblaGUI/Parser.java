package fblaGUI;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser {
	
	public String ID;
	public String GRADE;
	public String HOURS;
	public String FNAME;
	public String LNAME;
	public String SCHOOL;
	String[] header = {"ID", "Grade", "First Name", "Last Name", "School", "Hours" };
	protected DefaultTableModel DTM = new DefaultTableModel(header, 0);
	
	
	public DefaultTableModel parse() {
		
		
		
	try
	{
		SAXParserFactory fact = SAXParserFactory.newInstance();
		SAXParser saxParser = fact.newSAXParser();
		
		DefaultHandler handle = new DefaultHandler() {
			boolean bID = false;
			boolean bgrade = false;
			boolean bfname = false;
			boolean blname = false;
			boolean bschool = false;
			boolean bhours = false;
			
			
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
				
				if(qName.equals("id")) {
					bID = true;
				}
				if(qName.equals("grade")) {
					bgrade = true;
				}
				if(qName.equals("firstname")) {
					bfname = true;
				}
				if(qName.equals("lastname")) {
					blname = true;
				}
				if(qName.equals("school")) {
					bschool = true;
				}
				if(qName.equals("hours")) {
					bhours = true;
				}
				
			}
			
			public void endElement(String uri, String localName, String qName) {
				
			}
			
			public void characters(char[] ch, int start, int length )throws SAXException{
				//ID will always be first
				
				
				if(bID) {
			
					ID = new String(ch, start, length);
					bID = false;
					
				}
				if(bgrade) {
					
					GRADE = new String(ch, start, length);
					bgrade = false;
				}
				if(bfname) {
				
					FNAME = new String(ch, start, length);
					bfname = false;
				}
				if(blname) {
					
					LNAME = new String(ch, start, length);
					blname = false;
				}
				if(bschool) {
					
					SCHOOL = new String(ch, start, length);
					bschool = false;
				}
				
				if(bhours) {
					
					HOURS = new String(ch, start, length);
					bhours = false;
					
					Object[] info = {ID, GRADE, FNAME, LNAME, SCHOOL, HOURS};
					DTM.addRow(info);
						
					
				}
				
				
				
				
				
			}
		};
		saxParser.parse("students.xml", handle);
		
		}
	
	catch(Exception ex ) {
		
	}
	return DTM;
}
}