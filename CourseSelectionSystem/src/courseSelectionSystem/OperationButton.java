package courseSelectionSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

@SuppressWarnings("serial")
class OperationButton extends JButton {
	private ArrayList<Object> args;
	private Operation operation;
	
	/**
	 * Press the button.
	 */
	public void operate() {
		try {
			operation.operate(args);
		} catch (IllegalOperationException e1) {
			e1.printStackTrace();
		}
	}
	
	public OperationButton(Operation _operation, ArrayList<Object> _args) {
		super(_operation.getName());
		operation = _operation;
		args = _args;
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				operate();
			}
		});
	}
}
