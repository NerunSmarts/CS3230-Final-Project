public class MemoryManager {
    private final int[] memory = new int[100];

    public MemoryManager() {
        for (int i = 0; i < memory.length; i++) {
            memory[i] = 0;
        }
    }

    public boolean allocate(int pid, int size) {
        // Best-fit allocation logic
        int bestStart = -1;
        int bestSize = Integer.MAX_VALUE;

        for (int i = 0; i < memory.length; ) {
            // Find the start of a free block
            if (memory[i] == 0) {
            int j = i;
            // Find the end of this free block
            while (j < memory.length && memory[j] == 0) {
                j++;
            }
            int blockSize = j - i;
            if (blockSize >= size && blockSize < bestSize) {
                bestStart = i;
                bestSize = blockSize;
            }
            i = j;
            } else {
            i++;
            }
        }

        if (bestStart != -1) {
            for (int k = 0; k < size; k++) {
                memory[bestStart + k] = pid;
            }
            return true;
        } else {
            return false;
        }
    }

     public boolean allocate(int pid, int size, int start) {
        // Manual allocation logic\
        if (start < 0 || start + size > memory.length) {
            return false;
        }
        for (int i = start; i < start + size; i++) {
            memory[i] = pid;
        }
        return true;
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

    public int memFree() {
        // Calculate free memory
        int freeMemory = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                freeMemory++;
            }
        }
        return freeMemory;
    }

    public void printMemory() {
        // Print memory status
        int col = 0;
        System.out.print("Memory contents: \n");
        for (int i = 0; i < memory.length; i++) {
            if (col == 10) {
                System.out.println();
                col = 0;
            }
            System.out.print(memory[i] + " ");
            col++;
        }
        System.out.println();
    }
}
