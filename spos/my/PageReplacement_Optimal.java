import java.util.HashSet;
import java.util.Scanner;

public class PageReplacement_Optimal
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

        HashSet<Integer> cache = new HashSet<>(frames);
        int pageFaults = 0;

        for (int i = 0; i < n; i++)
        {
            int page = pages[i];

            if (!cache.contains(page))
            {
                if (cache.size() == frames)
                {
                    int pageToReplace = findOptimalPageToReplace(cache, pages, i + 1);
                    cache.remove(pageToReplace);
                }
                cache.add(page);
                pageFaults++;
            }
        }

        System.out.println("Total Page Faults: " + pageFaults);
        scanner.close();
    }

    private static int findOptimalPageToReplace(HashSet<Integer> cache, int[] pages, int currentIndex)
    {
        int farthest = currentIndex;
        int pageToReplace = -1;

        for (int page : cache)
        {
            int nextUse = Integer.MAX_VALUE;
            for (int j = currentIndex; j < pages.length; j++)
            {
                if (pages[j] == page)
                {
                    nextUse = j;
                    break;
                }
            }

            if (nextUse > farthest)
            {
                farthest = nextUse;
                pageToReplace = page;
            }
        }

        return pageToReplace == -1 ? cache.iterator().next() : pageToReplace;
    }
}
