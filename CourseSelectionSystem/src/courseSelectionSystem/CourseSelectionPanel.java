package courseSelectionSystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * This is the panel for students to select courses.
 */
@SuppressWarnings("serial")
class CourseSelectionPanel extends JPanel {
	private DisplayTablePanel all, chosen;
	private ArrayList<Course> allCourses, enrolledCourses;
	private int credit;

	private void prepareCourses() {
		allCourses = new ArrayList<Course>();
		ArrayList<Course> _courses = CourseSelectionSystem.server.getCourses();
		for(int i = 0; i < _courses.size(); i++)
			if(!((Student)CourseSelectionSystem.getUsingAccount()).searchCourse(_courses.get(i)))
				allCourses.add(_courses.get(i));
		enrolledCourses = ((Student)CourseSelectionSystem.getUsingAccount()).getEnrolled();
		credit = 0;
		for(Course c : enrolledCourses)
			credit += c.getCredit();
	}

	public CourseSelectionPanel() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setSize(1500,800);
		setLayout(new VFlowLayout());
		prepareCourses();
		all = new DisplayTablePanel(1500,500,allCourses);
		chosen = new DisplayTablePanel(1500,200,enrolledCourses);
		{
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(1500,40));
			p.setLayout(new FlowLayout(FlowLayout.RIGHT));
			p.setOpaque(false);
			p.add(new OperationButton(new ChangePanelOperation(LanguageManager.RETURN,new MainPanel()), null));
			add(p);
		}  
		if(allCourses.isEmpty())
			all.addColumn(LanguageManager.OPERATION,null);
		else {
			Object[] columnData = new Object[allCourses.size()];
			for(int i = 0; i < allCourses.size(); i++) {
				ArrayList<Object> arg = new ArrayList<Object>();
				arg.add(allCourses.get(i));
				arg.add(this);
				ArrayList<Object> arg1 = new ArrayList<Object>();
				arg1.add(allCourses.get(i));
				JPanel p = new JPanel();
				p.setLayout(new FlowLayout());
				p.add(new OperationButton(new ViewCourseDescriptionOperation(LanguageManager.VIEWCOURSEDESCRIPTION),arg));
				p.add(new OperationButton(new EnrollOperation(LanguageManager.ENROLL),arg1));
				columnData[i] = p;
			}
			int n = all.getColumnCount();
			all.addColumn(LanguageManager.OPERATION,columnData);
			
			all.setColumnRenderer(n,new JPanelRenderer());
			all.setColumnTableCellEditor(n,new JPanelEditor());
		}
		all.setRowHeight(40);
		all.setColumnWidth(0,80);
		all.setColumnWidth(2,80);
		all.setColumnWidth(5,30);
		all.setColumnWidth(7,80);
		all.setColumnWidth(8,80);
		add(all);
		{
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(1500,40));
			p.setLayout(new FlowLayout(FlowLayout.LEFT));
			p.setOpaque(false);
			JLabel lbl1 = new JLabel(LanguageManager.ENROLLEDCOURSES);
			lbl1.setFont(LanguageManager.getFont(Font.BOLD,20));
			lbl1.setOpaque(false);
			lbl1.setPreferredSize(new Dimension(300,40));
			p.add(lbl1);
			JLabel lbl2 = new JLabel(LanguageManager.ENROLLEDCREDIT);
			lbl2.setFont(LanguageManager.getFont(Font.PLAIN,18));
			lbl2.setOpaque(false);
			lbl2.setForeground(Color.RED);
			lbl2.setPreferredSize(new Dimension(100,40));
			p.add(lbl2);
			JLabel lbl3 = new JLabel();
			lbl3.setFont(LanguageManager.getFont(Font.PLAIN,18));
			lbl3.setOpaque(false);
			lbl3.setPreferredSize(new Dimension(100,40));
			lbl3.setText(credit + "/" + ((Student)CourseSelectionSystem.getUsingAccount()).getCreditLimit());
			p.add(lbl3);
			add(p);
		}
		add(chosen);
	}

}
