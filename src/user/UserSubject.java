package user;

import java.util.List;

public abstract class UserSubject extends TreeEntry{
	private List<Observer> observers;
	
	public UserSubject(String ID) {
		super(ID);
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detatch(Observer observer) {
		observers.remove(observer);
	}
	
	/**
	 * Notifies all observers with the current UserSubject instance
	 */
	public void notifyObservers() {
		for(Observer observer : observers) {
			observer.update(this);
		}
	}
}
