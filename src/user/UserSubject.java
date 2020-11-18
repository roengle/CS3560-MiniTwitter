package user;

import java.util.ArrayList;
import java.util.List;

public abstract class UserSubject extends TreeEntry{
	//List of observers represent the observers watching this User
	private List<Observer> observers;
	
	/**
	 * Instantiates a UserSubject object that has an ID.
	 * 
	 * @param ID the ID of the User (and the UserSubject, however 
	 * its context in UserSubject is not used)
	 */
	public UserSubject(String ID) {
		super(ID);
		observers = new ArrayList<>();
	}
	
	/**
	 * Attach an observer to *this* UserSubject's observers list
	 * 
	 * @param observer the observer to add
	 */
	public void attach(Observer observer) { observers.add(observer); }
	
	/**
	 * Remove an observer from *this* Usersubject's observers list
	 * 
	 * @param observer the observer to remove
	 */
	public void detatch(Observer observer) { observers.remove(observer); }
	
	/**
	 * Notifies all observers that are attached to our observers list.
	 */
	public void notifyObservers() {
		for(Observer observer : observers) {
			observer.update(this);
		}
	}
	
	/**
	 * Updates a single specified observer.
	 * 
	 * @param user the observer(User) that we want to notify
	 */
	public void notifyObserver(User user) { user.update(this); }
}
