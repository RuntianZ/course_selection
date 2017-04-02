package courseSelectionSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

class Course extends Displayable {
	private ArrayList<String> professor;
	private String title, id, college, flag;
	private ArrayList<CourseSchedule> schedule;
	private CourseDescription description;
	private Classroom classroom;
	private int credit, studentsEnrolled, studentsLimit;
	private CourseStatus status = null;

	/**
	 * 
	 * @param _id The id of the course.
	 * @param _title The name of the course.
	 * @param _flag The attributes of the course.
	 * @param _college The college responsible for the course.
	 * @param _professor The professor list of the course (their account paths).
	 * @param _schedule The course schedule.
	 * @param _classroom The classroom the course is arranged.
	 * @param _credit The credit alloted.
	 * @param _description The detailed description of the course.
	 * @param _enrolled The number of students enrolled in the course.
	 * @param _limit The limit of the student number.
	 */
	public Course(String _id, String _title, String _flag, String _college, 
			ArrayList<String> _professor, ArrayList<CourseSchedule> _schedule,
			Classroom _classroom, int _credit, CourseDescription _description, int _enrolled, int _limit) {
		id = _id;
		title = _title;
		college = _college;
		flag = _flag;
		professor = _professor;
		schedule = _schedule;
		classroom = _classroom;
		description = _description;
		credit = _credit;
		studentsEnrolled = _enrolled;
		studentsLimit = _limit;
	}
	
	/**
	 * 
	 * @return The credit alloted for the course.
	 */
	public int getCredit() {
		return credit;
	}

	public String getFlag() {
		return flag;
	}
	/**
	 * 
	 * @return The classroom the course takes place.
	 */
	public Classroom getClassroom() {
		return classroom;
	}

	/**
	 * 
	 * @return The professor(s) teaching the course.
	 */
	public Iterator<String> getProfessor() {
		return professor.iterator();
	}

	/**
	 * 
	 * @return The schedule of the course.
	 */
	public Iterator<CourseSchedule> getCourseSchedule() {
		return schedule.iterator();
	}

	/**
	 * 
	 * @return The title of the course.
	 */
	public String getTitle() {
		return title;
	}

	public int getEnrolledNumber() {
		return studentsEnrolled;
	}
	
	public int getLimit() {
		return studentsLimit;
	}
	
	/**
	 * 
	 * @return The description of the course.
	 */
	public CourseDescription getCourseDescription() {
		return description;
	}
	
	public String getID() {
		return id;
	}
	
	public String getCollege() {
		return college;
	}

	@Override
	public String toString() {
		return title;
	}

	@Override
	public void displayPrepare() {
		clearContents();
		add(LanguageManager.COURSEID,id);
		add(LanguageManager.COURSETITLE,title);
		add(LanguageManager.COURSEFLAG, flag);
		add(LanguageManager.COURSECOLLEGE, college);
		String s = "";
		for(String st : professor) {
			Professor p = Professor.createProfessor(st);
			s += p.getName() + "(" + p.getTitle() + "), ";
		}
		if(s.length() >= 2)
			s = s.substring(0,s.length() - 2);
		add(LanguageManager.COURSEPROFESSOR, s);
		String s1 = "";
		for(CourseSchedule cs : schedule) {
			s1 += LanguageManager.DAYS[cs.getDay()] + " " + cs.getBegin();
			if(cs.getBegin() != cs.getEnd())
				s1 += "~" + cs.getEnd();
			s1 += ", ";
		}
		if(s1.length() >= 2)
			s1 = s1.substring(0,s1.length() - 2);
		add(LanguageManager.CREDIT,Integer.toString(credit));
		add(LanguageManager.COURSETIME,s1);
		add(LanguageManager.CLASSROOM,classroom.toString());
		add(LanguageManager.STUDENTSENROLLEDANDLIMIT,studentsEnrolled + "/" + studentsLimit);
	}

	/**
	 * Create a Course Object from the file path.
	 * @param path The path of the file.
	 * @return A course object.
	 */
	public static Course getFromFile(String path) {
		FastScanner scanner = null;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(path))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = scanner.nextLine();
		String title = scanner.nextLine();
		String flag = scanner.nextLine();
		String college = scanner.nextLine();
		int n = scanner.nextInt();
		ArrayList<String> professor = new ArrayList<String>();
		for(int i = 0; i < n; i++)
			professor.add(scanner.nextLine());
		n = scanner.nextInt();
		ArrayList<CourseSchedule> schedule = new ArrayList<CourseSchedule>();
		for(int i = 0; i < n; i++)
			schedule.add(new CourseSchedule(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
		Classroom classroom = Classroom.getFromFile(scanner.nextLine());
		int credit = scanner.nextInt();
		CourseDescription description = CourseDescription.getFromFile(scanner.nextLine());
		int enrolled = scanner.nextInt();
		int limit = scanner.nextInt();
		scanner.close();
		return new Course(id,title,flag,college,professor,schedule,classroom,credit,description,enrolled,limit);
	}
	
}

class CourseStatus extends Displayable {
	private int status, score;
	private Course course;

	/**
	 * The student doesn't enroll in the course.
	 */
	public static final int UNENROLLED = 0;

