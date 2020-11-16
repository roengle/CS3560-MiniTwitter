package admin;

import user.TreeEntry;
import user.User;
import user.UserGroup;

public class AdminController {
	//Static instance field for AdminController singleton
	private static volatile AdminController instance = null;
	
	/* Static fields */
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
		if(instance == null) {
			instance = new AdminController();
			rootUserGroup = new UserGroup("root");
		}
		return instance;
	}
	
	/**
	 * Selects an entry by setting the selectedEntry field
	 * @param entry the entry to be selected
	 */
	public void selectEntry(TreeEntry entry) {
		selectedEntry = entry;
	}
	
	public void showUserTotal() {

	}

	public void showGroupTotal() {

	}

	public void showMessagesTotal() {

	}

	public void showPosMessagesTotal() {

	}
	
	public static TreeEntry getRootEntry() {
		return rootUserGroup;
	}
	/**
	 * Gets the current selected TreeEntry
	 * 
	 * @return the selected TreeEntry
	 */
	public static TreeEntry getSelectedEntry() {
		return selectedEntry;
	}
	
	/**
	 * Finds a User object given a user ID
	 * 
	 * @param ID the ID of the User to get
	 * @return the User object if it exists, otherwise null
	 */
	public static User getUserByID(String ID) {
		return rootUserGroup.findUserByID(ID);
	}
	
	/**
	 * Finds a UserGroup object given a group ID
	 * 
	 * @param ID the ID of the UserGroup to get
	 * @return the UserGroup object if it exists, otherwise null
	 */
	public static UserGroup getGroupByID(String ID) {
		return rootUserGroup.findGroupByID(ID);
	}
	
	/**
	 * Sets the AdminController's root group
	 * @param root the UserGroup object representing root
	 */
	public static void setRootGroup(UserGroup root) {
		rootUserGroup = root;
	}
	
	public static void printAllEntries() {
		System.out.println(rootUserGroup.getID() + " - " + rootUserGroup.getClass().getName());
		rootUserGroup.printAllEntries(1);
		System.out.println("----------------------------");
	}

}
