package user;

import java.util.ArrayList;
import java.util.List;

import admin.AdminController;

public class User extends UserSubject implements Observer {
	
	/* Instance fields*/
	private UserGroup parentGroup;
	private List<String> followers;
	private List<String> followings;
	private List<String> feed;
	private String mostRecentMessage;
	
	
	
	/* List<Observer> inherited from UserSubject! */
	
	/*
	 * States of interest to observers(our observers are our followers):
	 * ID -- used for adding to follower list if not already in
	 * mostRecentMessage -- used for updating our feed
	 */
	
	/**
	 * Constructs a User object with an ID.
	 * TODO: See if this can be removed since each user *must* have a parent.
	 * @param ID
	 */
	public User(String ID) {
		super(ID);
		initializeLists();
	}
	
	public User(String ID, String parentID) {
		super(ID);
		this.parentGroup = AdminController.getGroupByID(parentID);
		initializeLists();
	}
	
	public User(String ID, UserGroup parentGroup) {
		super(ID);
		this.parentGroup = parentGroup;
		initializeLists();
	}
	
	public void initializeLists() {
		followers = new ArrayList<>();
		followings = new ArrayList<>();
		feed = new ArrayList<>();
	}
	
	/**
	 * Followers a User given the ID. The following are the steps to follow a user:
	 * (1) Get their User object.
	 * (2) Attach yourself(observer) to their observers list
	 * (3) Add their ID to followings list
	 * (4) Notify them such that they only update their followers list with your ID.
	 * 
	 * @param ID the ID of the User to follow
	 */
	public void followUser(String ID) {
		//Get the user object
		UserSubject user = AdminController.getUserByID(ID);
		//Since the User class is also an observer, we can attach *this* to the user's observers
		user.attach(this);
		//Add the users ID to our followings list
		followings.add(user.getID());
		//Set most recent message to null
		mostRecentMessage = null;
		//Notify the specific observer. Since mostRecentMessage is null, it will only update its followers list
		notifyObserver((User)user);
		//When following a user, the following view needs to be updated
		updateFollowingView(ID);
	}
	
	/**
	 * Posts a tweet given a message. Additionally updates observers to update any of *our* followers feeds. The
	 * following are the steps to do this:
	 * (1) Format the string so it includes our ID.
	 * (2) Update our own feed
	 * (3) Set our most recent message field
	 * (4) Notify observers
	 * 		(4a) In the User(s) notified, the most recent message field is read updated into their feed
	 * 
	 * @param message the message to tweet
	 */
	public void postTweet(String message) {
		//Update our own feed with our own message
		String formattedString = String.format("%s: %s", this.getID(), message);
		feed.add(formattedString);
		//Set our most recent message
		this.mostRecentMessage = message;
		//Notify our observers that we posted a message
		notifyObservers();
		
	}
	
	/**
	 * Update our following view given an ID. Adds the ID to our followings list and updates the GUI list view
	 * that represents our followings.
	 * 
	 * @param ID the ID of the User we are following
	 */
	public void updateFollowingView(String ID) {
		followings.add(ID);
		//TODO: Update GUI elements
	}

	/**
	 * Sets this User's parent UserGroup
	 * 
	 * @param parent the UserGroup that is the parent
	 */
	public void setParent(UserGroup parent) {
		this.parentGroup = parent;
	}
	/**
	 * Gets the User's feed list
	 * 
	 * @return the feed list
	 */
	public List<String> getFeed() { return this.feed; }
	
	/**
	 * Gets the User's followings list
	 * 
	 * @return the followings list
	 */
	public List<String> getFollowings(){ return this.followings; }
	
	/**
	 * Returns *this* User's most recent message, which is in the mostRecentMessage field
	 * 
	 * @return the most recent message of this User
	 */
	public String getMostRecentMessage() { return this.mostRecentMessage; }
	
	/**
	 * Returns *this* User's parent UserGroup.
	 * 
	 * @return the parent UserGroup
	 */
	public UserGroup getParentGroup() { return this.parentGroup; }
	
	
	@Override
	public void update(UserSubject userSubject) {
		/*
		 * One of our observers has called us...
		 * Remember we only care about our followings messages
		 * and ID. Once ID is added to our followers list, it won't be used
		 * for anything else.
		 */
		//Get ID of our subject
		String id = userSubject.getID();
		//If their ID isn't in our follower list...
		if(!followers.contains(id)) {
			followers.add(id);
		}
		//Get most recent message
		String msg = ((User)userSubject).getMostRecentMessage();
		//If just getting a new follower, msg will be null so don't update feed
		if(msg != null) {
			String formattedString = String.format("%s: %s", id, msg);
			feed.add(formattedString);
			//TODO: update feed GUI
		}
	}
	
	
}
