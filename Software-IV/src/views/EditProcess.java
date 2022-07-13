package views;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Events;
import exception.*;

public class EditProcess extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField newTimProcess, newNamProcess,size;
	private JComboBox idProcessC, newBlocProcess, partition;
	private JPanel mainPanel;

	public EditProcess(ActionListener listener, ArrayList<Object[]>data) {
		setSize(700, 500);
		initComponents(listener, data);
		setLocationRelativeTo(null);
		setUndecorated(true);
		((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setVisible(true);
	}

	private void initComponents(ActionListener listener, ArrayList<Object[]>datas) {
		mainPanel = new JPanel();
		mainPanel.setBackground(Constant.COLOR_PINK);
		initialPanel(listener, datas);
		mainPanel.setBorder(new EmptyBorder(0,20,0,20));
		add(mainPanel, BorderLayout.CENTER);
	}

	public void initialPanel(ActionListener listener, ArrayList<Object[]>datas) {
		mainPanel.removeAll();
		optionProcess(listener, datas);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.repaint();
		mainPanel.updateUI();
		repaint();
	}

	public void optionProcess(ActionListener listener, ArrayList<Object[]>datas){
		mainPanel.removeAll();

		JPanel panel = new JPanel(new GridLayout(4, 1, 20, 20));
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(150, 0, 0, 0));

		JLabel nameProcess = new JLabel("Nombre del proceso");
		nameProcess.setBorder(new EmptyBorder(0, 40, 0, 40));

		idProcessC = new JComboBox<String>();
		for (Object[] objects : datas) {
			idProcessC.addItem(objects[0]);
		}

		idProcessC.setBorder(new EmptyBorder(0, 40, 0, 40));

		UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.WHITE));

		panel.add(nameProcess);
		panel.add(idProcessC);
		panel.add(addButtonEditProcess(listener));
		mainPanel.add(panel);

		mainPanel.repaint();
		mainPanel.updateUI();
		repaint();
	}


	public void changeDataProcess(ActionListener listener, ArrayList<Object[]>datas, Object[] infoProcess) {
		mainPanel.removeAll();

		JPanel panel = new JPanel(new GridLayout(6, 1, 0, 15));
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(50, 10, 0, 10));

		JLabel nameProcess = new JLabel("Nuevo nombre del proceso");
		nameProcess.setBorder(new EmptyBorder(0, 10, 0, 10));

		newNamProcess = new JTextField();
		newNamProcess.setText(String.valueOf(infoProcess[0]));

		JLabel newTimeProcess = new JLabel("Nuevo tiempo del proceso");
		newTimeProcess.setBorder(new EmptyBorder(0, 10, 0, 10));
		newTimProcess = new JTextField();
		newTimProcess.setText(String.valueOf(infoProcess[2]));

		JLabel newSize = new JLabel("Nuevo tamaño del proceso");
		newSize.setBorder(new EmptyBorder(0, 10, 0, 10));
		size = new JTextField();
		size.setText(String.valueOf(infoProcess[2]));

		JLabel newBlockProcess = new JLabel("Nuevo estado de bloqueado del proceso");
		newBlockProcess.setBorder(new EmptyBorder(0, 10, 0, 10));

		newBlocProcess = new JComboBox<String>();
		newBlocProcess.addItem("Si");
		newBlocProcess.addItem("No");
		newBlocProcess.setSelectedItem(infoProcess[3].equals(true) ? "Si" : "No");

		UIManager.put("ComboBox.background",new javax.swing.plaf.ColorUIResource(Color.WHITE));

		JLabel partitionLb = new JLabel("Nueva partición del proceso");
		partitionLb.setBorder(new EmptyBorder(0, 10, 0, 10));
		partition = new JComboBox<String>();
		for (Object[] objects : datas) {
			partition.addItem(objects[0]);
		}

		panel.add(nameProcess);
		panel.add(newNamProcess);
		panel.add(newTimeProcess);
		panel.add(newTimProcess);
		panel.add(newSize);
		panel.add(size);
		panel.add(newBlockProcess);
		panel.add(newBlocProcess);
		panel.add(partitionLb);
		panel.add(partition);

		mainPanel.add(panel);
		mainPanel.add(addButtonNewProcess(listener, Events.ADD_CHANGES.toString()));

		mainPanel.repaint();
		mainPanel.updateUI();
		repaint();
	}

	public JPanel addButtonNewProcess(ActionListener listener, String command) {
		JPanel panel = new JPanel();

		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(70, 10, 10, 10));

		JButtonInformation addChanges = new JButtonInformation(10, 10, "A\u00f1adir cambios", Constant.COLOR_RED,
				Color.white, Constant.FONT_NUNITO);

		addChanges.setActionCommand(Events.ADD_CHANGES.toString());
		addChanges.addActionListener(listener);

		JButtonInformation exit = new JButtonInformation(10, 10, "Regresar", Constant.COLOR_RED, Color.white,
				Constant.FONT_NUNITO);
		exit.setActionCommand(Events.BACK.toString());
		exit.addActionListener(listener);

		panel.add(addChanges);
		panel.add(exit);
		return panel;
	}

	public JPanel addButtonEditProcess(ActionListener listener) {
		JPanel panel = new JPanel();

		panel.setOpaque(false);

		JButtonInformation selectProcess = new JButtonInformation(10, 10, "Seleccionar", Constant.COLOR_RED,
				Color.white, Constant.FONT_NUNITO);

		selectProcess.setActionCommand(Events.ENTER_EDIT.toString());
		selectProcess.addActionListener(listener);

		JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_RED, Color.white,
				Constant.FONT_NUNITO);
		exit.setActionCommand(Events.EXIT_DIALOG_EDIT.toString());
		exit.addActionListener(listener);

		panel.add(selectProcess);
		panel.add(exit);
		return panel;
	}

	public String getNameProcess(){
		return idProcessC.getSelectedItem().toString();
	}


	public String[] getTimeProcess() throws TimeInNumber, PossitiveValues {
		if((boolean)newTimProcess.getText().matches("-?\\d+") !=true){
			throw new TimeInNumber();
		}else if(Integer.parseInt(newTimProcess.getText()) <= 0){
			throw new PossitiveValues();
		}else{
			return new String[] {idProcessC.getSelectedItem().toString(), newTimProcess.getText()};
		}
	}

	public String [] getBlockProcess(){
		return new String[] {idProcessC.getSelectedItem().toString(),
				newBlocProcess.getSelectedItem().toString()};
	}


	public String[] getProcessData() throws EmptyTextFieldException, PossitiveValues, TimeInNumber  {
		String[] datas = {getNameInfo(), getTimeInfo(), getSizeInfo(),
				newBlocProcess.getSelectedItem().toString()};
		return datas;
	}

	public String getNameInfo() throws EmptyTextFieldException {
		if(newNamProcess.getText().isEmpty()){
			throw new EmptyTextFieldException();
		}else{
			return newNamProcess.getText().toUpperCase();
		}
	}

	public String getTimeInfo() throws EmptyTextFieldException, TimeInNumber, PossitiveValues{
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

	public String getSizeInfo() throws EmptyTextFieldException, TimeInNumber, PossitiveValues{
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