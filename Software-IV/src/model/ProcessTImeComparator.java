package model;

import org.omg.Dynamic.Parameter;

import java.util.Comparator;

public class ProcessTImeComparator implements Comparator<Process>  {

    @Override
	public int compare(Process o1, Process o2) {
		return o1.getTime() - o2.getTime();
	}
}
