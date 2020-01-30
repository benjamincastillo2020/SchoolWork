package fblaGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.TableRowSorter;




public class LandingPage {
	public String ID;
	public String GRADE;
	public String HOURS;
	public String FNAME;
	public String LNAME;
	public String SCHOOL;
	
	
	LandingPage(){    
		
		JFrame frame=new JFrame("Landing Page"); 
					//submit button
		JButton addStudentButton = new JButton("Add Student");    
		addStudentButton.setBounds(820,130,150, 40);
		
		
		
		JButton updateButton=new JButton("Update");    
		updateButton.setBounds(820,20,150, 40); 
		JButton saveButton=new JButton("Save Data");    
		saveButton.setBounds(820,80,150, 40); 
					//enter name label
		JLabel label = new JLabel();		
		label.setText("Search Data");
		label.setBounds(10, 410, 100, 100);
					//empty label which will show event after button clicked
		
					// to enter name
		Parser parseclass = new Parser();
		JTable list = new JTable(parseclass.parse());
		JScrollPane scrollPane = new JScrollPane(list);
		TableRowSorter sorter = new TableRowSorter<>(parseclass.DTM);
		list.setRowSorter(sorter);
		
					//add to frame
		
		JTextField jtf= new JTextField();
		jtf.setBounds(115, 440, 350, 40);
		
		
		
		scrollPane.setBounds(10, 20, 800, 400);
		frame.add(scrollPane);
		frame.add(label);
		frame.add(addStudentButton);
		frame.add(updateButton);
		frame.add(jtf);
		frame.add(saveButton);
		frame.setSize(1000,1000);  
		frame.setLayout(null);    
		frame.setVisible(true);    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		
							
		addStudentButton.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddStudent();		
			}          
	      });
		updateButton.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				parseclass.DTM.setRowCount(0);
				JTable list = new JTable(parseclass.parse());
				JScrollPane scrollPane = new JScrollPane(list);
				
				
			}          
	      });
		jtf.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
	            search(jtf.getText());
	         }
			@Override
	         public void removeUpdate(DocumentEvent e) {
	            search(jtf.getText());
	         }
			@Override
	         public void changedUpdate(DocumentEvent e) {
	            search(jtf.getText());
	         }
			public void search(String str) {
	            if (str.length() == 0) {
	               sorter.setRowFilter(null);
	            } else {
	               sorter.setRowFilter(RowFilter.regexFilter(str));
	            }
	         }
	      });
		
		
	}         
	
	
	public static void main(String[] args) {    
		    new LandingPage();    
		}    
		
		
 }
