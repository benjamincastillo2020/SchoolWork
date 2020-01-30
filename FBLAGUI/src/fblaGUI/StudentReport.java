package fblaGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Array;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.DocumentException;

public class StudentReport {
	String I_D;
	String Grade;
	String FName;
	String LName;
	String School;
	String Hours;
	StudentReportBot reporter = new StudentReportBot();
	public StudentReport() {
		JFrame frame = new JFrame("Select Student");
		File filename = null;
		
		JButton fileLoc = new JButton("Select Report Location");    
		fileLoc.setBounds(10,520,150, 40);
		JLabel title = new JLabel("Select Student");
		title.setFont(new Font("Serif", Font.BOLD, 40));
		title.setBounds(170, 7, 500, 40);
		JTextField fileLocField= new JTextField();
		fileLocField.setBounds(170, 520, 350, 35);
		
		Parser parseclass = new Parser();
		JTable list = new JTable(parseclass.parse());
		JScrollPane scrollPane = new JScrollPane(list);
		TableRowSorter sorter = new TableRowSorter<>(parseclass.DTM);
		list.setRowSorter(sorter);
		
		JTextField jtf= new JTextField();
		jtf.setBounds(115, 455, 350, 40);
		
		JLabel search = new JLabel("Search");
		search.setBounds(20, 455, 55, 40);
		
		JButton Create = new JButton("Create Report");    
		Create.setBounds(10,570,150, 40);
		
		scrollPane.setBounds(10, 50, 600, 400);
		frame.add(scrollPane);
		frame.add(jtf);
		frame.add(Create);
		frame.add(title);
		frame.add(search);
		frame.add(fileLoc);
		frame.add(fileLocField);
		frame.setSize(650,750);  
		frame.setLayout(null);    
		frame.setVisible(true);    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
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
		
		Create.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			    int selectedRows = list.getSelectedRow();
			    
			    	for (int i=0;i<=5;i++) {
			    		System.out.println(list.getValueAt(selectedRows,i));
			    		if(i==0) {
			    			I_D=(String) list.getValueAt(selectedRows,i);
			    		}
			    		if(i==1) {
			    			Grade=(String) list.getValueAt(selectedRows,i);
			    		}
			    		if(i==2) {
			    			FName=(String) list.getValueAt(selectedRows,i);
			    		}
			    		if(i==3) {
			    			LName=(String) list.getValueAt(selectedRows,i);
			    		}
			    		if(i==4) {
			    			School=(String) list.getValueAt(selectedRows,i);
			    		}
			    		if(i==5) {
			    			Hours=(String) list.getValueAt(selectedRows,i);
			    		}
			    
			    	}
			    	//Create PDF
			    	try {
						reporter.writeStudentReport(I_D, Grade, FName, LName, School, Hours, filename.getPath());
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		new StudentReport();
		

	}
	

}
