public class NextFit{
    public static void main(String[] args)
     {
     
        int[] partitions = {100, 500, 200, 300, 600};
        int[] processes = {212, 417, 112, 426};   
        int lastAllocated = 0;
        for (int i = 0; i < processes.length; i++) {
            boolean allocated = false;
            int start = lastAllocated;
            do {
                if (processes[i] <= partitions[lastAllocated]) {
                    System.out.println("Process " + (i + 1) + " of size " + processes[i] + " allocated to partition " + (lastAllocated + 1));
                    partitions[lastAllocated] -= processes[i];  
                    allocated = true;
                    break;
                }
                lastAllocated = (lastAllocated + 1) % partitions.length;  // Move to the next partition
            } while (lastAllocated != start);  // Stop when we cycle through all partitions

            if (!allocated) {
                System.out.println("Process " + (i + 1) + " of size " + processes[i] + " could not be allocated.");
            }
        }
    }
}
