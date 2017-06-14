package spreadsheetUpdates.observer;

import java.util.List;

import spreadsheetUpdates.module.Cell;

/**
 * SubjectI    THis is interface
 * @author    Shraddha Apharande
 */

public interface SubjectI {
	
	public void removeObserver(ObserverI cell);
	
	public void notifyObservers(int updateValue);
	
	public void registerObserver(List<Cell> subjectLisIn);
	
	public void addToObserversList(Cell cellIn);
	
	public List<Cell> getObserversList();
	
	public void setValue(int i);
	
	public int getValue();
	
	public String getName();
	
	public void setName(String name);
	
	public List<Cell> getSubjectList();


}
