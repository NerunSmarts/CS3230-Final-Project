public class MemoryManager {
    private int[] memory = new int[100];

    public MemoryManager() {
        // Initialize memory with zeros
        for (int i = 0; i < memory.length; i++) {
            memory[i] = 0;
        }
    }

    public void allocate(int pid, int size) {
        // Simple allocation logic
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                boolean canAllocate = true;
                for (int j = 0; j < size; j++) {
                    if (i + j >= memory.length || memory[i + j] != 0) {
                        canAllocate = false;
                        break;
                    }
                }
                if (canAllocate) {
                    for (int j = 0; j < size; j++) {
                        memory[i + j] = pid;
                    }
                    System.out.println("Allocated " + size + " units of memory to process " + pid);
                    return;
                }
            }
        }
        System.out.println("Memory allocation failed for process " + pid);
    }

    public void free(int pid) {
        // Simple deallocation logic
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == pid) {
                memory[i] = 0;
            }
        }
        System.out.println("Freed memory for process " + pid);
    }
}
