package spreadsheetUpdates.module;

import java.util.ArrayList;
import java.util.List;

import spreadsheetUpdates.observer.ObserverI;
import spreadsheetUpdates.observer.SubjectI;

/**
 * Class having methods for cell functionality
 * 
 * @author Shraddha Apharande
 */
public class Cell implements SubjectI, ObserverI {

	private List<Cell> observersList = new ArrayList<Cell>();
	private List<Cell> subjectList = new ArrayList<Cell>();
	private int value = 0;
	private String name = "";

	public Cell(String nameIn) {
		name = nameIn;

	}

	/**
	 * Method to initialize cells
	 * 
	 * @return void
	 */
	public void initializeCell(int valueIn, List<Cell> subjectLisIn) {
		value = valueIn;

		registerObserver(subjectLisIn);

	}

	/**
	 * update
	 * 
	 * @return void
	 */
	@Override
	public void update(SubjectI cell, int updateValue) {
		// TODO Auto-generated method stub
		int oldValue = cell.getValue();
		int updatedValueofThisCell = this.getValue() - oldValue + updateValue;
		updateCell(updatedValueofThisCell);
	}

	/**
	 * registerObserver
	 * 
	 * @return void
	 */

	@Override
	public void registerObserver(List<Cell> subjectLisIn) {
		// TODO Auto-generated method stub
		this.setSubjectList(subjectLisIn);
		for (SubjectI subjectCell : subjectLisIn) {
			subjectCell.addToObserversList(this);
		}
	}

	/**
	 * removeObserver
	 * 
	 * @return void
	 */

	@Override
	public void removeObserver(ObserverI cell) {
		// TODO Auto-generated method stub
		if (cell != null && observersList.contains(cell)) {
			cell.getSubjectList().remove(this);
			observersList.remove(cell);
		}
	}

	/**
	 * notifyObservers
	 * 
	 * @return void
	 */

	@Override
	public void notifyObservers(int updateValue) {
		// TODO Auto-generated method stub
		for (ObserverI cell : this.getObserversList()) {
			cell.update(this, updateValue);
		}
	}

	/**
	 * maxNodeUpdated
	 * 
	 * @return void
	 */
	public void updateCell(int updateValue) {
		notifyObservers(updateValue);
		this.value = updateValue;
	}

	/**
	 * Method to add observers to list
	 * 
	 * @return void
	 */
	@Override
	public void addToObserversList(Cell cell) {
		// TODO Auto-generated method stub
		this.observersList.add(cell);
	}

	/**
	 * Method to get list of observers
	 * 
	 * @return List of Observers
	 */
	@Override
	public List<Cell> getObserversList() {
		// TODO Auto-generated method stub
		return observersList;
	}

	/**
	 * Method to set value
	 * 
	 * @return void
	 */
	@Override
	public void setValue(int valueIn) {
		// TODO Auto-generated method stub
		value = valueIn;
	}

	/**
	 * Method to get value
	 * 
	 * @return integer
	 */
	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	/**
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cell [value=" + value + ", name=" + name + "]";
	}

	public List<Cell> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Cell> subjectList) {
		this.subjectList = subjectList;
	}
	
	
	

}
