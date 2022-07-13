package model;

import exception.RepeatedPartition;
import exception.RepeatedProcess;

import java.util.ArrayList;

public class Manager {

	private ArrayList<Partition> partitionList;

	public Manager() {
		this.partitionList = new ArrayList<Partition>();
	}

	public void addNewPartition(String name, int size) throws RepeatedPartition {
		if(validateNamePartition(name)) {
			partitionList.add(new Partition(name, size));
		} else {
			throw new RepeatedPartition();
		}
	}

	public void addNewProcess(String name, int time, int size, boolean blocked, String partitionName) throws RepeatedProcess {
		if(validateNameProcess(name, partitionName)) {
			for (int i = 0; i < partitionList.size(); i++) {
				if (partitionList.get(i).getName().equals(partitionName)) {
					if (size <= partitionList.get(i).getSize()){
						partitionList.get(i).getReadyList().add(new Process(name, time, size, blocked, partitionName));
					}else{
						partitionList.get(i).getNoReadyList().add(new Process(name, time, size, blocked, partitionName));
					}
				}
				}
			} else {
			throw new RepeatedProcess();
		}
	}

	private boolean validateNamePartition(String name) {
		for (int i = 0; i < partitionList.size(); i++) {
			if(partitionList.get(i).getName().equals(name)) {
				return false;
			}
		}
		return true;
	}

	private boolean validateNameProcess(String name, String partition) {
		for (int i = 0; i < partitionList.size(); i++) {
			//if (partitionList.get(i).getName().equals(partition)) {
				for (int j = 0; j < partitionList.get(i).getReadyList().size() ; j++) {
					if(partitionList.get(i).getReadyList().get(j).getName().equals(name)) {
						return false;
					}
				}
			}
		//}
		return true;
	}

	public void makeTransition() {
		for (int i = 0; i < partitionList.size(); i++) {
			for (int j = 0; j < partitionList.get(i).getReadyList().size(); j++) {
				Process process = new Process(partitionList.get(i).getReadyList().get(j).getName(),
						partitionList.get(i).getReadyList().get(j).getTime(),partitionList.get(i).getReadyList().get(j).getSize(),
						partitionList.get(i).getReadyList().get(j).isBlocked(), partitionList.get(i).getReadyList().get(j).getNamePartition());
				partitionList.get(i).validateProcess(process, j);
			}
		}
	}

	public void printList(ArrayList<Process> list) {
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName() + " - " + list.get(i).getTime() + " - "
					+ list.get(i).isBlocked() + list.get(i).getNamePartition()) ;
		}
	}
	public ArrayList<Object[]> getPartitionNameList(){
		ArrayList<Object[]> datas = new ArrayList<Object[]>();
		for (int i = 0; i < partitionList.size(); i++) {
			datas.add(partitionList.get(i).toObjectVector());
		}
		return datas;
	}

	public ArrayList<Partition> getPartitionList() {
		return partitionList;
	}

	public void setPartitionList(ArrayList<Partition> partitionList) {
		this.partitionList = partitionList;
	}

}
