package admin;

import user.TreeEntry;
import user.User;
import user.UserGroup;

public class AdminController implements AdminInterface {
	//Static instance field for AdminController singleton
	private static volatile AdminController instance = null;
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
	
	public static void setRootGroup(UserGroup root) {
		rootUserGroup = root;
	}
	
	public static void selectEntry(TreeEntry entry) {
		selectedEntry = entry;
	}
	
	public void addUser(String ID, UserGroup parentGroup) {
		
	}

	public void addGroup(String ID, UserGroup parentGroup) {
		
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
	
	public static User getUserByID(String ID) {
		return rootUserGroup.findUserByID(ID);
	}
	
	public static UserGroup getGroupByID(String ID) {
		return rootUserGroup.findGroupByID(ID);
	}

}
