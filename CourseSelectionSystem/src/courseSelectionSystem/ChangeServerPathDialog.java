package courseSelectionSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
class ChangeServerPathDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ChangeServerPathDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChangeServerPathDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/warning.png")));
		setTitle(LanguageManager.ERROR);
		setBounds(100, 100, 422, 161);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setLocation((int)(toolkit.getScreenSize().getWidth()-getWidth())/2,(int)(toolkit.getScreenSize().getHeight()-getHeight())*2/5);
		contentPanel.setLayout(new FlowLayout());
		{
			JLabel lblNewLabel = new JLabel(LanguageManager.SERVERNOTFOUND);
			lblNewLabel.setBounds(62, 29, 288, 21);
			lblNewLabel.setFont(LanguageManager.getFont(Font.PLAIN, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton fileServerButton = new JButton(LanguageManager.DOMESTICSERVER + "...");
				fileServerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean changed = false;
						JFileChooser chooser = new JFileChooser();
					    FileNameExtensionFilter filter = new FileNameExtensionFilter(LanguageManager.SERVERFILE + "(*.svr)","svr");
					    chooser.setFileFilter(filter);
					    chooser.setDialogTitle(LanguageManager.CHOOSEASERVER);
					    chooser.setApproveButtonText(LanguageManager.OK);
					    int returnVal = chooser.showOpenDialog(new OpenDialog());
					    if(returnVal == JFileChooser.APPROVE_OPTION) {
					    	CourseSelectionSystem.server.changeServer(chooser.getSelectedFile().getPath());
					    	changed = true;
					    }
						if(changed)
							dispose();
					}
				});
				fileServerButton.setActionCommand("OK");
				buttonPane.add(fileServerButton);
				getRootPane().setDefaultButton(fileServerButton);
			}
			{
				JButton cancelButton = new JButton(LanguageManager.CANCEL);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				
				JButton onlineSeverButton = new JButton(LanguageManager.ONLINESERVER + "...");
				onlineSeverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				buttonPane.add(onlineSeverButton);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

@SuppressWarnings("serial")
class OpenDialog extends JDialog {
	public OpenDialog() {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChangeServerPathDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
	}
}
