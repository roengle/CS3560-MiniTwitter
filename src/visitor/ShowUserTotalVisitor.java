package visitor;

import user.TreeEntry;
import user.UserGroup;

public class ShowUserTotalVisitor implements TreeEntryVisitor{
	@Override
	public int visit(TreeEntry entry) {
		return ((UserGroup)entry).countUsers();
	}
}
