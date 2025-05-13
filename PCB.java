public class PCB {
    private int pid;
    private String name;
    private String state;
    private boolean active;

    public PCB(int pid, String name) {
        this.pid = pid;
        this.name = name;
        this.state = "READY";
        this.active = true;
    }
}
