import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
class Process
 {
    int pid; 
    int arrivalTime; 
    int burstTime;
    int remainingTime; 
    int completionTime; 
    int waitingTime; 
    int turnaroundTime; 
    public Process(int pid, int arrivalTime, int burstTime) 
    {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}
public class CPU_SJF 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        ArrayList<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) 
        {
            System.out.println("Enter details for Process " + (i + 1));
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            processes.add(new Process(i + 1, arrivalTime, burstTime));
        }
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int completed = 0;
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        while (completed < n) 
        {
            Process shortestProcess = null;
            for (Process p : processes) 
            {
                if (p.arrivalTime <= currentTime && p.remainingTime > 0) 
                {
                    if (shortestProcess == null || p.remainingTime < shortestProcess.remainingTime)
                     {
                        shortestProcess = p;
                    }
                }
            }

            if (shortestProcess != null) 
            {
                shortestProcess.remainingTime--;
                currentTime++;
                if (shortestProcess.remainingTime == 0) 
                {
                    completed++;
                    shortestProcess.completionTime = currentTime;
                    shortestProcess.turnaroundTime = shortestProcess.completionTime - shortestProcess.arrivalTime;
                    shortestProcess.waitingTime = shortestProcess.turnaroundTime - shortestProcess.burstTime;
                    totalWaitingTime += shortestProcess.waitingTime;
                    totalTurnaroundTime += shortestProcess.turnaroundTime;
                }
            } else 
            {
                currentTime++;
            }
        }
        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) 
        {
            System.out.println(p.pid + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime + "\t\t" +
                    p.completionTime + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
        }
        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / n);
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / n);
        scanner.close();
    }
}