package courseSelectionSystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPasswordField;

abstract class Operation{
	
	abstract protected void run(ArrayList<Object> args);
	
	/**
	 * 
	 * @return Whether the current operation is available.
	 */
	abstract public boolean isAvailable();
	protected String name;
	
	public Operation(String _name) {
		name = _name;
	}
	
	/**
	 * Performs the operation.
	 * @throws IllegalOperationException
	 */
	public void operate(ArrayList<Object> args) throws IllegalOperationException {
		if(isAvailable())
			run(args);
		else
			throw new IllegalOperationException();
	}
	
	/**
	 * 
	 * @return The name of the operation.
	 */
	public String getName() {
		return name;
	}
	
}

/**
 *	An exception thrown when trying to perform an operation that is not available.
 */
class IllegalOperationException extends Exception {
	private static final long serialVersionUID = 1L;	 
}

class LoginOperation extends Operation {
	
	@Override
	protected void run(ArrayList<Object> args) {
		JLabel lbl = (JLabel)args.get(2);
		String account = ((JTextField)args.get(0)).getText();
		String password = String.valueOf(((JPasswordField)args.get(1)).getPassword());
		
		if(account == null || account.equals(""))
		{
			lbl.setText(LanguageManager.ACCOUNTLABELCANNOTBEEMPTY);
			return;
		}
		if(password == null || password.equals(""))
		{
			lbl.setText(LanguageManager.PASSWORDLABELCANNOTBEEMPTY);
			return;
		}
		String s = null;
		s = CourseSelectionSystem.server.findAccount(account, password);
		if(s == null)
			lbl.setText(LanguageManager.ACCOUNTORPASSWORDWRONG);
		else { 
			lbl.setText("");
			CourseSelectionSystem.setUsingAccount(CourseSelectionSystem.server.createAccount(s));
			((JFrame)args.get(3)).dispose();
			CourseSelectionSystem.createMainPage();
		}
	}

	@Override
	public boolean isAvailable() {
		return CourseSelectionSystem.getUsingAccount() == null;
	}
	
	/**
	 * Argument list: <BR/>
	 * args[0] - The account label. <BR/>
	 * args[1] - The password label. <BR/>
	 * args[2] - The information label. <BR/>
	 */
	public LoginOperation(String _name) {
		super(_name);
	}
	
}

class EnrollOperation extends Operation {

	/**
	 * args[0] - The course.
	 */
	@Override
	protected void run(ArrayList<Object> args) {
		((Student)CourseSelectionSystem.getUsingAccount()).changeCourseStatus((Course)args.get(0),
				new CourseStatus(CourseStatus.ENROLLED,(Course)args.get(0)));
		System.out.println("test");
		
		// TODO Auto-generated constructor stub	
	}

	@Override
	public boolean isAvailable() {
		return CourseSelectionSystem.getSelectionPossible();
	}

	public EnrollOperation(String _name) {
		super(_name);
	}

}

class QuitOperation extends Operation {
	
	@Override
	protected void run(ArrayList<Object> args) {
		
	}

	@Override
	public boolean isAvailable() {
		return (CourseSelectionSystem.getSelectionPossible() || CourseSelectionSystem.getQuitPossible());
	}
	
	public QuitOperation(String _name) {
		super(_name);
	}
	
}

class ViewInfoOperation extends Operation {
	public ViewInfoOperation(String _name) {
		super(_name);
	}

	@Override
	protected void run(ArrayList<Object> args) {
		CourseSelectionSystem.getMainPage().changePanel(new InformationPanel());
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
}

class ChangePanelOperation extends Operation {
	
	private JPanel panel;
	public ChangePanelOperation(String _name, JPanel _panel) {
		super(_name);
		panel = _panel;
	}

	@Override
	protected void run(ArrayList<Object> args) {
		CourseSelectionSystem.getMainPage().changePanel(panel);
		
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
	
}

class UpdatePhotoOperation extends Operation {

