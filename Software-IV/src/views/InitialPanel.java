package views;

import controller.Events;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

public class InitialPanel extends JPanel{

    public InitialPanel(ActionListener listener){
        setLayout(new GridLayout(2,1,10,20));

        setBackground(Constant.COLOR_WHITE);
        JLabel titlelLb = new JLabel("<html><center>Transiciones de estados de los procesos<center><html>");
        titlelLb.setForeground(Constant.COLOR_BLACK);
        titlelLb.setFont(new Font("Arial", Font.ITALIC, 65));
        titlelLb.setBorder(new EmptyBorder(80, 90, 0, 45));

        JButtonInformation newTransition = new JButtonInformation(10, 10, "Nueva transici\u00f3n", Constant.COLOR_BLUE,
                Color.white, Constant.FONT_NUNITO);
        newTransition.setActionCommand(Events.NEW_TRANSITION.toString());
        newTransition.addActionListener(listener);
        newTransition.setBorder(new EmptyBorder(45, 45, 45, 45));

        JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_RED_2,
                Color.white, Constant.FONT_NUNITO);
        exit.setActionCommand(Events.EXIT.toString());
        exit.addActionListener(listener);
        exit.setBorder(new EmptyBorder(45, 45, 45, 45));

        add(titlelLb);
        add(addButton(listener));
    }

    private JPanel addButton(ActionListener listener){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2,1,10,20));
        panel.setBorder(new EmptyBorder(100, 490, 100, 490));

        JButtonInformation newTransition = new JButtonInformation(10, 10, "Nueva transici\u00f3n", Constant.COLOR_BLUE,
                Color.white, Constant.FONT_NUNITO);
        newTransition.setActionCommand(Events.NEW_TRANSITION.toString());
        newTransition.addActionListener(listener);

        JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_RED_2,
                Color.white, Constant.FONT_NUNITO);
        exit.setActionCommand(Events.EXIT.toString());
        exit.addActionListener(listener);
        panel.add(newTransition,  BorderLayout.CENTER);
        panel.add(exit);
        return panel;
    }
}
