package courseSelectionSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField accountField;
	private JPasswordField passwordField;


	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle(LanguageManager.LOGIN);
		setIconImage(Toolkit.getDefaultToolkit().getImage("pic\\SchoolLogo.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 517);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setLocation((int)(toolkit.getScreenSize().getWidth()-getWidth())/2,(int)(toolkit.getScreenSize().getHeight()-getHeight())*2/5);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel accountLabel = new JLabel(LanguageManager.ACCOUNT);
		accountLabel.setFont(LanguageManager.getFont(Font.PLAIN, 20));
		accountLabel.setBounds(206, 120, 88, 24);
		contentPane.add(accountLabel);
		
		accountField = new JTextField();
		accountField.setBounds(297, 120, 92, 24);
		contentPane.add(accountField);
		accountField.setColumns(10);
		
		JLabel titleLabel = new JLabel(LanguageManager.LOGINTOSYSTEM);
		titleLabel.setFont(LanguageManager.getFont(Font.PLAIN, 20));
		titleLabel.setBounds(195, 89, 200, 23);
		contentPane.add(titleLabel);
		
		JLabel passwordLabel = new JLabel(LanguageManager.PASSWORD);
		passwordLabel.setFont(LanguageManager.getFont(Font.PLAIN, 20));
		passwordLabel.setBounds(206, 157, 88, 23);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(297, 158, 92, 24);
		contentPane.add(passwordField);
		
		JLabel informationLabel = new JLabel("");
		informationLabel.setForeground(Color.RED);
		informationLabel.setFont(LanguageManager.getFont(Font.PLAIN, 15));
		informationLabel.setBounds(246, 248, 143, 27);
		contentPane.add(informationLabel);
		
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(accountField);
		args.add(passwordField);
		args.add(informationLabel);
		args.add(this);
		OperationButton loginButton = new OperationButton(new LoginOperation(LanguageManager.LOGIN), args);
		loginButton.setForeground(Color.BLACK);
		loginButton.setBackground(Color.ORANGE);
		loginButton.setBounds(257, 208, 73, 27);
		contentPane.add(loginButton);
		
		JLabel schoolLogoLabel = new JLabel();
		Image schoolLogo = null;
		try {
			schoolLogo = ImageIO.read(new FileInputStream("pic\\SchoolLogo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolLogo = schoolLogo.getScaledInstance(180,180,Image.SCALE_SMOOTH);
		schoolLogoLabel.setBounds(14, 13, 180, 180);
		schoolLogoLabel.setIcon(new ImageIcon(schoolLogo));
		contentPane.add(schoolLogoLabel);
		
		JLabel newsLabel = new JLabel();
		Image news = null;
		try {
			news = ImageIO.read(new FileInputStream("pic\\news.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		news = news.getScaledInstance(180,127,Image.SCALE_SMOOTH);
		newsLabel.setBounds(14, 265, 180, 127);
		newsLabel.setIcon(new ImageIcon(news));
		contentPane.add(newsLabel);
		
		JLabel newsInfoLabel = new JLabel("Our school idols");
		newsInfoLabel.setFont(LanguageManager.getFont(Font.PLAIN, 16));
		newsInfoLabel.setBounds(14, 405, 180, 34);
		contentPane.add(newsInfoLabel);
		passwordField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					loginButton.operate();
				else
					informationLabel.setText("");
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
		});
		
		accountField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					loginButton.operate();
				else
					informationLabel.setText("");
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
		});
		
	}
}
