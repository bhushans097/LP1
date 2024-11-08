import java.util.Arrays;

public class BestFit {
    public static void main(String[] args) {
        int[] blockSize = {100, 500, 200, 300, 600};
        int[] processSize = {212, 417, 112, 426};
        int[] allocation = new int[processSize.length];
        Arrays.fill(allocation, -1);

        for (int i = 0; i < processSize.length; i++) {
            int bestIndex = -1;
            for (int j = 0; j < blockSize.length; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIndex == -1 || blockSize[j] < blockSize[bestIndex]) {
                        bestIndex = j;
                    }
                }
            }
            if (bestIndex != -1) {
                allocation[i] = bestIndex;
                blockSize[bestIndex] -= processSize[i];
            }
        }

        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < processSize.length; i++) {
            System.out.println((i + 1) + "\t\t" + processSize[i] + "\t\t" +
                (allocation[i] != -1 ? allocation[i] + 1 : "Not Allocated"));
        }
    }
}
