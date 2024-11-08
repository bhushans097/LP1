import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

class Process 
{
    int pid; 
    int arrivalTime; 
    int burstTime; 
    int priority; 
    int completionTime; 
    int waitingTime; 
    int turnaroundTime; 

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class CPU_Priority {
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
            System.out.print("Priority (lower number means higher priority): ");
            int priority = scanner.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime, priority);
        }
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        int completed = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        while (completed < n)
         {
            Process highestPriorityProcess = null;
            for (Process p : processes) 
            {
                if (p.arrivalTime <= currentTime && p.completionTime == 0) 
                { 
                    if (highestPriorityProcess == null || p.priority > highestPriorityProcess.priority)
                     {
                        highestPriorityProcess = p;
                    }
                }
            }

            if (highestPriorityProcess != null) 
            {
        
                highestPriorityProcess.completionTime = currentTime + highestPriorityProcess.burstTime;
                highestPriorityProcess.turnaroundTime = highestPriorityProcess.completionTime - highestPriorityProcess.arrivalTime;
                highestPriorityProcess.waitingTime = highestPriorityProcess.turnaroundTime - highestPriorityProcess.burstTime;

                totalWaitingTime += highestPriorityProcess.waitingTime;
                totalTurnaroundTime += highestPriorityProcess.turnaroundTime;


                currentTime = highestPriorityProcess.completionTime;
                completed++;
            }
             else
            {
                currentTime++;
            }
        }
        System.out.println("\nProcess\tArrival Time\tBurst Time\tPriority\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println(p.pid + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime + "\t\t" + p.priority + "\t\t" +
                    p.completionTime + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / n);
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / n);

        scanner.close();
    }
}
