import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class CPU_RoundRobin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        System.out.print("Enter the time quantum: ");
        int quantum = scanner.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Process " + (i + 1));
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        int currentTime = 0;
        int completed = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        Queue<Process> queue = new LinkedList<>();
        boolean[] isInQueue = new boolean[n];

        while (completed < n) {
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && p.remainingTime > 0 && !isInQueue[p.pid - 1]) {
                    queue.add(p);
                    isInQueue[p.pid - 1] = true;
                }
            }

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();
                isInQueue[currentProcess.pid - 1] = false;

                if (currentProcess.remainingTime > quantum) {
                    currentProcess.remainingTime -= quantum;
                    currentTime += quantum;

                    for (Process p : processes) {
                        if (p.arrivalTime <= currentTime && p.remainingTime > 0 && !isInQueue[p.pid - 1]) {
                            queue.add(p);
                            isInQueue[p.pid - 1] = true;
                        }
                    }
                    queue.add(currentProcess);
                    isInQueue[currentProcess.pid - 1] = true;
                } else {
                    currentTime += currentProcess.remainingTime;
                    currentProcess.remainingTime = 0;
                    currentProcess.completionTime = currentTime;
                    currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                    currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
                    totalWaitingTime += currentProcess.waitingTime;
                    totalTurnaroundTime += currentProcess.turnaroundTime;
                    completed++;
                }
            } else {
                currentTime++;
            }
        }

        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println(p.pid + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime + "\t\t" +
                    p.completionTime + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / n);
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / n);

        scanner.close();
    }
}
