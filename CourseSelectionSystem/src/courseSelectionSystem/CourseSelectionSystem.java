package courseSelectionSystem;

public class CourseSelectionSystem {
	
	/**
	 * The default credit limit per semester.
	 */
	public static final int CREDIT_LIMIT = 25;
	private static boolean selectionPossible = true, quitPossible = false;
	private static Person accountLoggedIn = null;
	private static MainPage mainPage;
	public static final Server server = new Server("server\\index.svr");
	
	/**
	 * The default required score for a student to pass a particular course exam.
	 */
	public static final int REQUIRED_SCORE = 60;
	
	/**
	 * 
	 * @return The account that has logged in the system.
	 */
	public static Person getUsingAccount() {
		return accountLoggedIn;
	}
	
	/**
	 * Set the current account to the person.
	 * @param _account The person using the system.
	 */
	public static void setUsingAccount(Person _account) {
		accountLoggedIn = _account;
	}
	
	/**
	 * 
	 * @return Whether it is possible for students to select courses.
	 */
	public static boolean getSelectionPossible() {
		return selectionPossible;
	}
	
	/**
	 * 
	 * @return Whether it is possible for students to quit courses.
	 */
	public static boolean getQuitPossible() {
		return quitPossible;
	}
	
	/**
	 * Start the main page.
	 */
	public static void createMainPage() {
		mainPage = new MainPage();
		mainPage.setVisible(true);
	}
	
	/**
	 * 
	 * @return The current main page.
	 */
	public static MainPage getMainPage() {
		return mainPage;
	}
	
	public static void main (String[] args) {

	}
}
