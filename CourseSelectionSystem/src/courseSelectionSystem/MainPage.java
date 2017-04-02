package courseSelectionSystem;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;

/**
 * The frame of the system.
 */
@SuppressWarnings("serial")
class MainPage extends JFrame {
	
	private JPanel panel;

	
	public MainPage() {
		setTitle(LanguageManager.SYSTEMTITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setIconImage(Toolkit.getDefaultToolkit().getImage("pic\\SchoolLogo.jpg"));
		setLayout(new BorderLayout());
		panel = new MainPanel();
		setSize(panel.getWidth(), panel.getHeight());
		setLocation((int)(toolkit.getScreenSize().getWidth()-getWidth())/2,(int)(toolkit.getScreenSize().getHeight()-getHeight())*2/5);
		add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * Change the current panel. Resize the frame if needed.
	 * @param _panel The new panel.
	 */
	public void changePanel(JPanel _panel) {
		int x = _panel.getWidth();
		int y = _panel.getHeight();
		int x0 = panel.getWidth();
		int y0 = panel.getHeight();
		remove(panel);
		if(x != x0 || y != y0)
			pageResize(x,y);
		panel = _panel;
		add(panel, BorderLayout.CENTER);
		panel.requestFocus();
		update();
	}
	
	public void pageResize(int width, int height) {
		int x = getX() + getWidth() / 2;
		int y = getY() + getHeight() / 2;
		setSize(width, height);
		int x0 = x - width / 2;
		if(x0 < 10)
			x0 = 10;
		int y0 = y - height / 2;
		if(y0 < 10)
			y0 = 10;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		if(x0 > (int)toolkit.getScreenSize().getWidth() - 20)
			x0 = (int)toolkit.getScreenSize().getWidth() - 20;
		if(y0 > (int)toolkit.getScreenSize().getHeight() * 7 / 8)
			y0 = (int)toolkit.getScreenSize().getHeight() * 7 / 8;
		setLocation(x0, y0);
	}
	
	/**
	 * Update the frame.
	 */
	public void update() {
		((JPanel)getContentPane()).updateUI();
		repaint();
	}
	
	/**
	 * 
	 * @return The current panel.
	 */
	public JPanel getPanel() {
		return panel;
	}
}

@SuppressWarnings("serial")
class ChangePasswordPanel extends JPanel {
	private JPasswordField oldPassword, newPassword, confirmPassword;
	private JLabel strength1, strength2, strength3, oldicon, newicon, confirmicon, message;
	private ImageIcon icon;
	private String old;
	private OperationButton btn;
	
	public ChangePasswordPanel(InformationPanel arg) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setLayout(new VFlowLayout(VFlowLayout.CENTER));
		setSize(500,500);
		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.setPreferredSize(new Dimension(500,50));
			p.setOpaque(false);
			p.setAlignmentY(SwingConstants.CENTER);  
			JLabel lbl = new JLabel(LanguageManager.OLDPASSWORD);
			lbl.setFont(LanguageManager.getFont(Font.PLAIN,20));
			p.add(lbl);
			oldPassword = new JPasswordField(20);
			oldPassword.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					check();
					updateUI();
					CourseSelectionSystem.getMainPage().update();
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						btn.operate();
				}
			});
			p.add(oldPassword);
			oldicon = new JLabel();
			oldicon.setPreferredSize(new Dimension(40,40));
			p.add(oldicon);
			add(p);
		}
		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.setPreferredSize(new Dimension(500,50));
			p.setOpaque(false);
			p.setAlignmentY(SwingConstants.CENTER);  
			JLabel lbl = new JLabel(LanguageManager.NEWPASSWORD);
			lbl.setFont(LanguageManager.getFont(Font.PLAIN,20));
			p.add(lbl);
			newPassword = new JPasswordField(20);
			newPassword.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					check();
					updateUI();
					CourseSelectionSystem.getMainPage().update();
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						btn.operate();
				}
			});
			p.add(newPassword);
			newicon = new JLabel();
			newicon.setPreferredSize(new Dimension(40,40));
			p.add(newicon);
			add(p);
		}
		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.setPreferredSize(new Dimension(500,50));
			p.setOpaque(false);
			p.setAlignmentY(SwingConstants.CENTER);  
			JLabel lbl = new JLabel(LanguageManager.CONFIRMPASSWORD);
			lbl.setFont(LanguageManager.getFont(Font.PLAIN,20));
			p.add(lbl);
			confirmPassword = new JPasswordField(20);
			confirmPassword.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					check();
					updateUI();
					CourseSelectionSystem.getMainPage().update();
					if(e.getKeyCode() == KeyEvent.VK_ENTER)
						btn.operate();
				}
			});
			p.add(confirmPassword);
			confirmicon = new JLabel();
			confirmicon.setPreferredSize(new Dimension(40,40));
			p.add(confirmicon);
			add(p);
		}
		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.setPreferredSize(new Dimension(500,50));
			p.setOpaque(false);
			p.setAlignmentY(SwingConstants.CENTER);  
			JLabel lbl = new JLabel(LanguageManager.PASSWORDSTRENGTH);
			lbl.setFont(LanguageManager.getFont(Font.PLAIN,18));
			p.add(lbl);
			strength1 = new JLabel();
			strength1.setPreferredSize(new Dimension(100,20));
			strength1.setOpaque(true);
			p.add(strength1);
			strength2 = new JLabel();
			strength2.setPreferredSize(new Dimension(100,20));
			strength2.setOpaque(true);
			p.add(strength2);
			strength3 = new JLabel();
			strength3.setPreferredSize(new Dimension(100,20));
			strength3.setOpaque(true);
			p.add(strength3);
			add(p);
		}
		{
			Image image = null;
			try {
				image = ImageIO.read(new FileInputStream("pic\\ok.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			image = image.getScaledInstance(40,40,Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		}
		setStrength();
		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.setPreferredSize(new Dimension(500,50));
			p.setOpaque(false);
			p.setAlignmentY(SwingConstants.CENTER); 
			message = new JLabel();
			p.add(message);
			add(p);
		}
		old = CourseSelectionSystem.server.getPassword(CourseSelectionSystem.getUsingAccount().getAccountPath());
		if(old == null)
			throw new NullPointerException();
		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.setAlignmentY(SwingConstants.CENTER);
			p.setPreferredSize(new Dimension(500,50));
			p.setOpaque(false);
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(oldPassword);
			args.add(newPassword);
			args.add(confirmPassword);
			args.add(message);
			args.add(old);
			btn = new OperationButton(new ChangePasswordOperation(LanguageManager.OK),args);
			p.add(btn);
			JButton btn1 = new OperationButton(new ChangePanelOperation(LanguageManager.CANCEL,arg), null);
			btn1.addActionListener(e -> {
				oldPassword.setText("");
				newPassword.setText("");
				confirmPassword.setText("");
				check();
				message.setText("");
			});
			p.add(btn1);
			add(p);
		}
	}
	
	@Override
	public void requestFocus() {
		oldPassword.requestFocus();
	}
	
	private void setStrength() {
		boolean[] p = new boolean[3];
		String s = String.valueOf(newPassword.getPassword());
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
				p[0] = true;
			else if((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'))
				p[1] = true;
			else
				p[2] = true;
		}
		int count = 0;
		for(int i = 0; i < 3; i++)
			if(p[i])
				count++;
		switch(count) {
		case 0:
			strength1.setBackground(Color.LIGHT_GRAY);
			strength2.setBackground(Color.LIGHT_GRAY);
			strength3.setBackground(Color.LIGHT_GRAY);
			break;
		case 1:
			strength1.setBackground(Color.RED);
			strength2.setBackground(Color.LIGHT_GRAY);
			strength3.setBackground(Color.LIGHT_GRAY);
			break;
		case 2:
			strength1.setBackground(Color.YELLOW);
			strength2.setBackground(Color.YELLOW);
			strength3.setBackground(Color.LIGHT_GRAY);
			break;
		case 3:
			strength1.setBackground(Color.GREEN);
			strength2.setBackground(Color.GREEN);
			strength3.setBackground(Color.GREEN);
		}
	}
	
	private void check() {
		setStrength();
		String s = String.valueOf(oldPassword.getPassword());
		oldicon.setIcon(null);
		newicon.setIcon(null);
		confirmicon.setIcon(null);
		if(!s.equals(old))
			return;
		oldicon.setIcon(icon);
		s = String.valueOf(newPassword.getPassword());
		if(s.length() < 6 || s.length() > 16)
			return;
		newicon.setIcon(icon);
		String s1 = String.valueOf(confirmPassword.getPassword());
		if(s1.equals(s))
			confirmicon.setIcon(icon);
	}
}

/**
 *	A panel that shows the information of the current user.
 */
@SuppressWarnings("serial")
class InformationPanel extends DisplayPanel {

	public InformationPanel() {
		super(500,500,CourseSelectionSystem.getUsingAccount());
		buttons.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttons.setBackground(Color.WHITE);
		buttons.setLayout(new FlowLayout());
		buttons.add(new OperationButton(new UpdatePhotoOperation(LanguageManager.CHANGEPHOTO),null));
		buttons.add(new OperationButton(new ChangePanelOperation(LanguageManager.CHANGEPASSWORD,new ChangePasswordPanel(this)), null));
		buttons.add(new OperationButton(new ChangePanelOperation(LanguageManager.RETURN,new MainPanel()), null));
	}
}

/**
 *	The main panel of the system.
 */
@SuppressWarnings("serial")
class MainPanel extends JPanel {
	private JLabel broadcast;
	private JPanel buttons;
	public static final String BROADCAST_PATH = "pic\\broadcast.jpg";
	
	public MainPanel() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setSize(500,500);
		broadcast = new JLabel();
		Image image = null;
		try {
			image = ImageIO.read(new FileInputStream(BROADCAST_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = image.getScaledInstance(320,450,Image.SCALE_SMOOTH);
		broadcast.setIcon(new ImageIcon(image));
		broadcast.setSize(320,450);
		buttons = new JPanel();
		buttons.setLayout(new VFlowLayout());
		buttons.setSize(175,450);
		Iterator<Operation> it = CourseSelectionSystem.getUsingAccount().operationIterator();
		{
			JLabel lbl = new JLabel(LanguageManager.WELCOME);
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setFont(LanguageManager.getFont(Font.PLAIN, 18));
			buttons.add(lbl);
		}
		{
			JLabel lbl = new JLabel(CourseSelectionSystem.getUsingAccount().getName());
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setFont(LanguageManager.getFont(Font.PLAIN, 18));
			buttons.add(lbl);
		}
		{
			
			JLabel lbl = new JLabel(LanguageManager.POSSIBLEOPERATION);
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setFont(LanguageManager.getFont(Font.BOLD, 20));
			buttons.add(lbl);
		}
		while(it.hasNext()) {
			Operation op = it.next();
			if(op.isAvailable())
				buttons.add(new OperationButton(op, null));
		}
		add(broadcast,BorderLayout.WEST);
		add(buttons,BorderLayout.EAST);
	}
}