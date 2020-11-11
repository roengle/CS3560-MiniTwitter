package admin;

import user.UserGroup;

public interface AdminInterface {
	void addUser(String ID, String parentGroup);
	
	void addGroup(String ID, String parentGroup);
	
	void showUserTotal();
	
	void showGroupTotal();
	
	void showMessagesTotal();
	
	void showPosMessagesTotal();
}
