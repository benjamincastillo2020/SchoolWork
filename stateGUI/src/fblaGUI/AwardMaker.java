package fblaGUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AwardMaker {
	public String I_D;
	public String Grade;
	public String FName;
	public String LName;
	public String School;
	public String Hours;
	public StudentReportBot reporter = new StudentReportBot();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AwardMaker(String XMLroot) {
		JFrame frame=new JFrame("Landing Page"); 
		//submit button
		JButton studentRep = new JButton("Print Student Reports");    
		studentRep.setBounds(10,100,200, 40);
		
		String[] grades = { "All Grades", "8", "9", "10", "11", "12"};
		JComboBox StudentReportbyGrade= new JComboBox(grades);
		StudentReportbyGrade.setBounds(230, 100, 200, 40);
		
		
		
		JButton fileLoc = new JButton("Select Report Location");    
		fileLoc.setBounds(10,300,180, 40);
		
		JTextField fileLocField= new JTextField();
		fileLocField.setBounds(10, 380, 400, 40);
		fileLocField.setText(System.getProperty("user.dir") + "\\StudentReports");
		
		
		
		 		
		JLabel title=new JLabel("<html><span style='font-size:30px'>Select Reports or Award</span></html>");
		title.setBounds(0, 0, 500, 100);
		
	
		frame.add(title);
		frame.add(studentRep);
		frame.add(fileLoc);
		frame.add(fileLocField);
		
		frame.add(StudentReportbyGrade);
		
		frame.setSize(500,500);  
		frame.setLayout(null);    
		frame.setVisible(true);    
		
		
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
			    	JOptionPane.showMessageDialog(frame,
						    "No Directory Chosen",
						    "Error",
						    JOptionPane.INFORMATION_MESSAGE);
			    }
			
				
				
			}          
	      }); 
		 
	        	
		studentRep.addActionListener(new ActionListener() {
			void makeReport() {
				File tmpDir = new File(fileLocField.getText());
	        	String Dir = fileLocField.getText();
	        	boolean exists = tmpDir.exists();
	        	if (exists) {
	        		reporter.saveReport(I_D, Grade,FName ,LName, School, Hours, Dir);
	        		}	else {
	        		
	        		JOptionPane.showMessageDialog(frame,
						    "Please Select Existant Directory",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
	        		
	        	}
			}
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Parser parseclass = new Parser();
				JTable list = new JTable(parseclass.parse(XMLroot));
				
				int rowCount = list.getRowCount();

				for (int r=0; r<rowCount; r++) {
					
					for (int i=0;i<6;i++) {
		
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
		    	}
					
					if (StudentReportbyGrade.getSelectedItem().toString() == "All Grades") {
						makeReport();
		    	} else {
		    		int i = Integer.parseInt(StudentReportbyGrade.getSelectedItem().toString());
		    		int l = Integer.parseInt(Grade);
		    		
		    		if (i == l) {
		    			makeReport();
		   
		    		}
		    	}

				}
			}
	      });

	}
}
