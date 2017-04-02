package courseSelectionSystem;

import java.awt.Font;

/**
 *	This is the Chinese language pack of the software.
 */
class LanguageManager {
	public static final String[] DAYS = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
	public static final String COMMA = ", ";
	public static final String OK = "OK";
	public static final String CANCEL = "Cancel";
	public static final String NA = "N/A";
	public static final String UPLOADPHOTO = "Update a photo";
	public static final String PHOTOFILES = "Photo files";
	public static final String WELCOME = "Welcome";
	public static final String POSSIBLEOPERATION = "Available operations";
	public static final String OPERATION = "Operation";
	public static final String ACCOUNTLABELCANNOTBEEMPTY = "The account is empty";
	public static final String CHANGEPHOTO = "Change your photo";
	public static final String RETURN = "Return";
	public static final String PASSWORDLABELCANNOTBEEMPTY = "The password is empty";
	public static final String ACCOUNTORPASSWORDWRONG = "Incorrect account or password";
	public static final String SERVERFILE = "Server file";
	public static final String CHOOSEASERVER = "Choose a server";
	public static final String DOMESTICSERVER = "Local server";
	public static final String SERVERNOTFOUND = "Server error. Choose a server.";
	public static final String ERROR = "Error";
	public static final String ONLINESERVER = "Online server";
	public static final String LOGIN = "Login";
	public static final String ACCOUNT = "Account";
	public static final String LOGINTOSYSTEM = "Log into course selection system";
	public static final String PASSWORD = "Password";
	public static final String SYSTEMTITLE = "Course Selection System";
	public static final String PHOTO = "Photo";
	public static final String NAME = "Name";
	public static final String GENDER = "Gender";
	public static final String TITLE = "Title";
	public static final String ID = "ID";
	public static final String VIEWPERSONALINFO = "View personal info";
	public static final String LOGOUT = "Log out";
	public static final String COLLEGE = "College";
	public static final String ADMIN = "Admin";
	public static final String COURSEREQUIRED = "Required courses";
	public static final String COURSETITLE = "Course title";
	public static final String COURSEFLAG = "Course flag";
	public static final String COURSECOLLEGE = "College";
	public static final String COURSEPROFESSOR = "Professors";
	public static final String COURSETIME = "Schedule";
	public static final String CLASSROOM = "Classroom";
	public static final String CREDIT = "Credit";
	public static final String STUDENTSENROLLEDANDLIMIT = "Enrolled / Limit";
	public static final String COURSEID = "Course ID";
	public static final String ENROLL = "ENROLL";
	public static final String COURSEDESCRIPTION = "Course Description";
	public static final String COURSESTATUS = "Course Status";
	public static final String COURSEENROLLED = "Enrolled";
	public static final String COURSEPASSED = "Passed";
	public static final String COURSEFAILED = "Failed";
	public static final String COURSEWAITING = "Waiting";
	public static final String COURSEUNENROLLED = "Not enrolled";
	public static final String COURSESCORE = "Score";
	public static final String COURSEQUITTED = "Quitted";
	public static final String CHANGEPASSWORD = "Change your password";
	public static final String OLDPASSWORD = "Enter old password";
	public static final String NEWPASSWORD = "Enter new password";
	public static final String CONFIRMPASSWORD = "Confirm new password";
	public static final String PASSWORDSTRENGTH = "Password strength";
	public static final String OLDPASSWORDWRONG = "Incorrect old password.";
	public static final String WRONGNEWPASSWORDLENGTH = "Password length need to be within 6~16 bytes.";
	public static final String CONFIRMPASSWORDNOTMATCH = "Passwords not match";
	public static final String CHANGEPASSWORDSUCCESSFUL = "Password changed successfully.";
	public static final String ENROLLEDCOURSES = "Course selected";
	public static final String ENROLLEDCREDIT = "Selected credits";
	public static final String COURSESELECTION = "Course Selection";
	public static final String VIEWCOURSEDESCRIPTION = "View";
	
	public static Font getFont(int arg1, int arg2) {
		return new Font("Times New Roman",arg1,arg2 * 4 / 5);
	}
}