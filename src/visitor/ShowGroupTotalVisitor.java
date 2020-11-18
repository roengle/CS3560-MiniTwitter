package visitor;

import java.util.List;

import admin.AdminController;
import user.TreeEntry;
import user.User;
import user.UserGroup;

public class ShowGroupTotalVisitor implements TreeEntryVisitor{

	@Override
	public int visit(TreeEntry entry) {
		//Use helper method countUsers
		int amountGroups = countGroups(entry);
		//Print out to console
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Button ShowGroupTotal pressed. There are " + amountGroups + " groups total.");
		System.out.println("---------------------------------------------------------------------------");
		//Set AdminController's visitorOutput field
		AdminController.setVisitorOutput(amountGroups + 1);
		//Return the amount of users
		return amountGroups + 1;
	}
	
	/**
	 * Helper method to count the amount of groups inside of entry, where entry acts as the root group.
	 * Root is not counted in this calculation. When a group is found, a counter is incremented and that group is then
	 * recursively searched for more groups, and so on...
	 * 
	 * @param entry the TreeEntry(in this case it should be a UserGroup) that is the root of the tree we want to search
	 * @return the number of UserGroups that exist in the tree with entry as the root node
	 */
	public int countGroups(TreeEntry entry) {
		List<TreeEntry> entries = ((UserGroup)entry).getEntries();
		//Initialize counter to 0
		int groupCount = 0;
		//Loop through entries
		for(int i = 0; i < entries.size(); i++) {
			//Get element
			TreeEntry element = entries.get(i);
			//If element is a group
			if(element instanceof UserGroup) {
				//Increment our count by one
				groupCount++;
				//Recursively call countGroups and add that result to our current
				groupCount += countGroups(element);
			}
		}
		//Return the amount of users
		return groupCount;
	}
}
