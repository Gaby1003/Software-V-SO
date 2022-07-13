package controller;

import model.Partition;
import views.*;

import javax.swing.*;

import exception.*;

import model.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private MainFrame mainFrame;
    private CreateProcess createProcess;
    private CreatePartition createPartition;
    private DialogException dialogException;
    private EditProcess editProcess;
    private DeleteProcessDialog deleteProcessDialog;
    private Manager manager;

    public Controller() {
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
        manager = new Manager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())) {
            case START_SIMULATION:
                addPartitionTab();
                break;
            case NEW_TRANSITION:
                newTransition();
                break;
            case CREATE_PROCESS_DIALOG:
                showCreateProcess();
                break;
            case CREATE_PARTITION_DIALOG:
                showCreatePartition();
                break;
            case EXIT:
                System.exit(0);
                break;
            case EXIT_PROCESS:
                createProcess.dispose();
                break;
            case EXIT_PARTITION:
                createPartition.dispose();
                break;
            case CREATE_PARTITION:
                addPartition();
                break;
            case CREATE_PROCESS:
                addProcess();
                break;
            case EXIT_DIALOG_EXCEPTION:
                dialogException.dispose();
                break;
            case EDIT_PROCESS:
                showEditProcess();
                break;
            case EXIT_DIALOG_EDIT:
                editProcess.dispose();
                break;
            case ENTER_EDIT:
                editProcess();
                break;
            case DELETE_PROCESS:
                showDeleteProcess();
                break;
        }
    }

    private void showDeleteProcess() {
        deleteProcessDialog = new DeleteProcessDialog(this, null);
    }

    private void editProcess() {
        try {
            editProcess.getProcessData();
        } catch (EmptyTextFieldException | PossitiveValues | TimeInNumber e) {
           exception(e.getMessage());
        }
    }

    private void showEditProcess() {
        editProcess = new EditProcess(this, manager.getPartitionNameList());
    }



    private void addProcess() {
        try {
            String[] datas =  createProcess.getInfo();
            manager.addNewProcess(datas[0], Long.parseLong(datas[1]),
                    Long.parseLong(datas[2]), datas[3].equals("Si") ? true : false);
            createProcess.dispose();
            mainFrame.addRowToTable(datas);
        } catch (RepeatedProcess | EmptyTextFieldException | PossitiveValues | TimeInNumber e) {
            exception(e.getMessage());
        }
    }

    private void addPartitionTab(){

        manager.makeTransition();
        mainFrame.cleanPanel();
        for (int i = 0; i < manager.getPartitionList().size(); i++) {
            System.out.println("Dentro");
            mainFrame.initSimulationPanel(this, manager.getPartitionList(),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getReadyList())),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getDispatchList())),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getExpirationTimeList())),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getInExecutionList())),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getWakeUpList())),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getBlockList())),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getBlockedList())),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getOutputList())),
                    manager.returnList(manager.getListByPartition(manager.getPartitionList().get(i).getName(),
                            manager.getNoReadyList())), manager.getPartitionList().get(i).getName());
        }
        mainFrame.addGeneralTab(manager.returnList(manager.getNoReadyList()), "No ejecutados",
                "no ejecutados por exceso de tamaño");
    }
    public void addPartition(){
        try {
            String[] datas =  createPartition.getInfo();
            manager.addNewPartition(datas[0], Long.parseLong(datas[1]));
            createPartition.dispose();
            mainFrame.addRowToTablePartitions(datas);
        } catch (RepeatedPartition | EmptyTextFieldException | PossitiveValues | TimeInNumber e) {
            exception(e.getMessage());
        }
    }

    private void showCreatePartition() {
        createPartition = new CreatePartition(this);
    }

    private void showCreateProcess() {
        createProcess = new CreateProcess(this, manager.getPartitionNameList());
    }


    private void newTransition() {
        manager.initLists();
        mainFrame.initAddProcessPanel(this);
    }

    private void exception(String text) {
        dialogException = new DialogException(this, text);
        dialogException.setVisible(true);
    }


}
