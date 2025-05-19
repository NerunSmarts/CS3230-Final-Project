import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // i did a lot, will add the word parsing in a bit.

        Scanner in = new Scanner(System.in);
        ProcessManager processManager = new ProcessManager();
        boolean running = true;

        while (running) {
            System.out.print("MicroOS % ");
            String input = in.nextLine();
            String[] command = input.split(" ");

            switch (command[0].toLowerCase()) {
                case "create" -> {
                    if (command.length == 4) {
                        String name = command[1];
                        int memorySize = Integer.parseInt(command[2]);
                        int runtime = Integer.parseInt(command[3]);
                        processManager.createProcess(name, memorySize, runtime);
                    } else {
                        System.out.println("Usage: create <name> <memorySize> <runtime>");
                    }
                }
                case "schedule" -> {
                    if (command.length == 2) {
                        int timeQuantum = Integer.parseInt(command[1]);
                        processManager.schedule(timeQuantum);
                    } else {
                        System.out.println("Usage: schedule <timeQuantum>");
                    }
                }
                case "mem" -> {
                    if (command.length == 1) {
                        processManager.printMemoryStatus();
                    } else {
                        System.out.println("Usage: mem");
                    }
                }
                case "alloc" -> {
                    switch (command.length) {
                        case 3 ->  {
                            int pid = Integer.parseInt(command[1]);
                            int size = Integer.parseInt(command[2]);
                            processManager.manualAlloc(pid, size);
                        }
                        case 4 ->  {
                            int pid = Integer.parseInt(command[1]);
                            int size = Integer.parseInt(command[2]);
                            int start = Integer.parseInt(command[3]);
                            processManager.manualAlloc(pid, size, start);
                        }
                        default -> System.out.println("Usage: alloc <pid> <size> [<start>]");
                    }
                }
                case "ps" -> processManager.listProcesses();
                case "exit" -> {running = false; System.out.println("Exiting MicroOS..."); in.close();}
                default -> System.out.println("Unknown command: " + command[0]);
            }
        }
    }
}
