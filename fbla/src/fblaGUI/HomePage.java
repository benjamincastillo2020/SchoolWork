package fblaGUI;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class HomePage {

	public HomePage() {
		JFrame frame=new JFrame("Landing Page"); 
		//submit button
		JButton addStudentButton = new JButton("Open Databse");    
		addStudentButton.setBounds(10,100,150, 40);

		JButton updateButton=new JButton("New Database");    
		updateButton.setBounds(10,200,150, 40); 
		
		JButton saveButton=new JButton("Print Forms");    
		saveButton.setBounds(10,300,150, 40); 
		//enter name label
		 		
		JLabel label=new JLabel(
		"<html><header>Welcome to the Student Service Learning Database</header></html>"
				);
		label.setBounds(0, 0, 500, 100);
		label.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
	
		frame.add(label);
		frame.add(addStudentButton);
		frame.add(updateButton);
		
		frame.add(saveButton);
		frame.setSize(500,500);  
		frame.setLayout(null);    
		frame.setVisible(true);    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	}

	public static void main(String[] args) {
		new HomePage();
		String fonts[] = 
			      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

			    for ( int i = 0; i < fonts.length; i++ )
			    {
			      System.out.println(fonts[i]);
			    }

	}

}
