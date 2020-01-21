package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class example {
	public String ID;
	public String GRADE;
	public String HOURS;
	public String FNAME;
	public String LNAME;
	public String SCHOOL;
	
	example(){    
		String[] header = {"ID", "Grade", "First Name", "Last Name", "School", "Hours" };
		DefaultTableModel DTM = new DefaultTableModel(header, 0);
		JTable list = new JTable(DTM);
		JScrollPane scrollPane = new JScrollPane(list);
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
		
		
		
		
		
		
		
		JFrame f=new JFrame("Button Example"); 
					//submit button
		JButton b=new JButton("Search");    
		b.setBounds(475,440,150, 40); 
		JButton u=new JButton("Update");    
		u.setBounds(100,440,150, 40);   
					//enter name label
		JLabel label = new JLabel();		
		label.setText("Enter Name :");
		label.setBounds(10, 10, 100, 100);
					//empty label which will show event after button clicked
		
					// to enter name
		
		scrollPane.setBounds(110, 20, 700, 400);
					//add to frame
		
		JTextField textfield= new JTextField();
		textfield.setBounds(115, 440, 350, 40);
		
		
		
	
		f.add(scrollPane);
		f.add(label);
		f.add(b); 
		f.add(textfield);
		f.setSize(1000,1000);  
		f.setLayout(null);    
		f.setVisible(true);    
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		
							//action listener
		b.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddStudent();		
			}          
	      });
		}         
	
	
		public static void main(String[] args) {    
		    new example();    
		}    
 }
