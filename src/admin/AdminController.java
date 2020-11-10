package admin;

import user.TreeEntry;
import user.User;
import user.UserGroup;

public class AdminController implements AdminInterface {
	//Static instance field for AdminController singleton
	private static volatile AdminController instance = null;
	
	/* Instance fields */
	private static UserGroup rootUserGroup;
	private static TreeEntry selectedEntry;
	
	/**
	 * A private constructor for AdminController to obey singleton pattern
	 */
	private AdminController() {
		//Initialize selected entry to null
		selectedEntry = null;
	}

	/**
	 * Returns current instance of AdminController if it isn't null. Otherwise,
	 * creates new instance
	 * @return current or new instance of AdminController
	 */
	public static AdminController getInstance() {
		return instance == null ? new AdminController() : instance;
	}
	
	/**
	 * Sets the AdminController's root group
	 * @param root the UserGroup object representing root
	 */
	public static void setRootGroup(UserGroup root) {
		rootUserGroup = root;
	}
	
	/**
	 * Selects an entry by setting the selectedEntry field
	 * @param entry the entry to be selected
	 */
	public static void selectEntry(TreeEntry entry) {
		selectedEntry = entry;
	}
	
	public void addUser(String ID, String parentGroupID) {
		//Get parent UserGroup
		UserGroup parentGroup = getGroupByID(parentGroupID);
		//Add the user to the parent group's entries list
		parentGroup.addUser(ID);
	}

	public void addGroup(String ID, String parentGroupID) {
		UserGroup parentGroup = getGroupByID(parentGroupID);
		//Add the user group to the parent group's entries list
		parentGroup.addUserGroup(ID);
	}

	/**
	 * 
	 * Assumption: selectedEntry != null && selectedEntry instanceof UserGroup
	 */
	public void openUserView() {

	}

	public void showUserTotal() {

	}

	public void showGroupTotal() {

	}

	public void showMessagesTotal() {

	}

	public void showPosMessagesTotal() {

	}
	
	/**
	 * Gets the current selected TreeEntry
	 * 
	 * @return the selected TreeEntry
	 */
	public TreeEntry getSelectedEntry() {
		return selectedEntry;
	}
	
	public static User getUserByID(String ID) {
		return rootUserGroup.findUserByID(ID);
	}
	
	public static UserGroup getGroupByID(String ID) {
		return rootUserGroup.findGroupByID(ID);
	}

}
