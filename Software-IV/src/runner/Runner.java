package runner;

import controller.Controller;
import exception.RepeatedPartition;
import exception.RepeatedProcess;
import model.Manager;
import model.Partition;
import model.Process;

public class Runner {
	
	public static void main(String[] args) throws RepeatedProcess, RepeatedPartition {
		new Controller();
	}
}

