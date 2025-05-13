import java.util.ArrayList;

public class ProcessManager {

    private ArrayList<PCB> processList;


    public void createProcess(String name) {
        int pid = processList.size() + 1; // Simple PID assignment
        PCB newProcess = new PCB(pid, name);
        processList.add(newProcess);
    }

    public void schedule(){
        // Implement scheduling logic here
    }

    public void listProcesses() {
        // Implement process listing logic here
    }
}
