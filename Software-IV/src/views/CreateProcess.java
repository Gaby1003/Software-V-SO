package views;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Events;
import exception.EmptyTextFieldException;
import exception.PossitiveValues;
import exception.TimeInNumber;

public class CreateProcess extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField newTimProcess, newNamProcess, size;
	private JComboBox  newBlocProcess, partition;
	private JPanel mainPanel;

	public CreateProcess(ActionListener listener, ArrayList<Object[]>datas) {
		setSize(700, 500);
		initComponents(listener, datas);
		setLocationRelativeTo(null);
		setUndecorated(true);
		((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setVisible(true);
	}

	private void initComponents(ActionListener listener, ArrayList<Object[]>datas) {
		mainPanel = new JPanel();
		mainPanel.setBackground(Constant.COLOR_PINK);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		initialPanel(listener, datas);
		mainPanel.setBorder(new EmptyBorder(0,20,0,20));
		add(mainPanel, BorderLayout.CENTER);
	}

	public void initialPanel(ActionListener listener, ArrayList<Object[]>datas) {
		mainPanel.removeAll();
		changeDataProcess(listener, datas);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.repaint();
		mainPanel.updateUI();
		repaint();
	}

	public void changeDataProcess(ActionListener listener, ArrayList<Object[]>datas) {
		mainPanel.removeAll();

		JPanel panel = new JPanel(new GridLayout(6, 1, 0, 15));
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(50, 10, 0, 10));

		JLabel nameProcess = new JLabel("Nombre del proceso");
		nameProcess.setBorder(new EmptyBorder(0, 5, 0, 5));
		nameProcess.setFont(Constant.FONT_NUNITO_TEXT);
		newNamProcess = new JTextField();

		JLabel newTimeProcess = new JLabel("Tiempo del proceso");
		newTimeProcess.setBorder(new EmptyBorder(0, 5, 0, 5));
		newTimeProcess.setFont(Constant.FONT_NUNITO_TEXT);
		newTimProcess = new JTextField();

		JLabel sizeProcess = new JLabel("Tamaño del proceso");
		sizeProcess.setFont(Constant.FONT_NUNITO_TEXT);
		sizeProcess.setBorder(new EmptyBorder(0, 5, 0, 5));
		size = new JTextField();

		JLabel newBlockProcess = new JLabel("Bloqueado");
		newBlockProcess.setFont(Constant.FONT_NUNITO_TEXT);
		newBlockProcess.setBorder(new EmptyBorder(0, 5, 0, 5));

		newBlocProcess = new JComboBox<String>();
		newBlocProcess.addItem("No");
		newBlocProcess.addItem("Si");
		UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.WHITE));

		JLabel partitionLb = new JLabel("Partición");
		partitionLb.setFont(Constant.FONT_NUNITO_TEXT);
		partitionLb.setBorder(new EmptyBorder(0, 5, 0, 5));
		partition = new JComboBox<String>();
		for (Object[] objects : datas) {
			partition.addItem(objects[0]);
		}
		UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.WHITE));

		panel.add(nameProcess);
		panel.add(newNamProcess);
		panel.add(newTimeProcess);
		panel.add(newTimProcess);
		panel.add(sizeProcess);
		panel.add(size);
		panel.add(newBlockProcess);
		panel.add(newBlocProcess);
		panel.add(partitionLb);
		panel.add(partition);

		mainPanel.add(panel);
		mainPanel.add(addButtonNewProcess(listener));

		mainPanel.repaint();
		mainPanel.updateUI();
		repaint();
	}

	public JPanel addButtonNewProcess(ActionListener listener) {
		JPanel panel = new JPanel();

		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(70, 10, 10, 10));

		JButtonInformation addChanges = new JButtonInformation(10, 10, "A\u00f1adir cambios", Constant.COLOR_RED,
				Color.white, Constant.FONT_NUNITO);

		addChanges.setActionCommand(Events.CREATE_PROCESS.toString());
		addChanges.addActionListener(listener);

		JButtonInformation exit = new JButtonInformation(10, 10, "Regresar", Constant.COLOR_RED, Color.white,
				Constant.FONT_NUNITO);
		exit.setActionCommand(Events.EXIT_PROCESS.toString());
		exit.addActionListener(listener);

		panel.add(addChanges);
		panel.add(exit);
		return panel;
	}

	public String[] getInfo() throws EmptyTextFieldException, PossitiveValues, TimeInNumber {
		return new String[] {getNameProcess(), getTimeProcess(), getSizeData(),
			newBlocProcess.getSelectedItem().toString(), partition.getSelectedItem().toString()};
	}

	public String getNameProcess() throws EmptyTextFieldException {
		if(newNamProcess.getText().isEmpty()){
			throw new EmptyTextFieldException();
		}else{
			return newNamProcess.getText();
		}
	}

	public String getTimeProcess() throws EmptyTextFieldException, TimeInNumber, PossitiveValues{
		if(newTimProcess.getText().isEmpty()){
			throw new EmptyTextFieldException();
		}else if((boolean)newTimProcess.getText().matches("-?\\d+") !=true){
			throw new TimeInNumber();
		}else if(Integer.parseInt(newTimProcess.getText()) <= 0){
			throw new PossitiveValues();
		}
		else{
			return newTimProcess.getText();
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