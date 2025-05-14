public class PCB implements Runnable {
    private int pid;
    private String name;
    private String state;
    private boolean active;
    private int runtime;
    private int memorySize;

    public PCB(int pid, String name, int memorySize, int runtime) {
        this.pid = pid;
        this.name = name;
        this.state = "READY";
        this.active = true;
        this.runtime = runtime; // Default runtime
        this.memorySize = memorySize; // Default memory size
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

    public void run(int time) {
        // TODO: Implement process execution logic here
        System.out.println("Process " + name + " with PID " + pid + " is running.");
        try {
            Thread.sleep(time * 1000); // Simulate runtime in seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        //never used
    }
}
