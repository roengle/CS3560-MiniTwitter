package user;

import java.util.ArrayList;
import java.util.List;

public class UserGroup extends TreeEntry {
	private List<TreeEntry> entries;
	private UserGroup parent;
		
	/**
	 * Constructor for UserGroup. Used only for the "root" UserGroup since
	 * that is the only one which does not have a parent.
	 * 
	 * @param ID the ID of the UserGroup
	 */
	public UserGroup(String ID) {
		super(ID);
		parent = null;
		entries = new ArrayList<>();
	}
	
	/**
	 * Constructor for UserGroup that has fields for an ID and a parent UserGroup.
	 * Used for all groups that are not the "root" group, as these must have a parent 
	 * UserGroup.
	 * 
	 * @param ID the ID of the new UserGroup
	 * @param parent the UserGroup object that should be the parent of the new UserGroup
	 */
	public UserGroup(String ID, UserGroup parent) {
		super(ID);
		this.parent = parent;
		entries = new ArrayList<>();
	}
	
	/**
	 * Adds a User to *this* UserGroup's entries list.
	 * 
	 * @param ID the ID of the new User
	 */
	public void addUser(String ID) {
		TreeEntry newUser = new User(ID);
		entries.add(newUser);
	}
	
	public void addUser(User user) {
		entries.add(user);
	}
	
	/**
	 * Adds a UserGroup to *this* UserGroup's entries list.
	 * 
	 * @param ID the ID of the new UserGroup
	 */
	public void addUserGroup(String ID) {
		TreeEntry newGroup = new UserGroup(ID);
		entries.add(newGroup);
	}
	
	public void addUserGroup(UserGroup newGroup) {
		entries.add(newGroup);
	}
	
	/**
	 * Gets *this* UserGroup's parent UserGroup
	 * @return the parent UserGroup
	 */
	public UserGroup getParent() {
		return this.parent;
	}
	
	/**
	 * Helper method to find a User (in this UserGroup's context) through a sequential search through entries.
	 * If not found, null is returned. The conditions of not being found are: (1) not being of type User and
	 * (2) not have an ID equal to the query ID.
	 * 
	 * @param ID the ID of the user to search for
	 * @return the User object if it exists, null otherwise
	 */
	public User findUserByID(String ID) {
		//Loop through each entry
		for(TreeEntry entry : entries) {
			//User object found
			if(entry instanceof User && entry.getID().equals(ID)) {
				return (User)entry;
			}
			//TODO: test if this fix works
			if(entry instanceof UserGroup) {
				if(((UserGroup) entry).findUserByID(ID) != null) {
					return ((UserGroup) entry).findUserByID(ID);
				}
			}
		}
		//TODO: recursively search subdirectories AND FIX IT
		return null;
	}
	
	/**
	 * Helper method to find a UserGroup (in this UserGroup's context) through a sequential search through entries.
	 * If not found, null is returned. The conditions of not being found are: (1) not being of type UserGroup and (2)
	 * not having an ID equal to the query ID.
	 * 
	 * @param ID the ID of the user to search for
	 * @return the UserGroup object if it exists, null otherwise
	 */
	public UserGroup findGroupByID(String ID) {
		//If getting root User Group
		if(this.getID().equals(ID)) {
			return this;
		}
		//Loop through each entry
		for(TreeEntry entry : entries) {
			//UserGroup object found
			if(entry instanceof UserGroup && entry.getID().equals(ID)) {
				return (UserGroup)entry;
			}
			else if(entry instanceof UserGroup) {
				if(((UserGroup) entry).findGroupByID(ID) != null) {
					return ((UserGroup) entry).findGroupByID(ID);
				}
			}
		}
		return null;
	}
	
	/**
	 * Prints all entries in the usergroup using a depth-first search. Mainly used for debugging
	 */
	public void printAllEntries(int tabCount) {
		for(int i = 0; i < entries.size(); i++){
			TreeEntry element = entries.get(i);
			for(int j = 0; j < tabCount; j++) {
				System.out.print("\t");
			}
			System.out.println(element.getID() + " - " + element.getClass().getName());
			if(element instanceof UserGroup) {
				((UserGroup) element).printAllEntries(tabCount + 1);
			}
		}
	}
	
	/**
	 * Counts the amount of users in this user's entries list and users in this entries usergroups
	 * 
	 * @return the amount of entries in this group and all subgroups
	 */
	public int countUsers() {
		int userCount = 0;
		for(int i = 0; i < entries.size(); i++) {
			TreeEntry element = entries.get(i);
			if(element instanceof User) { userCount++; }
			if(element instanceof UserGroup) {
				userCount = userCount + ((UserGroup)element).countUsers(); 
			}
		}
		return userCount;
	}
	
}
