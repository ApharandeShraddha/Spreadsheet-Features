package spreadsheetUpdates.store;
import java.util.ArrayList;
import java.util.List;


import spreadsheetUpdates.module.Cell;

/**
 * Results     This class is responsible for storing cell and display sum of all cell on screen
 * @author    Shraddha Apharande
 *  */
public class Results implements StdoutDisplayInterface {
	private List<Cell> cellList=new ArrayList<Cell>();

	/**
	   *  To save cell to the data structure
	   *  @return void
	   */ 
	public void storeCell(Cell cell){
		
		cellList.add(cell);
		
		
	}
	/**
	   * Returns summation of calls value
	   * @return int sumValue
	   */ 
	public int getCellsSummation(){
		int sum=0;
		for(Cell cell: cellList){
			sum+=cell.getValue();
		}
		
		return sum;
	}

	/**
	   * Displays sum of all cell's value on screen.
	   * @return void
	   *//* 
    public void writeSumToScreen() {
    	int sum=getCellsSummation();
    	System.out.println("The sum of all cell values is: "+sum);
    }*/
    
	/**
	   * Method o rectrive cells
	   * @return Cell
	   */
    public Cell retrieveCell(String nameIn){
		for (Cell cell : cellList) {
			if(cell.getName().equalsIgnoreCase(nameIn))
				return cell;
		}
		
		return null;
    }
    
	/**
	   * @return list of all cells
	   */
    public List<Cell> getCellList() {
		return cellList;
	}
    
	/**
	   * Displays cell name and value
	   * @return String
	   */ 
    private String getCells() {
    
		StringBuffer contents=new StringBuffer(); 
		for (Cell cell : cellList) {
			contents.append("Cell Name: ");
			contents.append(cell.getName());
			contents.append("::Cell Value: ");
			contents.append(cell.getValue());
			contents.append(", \n");
		}
		return contents.toString();
	}
    
   
    
    /**
     * @return String
     */
    public String toString() {
    	 
    	return "Contents of the data structure: "+(getCells())+"\n The sum of all cell values is: "+getCellsSummation();
    }
} 


