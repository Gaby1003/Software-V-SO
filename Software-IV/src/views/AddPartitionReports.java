package views;

import controller.Events;
import model.Partition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPartitionReports extends JPanel {
    private JTabbedPane tabbedPanePartitions;

    public AddPartitionReports(ActionListener listener, ArrayList<Partition> partitions){
        setLayout(new BorderLayout());
        tabbedPanePartitions = new JTabbedPane();
        tabbedPanePartitions.setBackground(Color.WHITE);
        for (int i = 0; i < partitions.size(); i++) {
            tabbedPanePartitions.add(new AddListProcessPanel(listener, partitions.get(i).returnList(partitions.get(i).getReadyList()),
                    partitions.get(i).returnList(partitions.get(i).getDispatchList()),
                    partitions.get(i).returnList(partitions.get(i).getExpirationTimeList()),
                    partitions.get(i).returnList(partitions.get(i).getInExecutionList()),
                    partitions.get(i).returnList(partitions.get(i).getWakeUpList()),
                    partitions.get(i).returnList(partitions.get(i).getBlockList()),
                    partitions.get(i).returnList(partitions.get(i).getBlockedList()),
                    partitions.get(i).returnList(partitions.get(i).getOutputList()),
                    partitions.get(i).returnList(partitions.get(i).getNoReadyList())), partitions.get(i).getName());
        }
        add(tabbedPanePartitions, BorderLayout.CENTER);
        add(addButtonExit(listener),  BorderLayout.SOUTH);
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
