package courseSelectionSystem;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

class DisplayContent{
	private String category;
	private JComponent content;
	private TableCellRenderer renderer;
	private TableCellEditor editor;

	public DisplayContent(String _category, JComponent _content, TableCellRenderer _renderer, TableCellEditor _editor) {
		category = _category;
		content = _content;
		renderer = _renderer;
		editor = _editor;
	}

	public DisplayContent(String _category, String _content) {
		category = _category;
		content = new JLabel(_content);
		content.setFont(LanguageManager.getFont(Font.PLAIN, 16));
		content.setOpaque(false);
		renderer = new JLabelRenderer();
		editor = new JLabelEditor();
		((DefaultTableCellRenderer)renderer).setHorizontalAlignment(SwingConstants.CENTER);
	}


	public TableCellRenderer getRenderer() {
		return renderer;
	}

	public TableCellEditor getEditor() {
		return editor;
	}
	
	public JComponent getContent() {
		return content;
	}

	public void setContent(JComponent _content) {
		content = _content;
	}

	public String getCategory() {
		return category;
	}
	
	
}

abstract class Displayable{
	protected ArrayList<DisplayContent> contents;

	public Displayable() {
		contents = new ArrayList<DisplayContent>();
	}

	/**
	 * Clear all the contents contained in the object.
	 */
	public void clearContents() {
		contents = new ArrayList<DisplayContent>();
	}

	Iterator<DisplayContent> iterator() {
		return contents.iterator();
	}

	/**
	 * Adds a DisplayContent to the current object.
	 * @param _category The category of the DisplayContent.
	 * @param _content The content of the DisplayContent.
	 */
	protected void add(String _category,JComponent _content, TableCellRenderer _renderer, TableCellEditor _editor) {
		contents.add(new DisplayContent(_category, _content, _renderer, _editor));
	}

	/**
	 * Adds a DisplayContent to the current object. The method will automatically convert the String into a JTextArea.
	 * @param _category The category of the DisplayContent.
	 * @param _content The content of the DisplayContent.
	 */
	protected void add(String _category, String _content) {
		contents.add(new DisplayContent(_category, _content));
	}

	/**
	 * Return the content according to the given category. Return null if the category is not found.
	 * @param _category The category name.
	 * @return The content of the category. Null if not found.
	 */
	public JComponent getContent(String _category) {
		for(DisplayContent c : contents)
			if(c.getCategory().equals(_category))
				return c.getContent();
		return null;
	}
	

	/**
	 * Prepare the object before being displayed. It is highly recommended that the first statement
	 * should be clearContents().
	 */
	abstract public void displayPrepare();

	/**
	 * This method creates a JTable that lists a series of Displayable object. <BR/>
	 * It should be noted that the table is created according to the first object in the list. If any
	 * object in the list fails to possess a category the first object possesses, an exception will be thrown.
	 * @param list The list to be displayed.
	 * @return A JTable displaying the list.
	 * @throws IllegalArgumentException If any object in the list fails to possess a category that the first
	 * object possesses.
	 */
	@SuppressWarnings("serial")
	public static JTable getTable(ArrayList<? extends Displayable> list) throws IllegalArgumentException {
		if(list.isEmpty())
			return new JTable();
		ArrayList<String> st = new ArrayList<String>();
		Object[][] rowData;
		Object[] columnNames;
		int num = 0;
		list.get(0).displayPrepare();
		Displayable d = list.get(0);
		Iterator<DisplayContent> ite = d.iterator();
		while(ite.hasNext()) {
			DisplayContent c = ite.next();
			st.add(c.getCategory());
			num++;
		}
		columnNames = st.toArray();
		rowData = new Object[list.size()][num];
		for(int i = 0; i < list.size(); i++) {
			if(i > 0)
				list.get(i).displayPrepare();
			for(int j = 0; j < num; j++) {
				rowData[i][j] = list.get(i).getContent(st.get(j));
				if(rowData[i][j] == null)
					throw new IllegalArgumentException();
			}
		}
		/*JTable ans = new JTable(new DefaultTableModel(rowData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});*/
		JTable ans = new JTable(new DefaultTableModel(rowData,columnNames));

		ite = d.iterator();
		for(int i = 0; i < num; i++) {
			DisplayContent c = ite.next();
			ans.getColumnModel().getColumn(i).setCellRenderer(c.getRenderer());
			ans.getColumnModel().getColumn(i).setCellEditor(c.getEditor());
		}
		return ans;
	}

}