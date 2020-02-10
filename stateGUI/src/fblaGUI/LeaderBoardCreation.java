/*
 * This class is in charge of creating student PDF Reports.
 * Utilizes ITextPDF to create Forms
 */

package fblaGUI;



import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;




public class LeaderBoardCreation {

	

	public static void writeStudentReport(String Root , String XMLroot, int count) throws DocumentException, MalformedURLException, IOException  {
		
		Parser parseclass = new Parser();
		JTable list = new JTable(parseclass.parse(XMLroot));
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(parseclass.DTM);
		list.setRowSorter(sorter);
			//set toggle sort order twice in order to make the hours Descending. 
			//Fastest way to do so and clears up space instead of "for" loop
		sorter.toggleSortOrder(5);
		sorter.toggleSortOrder(5);
		if (count > list.getRowCount()) {
			count = list.getRowCount();
			//if the count given is higher than row count, set to the max row count.
		}
		String outPutLocation = Root +  "\\"+ "Top" + count + ".pdf";
		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(outPutLocation));
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 55, BaseColor.BLACK);
		Font font2 = FontFactory.getFont(FontFactory.COURIER, 45, BaseColor.BLACK);
		//title
		Chunk chunk = new Chunk("Leaderboard", font);
		Paragraph preface = new Paragraph(chunk); 
		preface.setAlignment(Element.ALIGN_CENTER);
		Chunk Top = new Chunk(("TOP " + count +  "Students"), font2);
		document.add(preface);
		document.add(Top);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );
		
		
		
		
		
		
		for (int i=0; i < count; i++) {
			String Student = (String) list.getValueAt(i, 1) + " " + (String) list.getValueAt(i, 2);
			String RankNum = "#" + (i+1);
			String sHours = (String) list.getValueAt(i, 5);
			//ID
			Chunk  Rank = new Chunk(RankNum, FontFactory.getFont(FontFactory.COURIER, 25, BaseColor.BLACK));
			Chunk Name = new Chunk(Student, FontFactory.getFont(FontFactory.COURIER, 25, BaseColor.BLACK));
			Chunk Hours = new Chunk(sHours, FontFactory.getFont(FontFactory.COURIER, 25, BaseColor.BLACK));
			Paragraph studentField = new Paragraph(); 
			studentField.add(Rank);
			studentField.add(Name);
			studentField.add(Hours);
			document.add(studentField);
			document.add( Chunk.NEWLINE );
			document.add( Chunk.NEWLINE );
			
		}
		
		document.close();
	}
	
	
	
	public void CreateLeaderBoard(String Root , String XMLroot, int count)  {
		try {
			writeStudentReport(Root, XMLroot, count);
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
	
	
}
