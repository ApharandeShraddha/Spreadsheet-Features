
package spreadsheetUpdates.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import spreadsheetUpdates.module.Cell;
import spreadsheetUpdates.observer.ObserverI;

import spreadsheetUpdates.store.Results;
import spreadsheetUpdates.store.StdoutDisplayInterface;
import spreadsheetUpdates.util.FileDisplayInterface;
import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;

/**
 * Results This class is main driver which will start the program
 * 
 * @author Shraddha Apharande
 */
public class Driver {
	StdoutDisplayInterface resObj;
	int debugValue = 1;
	String inputFileName = "";
	String outputFileName = "";

	public static void main(String args[]) {
		Driver dr = new Driver();
		FileDisplayInterface fp;

		BufferedWriter writer = null;
		BufferedReader reader = null;
		try {

			dr.validateArgs(args);
			Logger.setDebugValue(dr.getDebugValue());
			dr.resObj = new Results();
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(dr.getInputFileName())));
			File file = new File(dr.getOutputFileName());
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

			fp = new FileProcessor(dr.resObj, reader, writer);

			String line = fp.readFile();

			if (line == null) {
				System.err.println("Input file is empty");
				System.exit(1);

			}
			while (line != null) {
			
				dr.parseLineByLine(line, fp);
				line = fp.readFile();
			}
			Logger.writeMessage("The sum of all cell values is: " + dr.resObj.getCellsSummation(), Logger.DebugLevel.RELEASE);
		
			fp.writeStringToFile("The sum of all cell values is: " +String.valueOf(dr.resObj.getCellsSummation()));

		} catch (FileNotFoundException ex) {
			System.err.println("Input File " + dr.getInputFileName() + " doesn't exist.");
			System.exit(1);
		} catch (Exception ex) {
			System.err.println("Improper Input");
			System.exit(1);

		} finally {
			try {
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	} // end main(...)
	

	/**
	 * To check if there is any cyclic dependency
	 * @param subject cell and new cell
	 * @return boolean
	 */
	public boolean isDependencyFound(Cell subject,Cell newCell){
		for(Cell cell:newCell.getObserversList()){
  		  if(cell.getName().equalsIgnoreCase(subject.getName())){
  			return true;
  		  }else{
  			for (Cell subSubject : subject.getSubjectList()) {
  				if(isDependencyFound(subSubject, cell)){
  					return true;
  				}
  			}
  		  }
  	  }
		return false;
	}
	

	/**
	 * To parse line by line and creating excel sheet from it.
	 * @param line and file processor object
	 * @return void
	 */
	void parseLineByLine(String line, FileDisplayInterface fp) {

		String[] subjectSplit = line.split("=");
		String observerCellName = subjectSplit[0];

		String[] observerSplit = subjectSplit[1].split("\\+");
		String subjectCellName1 = observerSplit[0];
		String subjectCellName2 = observerSplit[1];

		// Check if this is new cell or existing
		// If it is existing then call updatevalue on that cell and update its
		// subject list as well.

		// Parse right sides and check it its literal or subjectCell
		// if subject cell then add current cell to subject's observer list.
		int value = 0;
		List<Cell> subjectList = new ArrayList<Cell>();

		if (isInteger(subjectCellName1)) {
			value += Integer.parseInt(subjectCellName1);
		} else {
			Cell subjectCell1 = resObj.retrieveCell(subjectCellName1);
			if (subjectCell1 == null) {
				subjectCell1 = new Cell(subjectCellName1);
				subjectCell1.initializeCell(value, new ArrayList<Cell>());
	    		 resObj.storeCell(subjectCell1);
			}
			subjectList.add(subjectCell1);
			value += subjectCell1.getValue();
		}

		if (isInteger(subjectCellName2)) {
			value += Integer.parseInt(subjectCellName2);
		} else {
			Cell subjectCell2 = resObj.retrieveCell(subjectCellName2);
			if (subjectCell2 == null) {
				subjectCell2 = new Cell(subjectCellName2);
				subjectCell2.initializeCell(value, new ArrayList<Cell>());
	    		resObj.storeCell(subjectCell2);
			}
			subjectList.add(subjectCell2);
			value += subjectCell2.getValue();

		}

		Cell newCell = resObj.retrieveCell(observerCellName);
		if (newCell != null) {
			// Detect depedency cycle.
			/*
			 * o1=s1+s2; s1=o1+E1; If subject is already in observers list of
			 * new observer.
			 * 
			 */
			for (Cell subject : subjectList) {
				if(isDependencyFound(subject,newCell)){
					Logger.writeMessage("Cycle Detected!!! : " + line, Logger.DebugLevel.CYCLE);
					 fp.writeStringToFile("Cycle Detected!!! "+line);
	    			  return;
				}
			}

			// Remove from old subjects observer list
			for (Cell cell : resObj.getCellList()) {
				for (ObserverI obs : cell.getObserversList()) {
					if (obs.getName().equalsIgnoreCase(observerCellName)) {
						cell.removeObserver(newCell);
						break;
					}
				}
			}

			newCell.updateCell(value);
			newCell.registerObserver(subjectList);

		} else {

			// Create cell object and add to result's data structure of all
			// cells.
			Cell cell = new Cell(observerCellName);
			cell.initializeCell(value, subjectList);
			resObj.storeCell(cell);
		}
	}

	/**
	 * @return boolean
	 */
	public boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Validate input arguments
	 * 
	 * @param args
	 * @return void
	 */
	private void validateArgs(String args1[]) {
		
		
		if (args1.length == 3) {
			inputFileName = args1[0];
			outputFileName = args1[1];

			try {
				debugValue = Integer.valueOf(args1[2]);
				if (debugValue < 0 || debugValue > 4) {
					System.err.println("Debug value should be between 0 and 4.");
					System.exit(1);
				}
			} catch (IllegalArgumentException ex) {
				System.err.println("Debug level should be an Integer");
				System.exit(1);
			}finally{
				
			}
		} else {
			System.err.println("Invalid number of arguments. Expected : input file, output file, debug value .");
			System.exit(1);
		}
	}
	
	/**
	 * @return integer
	 */
	public int getDebugValue() {
		return debugValue;
	}

	/**
	 * @return void
	 */
	public void setDebugValue(int debugValue) {
		this.debugValue = debugValue;
	}

	/**
	 * @return String
	 */
	public String getRegPreferenceFileName() {
		return inputFileName;
	}

	/**
	 * @return void
	 */
	public void setRegPreferenceFileName(String regPreferenceFileName) {
		this.inputFileName = regPreferenceFileName;
	}

	/**
	 * @return String
	 */
	public String getOutputFileName() {
		return outputFileName;
	}

	/**
	 * @return void
	 */
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	/**
	 * @return String
	 */
	public String getInputFileName() {
		return inputFileName;
	}

	/**
	 * @return void
	 */
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

} // end public class Driver