	public UpdatePhotoOperation(String _name) {
		super(_name);
	}

	@Override
	protected void run(ArrayList<Object> args) {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(LanguageManager.PHOTOFILES,"jpg","jpeg","png","bmp");
	    chooser.setFileFilter(filter);
	    chooser.setDialogTitle(LanguageManager.UPLOADPHOTO);
	    chooser.setApproveButtonText(LanguageManager.OK);
	    int returnVal = chooser.showOpenDialog(new OpenDialog());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	CourseSelectionSystem.getUsingAccount().updatePhoto(chooser.getSelectedFile().getPath());
	    }
	    CourseSelectionSystem.getMainPage().update();
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
	
}

class LogoutOperation extends Operation {

	public LogoutOperation(String _name) {
		super(_name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(ArrayList<Object> args) {
		CourseSelectionSystem.getMainPage().dispose();
		CourseSelectionSystem.setUsingAccount(null);
		new LoginPage().setVisible(true);
	}

	@Override
	public boolean isAvailable() {
		return (CourseSelectionSystem.getUsingAccount() != null);
	}
	
}

class ChangePasswordOperation extends Operation {

	public ChangePasswordOperation(String _name) {
		super(_name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Argument list: <BR/>
	 * args[0] - oldPassword
	 * args[1] - newPassword
	 * args[2] - confirmPassword
	 * args[3] - message
	 * args[4] - old password
	 */
	@Override
	protected void run(ArrayList<Object> args) {
		String s0 = String.valueOf(((JPasswordField)args.get(0)).getPassword());
		String s1 = String.valueOf(((JPasswordField)args.get(1)).getPassword());
		String s2 = String.valueOf(((JPasswordField)args.get(2)).getPassword());
		String s3 = (String)args.get(4);
		JLabel lbl = (JLabel)args.get(3);
		lbl.setFont(LanguageManager.getFont(Font.PLAIN,18));
		lbl.setForeground(Color.RED);
		if(!s0.equals(s3)) {
			lbl.setText(LanguageManager.OLDPASSWORDWRONG);
		} else if(s1.length() < 6 || s1.length() > 16) {
			lbl.setText(LanguageManager.WRONGNEWPASSWORDLENGTH);
		} else if(!s1.equals(s2)) {
			lbl.setText(LanguageManager.CONFIRMPASSWORDNOTMATCH);
		} else {
			CourseSelectionSystem.server.changePassword(s1);
			Thread t = new Thread(() -> {
				lbl.setText(LanguageManager.CHANGEPASSWORDSUCCESSFUL);
				CourseSelectionSystem.getMainPage().update();
			});
			new Thread(() -> {
				t.start();
				try {
					t.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CourseSelectionSystem.getMainPage().changePanel(new InformationPanel());
			}).start();
		}
	}

	@Override
	public boolean isAvailable() {
		return (CourseSelectionSystem.getUsingAccount() != null);
	}
	
}

class ViewCourseDescriptionOperation extends Operation {

	public ViewCourseDescriptionOperation(String _name) {
		super(_name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * args[0] - The course.
	 * args[1] - The original panel.
	 */
	@Override
	protected void run(ArrayList<Object> args) {
		CourseDescription c = ((Course)args.get(0)).getCourseDescription();
		CourseSelectionSystem.getMainPage().changePanel(new ViewCourseDescriptionPanel(c,((JPanel)args.get(1))));
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
	
	@SuppressWarnings("serial")
	private class ViewCourseDescriptionPanel extends DisplayPanel {
		public ViewCourseDescriptionPanel(Displayable arg1, JPanel arg2) {
			super(1500,800,arg1);
			buttons.setBorder(new EmptyBorder(5, 5, 5, 5));
			buttons.setBackground(Color.WHITE);
			buttons.setLayout(new FlowLayout());
			buttons.add(new OperationButton(new ChangePanelOperation(LanguageManager.RETURN,arg2),null));
		}
	}
	
}