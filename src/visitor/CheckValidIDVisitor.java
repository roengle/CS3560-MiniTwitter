package visitor;

import java.util.ArrayList;
import java.util.List;

import admin.AdminController;

import user.TreeEntry;
import user.UserGroup;

public class CheckValidIDVisitor implements TreeEntryVisitor {

	@Override
	/* Returns 1 if all IDs are valid, and 0 if not*/
	public int visit(TreeEntry entry) {
		//List to keep track of the IDs we have checked. Initialized here since helper method recursively calls itself.
		List<String> checkedIDs = new ArrayList<>();
		//Get output of helper method
		boolean isValid = checkIfValidID(entry, checkedIDs);
		//Print out to console
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Button CheckValidIDs pressed. Status: " + isValid);
		System.out.println("---------------------------------------------------------------------------");
		//Set visitor output
		AdminController.setVisitorOutput(isValid ? 1 : 0);
		//Return based on if result if valid
		return isValid ? 1 : 0;
	}
	
	/**
	 * Checks if the entries of the children of root and its sub-children are valid.
	 * The criteria for being valid is the following:
	 * 
	 * 1) There must be no duplicate IDs
	 * 2) The cannot be any space characters in an ID
	 * 
	 * If both criteria aren't met for all of the entries, then is it not valid.
	 * 
	 * @param root the root of the tree to check for valid IDs
	 * @param checkedIDs an ArrayList that keeps track of the IDs that have already been checked
	 * @return true if all IDs are valid, false it not
	 */
	public boolean checkIfValidID(TreeEntry root, List<String> checkedIDs) {
		//Get entries
		List<TreeEntry> entries = ((UserGroup)root).getEntries();
		//Loop through entries
		for(int i = 0; i < entries.size(); i++) {
			TreeEntry entry = entries.get(i);
			//Check if ID already exists or if it contains a space
			if(checkedIDs.contains(entry.getID()) || entry.getID().contains(" ")) {
				//Return out of the function
				return false;
			}
			//Add the ID we checked the the checked list
			checkedIDs.add(entries.get(i).getID());
			//If entry is a UserGroup, recursively search subsequent ID values
			if(entry instanceof UserGroup) {
				//If recursive search returned false at any time, return false
				if(!checkIfValidID(entry, checkedIDs)) {
					return false;
				}
			}
		}
		//False hasn't been returned, so all IDs are valid. Return true
		return true;
	}
}//end ChickValidIDVisitor class
