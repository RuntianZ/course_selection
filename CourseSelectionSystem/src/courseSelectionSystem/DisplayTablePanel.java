package courseSelectionSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
class DisplayTablePanel extends JPanel {
	protected JTable table;
	protected ArrayList<TableCellRenderer> renderers;
	protected ArrayList<TableCellEditor> editors;

	/**
	 * A table that displays a list of Displayable objects in a JTable.
	 * @param width	The width of the panel.
	 * @param height The height of the panel.
	 * @param arg1 The list of objects.
	 */
	public DisplayTablePanel(int width, int height, ArrayList<? extends Displayable> arg1) {
		table = Displayable.getTable(arg1);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		renderers = new ArrayList<TableCellRenderer>();
		editors = new ArrayList<TableCellEditor>();
		if(!arg1.isEmpty())
			for(int i = 0; i < table.getColumnCount(); i++) {
				renderers.add(table.getCellRenderer(0,i));
				editors.add(table.getCellEditor(0,i));
			}
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width,height));
		JScrollPane jp = new JScrollPane(table);
		jp.setOpaque(false);
		jp.setPreferredSize(new Dimension(width, height));
		jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jp,BorderLayout.CENTER);
	}

	/**
	 * Adds a column to the table contained in the panel.
	 * @param columnName The name of the column.
	 * @param columnData The database.
	 */
	public void addColumn(Object columnName, Object[] columnData) {
		((DefaultTableModel)table.getModel()).addColumn(columnName, columnData);
		renderers.add(new DefaultTableCellRenderer());
		editors.add(new JLabelEditor());
		updateCells();
	}

	public void setRowHeight(int rowHeight) {
		table.setRowHeight(rowHeight);
	}

	public void setRowHeight(int row, int rowHeight) {
		table.setRowHeight(row, rowHeight);
	}

	public void setColumnWidth(int column, int columnWidth) {
		TableColumn c = table.getColumnModel().getColumn(column);
		c.setPreferredWidth(columnWidth);
		c.setMaxWidth(columnWidth);
		c.setMinWidth(columnWidth);
	}

	public int getColumnCount() {
		return table.getColumnCount();
	}

	/**
	 * Set the renderer for a column.
	 * @param arg0 The index of the column.
	 * @param arg1 The renderer.
	 */
	public void setColumnRenderer(int arg0, TableCellRenderer arg1) {
		renderers.set(arg0, arg1);
		updateCells();
	}

	private void updateCells() {
		for(int i = 0; i < renderers.size(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderers.get(i));	
			table.getColumnModel().getColumn(i).setCellEditor(editors.get(i));
		}
	}

	public void setModel(TableModel model) {
		table.setModel(model);
	}
	
	/**
	 * Set the table cell editor for a column.
	 * @param arg0 The index of the column.
	 * @param arg1 The table cell editor.
	 */
	public void setColumnTableCellEditor(int arg0, TableCellEditor arg1) {
		editors.set(arg0,arg1);
		updateCells();
	}

	public void setColumnWidth(int columnWidth) {
		for(int i = 0; i < table.getColumnCount(); i++)
			setColumnWidth(i, columnWidth);
	}

}

@SuppressWarnings("serial")
class DisplayPanel extends JPanel {
	protected DisplayBoard board;
	protected JPanel buttons;

	public DisplayPanel(int width, int height, Displayable arg1) {
		board = new DisplayBoard(arg1);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setSize(width,height);
		JScrollPane jp = new JScrollPane(board);
		jp.setBackground(Color.WHITE);
		jp.setSize(width, height - 80);
		jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jp,BorderLayout.CENTER);
		buttons = new JPanel();
		buttons.setSize(width,80);
		add(buttons,BorderLayout.SOUTH);
	}
}

class JPanelRenderer implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub
		return (JPanel)arg1;
	}
	
}

@SuppressWarnings("serial")
class JLabelRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object lbl,
			boolean isSelected, boolean hasFocus, int row, int column) {
		((JLabel)lbl).setHorizontalAlignment(SwingConstants.CENTER);
		return (JLabel)lbl;
	}
}

class JLabelEditor implements TableCellEditor {

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
			int column) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class JPanelEditor implements TableCellEditor {

	@Override
	public void addCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		return true;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldSelectCell(EventObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		return (JPanel)arg1;
	}
	
}
