package spreadsheetUpdates.observer;

import java.util.List;

import spreadsheetUpdates.module.Cell;


/**
 * ObserverI    This is interface
 * @author    Shraddha Apharande
 */


public interface ObserverI {
	
	public List<Cell> getObserversList();

	public void setValue(int i);

	public int getValue();

	void update(SubjectI cell, int updateValue);
	
	public String getName();
	
	public void setName(String name);
	
	public List<Cell> getSubjectList();

}
