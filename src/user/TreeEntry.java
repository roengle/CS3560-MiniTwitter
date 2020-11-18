package user;

import visitor.TreeEntryVisitable;
import visitor.TreeEntryVisitor;

public abstract class TreeEntry implements TreeEntryVisitable{
	private String ID;
	
	public TreeEntry(String ID) {
		this.ID = ID;
	}
	
	/**
	 * Returns a string copy of the tree entry ID
	 * @return the ID in a new string
	 */
	public String getID() {
		//Don't return a direct reference to ID
		return String.copyValueOf(ID.toCharArray());
	}
	
	/**
	 * Gets the ID of the TreeEntry
	 */
	public String toString() {
		return ID;
	}
	
	/**
	 * Accepts a TreeEntryVisitor, which sends *this* to the visitor's visit method.
	 */
	public void accept(TreeEntryVisitor visitor) {
		visitor.visit(this);
	}
}
