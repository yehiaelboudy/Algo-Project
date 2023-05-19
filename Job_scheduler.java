//import java.util.*;

public class Job_scheduler {
    
    // Define a class to store each job's attributes
    static class Job {
        int start, end, profit;
        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
    
    // Comparator to sort jobs in ascending order of end time
    static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job a, Job b) {
            return a.end - b.end;
        }
    }
    
    public static void main(String[] args) {
        // Define the input jobs
        Job[] jobs = new Job[] {
            new Job(1, 6, 6),
            new Job(2, 5, 5),
            new Job(5, 7, 5),
            new Job(6, 8, 3)
        };
        
        // Sort jobs in ascending order of end time
        Arrays.sort(jobs, new JobComparator());
        
        // Initialize the array to keep track of the maximum profit for each job ending at a given time
        int[] maxProfit = new int[jobs[jobs.length - 1].end];
        
        // Iterate over the jobs and calculate the maximum profit for each job ending at a given time
        for (int i = 0; i < jobs.length; i++) {
            int currProfit = jobs[i].profit;
            int prevEnd = jobs[i].start;
            for (int j = prevEnd - 1; j >= 0; j--) {
                currProfit += (j < maxProfit.length) ? maxProfit[j] : 0;
                prevEnd = j + 1;
                if (j == jobs[i].end - 1) {
                    break;
                }
            }
            if (prevEnd <= jobs[i].end && currProfit > maxProfit[jobs[i].end - 1]) {
                maxProfit[jobs[i].end - 1] = currProfit;
            }
        }
        
        // Find the maximum profit among all jobs
        int max = 0;
        for (int i = 0; i < maxProfit.length; i++) {
            max = Math.max(max, maxProfit[i]);
        }
        
        // Print the output
        System.out.println("Maximum profit: " + max);
    }
}
