
package spreadsheetUpdates.store;

import java.util.List;

import spreadsheetUpdates.module.Cell;

/**
 * StdoutDisplayInterface     This interface is responsible for providing methods needed for display and store data
 * @author    Shraddha Apharande
 *  */

public interface StdoutDisplayInterface {
	
	public void storeCell(Cell cell);
	
    public Cell retrieveCell(String nameIn);
    
    public int getCellsSummation();
    
    public List<Cell> getCellList() ;

} 
