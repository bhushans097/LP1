import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PageReplacement_FIFO
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int frames = scanner.nextInt();

        System.out.print("Enter the number of page requests: ");
        int n = scanner.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page requests:");
        for (int i = 0; i < n; i++)
        {
            pages[i] = scanner.nextInt();
        }

        Queue<Integer> queue = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pages)
        {
            if (!queue.contains(page))
            {
                if (queue.size() == frames)
                {
                    queue.poll(); // Remove the oldest page
                }
                queue.add(page); // Add the new page
                pageFaults++;
            }
        }

        System.out.println("Total Page Faults: " + pageFaults);
        scanner.close();
    }
}
