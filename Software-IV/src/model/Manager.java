package model;

import exception.RepeatedPartition;
import exception.RepeatedProcess;

import java.util.ArrayList;

public class Manager {

    private static final long EXECUTION_TIME = 5;
    private ArrayList<Partition> partitionList;
    private ArrayList<Process> readyList; //Listos
    private ArrayList<Process> dispatchList; //Despachar
    private ArrayList<Process> expirationTimeList; //Expiraci�n de tiempo
    private ArrayList<Process> inExecutionList; //En ejecuci�n
    private ArrayList<Process> wakeUpList; //Despertar
    private ArrayList<Process> blockList; //Bloquear
    private ArrayList<Process> blockedList; //Bloqueado
    private ArrayList<Process> outputList; //Salida
    private ArrayList<Process> noReadyList; //Salida
    private ArrayList<Process> initList;

    public Manager() {
        initLists();
    }

    public void initLists() {
        initList = new ArrayList<Process>();
        this.partitionList = new ArrayList<Partition>();
        readyList = new ArrayList<Process>();
        dispatchList = new ArrayList<Process>();
        expirationTimeList = new ArrayList<Process>();
        inExecutionList = new ArrayList<Process>();
        wakeUpList = new ArrayList<Process>();
        blockedList = new ArrayList<Process>();
        blockList = new ArrayList<Process>();
        outputList = new ArrayList<Process>();
        noReadyList = new ArrayList<Process>();
    }

    public void addNewPartition(String name, long size) throws RepeatedPartition {
        if (validateNamePartition(name)) {
            partitionList.add(new Partition(name, size));
        } else {
            throw new RepeatedPartition();
        }
    }

    public void addNewProcess(String name, long time, long size, boolean blocked) throws RepeatedProcess {
        if (validateNameProcess(name)) {
            readyList.add(new Process(name, time, size, blocked, null));
        } else {
            throw new RepeatedProcess();
        }
    }

    public void validateProcessTransition(Process process, int i) {
        process.setTime(process.getTime() - EXECUTION_TIME < 0 ?
                0 : process.getTime() - EXECUTION_TIME);
        dispatchList.add(readyList.get(i));
        inExecutionList.add(new Process(process.getName(), process.getTime(), process.getSize(),
                process.isBlocked(), process.getNamePartition()));
        isExpirationTime(new Process(process.getName(), process.getTime(), process.getSize(),
                process.isBlocked(), process.getNamePartition()));
        int partitionPosition = findPartition(process.getNamePartition());
        partitionList.get(partitionPosition).getDispatchList().add(new Process(process.getName(), process.getTime(), process.getSize(),
                process.isBlocked(), process.getNamePartition()));
        partitionList.get(partitionPosition).getInExecutionList().add(new Process(process.getName(), process.getTime(), process.getSize(),
                process.isBlocked(), process.getNamePartition()));
    }

