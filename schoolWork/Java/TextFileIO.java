package textFileIO;

/***********************************************************************
Program Name: TextFileIO.java
Programmer's Name: Jeremy Myser
Program Description: Write and Read from a text file
***********************************************************************/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;



public class TextFileIO {

	public static void main(String[] args) throws IOException {
		
		
		writeToFile("numbers.dat", "", 0);
		
		writeToFile("numbers.dat", readFromFile(), 1);
		
	}
	
	public static String readFromFile() throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("numbers.dat")); 
		String textFromFile = f.readLine();
		f.close();
		return textFromFile;
	}
	
	private static void writeToFile(String pathname, String previousText, int c) throws IOException {
		Writer textWriter = null;
		
		try {
		    textWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathname), "utf-8"));
		    textWriter.write(previousText);
		    int count = c;
	        while (count <= 100) {
	        	textWriter.write(count + ",");
	        	count = count +2;
	        }
	        textWriter.write("\n");
		} catch (IOException ex) {
		  // report
		} finally {
		    try {textWriter.close();} catch (Exception ex) {}
		}
	}

}
