package visitor;

import user.TreeEntry;

public interface TreeEntryVisitor {
	/**
	 * Visits the entry specified
	 * 
	 * @param entry the entry to visit
	 * @return the result of visiting that entry
	 */
	public int visit(TreeEntry entry);
}
