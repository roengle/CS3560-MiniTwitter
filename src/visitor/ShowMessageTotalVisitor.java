package visitor;

import java.util.List;

import admin.AdminController;
import user.TreeEntry;
import user.User;
import user.UserGroup;

public class ShowMessageTotalVisitor implements TreeEntryVisitor{

	@Override
	public int visit(TreeEntry entry) {
		//Use helper method
		int amountMessages = getTotalMessages(entry);
		//Print out to console
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Button ShowMessageTotal pressed. There are " + amountMessages + " messages total.");
		System.out.println("---------------------------------------------------------------------------");
		//Set AdminController's visitorOutput field
		AdminController.setVisitorOutput(amountMessages);
		return amountMessages;
	}
	
	public int getTotalMessages(TreeEntry entry) {
		List<TreeEntry> entries = ((UserGroup)entry).getEntries();
		//Initialize counter to 0
		int messageCount = 0;
		//Loop through entries
		for(int i = 0; i < entries.size(); i++) {
			//Get element
			TreeEntry element = entries.get(i);
			//If user, get their tweet count and add it to the total
			if(element instanceof User) {
				int tweetCount = ((User)element).getTweetCount();
				messageCount += tweetCount;
			}
			//If user group, recursively search
			if(element instanceof UserGroup) {
				messageCount += getTotalMessages(element);
			}
		}
		//Return total amount of messages
		return messageCount;
	}

}
