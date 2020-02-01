package fblaGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;




public class LandingPage {
	static JTable list = null;
	public String ID;
	public String GRADE;
	public String HOURS;
	public String FNAME;
	public String LNAME;
	public String SCHOOL;
	
	public String I_D;
	public String Grade;
	public String FName;
	public String LName;
	public String School;
	public String Hours;
	public StudentReportBot reporter = new StudentReportBot();
	
	//(x, y, width, height)
	public LandingPage(){    
		
		JFrame frame=new JFrame("Landing Page"); 
					//submit button
		JButton addStudentButton = new JButton("Add Student");    
		addStudentButton.setBounds(820,130,150, 40);
		
		JButton printReports=new JButton("Print Reports");
		printReports.setBounds(820,180,150, 40);
		
		JButton fileLoc = new JButton("Select Report Location");    
		fileLoc.setBounds(10,520,180, 40);
		
		JTextField fileLocField= new JTextField();
		fileLocField.setBounds(220, 520, 400, 40);
		fileLocField.setText(System.getProperty("user.dir") + "\\StudentReports");
		
		
		JButton clear=new JButton("Clear Selection");
		clear.setBounds(820,280,150, 40);
		
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
		frame.add(fileLoc);
		frame.add(fileLocField);
		frame.add(clear);
		clear.setVisible(false);
		frame.add(printReports);
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
		
		clear.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				list.getSelectionModel().clearSelection();
				clear.setVisible(false);
			}          
	      });
		
		printReports.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int[] indexs=list.getSelectedRows();
				int rowCount = list.getRowCount();
				//all the selected row are here no need to go throw 
				//all your rows to see if they are selected or not
				
				if (clear.isVisible()) {
				
					if (list.getSelectedRowCount() > 1) {
						JOptionPane.showMessageDialog(frame,
							    "Please Select 1 Row",
							    "Warning",
							    JOptionPane.WARNING_MESSAGE);
					}
					else {
						System.out.println(list.getSelectedRow());
						//Print Form Here\/\/\/\/\/
						if (fileLocField.getText() == " ") {
							JOptionPane.showMessageDialog(frame,"Please Select A File Location.","Alert",JOptionPane.WARNING_MESSAGE); 
						}
						else {	
						int selectedRows = list.getSelectedRow();
					    	
						
							for (int i=0;i<=5;i++) {
					    		System.out.println(list.getValueAt(selectedRows,i));
					    		
					    		if (i==0) {
					    			I_D = (String) list.getValueAt(selectedRows,i);
					    			
					    		}

					    		if (i==1) {
					    			FName = (String) list.getValueAt(selectedRows,i);
					    			
					    			
					    		}

					    		if (i==2) {
					    			LName = (String) list.getValueAt(selectedRows,i);
					    			
					    		}

					    		if (i==3) {
					    			School = (String) list.getValueAt(selectedRows,i);
					    			
					    		}

					    		if (i==4) {
					    			Grade = (String) list.getValueAt(selectedRows,i);
					    			
					    		}

					    		if (i==5) {
					    			Hours = (String) list.getValueAt(selectedRows,i);
					    			
					    		}
					    	
					    		
					    	}
					    	
					    	reporter.saveReport(I_D, Grade,FName ,LName, School, Hours, fileLocField.getText());
					    	
								
							
						}		
				 } 
				}
				else {
					JOptionPane.showMessageDialog(frame,
						    "Please Select Row to Print Report",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}          
	      });
		
		
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
	    		if (list.getSelectedRow() != -1) {
	    			clear.setVisible(true);
	    		}
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
