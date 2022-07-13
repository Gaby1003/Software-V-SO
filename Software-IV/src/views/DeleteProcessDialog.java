package views;

import controller.Events;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteProcessDialog extends JDialog {

    private JComboBox idProcessC;
    private JPanel mainPanel;

    public DeleteProcessDialog(ActionListener listener, ArrayList<Object[]>datas) {
        setPreferredSize( new Dimension( 200, 500 ) );
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        initComponents(listener, datas);
    }

    private void initComponents(ActionListener listener, ArrayList<Object[]>datas) {
        mainPanel = new JPanel();
        initialPanel(listener, datas);
        mainPanel.setBorder(new EmptyBorder(0,20,0,20));
        add(mainPanel, BorderLayout.CENTER);
    }

    public void initialPanel(ActionListener listener, ArrayList<Object[]> datas) {
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

    public JPanel addButtonEditProcess(ActionListener listener) {
        JPanel panel = new JPanel();

        panel.setOpaque(false);

        JButtonInformation selectProcess = new JButtonInformation(10, 10, "Seleccionar", Constant.COLOR_RED,
                Color.white, Constant.FONT_NUNITO);

        selectProcess.setActionCommand(Events.ENTER_DELETE.toString());
        selectProcess.addActionListener(listener);

        JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_RED, Color.white,
                Constant.FONT_NUNITO);
        exit.setActionCommand(Events.EXIT_DIALOG.toString());
        exit.addActionListener(listener);

        panel.add(selectProcess);
        panel.add(exit);
        return panel;
    }
}
