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
		/**Manager m = new Manager();

		m.addNewProcess("P1", 20, 30, false);
		m.addNewProcess("P2", 15, 25, true);
		m.addNewProcess("P3", 22, 39, false);
		m.addNewProcess("P4", 11, 10, true);
		m.addNewProcess("P5", 15, 80, true);

		m.addNewPartition("PAR1", 20);
		m.addNewPartition("PAR2", 40);
		m.addNewPartition("PAR3", 15);
		m.addNewPartition("PAR4", 30);

		m.makeTransition();
		System.out.println("-----------------Listos-----------------");
		m.printList(m.getReadyList());
		System.out.println("-----------------Ejecutados-----------------");
		m.printList(m.getInExecutionList());
		System.out.println("-----------------Expirados-----------------");
		m.printList(m.getExpirationTimeList());
		System.out.println("-----------------Bloqueo-----------------");
		m.printList(m.getBlockedList());
		System.out.println("-----------------Finalizados-----------------");
		m.printList(m.getOutputList());
		System.out.println("-----------------No ejecutados-----------------");
		m.printList(m.getNoReadyList());

		System.out.println("-----------------Listos PAR2-----------------");
		m.printList(m.getListByPartition("PAR2", m.getReadyList()));
		System.out.println("-----------------Ejecutados-----------------");
		m.printList(m.getListByPartition("PAR2",m.getInExecutionList()));
		System.out.println("-----------------Expirados-----------------");
		m.printList(m.getListByPartition("PAR2",m.getExpirationTimeList()));
		System.out.println("-----------------Bloqueo-----------------");
		m.printList(m.getListByPartition("PAR2",m.getBlockedList()));
		System.out.println("-----------------Finalizados-----------------");
		m.printList(m.getListByPartition("PAR2",m.getOutputList()));
		System.out.println("-----------------No ejecutados-----------------");
		m.printList(m.getListByPartition("PAR2",m.getNoReadyList()));**/
	}
}

