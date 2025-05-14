import java.util.ArrayList;

public class ProcessManager {

    private ArrayList<PCB> processList;
    private MemoryManager memoryManager;

    public void createProcess(String name, int memorySize, int runtime) {
        int pid = processList.size() + 1; // Simple PID assignment
        PCB newProcess = new PCB(pid, name, memorySize, runtime);
        processList.add(newProcess);
        // Allocate memory for the process
        memoryManager.allocate(pid, memorySize);
    }

    public void schedule(){
        // Implement round robin scheduling logic here
        while (!processList.isEmpty()) {
            for (PCB process : processList) {
                if (process.getState().equals("READY")) {
                    // Simulate process execution
                    System.out.println("Executing process: " + process.getName());
                    // Change state to RUNNING
                    process.setState("RUNNING");
                    // Simulate time slice
                    try {
                        Thread.sleep(1000); // Simulate time slice, guessing this is what she wants?
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Change state back to READY
                    process.setState("READY");
                    process.setRuntime(process.getRuntime() - 1);
                    //check if process is finished
                    if (process.getRuntime() <= 0) {
                        processList.remove(process);
                    }
                }
            }
        }
    }

    public void listProcesses() {
        // Implement process listing logic here
        for (PCB process : processList) {
            System.out.println("Process ID: " + process.getPid() + ", Name: " + process.getName() + ", State: " + process.getState());
        }
    }
}
