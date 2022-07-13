package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import exception.*;
import model.Partition;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Sistemas Operativos";
    public static final String LANGUAGE_PANEL_TITLE = "Lenguaje";
    private AddProcessPanel addProcessPanel;
    private AddListProcessPanel listProcessPanel;
    private OptionsPanel optionsPanel;
    private JPanel mainPanel;
    private AddPartitionsPanel addPartitionsPanel;
    private AddPartitionReports addPartitionReports;

    public MainFrame(ActionListener listener){
        setTitle(TITLE);
        setIconImage(new ImageIcon(getClass().getResource(Constant.ICON_APP)).getImage());
        setUndecorated(true);
        setSize(900,600);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
       // getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        initComponents(listener);
    }

    private void initComponents(ActionListener listener){
        mainPanel = new JPanel(new GridLayout(1,1));
        initInitialPanel(listener);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void initInitialPanel(ActionListener listener){
        InitialPanel initialPanel = new InitialPanel(listener);        
        mainPanel.add(initialPanel);
    }

    public void initAddProcessPanel(ActionListener listener) {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        optionsPanel = new OptionsPanel(listener);
        optionsPanel.setBorder(BorderFactory.createTitledBorder(null,"Menú",  1, 1,
            Constant.FONT_NUNITO_TEXT));
        optionsPanel.setPreferredSize(new Dimension(300,500));
        mainPanel.add(optionsPanel, BorderLayout.WEST);

        addProcessPanel = new AddProcessPanel(listener);
        mainPanel.add(addProcessPanel, BorderLayout.CENTER);

        addPartitionsPanel = new AddPartitionsPanel(listener, "Lista de particiones");
        mainPanel.add(addPartitionsPanel, BorderLayout.EAST);
       
        mainPanel.repaint();
        mainPanel.updateUI();
        repaint();
    }

    public void initSimulationPanel(ActionListener listener, ArrayList<Partition> partitions) {
        mainPanel.removeAll();
        addPartitionReports = new AddPartitionReports(listener, partitions);
        mainPanel.add(addPartitionReports, BorderLayout.CENTER);
       
        mainPanel.repaint();
        mainPanel.updateUI();
        repaint();
    }

    public String[] getProcessData() throws EmptyTextFieldException, TimeInNumber, PossitiveValues {
    	return addProcessPanel.getProcessData();
    }

    public void windowTable(JPanel panel, String name){
        listProcessPanel.createScroll(panel, name);        
    }

    public void addToTable(ArrayList<Object[]> datas, ActionListener actionListener) {
    	initAddProcessPanel(actionListener);
		addProcessPanel.addRows(datas);
	}

    public void addRowToTable(Object[] data) {
		addProcessPanel.addRowToTable(data);
	}

    public void addToTablePartitions(ArrayList<Object[]> datas, ActionListener actionListener) {
        initAddProcessPanel(actionListener);
        addPartitionsPanel.addRows(datas);
    }

    public void addRowToTablePartitions(Object[] data) {
        addPartitionsPanel.addRowToTable(data);
    }

    public JPanel addTransitionsTable(String title, ArrayList<Object[]> list){
        return listProcessPanel.dataTable(title, list);
    }


}
