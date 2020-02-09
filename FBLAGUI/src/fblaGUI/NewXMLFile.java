package fblaGUI;


import java.io.FileOutputStream;
import java.io.IOException;

public final class NewXMLFile {

	public String XMLTemplate() {
		String newXML = ("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n" + 
				"<!--THIS IS A SAMPLE XML DATABASE FILE CREATED BY BENJAMIN CASTILLO-->\r\n" + 
				"<!--IF YOU WOULD LIKE TO ADD DATA BY HAND, PLEASE FOLLOW PROCEDURE BELOW-->\r\n" + 
				"<!-- \r\n" + 
				"		1. YOU MUST LOOK IN THE DOCUMENT FOR A NODE THAT LOOKS LIKE THIS\\/	\r\n" + 
				"						\r\n" + 
				"						</student> OR <students>\r\n" + 
				"																\r\n" + 
				"		2. YOU MUST THEN ADD THE DATA USING THIS TEMPLATE \\/\r\n" + 
				"			<student>\r\n" + 
				"				<id>			</id>\r\n" + 
				"				<grade>			</grade>\r\n" + 
				"				<Firstname>		</Firstname>\r\n" + 
				"				<Lastname>		</Lastname>\r\n" + 
				"				<school>		</school>\r\n" + 
				"				<hours>			</hours>\r\n" + 
				"			</student>\r\n" + 
				"			\r\n" + 
				"			EXAMPLE:\r\n" + 
				"				<student>\r\n" + 
				"					<id>1234</id>\r\n" + 
				"					<grade>12</grade>\r\n" + 
				"					<Firstname>John</Firstname>\r\n" + 
				"					<Lastname>Smith</Lastname>\r\n" + 
				"					<school>CITYUNIV</school>\r\n" + 
				"					<hours>45</hours>\r\n" + 
				"				</student>\r\n" + 
				"		2. IF DATABASE IS NOT BEING READ, REVISIT STEP 1 TO ENSURE DATA IS ADDED CORRECTLY	\r\n" + 
				"\r\n" + 
				" -->\r\n" + 
				" <students>\r\n" + 
				" \r\n" + 
				"</students>");
		return newXML;
	}

	public void newXML(String root) {
		try {
			FileOutputStream out = new FileOutputStream(root);
			out.write(XMLTemplate().getBytes());
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
