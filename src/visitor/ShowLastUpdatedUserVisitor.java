package visitor;

import java.util.List;

import admin.AdminController;
import user.TreeEntry;
import user.User;
import user.UserGroup;

public class ShowLastUpdatedUserVisitor implements TreeEntryVisitor{
	
	private class UserLongPair{
		private User user;
		private long value;
		
		public UserLongPair(User user, long value) {
			this.user = user;
			this.value = value;
		}
		
		public void setUser(User user) {
			this.user = user;
			this.value = user.getLastUpdateTime();
		}
		
		
		public User getUser() { return this.user == null ? null : this.user; }
		
		public long getValue() { return this.value; }
	}//end UserLongPair class
	
	@Override
	public int visit(TreeEntry entry) {
		UserLongPair lastUpdatedPair = new UserLongPair(null, (long)0);
		//Use helper method to find most recently updated user
		findMostRecentUser(entry, lastUpdatedPair);
		User mostRecentUser = lastUpdatedPair.getUser();
		if(mostRecentUser != null) {
			//Print out to console
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Button Show Last Updated User pressed. User: " + mostRecentUser.getID());
			System.out.println("---------------------------------------------------------------------------");
			//Set AdminController's visitorOutput field
			AdminController.setVisitorOutput(mostRecentUser);
		}else {
			//There are no users in the system
			//Print out to console
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Button Show Last Updated User pressed. User: NULL");
			System.out.println("---------------------------------------------------------------------------");
			//Set AdminController's visitorOutput field
			AdminController.setVisitorOutput(null);
		}
		//Default return
		return 0;
	}

	/**
	 * Finds the most recently updated user in the system. Updates a value in lastUpdatedPair that keeps track of the user.
	 * 
	 * @param root the TreeEntry that represents the root of the tree to search
	 * @param lastUpdatedPair a UserLongPair object for the output result to be stored in
	 */
	private void findMostRecentUser(TreeEntry root, UserLongPair lastUpdatedPair) {
		//Get entries
		List<TreeEntry> entries = ((UserGroup)root).getEntries();
		//Loop through entries
		for(int i = 0; i < entries.size(); i++) {
			//Get entry
			TreeEntry entry = entries.get(i);
			//If user
			if(entry instanceof User) {
				if(lastUpdatedPair.getUser() == null) {
					//First user being checked, set pair accordingly
					lastUpdatedPair.setUser((User)entry);
				}else {
					//A user has already been checked. Now compare last update time to update
					if(((User)entry).getLastUpdateTime() > lastUpdatedPair.getValue()) {
						lastUpdatedPair.setUser((User)entry);
					}
				}
			}
			else if(entry instanceof UserGroup) {
				//UserGroup found. Recursively search group for Users and UserGroups
				findMostRecentUser(entry, lastUpdatedPair);
			}
		}
	}
}
