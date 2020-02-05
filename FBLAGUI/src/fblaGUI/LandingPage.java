package fblaGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
	AddXmlNode upload = new AddXmlNode();
	
	//(x, y, width, height)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LandingPage(String XMLroot){    
		
		JFrame frame=new JFrame("Landing Page"); 
					//submit button
		JButton addStudentButton = new JButton("Add Student");    
		addStudentButton.setBounds(820,130,150, 40);
		
		JButton printReports=new JButton("Print Reports");
		printReports.setBounds(820,180,150, 40);
		
		JButton fileLoc = new JButton("Select Report Location");    
		fileLoc.setBounds(10,520,180, 40);
		
		String[] grades = {"", "8", "9", "10", "11", "12", "All Grades"};
		
		JComboBox gradeChooser = new JComboBox(grades);
		
		gradeChooser.setBounds(820,240,150, 40);
		JTextField fileLocField= new JTextField();
		fileLocField.setBounds(220, 520, 400, 40);
		fileLocField.setText(System.getProperty("user.dir") + "\\StudentReports");
		
		
		JButton clear=new JButton("Clear Selection");
		clear.setBounds(820,380,150, 40);
		
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
		JTable list = new JTable(parseclass.parse(XMLroot));
		JScrollPane scrollPane = new JScrollPane(list);
		TableRowSorter sorter = new TableRowSorter<>(parseclass.DTM);
		list.setRowSorter(sorter);
		
		
		
					//add to frame
		
		JTextField jtf= new JTextField();
		jtf.setBounds(115, 440, 350, 40);
		
		
		
		scrollPane.setBounds(10, 20, 800, 400);
		frame.add(scrollPane);
		frame.add(label);
		frame.add(gradeChooser);
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
				
				@SuppressWarnings("unused")
				int[] indexs=list.getSelectedRows();
				@SuppressWarnings("unused")
				int rowCount = list.getRowCount();
				
				
				if (clear.isVisible()) {
				
					if (list.getSelectedRowCount() > 1) {
						JOptionPane.showMessageDialog(frame,
							    "Please Select 1 Row",
							    "Warning",
							    JOptionPane.WARNING_MESSAGE);
					}
					else {
						System.out.println(list.getSelectedRow());
						
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
	        
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				parseclass.DTM.setRowCount(0);
				JTable list = new JTable(parseclass.parse(XMLroot));
				JScrollPane scrollPane = new JScrollPane(list);
				
				
			}          
	      });
		
		jtf.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (gradeChooser.getSelectedItem().toString() == "All Grades") {
	            	search(jtf.getText());
	            }
	            else {
				search(jtf.getText() + gradeChooser.getSelectedItem().toString());
	            }
	            
	            
	            
	         }
			@Override
	         public void removeUpdate(DocumentEvent e) {
				if (gradeChooser.getSelectedItem().toString() == "All Grades") {
	            	search(jtf.getText());
	            }
	            else {
				search(jtf.getText() + gradeChooser.getSelectedItem().toString());
	            }
	            
	         }
			@Override
	         public void changedUpdate(DocumentEvent e) {
	            if (gradeChooser.getSelectedItem().toString() == "All Grades") {
	            	search(jtf.getText());
	            }
	            else {
				search(jtf.getText() + gradeChooser.getSelectedItem().toString());
	            }
	         }
			public void search(String str) {
	            if (str.length() == 0) {
	               sorter.setRowFilter(null);
	            } else {
	               sorter.setRowFilter(RowFilter.regexFilter(str));
	            }
	         }
	      });
		saveButton.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					clearTheFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				I_D = null;
				FName = null;
				LName = null;
				School = null;
				Grade = null;
				Hours = null;
				
				int rowCount = list.getRowCount();
				for (int r=0; r<=rowCount; r++) {
					
					for (int i=0;i<=5;i++) {
						System.out.println(list.getValueAt(r,i));
		    		
						if (i==0) {
							I_D = (String) list.getValueAt(r,i);
		    			
						}

						if (i==1) {
							FName = (String) list.getValueAt(r,i);
		    			
		    			
						}

						if (i==2) {
							LName = (String) list.getValueAt(r,i);
		    			
						}

						if (i==3) {
							School = (String) list.getValueAt(r,i);
							
						}

						if (i==4) {
							Grade = (String) list.getValueAt(r,i);
		    			
						}

						if (i==5) {
							Hours = (String) list.getValueAt(r,i);
		    			
						}
						//Add XML Nodes
						try {
							upload.XmlUpload(I_D, FName, LName, School, Grade, Hours);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	}		
		}
				
			}void clearTheFile() throws IOException {
		        FileWriter fwOb = new FileWriter("FileName", false); 
		        PrintWriter pwOb = new PrintWriter(fwOb, false);
		        pwOb.flush();
		        pwOb.close();
		        fwOb.close();
		    }});

		gradeChooser.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (gradeChooser.getSelectedItem().toString() == "All Grades") {
	            	search(jtf.getText());
	            }
	            else {
				search(jtf.getText() + gradeChooser.getSelectedItem().toString());
	            }
				
				
			}  
			
			public void search(String str) {
	            if (str.length() == 0) {
	               sorter.setRowFilter(null);
	            } else {
	               sorter.setRowFilter(RowFilter.regexFilter(str));
	            }
	         }
	      });
		
	      
		fileLoc.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File(".\\StudentReports"));
			    chooser.setDialogTitle("Please Select File Location");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      
			      File filename = chooser.getSelectedFile();
			      fileLocField.setText(filename.getPath());
			    } else {
			      System.out.println("No Selection ");
			      fileLocField.setText("No Selection");
			    }
			
				
				
			}          
	      }); 
		
		
	}         
	
	
	public static void main(String[] args) {    
		     
		  
		}    
		
		
 }
