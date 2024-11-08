import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class PageReplacement_LRU
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

        LinkedHashSet<Integer> cache = new LinkedHashSet<>(frames);
        HashMap<Integer, Integer> pageIndices = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < n; i++)
        {
            int page = pages[i];

            if (!cache.contains(page))
            {
                if (cache.size() == frames)
                {
                    int lruPage = findLRU(pageIndices, cache);
                    cache.remove(lruPage);
                    pageIndices.remove(lruPage);
                }
                cache.add(page);
                pageFaults++;
            }
            else
            {
                cache.remove(page);
                cache.add(page);
            }

            pageIndices.put(page, i);
        }

        System.out.println("Total Page Faults: " + pageFaults);
        scanner.close();
    }

    private static int findLRU(HashMap<Integer, Integer> pageIndices, LinkedHashSet<Integer> cache)
    {
        int lruPage = -1;
        int oldestIndex = Integer.MAX_VALUE;

        for (int page : cache)
        {
            int lastUsedIndex = pageIndices.get(page);
            if (lastUsedIndex < oldestIndex)
            {
                oldestIndex = lastUsedIndex;
                lruPage = page;
            }
        }

        return lruPage;
    }
}