    private int findPartition(String partition) {
        for (int i = 0; i < partitionList.size(); i++) {
            if (partition != null && partition.equals(partitionList.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    private void isExpirationTime(Process process) {
        int partitionPosition = findPartition(process.getNamePartition());
        if (process.getTime() > 0) {
            isBlocked(process);
            readyList.add(process);
            partitionList.get(partitionPosition).getReadyList().add(
                    new Process(process.getName(), process.getTime(), process.getSize(),
                            process.isBlocked(), process.getNamePartition()));
        } else {
            outputList.add(process);
            partitionList.get(partitionPosition).getOutputList().add(
                    new Process(process.getName(), process.getTime(), process.getSize(),
                            process.isBlocked(), process.getNamePartition()));
        }
    }

    private void isBlocked(Process process) {
        int partitionPosition = findPartition(process.getNamePartition());
        if (process.isBlocked() && process.getNamePartition() != null) {
            blockList.add(new Process(process.getName(), process.getTime(), process.getSize(),
                    process.isBlocked(), process.getNamePartition()));
            blockedList.add(new Process(process.getName(), process.getTime(), process.getSize(),
                    process.isBlocked(), process.getNamePartition()));
            wakeUpList.add(new Process(process.getName(), process.getTime(), process.getSize(),
                    process.isBlocked(), process.getNamePartition()));
            partitionList.get(partitionPosition).getBlockList().add(new Process(process.getName(), process.getTime(), process.getSize(),
                    process.isBlocked(), process.getNamePartition()));
            partitionList.get(partitionPosition).getBlockedList().add(new Process(process.getName(), process.getTime(), process.getSize(),
                    process.isBlocked(), process.getNamePartition()));
            partitionList.get(partitionPosition).getWakeUpList().add(new Process(process.getName(), process.getTime(), process.getSize(),
                    process.isBlocked(), process.getNamePartition()));
        } else if (process.getNamePartition() != null){
            expirationTimeList.add(new Process(process.getName(), process.getTime(), process.getSize(),
                    process.isBlocked(), process.getNamePartition()));
            partitionList.get(partitionPosition).getExpirationTimeList().add(new Process(process.getName(), process.getTime(), process.getSize(),
                    process.isBlocked(), process.getNamePartition()));
        }
    }

    public ArrayList<Process> getListByPartition(String namePartition, ArrayList<Process> array){
        ArrayList<Process> arrayList = new ArrayList<Process>();
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i).getNamePartition() != null &&
                    array.get(i).getNamePartition().equals(namePartition)){
                arrayList.add(array.get(i));
            }
        }
        return arrayList;
    }

    private boolean validateNamePartition(String name) {
        for (int i = 0; i < partitionList.size(); i++) {
            if (partitionList.get(i).getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateNameProcess(String name) {
        for (int i = 0; i < readyList.size(); i++) {
            for (int j = 0; j < readyList.size(); j++) {
                if (readyList.get(j).getName().equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateNameNoProcess(String name) {
        for (int i = 0; i < noReadyList.size(); i++) {
            for (int j = 0; j < noReadyList.size(); j++) {
                if (noReadyList.get(j).getName().equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void makeTransition() {
        int partitionPosition = 0;
        int count = 0;
        System.out.println(readyList.size());
        for (int i = 0; i < readyList.size(); i++) {
            Process process = new Process(readyList.get(i).getName(),
                    readyList.get(i).getTime(), readyList.get(i).getSize(),
                    readyList.get(i).isBlocked(), null);
            System.out.println("AAAA");
            int[] datas = validateProcess(process, partitionPosition, i, count);
            partitionPosition = datas[0];
            if(datas[1] >= 0 && validateNameNoProcess(process.getName())){
                process.setNamePartition(readyList.get(i).getNamePartition());
                validateProcessTransition(process, i);
            }
            count = 0;
            System.out.println(i);
        }
    }

    private int[] validateProcess(Process process, int partitionPosition, int i, int count) {
        int fail = 0;
        if(validateNameNoProcess(process.getName())){
            if (partitionPosition < partitionList.size() &&
                    partitionList.get(partitionPosition).getSize() >= readyList.get(i).getSize()
                    && count < partitionList.size()) {
                readyList.get(i).setNamePartition(partitionList.get(partitionPosition).getName());
                partitionPosition = getPartitionPosition(partitionPosition);
            } else if (partitionPosition < partitionList.size() &&
                    partitionList.get(partitionPosition).getSize() < readyList.get(i).getSize()
                    && count < partitionList.size()) {
                partitionPosition = getPartitionPosition(partitionPosition);
                count++;
                int datas[] = validateProcess(process, partitionPosition, i, count);
                partitionPosition = datas[0];
            } else if(count < partitionList.size()){
                partitionPosition = 0;
                count++;
                int datas[] = validateProcess(process, partitionPosition, i, count);
                partitionPosition = datas[0];
            } else {
                count = 0;
                noReadyList.add(process);
                fail = -1;
            }
        }
        return new int[]{partitionPosition, fail};
    }

    private int getPartitionPosition(int partitionPosition) {
        if(partitionPosition + 1 < partitionList.size()){
            partitionPosition++;
        }else{
            partitionPosition = 0;
        }
        return partitionPosition;
    }


    public void printList(ArrayList<Process> list) {
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + " - "  + " - Time: "
                    + list.get(i).getTime() + " - " + list.get(i).getNamePartition());
        }
    }

    public ArrayList<Object[]> getPartitionNameList() {
        ArrayList<Object[]> datas = new ArrayList<Object[]>();
        for (int i = 0; i < partitionList.size(); i++) {
            datas.add(partitionList.get(i).toObjectVector());
        }
        return datas;
    }

    public ArrayList<Object[]> returnList(ArrayList<Process> processes){
        ArrayList<Object[]> listObject = new ArrayList<>();
        for (Process process: processes) {
            listObject.add(process.toObjectVector());
        }
        return listObject;
    }

    public ArrayList<Partition> getPartitionList() {
        return partitionList;
    }

    public void setPartitionList(ArrayList<Partition> partitionList) {
        this.partitionList = partitionList;
    }


    public ArrayList<Process> getReadyList() {
        return readyList;
    }

    public void setReadyList(ArrayList<Process> readyList) {
        this.readyList = readyList;
    }

    public ArrayList<Process> getDispatchList() {
        return dispatchList;
    }

    public void setDispatchList(ArrayList<Process> dispatchList) {
        this.dispatchList = dispatchList;
    }

    public ArrayList<Process> getExpirationTimeList() {
        return expirationTimeList;
    }

    public void setExpirationTimeList(ArrayList<Process> expirationTimeList) {
        this.expirationTimeList = expirationTimeList;
    }

    public ArrayList<Process> getInExecutionList() {
        return inExecutionList;
    }

    public void setInExecutionList(ArrayList<Process> inExecutionList) {
        this.inExecutionList = inExecutionList;
    }

    public ArrayList<Process> getWakeUpList() {
        return wakeUpList;
    }

    public void setWakeUpList(ArrayList<Process> wakeUpList) {
        this.wakeUpList = wakeUpList;
    }

    public ArrayList<Process> getBlockList() {
        return blockList;
    }

    public void setBlockList(ArrayList<Process> blockList) {
        this.blockList = blockList;
    }

    public ArrayList<Process> getBlockedList() {
        return blockedList;
    }

    public void setBlockedList(ArrayList<Process> blockedList) {
        this.blockedList = blockedList;
    }

    public ArrayList<Process> getOutputList() {
        return outputList;
    }

    public void setOutputList(ArrayList<Process> outputList) {
        this.outputList = outputList;
    }

    public ArrayList<Process> getNoReadyList() {
        return noReadyList;
    }

    public void setNoReadyList(ArrayList<Process> noReadyList) {
        this.noReadyList = noReadyList;
    }


}
