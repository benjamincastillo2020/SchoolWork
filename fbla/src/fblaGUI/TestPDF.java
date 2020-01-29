package fblaGUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

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
import com.itextpdf.text.pdf.parser.Path;



public class TestPDF {

	public TestPDF() {
		// TODO Auto-generated constructor stub
	}

	public static void writeStudentReport(String I_D,String Grade, String FName,String LName, String School, String Hours) throws DocumentException, MalformedURLException, IOException {
		
		
		
		
		String Name = FName + LName;
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 55, BaseColor.BLACK);
		
		//title
		Chunk chunk = new Chunk("Student Report", font);
		Paragraph preface = new Paragraph(chunk); 
		preface.setAlignment(Element.ALIGN_CENTER);
		//ID
		Chunk ID = new Chunk("ID:       ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk I_DC = new Chunk(I_D, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph idField = new Paragraph(); 
		idField.add(ID);
		idField.add(I_DC);
		//Name
		Chunk name = new Chunk("NAME:       ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk NameC = new Chunk(Name, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph nameField = new Paragraph(); 
		nameField.add(name);
		nameField.add(NameC);
		//Grade
		Chunk grade = new Chunk("GRADE:       ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk GradeC = new Chunk(Grade, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph gradeField = new Paragraph(); 
		gradeField.add(grade);
		gradeField.add(GradeC);
		//School
		Chunk school = new Chunk("SCHOOL:       ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk SchoolC = new Chunk(School, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph schoolField = new Paragraph(); 
		schoolField.add(school);
		schoolField.add(SchoolC);
		//Hours
		Chunk hours = new Chunk("HOURS:       ", FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Chunk HoursC = new Chunk(Hours, FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK));
		Paragraph hoursField = new Paragraph(); 
		hoursField.add(hours);
		hoursField.add(HoursC);
		
		//StudentImage Image
		String imagelocation = "Photos/" + I_D + ".png";
		Image img1 = Image.getInstance(imagelocation);
		
		
		
		
		
		document.add(preface);
		document.add(img1);
		document.add(idField);
		document.add(nameField);
		document.add(gradeField);
		document.add(schoolField);
		document.add(hoursField);
		
		
		document.close();
	}
	
	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
		
			writeStudentReport("045980", "2022", "David", "Anderson", "TCCHS", "45");
		
		
	

}
	
}
