package spreadsheetUpdates.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import spreadsheetUpdates.store.StdoutDisplayInterface;

/**
 * Class having methods for file read and write operations
 * @author    Shraddha Apharande
 */
public class FileProcessor implements FileDisplayInterface{
	
	StdoutDisplayInterface stdOut;
	 BufferedReader reader;
	 BufferedWriter writer;

	public FileProcessor(StdoutDisplayInterface stdOutIn, BufferedReader readerIn, BufferedWriter writerIn) {
		stdOut = stdOutIn;
		reader=readerIn;
		writer=writerIn;
	}
/**
 * This method read input file 
 * @param Input filename
 * @return StringBuilder
 */
	public String readFile( ) {

		String line="";

		try {
			// File read code taken from web (below 1 line)
			 line = reader.readLine();
			if (line != null && line.trim().isEmpty()) {
				System.err.println("Either current line or Input file is empty \n");
				System.exit(1);
			}
		}  catch (IOException ex) {
			System.err.println("Error while reading file.");
			System.exit(1);
		} catch (Exception ex) {
			System.err.println("Error while performing file operations");
			System.exit(1);
		}finally{
			
		}
		return line;
	}
	
	/**
	 * This method writes sum of all cells to output file
	 * @param Output filename
	 * @return void
	 */

	public  void writeStringToFile(String outputString) {
		try {
			writer.write(outputString);
			writer.write("\n");
		} catch (IOException e) {
			System.err.println("Writing to output file failed");
			System.exit(1);
		} 
	}
	
	@Override
	public String toString() {
		return "FileProcessor [toString()=" + super.toString() + "]";
	}
}
