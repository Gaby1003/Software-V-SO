package model;

import java.util.Comparator;

public class ProcessComparator implements Comparator<Partition>  {

    @Override
	public int compare(Partition o1, Partition o2) {
		return o1.getTime() - o2.getTime();
	}
}
