package visitor;

public interface TreeEntryVisitable {
	/**
	 * Accepts a visitor.
	 * 
	 * @param visitor the visitor to accept
	 */
	public void accept(TreeEntryVisitor visitor);
}
