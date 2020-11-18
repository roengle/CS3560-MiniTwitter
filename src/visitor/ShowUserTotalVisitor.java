package visitor;

import java.util.List;

import admin.AdminController;
import user.TreeEntry;
import user.User;
import user.UserGroup;

public class ShowUserTotalVisitor implements TreeEntryVisitor{
	@Override
	public int visit(TreeEntry entry) {
		//Use helper method countUsers
		int amountUsers = countUsers(entry);
		//Print out to console
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Button ShowUserTotal pressed. There are " + amountUsers + " users total.");
		System.out.println("---------------------------------------------------------------------------");
		//Set AdminController's visitorOutput field
		AdminController.setVisitorOutput(amountUsers);
		//Return the amount of users
		return amountUsers;
	}
	
	/**
	 * Counts the amount of users in this user's entries list and users in this entries usergroups
	 * 
	 * @return the amount of entries in this group and all subgroups
	 */
	public int countUsers(TreeEntry entry) {
		List<TreeEntry> entries = ((UserGroup)entry).getEntries();
		//Initialize counter to 0
		int userCount = 0;
		//Loop through entries
		for(int i = 0; i < entries.size(); i++) {
			//Get element
			TreeEntry element = entries.get(i);
			//If it is a User object, increase counter
			if(element instanceof User) { userCount++; }
			//If it is a UserGroup object, recursively count users in that UserGroup
			if(element instanceof UserGroup) { userCount += countUsers(element); }
		}
		//Return the amount of users
		return userCount;
	}
}
