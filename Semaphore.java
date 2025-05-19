public class Semaphore {
    private int permits;

    public Semaphore(int initialPermits) {
        if (initialPermits < 0) {
            throw new IllegalArgumentException("Semaphore permits cannot be negative.");
        }
        this.permits = initialPermits;
    }

    public synchronized void waitSem() {
        while (permits == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        permits--;
    }

    public synchronized void signal() {
        permits++;
        notify();
    }

    public synchronized int availablePermits() {
        return permits;
    }
}
