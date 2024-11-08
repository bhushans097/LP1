import java.util.Scanner;
class Process {
    int pid; 
    int arrivalTime; 
    int burstTime;
    int completionTime; 
    int waitingTime; 
    int turnaroundTime; 
    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}
public class CPU_FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Process " + (i + 1));
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }
        java.util.Arrays.sort(processes, (p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime));
        int currentTime = 0;
        for (Process p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.completionTime = currentTime + p.burstTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
            currentTime += p.burstTime;
        }
        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        for (Process p : processes) {
            System.out.println(p.pid + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime + "\t\t" +
                    p.completionTime + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }
        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / n);
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / n);
        scanner.close();
    }
}
