package fblaGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


import static javax.swing.JOptionPane.showMessageDialog;

public class AddStudent {
	
	
	AddXmlNode upload = new AddXmlNode();
	AddStudent(String XMLroot){    
		JFrame f=new JFrame("Button Example");
					//submit button
		JButton b=new JButton("Submit");
		b.setBounds(160,340,140, 40);
					//enter name label
		JLabel newStudent = new JLabel("<html><span style='font-size:20px'>"+"Create New Student"+"</span></html>");
		newStudent.setBounds(50, 0, 400, 30);
		
		
		JLabel id = new JLabel("ID");
		id.setBounds(10, 50, 200, 30); 
		JLabel fname = new JLabel("First Name");
		fname.setBounds(10, 90, 200, 30);
		JLabel lname = new JLabel("Last Name");
		lname.setBounds(10, 130, 200, 30);
		JLabel school = new JLabel("School");
		school.setBounds(10, 170, 200, 30);
		JLabel grade = new JLabel("Grade");
		grade.setBounds(10, 210, 200, 30);
		JLabel hours = new JLabel("Existing Hours");
		hours.setBounds(10, 250, 200, 30);
		
		JTextField Tid = new JTextField("");
		Tid.setBounds(130, 50, 200, 30);
		JTextField Tfname = new JTextField("");
		Tfname.setBounds(130, 90, 200, 30);
		JTextField Tlname = new JTextField("");
		Tlname.setBounds(130, 130, 200, 30);
		JTextField Tschool = new JTextField("");
		Tschool.setBounds(130, 170, 200, 30);
		JTextField Tgrade = new JTextField("");
		Tgrade.setBounds(130, 210, 200, 30);
		JTextField Thours = new JTextField("");
		Thours.setBounds(130, 250, 200, 30);
					
		f.add(newStudent);
		f.add(id);
		f.add(fname);
		f.add(lname);
		f.add(school);
		f.add(grade);
		f.add(hours);
		f.add(Tid);
		f.add(Tfname);
		f.add(Tlname);
		f.add(Tschool);
		f.add(Tgrade);
		f.add(Thours);
		
		
		f.add(b);    
		f.setSize(400,470);    
		f.setLayout(null);    
		f.setVisible(true);    
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		
							//action listener
		b.addActionListener(new ActionListener() {
			public void reset() {
				Tid.setText("");
				Tfname.setText("");
				Tlname.setText("");
				Tschool.setText("");
				Tgrade.setText("");
				Thours.setText("");
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					upload.XmlUpload(Tid.getText(),  Tgrade.getText(), Tfname.getText(), Tlname.getText(), Tschool.getText(), Thours.getText(), XMLroot );
					reset();
					showMessageDialog(null, "Successfully Uploaded Student Information");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}          
	      });
		
		f.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	
		    }
		});
		
		
		
		}         
	
	
		public static void main(String[] args) {    
		       
		}
		
 }