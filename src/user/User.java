package user;

import java.util.List;

import admin.AdminController;

public class User extends UserSubject implements Observer{
	
	private UserGroup parentGroup;
	private List<String> followers;
	private List<String> followings;
	private List<String> feed;
	
	public User(String ID) {
		super(ID);
	}
	
	public User(String ID, String parentID) {
		super(ID);
		this.parentGroup = AdminController.getGroupByID(parentID);
	}
	
	public void followUser(String ID) {
		
		//When following a user, the following view and feed need to be updated
		updateFollowingView();
		updateFeed();
	}
	
	public void postTweet(String message) {
		
		
		notifyObservers();
	}
	
	public void updateFollowingView() {
		
	}
	
	public void updateFeed() {
		
	}

	@Override
	public void update(UserSubject userSubject) {
		
	}
	
	
}
