package fblaGUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableRowSorter;

public class AwardMaker {
	public String I_D;
	public String Grade;
	public String FName;
	public String LName;
	public String School;
	public String Hours;
	public StudentReportBot reporter = new StudentReportBot();
	public LeaderBoardCreation leaderBoardReporter = new LeaderBoardCreation();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AwardMaker(String XMLroot) {
		JFrame frame=new JFrame("Landing Page"); 
		//submit button
		JButton studentRep = new JButton("Print Student Reports");    
		studentRep.setBounds(10,100,200, 40);
		
		String[] grades = { "All Grades", "8", "9", "10", "11", "12"};
		JComboBox StudentReportbyGrade= new JComboBox(grades);
		StudentReportbyGrade.setBounds(230, 100, 200, 40);
		
		JButton leaderBoard = new JButton("Print Leaderboard");    
		leaderBoard.setBounds(10,150,200, 40);
		
		JLabel leader=new JLabel("Number of Positions:");
		leader.setBounds(220,150,150, 40);
		
		JTextField postionNum = new JTextField();    
		postionNum.setBounds(350,150,70, 40);
		
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
		frame.add(leaderBoard);
		frame.add(leader);
		frame.add(postionNum);
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
		
		leaderBoard.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isNumber(postionNum.getText()) == false ) {
					JOptionPane.showMessageDialog(frame,
						    "Please Enter Valid Integer",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				} else {
		
					leaderBoardReporter.CreateLeaderBoard(fileLocField.getText(), XMLroot, Integer.parseInt(postionNum.getText()));
					success(frame, "Leaderboard has been Created!");
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
	        		
	        		System.out.println();
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
				TableRowSorter sorter = new TableRowSorter<>(parseclass.DTM);
				list.setRowSorter(sorter);
				//set toggle sort order twice in order to make the hours Descending. 
				//Fastest way to do so and clears up space instead of "for" loop
				sorter.toggleSortOrder(5);
				sorter.toggleSortOrder(5);
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
		    			//make Report
		   
		    		}
		    	}

				}
				success(frame, "Report Has Been Created");
			}
	      });

	}
	/*
	 * Following method is a boolean to determine if a string is an integer or not
	 */
	 public static boolean isNumber(String s) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(s);
	 
	         // s is a valid integer
	 
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         // s is not an integer
	      }
	 
	      return isValidInteger;
	   }
	 public static void success(JFrame frame, String Message) {
		 JOptionPane.showMessageDialog(frame,
				 	Message,
				    "Success!",
				    JOptionPane.PLAIN_MESSAGE);
	 }
}
