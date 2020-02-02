package fblaGUI;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class HomePage {
	String XMLroot;
	
	
	ParsingCurrentData root = new ParsingCurrentData();
	
	
	
	
	public HomePage() throws FileNotFoundException {
		
		
		JFrame frame=new JFrame("Landing Page"); 
		//submit button
		JButton OpenData = new JButton("Open Databse");    
		OpenData.setBounds(10,100,150, 40);
		
		JTextField fileLocField= new JTextField();
		fileLocField.setBounds(175, 100, 400, 40);
		
		fileLocField.setText(root.currentXML());

		JButton updateButton=new JButton("New Database");    
		updateButton.setBounds(10,200,150, 40); 
		
		JButton changeLoc=new JButton("Edit Location");
		changeLoc.setBounds(30,145,130, 20);
		
		JButton saveButton=new JButton("Print Forms");    
		saveButton.setBounds(10,300,150, 40); 
		//enter name label
		 		
		JLabel label=new JLabel(
		"<html><header>Welcome to the Student Service Learning Database</header></html>"
				);
		label.setBounds(0, 0, 500, 100);
		label.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
	
		frame.add(label);
		frame.add(OpenData);
		frame.add(changeLoc);
		frame.add(updateButton);
		frame.add(fileLocField);
		frame.add(saveButton);
		frame.setSize(650,500);  
		frame.setLayout(null);    
		frame.setVisible(true);    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	
		OpenData.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				XMLroot = fileLocField.getText();
				
				File tmpDir = new File(XMLroot);
				boolean exists = tmpDir.exists();
				
				if (exists) {
					new LandingPage(XMLroot);
				}else {
					
				JOptionPane.showMessageDialog(frame,
					    "Please Select an XML file",
					    "Database Location Error",
					    JOptionPane.ERROR_MESSAGE);
				}   
			}
	      });
		
		changeLoc.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser chooser = new JFileChooser();
			    
				chooser.setCurrentDirectory(root.currentXMLFile());
				
			    chooser.setDialogTitle("Please Select File Location");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.xml", "xml"));
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      
			      File filename = chooser.getSelectedFile();
			      fileLocField.setText(filename.getPath());
			    } else {
			      JOptionPane.showMessageDialog(frame,
						    "No XML file change",
						    "Database Location Error",
						    JOptionPane.INFORMATION_MESSAGE);
			    }	
			}          
	      });
	
	}

	public static void main(String[] args) throws FileNotFoundException {
		new HomePage();
		
		
	}

}
