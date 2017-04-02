package courseSelectionSystem;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
class PhotoLabel extends JLabel {
	
	public static final String DEFAULT_PHOTO = "pic\\defaultPhoto.jpg";
	public static final int PHOTO_WIDTH = 120;
	public static final int PHOTO_HEIGHT = 168;
	
	private Image image;
	private String path;
	
	/**
	 * Place the label at (x,y) with fixed length.
	 * @param x The x coordinate of the label.
	 * @param y The y coordinate of the label.
	 */
	public void setBounds(int x, int y) {
		setBounds(x, y, PHOTO_WIDTH, PHOTO_HEIGHT);
	}
	
	private void createLabel() {
		image = image.getScaledInstance(PHOTO_WIDTH,PHOTO_HEIGHT,Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(image));
		setText("");
	}
	
	public PhotoLabel() {
		path = DEFAULT_PHOTO;
		try {
			image = ImageIO.read(new FileInputStream(DEFAULT_PHOTO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createLabel();
	}
	
	public PhotoLabel(String _path) {
		path = _path;
		try {
			image = ImageIO.read(new FileInputStream(_path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createLabel();
	}
	
	/**
	 * 
	 * @return The path of the photo.
	 */
	public String getPath() {
		return path;
	}
	
	public void changePhoto(String _path) {
		path = _path;
		try {
			image = ImageIO.read(new FileInputStream(_path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createLabel();
	}
}
