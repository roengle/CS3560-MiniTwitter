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
