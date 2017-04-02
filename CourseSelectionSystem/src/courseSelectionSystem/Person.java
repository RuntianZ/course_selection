package courseSelectionSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Person extends Displayable {
	protected String name, title, id, gender, accountPath;
	protected ArrayList<Operation> possibleOperation;
	protected PhotoLabel photoLabel;
	protected int operationIndex;
	
	public Person(String _accountPath, String _name, String _gender, String _title, String _id, String _photo) {
		possibleOperation = new ArrayList<Operation>();
		accountPath = _accountPath;
		name = _name;
		gender = _gender;
		title = _title;
		id = _id;
		operationIndex = 0;
		photoLabel = new PhotoLabel(_photo);
		addOperation(new ViewInfoOperation(LanguageManager.VIEWPERSONALINFO));
		addOperationToEnd(new LogoutOperation(LanguageManager.LOGOUT));
	}
	
	public Person(String _accountPath, String _name, String _gender, String _title, String _id) {
		accountPath = _accountPath;
		possibleOperation = new ArrayList<Operation>();
		name = _name;
		gender = _gender;
		title = _title;
		id = _id;
		operationIndex = 0;
		photoLabel = new PhotoLabel();
		add(LanguageManager.PHOTO,photoLabel,null,null);
		add(LanguageManager.NAME,name);
		add(LanguageManager.GENDER,gender);
		add(LanguageManager.TITLE,title);
		add(LanguageManager.ID,id);
		addOperation(new ViewInfoOperation(LanguageManager.VIEWPERSONALINFO));
		addOperationToEnd(new LogoutOperation(LanguageManager.LOGOUT));
	}
	
	public String getAccountPath() {
		return accountPath;
	}
	
	/**
	 * Adds a new operation to the account's operation list. The operation will be inserted before all
	 * the operation that was inserted using addOperationToEnd.
	 * @param _operation The operation to be added.
	 */
	public void addOperation(Operation _operation) {
		possibleOperation.add(operationIndex,_operation);
		operationIndex++;
	}
	
	/**
	 * Add a new operation to the end of the account's operation list, but before all the operation that
	 * was inserted using this method.
	 * @param _operation The operation to be added.
	 */
	public void addOperationToEnd(Operation _operation) {
		possibleOperation.add(operationIndex, _operation);
	}
	
	/**
	 * 
	 * @return All possible operations of the current account.
	 */
	public Iterator<Operation> operationIterator() {
		return possibleOperation.iterator();
	}
	
	/**
	 * Change the photo of the person.
	 */
	public void updatePhoto(String _path) {
		String path = photoLabel.getPath();
		int i = 0;
		while(i < path.length() - 1) {
			if(path.charAt(i) == '\\' && path.charAt(i + 1) == '\\')
				path = path.substring(0,i + 1) + path.substring(i + 2);
			else
				i++;
		}
		if(path.equals(PhotoLabel.DEFAULT_PHOTO)) {
			String lst = _path.substring(_path.lastIndexOf('.'));
			path = "server\\photo\\" + id + lst;
			CopyFileUtil.copyFile(_path, path, true);
			FastScanner scanner = null;
			try {
				scanner = new FastScanner(new BufferedReader(new FileReader(new File(accountPath))));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FastWriter writer = null;
			String newpath = accountPath.substring(0,accountPath.lastIndexOf('\\') + 1) + "temp.dat";
			try {
				writer = new FastWriter(new BufferedWriter(new FileWriter(newpath)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(i = 0; i < 5; i++) {
				String st = scanner.nextLine();
				writer.writeln(st);
			}
			writer.writeln(path);
			String st = scanner.nextLine();
			st = scanner.nextLine();
			writer.writeln(st);
			writer.close();
			scanner.close();
			CopyFileUtil.renameFile(newpath,accountPath,true);
		}
		else 
			CopyFileUtil.copyFile(_path, path, true);
		photoLabel.changePhoto(path);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public void displayPrepare() {
		clearContents();
		add(LanguageManager.PHOTO,photoLabel,null, null);
		add(LanguageManager.NAME,name);
		add(LanguageManager.GENDER,gender);
		add(LanguageManager.TITLE,title);
		add(LanguageManager.ID,id);
	}
	
}

class Student extends Person {
	private Map<Course, CourseStatus> courseEnrolled;
	private String college;
	private int creditLimit;
	
	public Student(String _accountPath, String _name, String _gender, String _title, String _id, String _photo, String _college) {
		super(_accountPath, _name, _gender, _title , _id, _photo);
		college = _college;
		creditLimit = CourseSelectionSystem.CREDIT_LIMIT;
		courseEnrolled = new HashMap<Course, CourseStatus>();
		addOperation(new Operation(LanguageManager.COURSESELECTION) {

			@Override
			protected void run(ArrayList<Object> args) {
				CourseSelectionSystem.getMainPage().changePanel(new CourseSelectionPanel());
				
			}

			@Override
			public boolean isAvailable() {
				return true;
			}
		
		});
	}
	
	public int getCreditLimit() {
		return creditLimit;
	}
	
	public void changeCourseStatus(Course c, CourseStatus s) {
		courseEnrolled.put(c, s);
	}

	/**
	 * Edits the credit limit of the current student.
	 * @param c The new credit limit.
	 */
	public void setCreditLimit(int c) {
		creditLimit = c;
	}

	
	@Override
	public void displayPrepare() {
		super.displayPrepare();
		add(LanguageManager.COLLEGE,college);
	}
	
	/**
	 * Decide whether the student has enrolled in or passed a particular course.
	 * @param c The course.
	 * @return True if the student has enrolled in or passed the course, false otherwise.
	 */
	public boolean searchCourse(Course c) {
		CourseStatus s = courseEnrolled.get(c);
		if(s == null)
			return false;
		int gs = s.getStatus();
		if(gs == CourseStatus.ENROLLED || gs == CourseStatus.PASSED)
			return true;
		return false;
	}
	
	/**
	 * Return a list of the student's courses that has the status ENROLLED.
	 * @return A list of courses with the status ENROLLED.
	 */
	public ArrayList<Course> getEnrolled() {
		ArrayList<Course> ans = new ArrayList<Course>();
		for(Map.Entry<Course, CourseStatus> e : courseEnrolled.entrySet()) {
			Course c = e.getKey();
			CourseStatus s = e.getValue();
			if(s.getStatus() == CourseStatus.ENROLLED)
				ans.add(c);
		}
		return ans;
	}
}

class Professor extends Person {

	private String college;
	private ArrayList<String> teaching;
	
	public Professor(String _accountPath, String _name, String _gender, String _title, String _id, String _photo,
			String _college) {
		super(_accountPath, _name, _gender, _title , _id, _photo);
		college = _college;
	}
	
	public static Professor createProfessor(String _filepath) {
		FastScanner scanner = null;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(_filepath))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.nextLine();
		String name = scanner.nextLine();
		String gender = scanner.nextLine();
		String title = scanner.nextLine();
		String id = scanner.nextLine();
		String photo = scanner.nextLine();
		String college = scanner.nextLine();
		return new Professor(_filepath, name, gender, title, id, photo, college);
	}
	
	/**
	 * Adds a course to the professor's teaching schedule.
	 * @param _course The path of the course to be added.
	 */
	public void add(String _course) {
		teaching.add(_course);
	}
	
	@Override
	public void displayPrepare() {
		super.displayPrepare();
		add(LanguageManager.COLLEGE,college);
	}
}

class Administrator extends Person {

	public Administrator(String _accountPath, String _name, String _id) {
		super(_accountPath, _name, "N/A", LanguageManager.ADMIN, _id);
	}
	
}