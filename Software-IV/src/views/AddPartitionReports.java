package views;

import controller.Events;
import model.Partition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPartitionReports extends JPanel {
    private JTabbedPane tabbedPanePartitions;

    public AddPartitionReports() {
        setLayout(new BorderLayout());
    }

    public void addTab(ActionListener listener, ArrayList<Partition> partitions,
                       ArrayList<Object[]> getReadyList,
                       ArrayList<Object[]> getDispatchList, ArrayList<Object[]> getExpirationTimeList,
                       ArrayList<Object[]> getInExecutionList, ArrayList<Object[]> getWakeUpList,
                       ArrayList<Object[]> getBlockList, ArrayList<Object[]> getBlockedList,
                       ArrayList<Object[]> getOutputList, ArrayList<Object[]> getNoReadyList, String name) {

        tabbedPanePartitions = new JTabbedPane();
        tabbedPanePartitions.setBackground(Color.WHITE);
        tabbedPanePartitions.add(new AddListProcessPanel(listener, getReadyList, getDispatchList,
                getExpirationTimeList, getInExecutionList, getWakeUpList, getBlockList,
                getBlockedList, getOutputList, getNoReadyList), name);
        add(tabbedPanePartitions, BorderLayout.CENTER);
        add(addButtonExit(listener), BorderLayout.SOUTH);

    }

    public JPanel addButtonExit(ActionListener listener) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        JButtonInformation newTransition = new JButtonInformation(10, 10, "Nueva transici\u00f3n", Constant.COLOR_RED,
                Color.BLACK, Constant.FONT_NUNITO);
        newTransition.setActionCommand(Events.NEW_TRANSITION.toString());
        newTransition.addActionListener(listener);

        JButtonInformation exit = new JButtonInformation(10, 10, "Salir", Constant.COLOR_RED,
                Color.BLACK, Constant.FONT_NUNITO);
        exit.setActionCommand(Events.EXIT.toString());
        exit.addActionListener(listener);

        panel.add(newTransition);
        panel.add(exit);
        return panel;
    }
}
