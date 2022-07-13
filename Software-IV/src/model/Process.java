package model;

import java.util.ArrayList;

public class Process {
	
	private String name;
	private int time;
	private boolean isBlocked;
	private int size;
	private String namePartition;
	
	public Process(String name, int time,int size, boolean isBlocked, String namePartition) {
		super();
		this.name = name;
		this.time = time;
		this.size = size;
		this.isBlocked = isBlocked;
		this.namePartition = namePartition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	public String getNamePartition() {
		return namePartition;
	}

	public void setNamePartition(String namePartition) {
		this.namePartition = namePartition;
	}

	public Object[] toObjectVector() {
        return new Object[] {name, time, size, isBlocked, namePartition};
    }

}
