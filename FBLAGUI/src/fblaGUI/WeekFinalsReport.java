package fblaGUI;

import java.awt.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class WeekFinalsReport {

	public static void writeStudentReport(String I_D, String Grade, String FName,String LName, String School, String Hours, String Root) throws DocumentException, MalformedURLException, IOException  {
		
		
		
		String outPutLocation = Root +  "\\"+ I_D + ".pdf";
		String Name = FName + " " + LName;
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(outPutLocation));
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 55, BaseColor.BLACK);
		
		//title
		Chunk chunk = new Chunk("Most Community Service Hours", font);
		Paragraph preface = new Paragraph(chunk); 
		preface.setAlignment(Element.ALIGN_CENTER);
		//ID
		Chunk ID = new Chunk("ID:       ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk I_DC = new Chunk(I_D, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph idField = new Paragraph(); 
		idField.add(ID);
		idField.add(I_DC);
		//Name
		Chunk name = new Chunk("NAME:     ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk NameC = new Chunk(Name, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph nameField = new Paragraph(); 
		nameField.add(name);
		nameField.add(NameC);
		//Grade
		Chunk grade = new Chunk("GRADE:    ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk GradeC = new Chunk(Grade, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph gradeField = new Paragraph(); 
		gradeField.add(grade);
		gradeField.add(GradeC);
		//School
		Chunk school = new Chunk("SCHOOL:   ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk SchoolC = new Chunk(School, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph schoolField = new Paragraph(); 
		schoolField.add(school);
		schoolField.add(SchoolC);
		//Hours
		Chunk hours = new Chunk("HOURS:    ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk HoursC = new Chunk(Hours, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph hoursField = new Paragraph(); 
		hoursField.add(hours);
		hoursField.add(HoursC);
		
		//StudentImage Image
		String imagelocation = "Photos/" + I_D + ".jpg";
		File fileLoc = new File(imagelocation);
		boolean exists = fileLoc.exists();
		
		if (exists==false) {
			imagelocation = "Photos/404.png";
		}
		Image img1 = Image.getInstance(imagelocation);
		
		
		
		
		
		document.add(preface);
		document.add(img1);
		document.add( Chunk.NEWLINE );
		document.add(idField);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		document.add(nameField);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		document.add(gradeField);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		document.add(schoolField);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		document.add(hoursField);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		
		
		document.close();
		
}
	public WeekFinalsReport() {
		JFrame frame=new JFrame("Leaderboard"); 
		Parser parseclass = new Parser();
		JTable list = new JTable(parseclass.parse());
		JScrollPane scrollPane = new JScrollPane(list);
		list.setAutoCreateRowSorter(true);
		scrollPane.setBounds(10, 20, 800, 400);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(list.getModel());
		list.setRowSorter(sorter);
		sorter.toggleSortOrder(5);
		sorter.toggleSortOrder(5);
	
		
		
		frame.add(scrollPane);
		frame.setSize(1000, 1000);
		frame.setLayout(null);    
		frame.setVisible(true);    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	
	}
	
	public static void main(String[] args) {    
	    new WeekFinalsReport();    
	}  

}
