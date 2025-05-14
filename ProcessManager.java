import java.util.ArrayList;

public class ProcessManager {

    private ArrayList<PCB> processList = new ArrayList<>();
    private ArrayList<PCB> killList = new ArrayList<>();
    private MemoryManager memoryManager = new MemoryManager();

    public void createProcess(String name, int memorySize, int runtime) {
        int pid = processList.size() + 1; // Simple PID assignment
        PCB newProcess = new PCB(pid, name, memorySize, runtime);
        processList.add(newProcess);
        // Allocate memory for the process
        memoryManager.allocate(pid, memorySize);
    }

    public void schedule(int timeQuantum){
        // Implement round robin scheduling logic here
        while (!processList.isEmpty()) {
            for (PCB process : processList) {
                if (process.getState().equals("READY")) {
                    // Simulate process execution
                    System.out.println("Executing process: " + process.getName());
                    // Change state to RUNNING
                    process.setState("RUNNING");
                    // Simulate time slice
                    process.run(timeQuantum);
                    // Change state back to READY
                    process.setState("READY");
                    process.setRuntime(process.getRuntime() - timeQuantum);
                    //check if process is finished
                    if (process.getRuntime() <= 0) {
                        killList.add(process);
                    }
                }
            }
            for (PCB process : killList) {
                // Free memory for the finished process
                memoryManager.free(process.getPid());
                processList.remove(process);
                System.out.println("Process " + process.getName() + " has finished.");
            }
            killList.clear();
        }
    }

    public void listProcesses() {
        // Implement process listing logic here
        if (!processList.isEmpty()) {
            for (PCB process : processList) {
                System.out.println("Process ID: " + process.getPid() + ", Name: " + process.getName() + ", State: " + process.getState());
            }
        } else {
            System.out.println("No processes running.");
        }
    }

    public void printMemoryStatus() {
        // Implement memory status printing logic here
        System.out.println("Memory status: " + (100 - memoryManager.memFree()) + "% used " + "(" + memoryManager.memFree() + " units free)\n");
        memoryManager.printMemory();
    }

    public void manualAlloc(int pid, int size) {
        // Implement manual memory allocation logic here
        memoryManager.allocate(pid, size);
    }
}
