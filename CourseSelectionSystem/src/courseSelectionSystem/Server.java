package courseSelectionSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JDialog;

/**
 *	Index file list: <BR/>
 *	Account Database <BR/>
 *	Course Database <BR/>
 *	Building Database <BR/>
 *	<BR/>
 *
 *	Account file list: <BR/>
 *	Identity (Student, Professor, Administrator) <BR/>
 *	Name <BR/>
 *	Gender (Male, Female) (None for Administrator) <BR/> 
 *	Title (None for Administrator) <BR/>	
 *	ID <BR/>
 *	PhotoPath <BR/>
 *	College (None for Administrator) <BR/> 
 *	<BR/>
 *	For Student Account: <BR/>
 *	Number of courses that they have chosen n <BR/>
 *	Lists of courses that they have chosen (course paths in n lines) <BR/>
 *	<BR/>
 *	For Professor Account: <BR/>
 *	Number of courses going to teach this semester n <BR/>
 *	Lists of courses going to teach this semester (course paths in n lines) <BR/>
 *	<BR/>
 *  Course file list: <BR/>
 *	Course ID <BR/>
 *	Course Name <BR/>
 *	Course Flag <BR/>
 *	Course College <BR/>
 *	Course Professor number n <BR/>
 *	Course Professors' account file paths (n lines) <BR/>
 *	Course Schedule number m <BR/>
 *	Course Schedule (Day Begin End) (m lines) <BR/>
 *	Course classroom's account file path <BR/>
 *	Course credit <BR/>
 */
class Server {
	private File indexFile;
	private ArrayList<Course> courseList;
	
	public void changeServer(String _path) {
		indexFile = new File(_path);
		if(!indexFile.exists() || indexFile.isDirectory()) {
			JDialog dialog = new ChangeServerPathDialog();
			dialog.setVisible(true);
		} else {
			System.setProperty("user.dir",_path.substring(0, _path.lastIndexOf('\\') + 1));
			new LoginPage().setVisible(true);
		}
	}

	public Server(String _path) {
		indexFile = new File(_path);
		if(!indexFile.exists() || indexFile.isDirectory()) {
			JDialog dialog = new ChangeServerPathDialog();
			dialog.setVisible(true);
		} else {
			new LoginPage().setVisible(true);
		}
	}
	

	/**
	 * Tests whether an account name and a password match.
	 * @param _account The account name.
	 * @param _password The password to be tested.
	 * @return Whether the password passes the test.
	 */
	public String findAccount(String _account, String _password){
		FastScanner scanner = null;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(indexFile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = scanner.nextToken();
		scanner.close();
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(path))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			String s1 = scanner.nextLine();
			if(s1 == null)
				break;
			String s2 = scanner.nextLine();
			if(s2 == null)
				break;
			String s3 = scanner.nextLine();
			if(s3 == null)
				break;
			if(s1.equals(_account) && s2.equals(_password)) {
				scanner.close();
				return s3;
			}
		}
		scanner.close();
		return null;
	}
	
	/**
	 * Get a password according to an account path.
	 * @param _account The account path of the account.
	 * @return The account's password.
	 */
	public String getPassword(String _account) {
		FastScanner scanner = null;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(indexFile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = scanner.nextToken();
		scanner.close();
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(path))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			String s1 = scanner.nextLine();
			if(s1 == null)
				break;
			String s2 = scanner.nextLine();
			if(s2 == null)
				break;
			String s3 = scanner.nextLine();
			if(s3 == null)
				break;
			if(s3.equals(_account)) {
				scanner.close();
				return s2;
			}
		}
		scanner.close();
		return null;
	}
	
	/**
	 * Create a person account according to the account path given.
	 * @param _accountPath The account path.
	 * @return The person object created.
	 */
	public Person createAccount(String _accountPath) {
		FastScanner scanner = null;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(_accountPath))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s;
		Person person;
		s = scanner.nextLine();
		if(s.equals("Student"))
			person = new Student(_accountPath, scanner.nextLine(), scanner.nextLine(), scanner.nextLine(),
					scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
		else if(s.equals("Professor"))
			person = new Professor(_accountPath, scanner.nextLine(), scanner.nextLine(), scanner.nextLine(),
					scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
		else
			person = new Administrator(_accountPath, scanner.nextLine(), scanner.nextLine());
		scanner.close();
		return person;
	}
	
	/**
	 * Change the password of the current account.
	 * @param newPassword The new password.
	 */
	public void changePassword(String newPassword) {
		FastScanner scanner = null;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(indexFile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = scanner.nextToken();
		scanner.close();
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(path))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FastWriter writer = null;
		try {
			writer = new FastWriter(new BufferedWriter(new FileWriter("server\\temp.dat")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			String s1 = scanner.nextLine();
			if(s1 == null)
				break;
			String s2 = scanner.nextLine();
			if(s2 == null)
				break;
			String s3 = scanner.nextLine();
			if(s3 == null)
				break;
			if(s3.equals(CourseSelectionSystem.getUsingAccount().getAccountPath()))
				s2 = newPassword;
			writer.writeln(s1);
			writer.writeln(s2);
			writer.writeln(s3);
		}
		scanner.close();
		writer.close();
		CopyFileUtil.renameFile("server\\temp.dat",path,true);
	}
	
	private ArrayList<Course> allCourses = null;
	public ArrayList<Course> getCourses() {
		FastScanner scanner = null;
		if(allCourses != null)
			return allCourses;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(indexFile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.nextLine();
		String _path = scanner.nextLine();
		scanner.close();
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(_path))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allCourses = new ArrayList<Course>();
		while(true) {
			String path = scanner.nextLine();
			if(path == null)
				break;
			allCourses.add(Course.getFromFile(path));
		}
		scanner.close();
		return allCourses;
	}
}

class FastScanner {
	private BufferedReader in;
	private StringTokenizer st;
	
	public FastScanner(BufferedReader in) {
		this.in = in;
	}
	
	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String nextLine() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean hasMoreTokens() {
		if(st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(st == null)
			return false;
		return st.hasMoreTokens();
	}
	
	public String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(nextToken());
	}

}

class FastWriter {
	private BufferedWriter out;
	private String content;
	
	public FastWriter(BufferedWriter out) {
		this.out = out;
		content = "";
	}
	
	public void writeln(String st) {
		content += st + '\n';
	}
	
	public void write(String st) {
		content += st;
	}
	
	public void close() {
		try {
			out.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}