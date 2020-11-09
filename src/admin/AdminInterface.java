package admin;

import user.UserGroup;

public interface AdminInterface {
	void addUser(String ID, UserGroup parentGroup);
	
	void addGroup(String ID, UserGroup parentGroup);
	
	//selectedEntry != null && selectedEntry instanceof UserGroup
	void openUserView();
	
	void showUserTotal();
	
	void showGroupTotal();
	
	void showMessagesTotal();
	
	void showPosMessagesTotal();
}
