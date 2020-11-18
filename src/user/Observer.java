package user;

public interface Observer {
	/**
	 * Updates *this* observer. Important info is in userSubject
	 * 
	 * @param userSubject the subject which notified us
	 */
	public void update(UserSubject userSubject);
}
