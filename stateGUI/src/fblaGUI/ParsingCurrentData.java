package fblaGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParsingCurrentData {
	String LocalFolder = System.getProperty("user.dir");
	public String currentXML() throws FileNotFoundException {
		File getCurrentDatabase = new File("DatabaseFiles\\currentDatabase.txt");
		try (Scanner persistantDataFile = new Scanner(getCurrentDatabase)) {
			persistantDataFile.useDelimiter(",");
			String storedData = (LocalFolder + persistantDataFile.nextLine());
			return storedData;
		}
		
	}
	
	public File currentXMLFile() {
		try {
			File f = new File (currentXML());
			return f;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	public String InSystemWriter() {
		File getCurrentDatabase = new File("currentDatabase.txt");
		try (Scanner persistantDataFile = new Scanner(getCurrentDatabase)) {
			persistantDataFile.useDelimiter(",");
			String storedData = (persistantDataFile.nextLine());
			return storedData;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	
}
