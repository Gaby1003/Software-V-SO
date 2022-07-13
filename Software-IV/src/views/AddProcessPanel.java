package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import exception.*;

public class AddProcessPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final String PROCESS_PANEL_TITLE = "Procesos";
    public static final String PROCESS_LIST_PANEL_TITLE = "Lista de procesos";
    private AddDataProcess variablesPanel;
    private AddSymbolsPanel symbolsPanel;

    public AddProcessPanel(ActionListener listener){
      setBackground(Color.WHITE);
      setLayout(new GridLayout(1,1));

      symbolsPanel = new AddSymbolsPanel(listener, PROCESS_LIST_PANEL_TITLE);
      add(symbolsPanel);
    }
    
    public String [] getProcessData()  throws EmptyTextFieldException,TimeInNumber, PossitiveValues{
    	return variablesPanel.getProcessData();
    }

    public void addRowToTable(Object[] data) {
		  symbolsPanel.addRowToTable(data);
	  }

    public void addRows(ArrayList<Object[]> datas) {
		  symbolsPanel.addRows(datas);
	  }
}
