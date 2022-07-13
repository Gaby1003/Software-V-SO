package views;

import controller.Events;
import exception.EmptyTextFieldException;
import exception.PossitiveValues;
import exception.TimeInNumber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreatePartition extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField  newNamProcess, size;
	private JPanel mainPanel;

	public CreatePartition(ActionListener listener) {
		setSize(700, 400);
		initComponents(listener);
		setLocationRelativeTo(null);
		setUndecorated(true);
		((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setVisible(true);
	}

	private void initComponents(ActionListener listener) {
		mainPanel = new JPanel();
		mainPanel.setBackground(Constant.COLOR_PINK);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		initialPanel(listener);
		mainPanel.setBorder(new EmptyBorder(0,20,0,20));
		add(mainPanel, BorderLayout.CENTER);

	}

	public void initialPanel(ActionListener listener) {
		mainPanel.removeAll();
		changeDataProcess(listener);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.repaint();
		mainPanel.updateUI();
		repaint();
	}


	public void changeDataProcess(ActionListener listener) {
		mainPanel.removeAll();

		JPanel panel = new JPanel(new GridLayout(2, 1, 40, 25));
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(120, 10, 100, 10));

		JLabel nameProcess = new JLabel("Nombre de la partición");
		nameProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
		nameProcess.setFont(Constant.FONT_NUNITO_TEXT);
		newNamProcess = new JTextField();


		JLabel sizeProcess = new JLabel("Tamaño de la partición");
		sizeProcess.setBorder(new EmptyBorder(10, 5, 10, 5));
		sizeProcess.setFont(Constant.FONT_NUNITO_TEXT);
		size = new JTextField();

		panel.add(nameProcess);
		panel.add(newNamProcess);
		panel.add(sizeProcess);
		panel.add(size);

		mainPanel.add(panel);
		mainPanel.add(addButtonNewProcess(listener, Events.ENTER_CREATE.toString()));

		mainPanel.repaint();
		mainPanel.updateUI();
		repaint();
	}

	public JPanel addButtonNewProcess(ActionListener listener, String command) {
		JPanel panel = new JPanel();

		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(0, 10, 10, 10));

		JButtonInformation addChanges = new JButtonInformation(10, 10, "A\u00f1adir cambios", Constant.COLOR_RED,
				Color.white, Constant.FONT_NUNITO);

		addChanges.setActionCommand(Events.CREATE_PARTITION.toString());
		addChanges.addActionListener(listener);

		JButtonInformation exit = new JButtonInformation(10, 10, "Regresar", Constant.COLOR_RED, Color.white,
				Constant.FONT_NUNITO);
		exit.setActionCommand(Events.EXIT_PARTITION.toString());
		exit.addActionListener(listener);

		panel.add(addChanges);
		panel.add(exit);
		return panel;
	}

	public String[] getInfo() throws EmptyTextFieldException, PossitiveValues, TimeInNumber {
		return new String[]{getNameProcess(), getSizeData()};
	}

	public String getNameProcess() throws EmptyTextFieldException {
		if(newNamProcess.getText().isEmpty()){
			throw new EmptyTextFieldException();
		}else{
			return newNamProcess.getText();
		}
	}

	public String getSizeData() throws EmptyTextFieldException, TimeInNumber, PossitiveValues{
		if(size.getText().isEmpty()){
			throw new EmptyTextFieldException();
		}else if((boolean)size.getText().matches("-?\\d+") !=true){
			throw new TimeInNumber();
		}else if(Integer.parseInt(size.getText()) <= 0){
			throw new PossitiveValues();
		}
		else{
			return size.getText();
		}
	}


}