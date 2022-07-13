package views;

import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Events;

public class DialogException extends JDialog {

	private static final long serialVersionUID = 1L;

	public DialogException(ActionListener listener, String text) {
		setSize(500, 300);
		initComponents(listener, text);	
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setUndecorated(true);
		//((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Constant.COLOR_GRAY));
		setVisible(true);
	}

	public void initComponents(ActionListener listener, String text) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		addText(listener, text);
		addButtonOptions(listener, text);
	}

	private JPanel addText(ActionListener listener, String text) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		JLabel nameException = new JLabel(text);
		nameException.setFont(Constant.FONT_NUNITO_2);
		nameException.setBorder(new EmptyBorder(60, 60, 20, 40));

		panel.add(nameException);
		add(panel, BorderLayout.CENTER);
		return panel;
	}

	private void addButtonOptions(ActionListener listener, String text) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		
		JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_RED_2, Color.white,
				Constant.FONT_NUNITO);
		exit.setActionCommand(Events.EXIT_DIALOG_EXCEPTION.toString());
		exit.addActionListener(listener);
		exit.setBorder(new EmptyBorder(10, 50, 10, 50));

		panel.add(exit);
		add(panel, BorderLayout.SOUTH);
	}
}