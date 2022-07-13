package views;

import controller.Events;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {

    public OptionsPanel(ActionListener listener) {
        setPreferredSize( new Dimension( 200, 500 ) );
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,15,25));
        panel.setBackground(Color.WHITE);
        panel.add(createButton(listener, "Crear partición", Events.CREATE_PARTITION_DIALOG.toString()));
        panel.add(createButton(listener, "Crear Proceso", Events.CREATE_PROCESS_DIALOG.toString()));
        panel.add(createButton(listener, "Iniciar simulación", Events.START_SIMULATION.toString()));
        panel.setBorder(new EmptyBorder(50,30,10,30));
        add(panel, BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.add(createButton(listener, "Salir", Events.EXIT.toString()), BorderLayout.SOUTH);
        panel1.setBorder(new EmptyBorder(400,30,10,30));
        add(panel1, BorderLayout.SOUTH);
    }

    private JButtonInformation createButton(ActionListener listener, String text, String command) {
        JButtonInformation addChanges = new JButtonInformation(10, 10, text,
                Constant.COLOR_RED, Color.white, Constant.FONT_NUNITO);
        addChanges.setBorder(new EmptyBorder(15,15,15,15));
        addChanges.setActionCommand(command);
        addChanges.addActionListener(listener);
        return addChanges;
    }

}
