package visitor;

import java.util.List;

import admin.AdminController;
import user.TreeEntry;
import user.User;
import user.UserGroup;

public class ShowPosMessageTotalVisitor implements TreeEntryVisitor{

	@Override
	public int visit(TreeEntry entry) {
		//Use helper method
		int amountPosMessages = getPosMessages(entry);
		//Print out to console
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Button ShowMessageTotal pressed. There are " + amountPosMessages + " positive messages total.");
		System.out.println("---------------------------------------------------------------------------");
		//Set AdminController's visitorOutput field
		AdminController.setVisitorOutput(amountPosMessages);
		return amountPosMessages;
	}
	
	/**
	 * Helper method to get the amount of positive messages in a tree with entry as the root. The requirements for a "positive" messages
	 * are described in the method containsPosWord. While looping through the entries list in entry, if a user is found, each of its messages
	 * are searched and checked if they contain positive words. If so, a counter is incremented. If a UserGroup is found, that sub-group is recursively
	 * searched and so on...
	 * 
	 * @param entry the TreeEntry(in this case UserGroup) that is the root of the tree we want to search
	 * @return the amount of positive messages in the tree where entry is the root.
	 */
	public int getPosMessages(TreeEntry entry) {
		//Initialize counter
		int posMessageCounter = 0;
		//Get entries 
		List<TreeEntry> entries= ((UserGroup)entry).getEntries();
		//Loop through entries
		for(int i = 0; i < entries.size(); i++) {
			//Get element
			TreeEntry element = entries.get(i);
			//Loop through elements messages if it is a user
			if(element instanceof User) {
				List<String> elementMessages = ((User)element).getOwnTweets();
				for(int j = 0; j < elementMessages.size(); j++) {
					//Increment counter  if elementMessages.get(j) contains a positive word
					posMessageCounter += containsPosWord(elementMessages.get(j)) ? 1 : 0;
				}
			}
			//If element is a UserGroup, recursively search the subgroup's users
			if(element instanceof UserGroup) {
				posMessageCounter += getPosMessages(element);
			}
		}
		//Return the amount of positive messages
		return posMessageCounter;
	}
	
	/**
	 * A helper method to determine if a String contains positive words. The positive words are below.
	 * 
	 * @param message the message to check
	 * @return true if it contains positive words, false if it doesn't
	 */
	public boolean containsPosWord(String message) {
		if(message.contains("nice")) { return true; }
		if(message.contains("great")) { return true; }
		if(message.contains("haha")) { return true; }
		if(message.contains("wonderful")) { return true; }
		if(message.contains("happy")) { return true; }
		return false;
	}

}
