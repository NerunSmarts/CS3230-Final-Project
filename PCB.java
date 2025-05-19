public class PCB implements Runnable {
    private final int pid;
    private final String name;
    private String state;
    private boolean active;
    private int runtime;
    private final int memorySize;
    private final Semaphore memoryLock;

    public PCB(int pid, String name, int memorySize, int runtime, Semaphore memoryLock) {
        this.pid = pid;
        this.name = name;
        this.state = "READY";
        this.active = true;
        this.runtime = runtime; // Default runtime
        this.memorySize = memorySize; // Default memory size
        this.memoryLock = memoryLock;
    }

    public int getPid() {
        return pid;
    }
    public String getName() {
        return name;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getRuntime() {
        return runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public int getMemorySize() {
        return memorySize;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void run(int time) {
        memoryLock.waitSem();
        System.out.println("Process " + name + " with PID " + pid + " is running.");
        try {
            Thread.sleep(time * 1000); // Simulate runtime in seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        memoryLock.signal();
    }

    @Override
    public void run() {
        //never used
    }
}
