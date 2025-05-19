import java.util.ArrayList;

public class ProcessManager {

    private ArrayList<PCB> processList = new ArrayList<>();
    private ArrayList<PCB> killList = new ArrayList<>();
    private MemoryManager memoryManager = new MemoryManager();
    private final Semaphore memoryLock = new Semaphore(1);

    public void createProcess(String name, int memorySize, int runtime) {
        int pid = processList.size() + 1;
        PCB newProcess = new PCB(pid, name, memorySize, runtime);
        processList.add(newProcess);

        memoryLock.waitSem();
        boolean allocated = memoryManager.allocate(pid, memorySize);
        memoryLock.signal();

        if (allocated) {
            newProcess.setState("READY");
            System.out.println("Process " + name + " created with PID " + pid + " and allocated " + memorySize + " units of memory.");
        } else {
            newProcess.setState("BLOCKED");
            System.out.println("Failed to allocate memory for process " + name);
        }
    }

    public void schedule(int timeQuantum) {
        while (!processList.isEmpty()) {
            ArrayList<Thread> threads = new ArrayList<>();

            for (PCB process : processList) {
                if (process.getState().equals("READY")) {
                    process.setState("RUNNING");

                    Thread t = new Thread(() -> {
                        System.out.println("Executing process: " + process.getName());
                        process.run(timeQuantum);

                        synchronized (this) {
                            process.setRuntime(process.getRuntime() - timeQuantum);
                            if (process.getRuntime() <= 0) {
                                killList.add(process);
                            } else {
                                process.setState("READY");
                            }
                        }
                    });

                    threads.add(t);
                    t.start();
                }
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            for (PCB process : killList) {
                memoryLock.waitSem();
                memoryManager.free(process.getPid());
                memoryLock.signal();

                processList.remove(process);
                System.out.println("Process " + process.getName() + " has finished.");
            }
            killList.clear();
        }
    }

    public void listProcesses() {
        if (!processList.isEmpty()) {
            for (PCB process : processList) {
                System.out.println("Process ID: " + process.getPid() + ", Name: " + process.getName() + ", State: " + process.getState());
            }
        } else {
            System.out.println("No processes running.");
        }
    }

    public void printMemoryStatus() {
        System.out.println("Memory status: " + (100 - memoryManager.memFree()) + "% used (" + memoryManager.memFree() + " units free)\n");
        memoryManager.printMemory();
    }

    public void manualAlloc(int pid, int size) {
        memoryLock.waitSem();
        boolean success = memoryManager.allocate(pid, size);
        memoryLock.signal();

        if (success) {
            System.out.println("Allocated " + size + " units of memory to process " + pid);
        } else {
            System.out.println("Failed to allocate memory for process " + pid);
        }
    }

    public void manualAlloc(int pid, int size, int start) {
        memoryLock.waitSem();
        boolean success = memoryManager.allocate(pid, size, start);
        memoryLock.signal();
        // USE ONLY FOR TESTING
        if (success) {
            System.out.println("Allocated " + size + " units of memory to process " + pid + " starting at " + start);
        } else {
            System.out.println("Failed to allocate memory for process " + pid);
        }
    }
}