	/**
	 * The student currently enrolls in the course.
	 */
	public static final int ENROLLED = 1;

	/**
	 * The student has passed the course exam and got the credit.
	 */
	public static final int PASSED = 2;

	/**
	 * The student fails to pass the course exam and doesn't get the credit.
	 */
	public static final int FAILED = 3;

	/**
	 * The student quits the course at mid-term.
	 */
	public static final int QUITTED = 4;
	
	/**
	 * The student is still on the wait list.
	 */
	public static final int WAITING = 5;

	public CourseStatus(int _status, Course _course) {
		status = _status;
		score = 0;
		course = _course;
	}

	/**
	 * Updates the student's score and decide whether the student passes the exam.
	 * @param _score The score the student acquires in the course exam.
	 */
	public void scoreUpdate(int _score) {
		score = _score;
		if(score >= CourseSelectionSystem.REQUIRED_SCORE)
			status = PASSED;
		else
			status = FAILED;
	}

	/**
	 * Quit the course.
	 * @throws IllegalOperationException
	 */
	public void quit() throws IllegalOperationException {
		if(CourseSelectionSystem.getSelectionPossible() && status == WAITING)
			status = UNENROLLED;
		else if (CourseSelectionSystem.getQuitPossible() && status == ENROLLED)
			status = QUITTED;
		else
			throw new IllegalOperationException();
	}

	/**
	 * 
	 * @return The status of the current course.
	 */
	public int getStatus() {
		return status;
	}

	@Override
	public void displayPrepare() {
		clearContents();
		add(LanguageManager.COURSETITLE,course.getTitle());
		add(LanguageManager.COURSEFLAG,course.getFlag());
		add(LanguageManager.CREDIT,Integer.toString(course.getCredit()));
		switch(status) {
		case UNENROLLED:
			add(LanguageManager.COURSESTATUS, LanguageManager.COURSEUNENROLLED);
			add(LanguageManager.COURSESCORE, LanguageManager.NA);
			break;
		case ENROLLED:
			add(LanguageManager.COURSESTATUS, LanguageManager.COURSEENROLLED);
			add(LanguageManager.COURSESCORE, LanguageManager.NA);
			break;
		case PASSED:
			add(LanguageManager.COURSESTATUS, LanguageManager.COURSEPASSED);
			add(LanguageManager.COURSESCORE, Integer.toString(score));
			break;
		case FAILED:
			add(LanguageManager.COURSESTATUS, LanguageManager.COURSEFAILED);
			add(LanguageManager.COURSESCORE, Integer.toString(score));
			break;
		case WAITING:
			add(LanguageManager.COURSESTATUS, LanguageManager.COURSEWAITING);
			add(LanguageManager.COURSESCORE, LanguageManager.NA);
			break;
		case QUITTED:
			add(LanguageManager.COURSESTATUS, LanguageManager.COURSEQUITTED);
			add(LanguageManager.COURSESCORE, LanguageManager.COURSEQUITTED);
			break;		
		}
	}
}

/**
 * This class indicates the exact time at which a particular course should be attended.
 */
class CourseSchedule implements Comparable<CourseSchedule> {	
	private int day, begin, end;

	@Override
	public int compareTo(CourseSchedule cs) {
		if(day - cs.day != 0)
			return day - cs.day;
		if(begin - cs.begin != 0)
			return begin - cs.begin;
		return end - cs.end;
	}

	/**
	 * 
	 * @param _day The day of week the course is scheduled.
	 * @param _begin The time the course starts.
	 * @param _end The time the course ends.
	 */
	public CourseSchedule(int _day, int _begin, int _end) {
		day = _day;
		begin = _begin;
		end = _end;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getBegin() {
		return begin;
	}
	
	public int getEnd() {
		return end;
	}

}

/**
 *	File list: <BR/>
 *	Title <BR/>
 *	Description <BR/>
 *	Number of required courses n <BR/>
 *	Paths of required courses (n lines) <BR/>
 */
class CourseDescription extends Displayable {
	private String title, description;
	private ArrayList<String> requirements;

	public void addRequirement(String c) {
		requirements.add(c);
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public CourseDescription(String _title, String _description, ArrayList<String> _requirements) {
		title = _title;
		description = _description;
		requirements = _requirements;
	}

	@Override
	public void displayPrepare() {
		clearContents();
		add(LanguageManager.COURSETITLE,title);
		add(LanguageManager.COURSEDESCRIPTION,description);
		if(requirements.isEmpty())
			add(LanguageManager.COURSEREQUIRED,null);
		else {
			String s = "";
			for(int i = 0; i < requirements.size(); i++)
				s += Course.getFromFile(requirements.get(i)).getTitle() + LanguageManager.COMMA;
			s = s.substring(0,s.length() - 1);
			add(LanguageManager.COURSEREQUIRED,s);
		}
		
	}
	
	public static CourseDescription getFromFile(String path) {
		FastScanner scanner = null;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(path))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = scanner.nextLine();
		String description = scanner.nextLine();
		int n = scanner.nextInt();
		ArrayList<String> requirements = new ArrayList<String>();
		for(int i = 0; i < n; i++)
			requirements.add(scanner.nextLine());
		scanner.close();
		return new CourseDescription(title, description, requirements);
	}

}