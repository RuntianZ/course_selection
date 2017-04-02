package courseSelectionSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *	File list: <BR/>
 *	Name <BR/>
 *	Timetable (7 * 12 lines): If no courses, use null <BR/>
 */
class Classroom {
	private String name;

	@Override
	public String toString() {
		return name;
	}
	
	public static Classroom getFromFile(String path) {
		FastScanner scanner = null;
		try {
			scanner = new FastScanner(new BufferedReader(new FileReader(new File(path))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = scanner.nextLine();
		if(s == null)
			throw new NullPointerException();
		scanner.close();
		return new Classroom(s);
	}
	
	public Classroom(String name) {
		this.name = name;
	}
}

/**
 *	File list: <BR/>
 *	Name <BR/>
 *	Number of classrooms n <BR/>
 *	Classroom paths (n lines)
 */
class Building {
	
	private String name;
	
	@Override
	public String toString() {
		return name;
	}
}

