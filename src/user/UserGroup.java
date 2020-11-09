package user;

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
	}
	
	/**
	 * Adds a user to a UserGroup's entries list. Used for when the parent UserGroup is
	 * known, and just needs the entries list to be updated with the new user.
	 * 
	 * @param ID the ID of the new User
	 */
	public void addUser(String ID) {
		TreeEntry newUser = new User(ID);
		entries.add(newUser);
	}
	
	/**
	 * Adds a User to a specified UserGroup's (AKA the "parent's") entries list. Used for when
	 * the parent UserGroup is unknown, in which a helper method is used to find the UserGroup.
	 * After the parent UserGroup is found, the other one-argument method is used to add the User
	 * to it.
	 * 
	 * @param ID the ID of the user to be added
	 * @param parentID the ID of the new User's parent group
	 */
	public void addUser(String ID, String parentID) {
		UserGroup parent = findGroupByID(parentID);
		parent.addUser(ID);
	}
	
	/**
	 * Adds a UserGroup to a UserGroup's entries list. Used for when the parent UserGroup
	 * is known, and just needs the entries list to be updated with the new user group.
	 * 
	 * @param ID the ID of the user group
	 */
	public void addUserGroup(String ID) {
		TreeEntry newGroup = new UserGroup(ID);
		entries.add(newGroup);
	}
	
	/**
	 * Adds a UserGroup to a specified UserGroup's (the "parent's") entries list. Used for when
	 * the parent UserGroup is unknown, in which a helper method is used to find the UserGroup. After
	 * it is found, the other one-argument method is used to add the UserGroup to it.
	 * 
	 * @param ID the ID of the UserGroup to be added
	 * @param parentID the ID of the parent UserGroup
	 */
	public void addUserGroup(String ID, String parentID) {
		UserGroup parent = findGroupByID(parentID);
		parent.addUser(ID);
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
		for(TreeEntry entry : entries) {
			if(entry instanceof User && entry.getID().equals(ID)) {
				return (User)entry;
			}
		}
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
		for(TreeEntry entry : entries) {
			if(entry instanceof UserGroup && entry.getID().equals(ID)) {
				return (UserGroup)entry;
			}
		}
		return null;
	}
	
}
