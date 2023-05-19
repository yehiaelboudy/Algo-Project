import java.util.*;

public class JobScheduler {
    
    // Define a class to store each job's attributes
    static class Job {
        int id, deadline, profit;
        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
    
    // Comparator to sort jobs in descending order of profit
    static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job a, Job b) {
            return b.profit - a.profit;
        }
    }
    
    public static void main(String[] args) {
        // Define the input jobs
        Job[] jobs = new Job[] {
            new Job(1, 4, 20),  // Job 1: Deadline in 4, Profit of 20
            new Job(2, 1, 10),  // Job 2: Deadline in 1, Profit of 10
            new Job(3, 1, 40),  // Job 3: Deadline in 1, Profit of 40
            new Job(4, 1, 30)   // Job 4: Deadline in 1, Profit of 30
        };
        
        // Sort jobs in descending order of profit
        Arrays.sort(jobs, new JobComparator());
        
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }
        
        // Initialize the array to keep track of which slots are occupied
        boolean[] slots = new boolean[maxDeadline];
        
        int numJobsDone = 0;
        int maxProfit = 0;
        
        // Iterate over the jobs and schedule them if possible
        for (Job job : jobs) {
            // Find the latest available slot before the job's deadline
            int slot = job.deadline - 1;
            while (slot >= 0 && slots[slot]) {
                slot--;
            }
            // If a slot is available, schedule the job
            if (slot >= 0) {
                slots[slot] = true;
                numJobsDone++;
                maxProfit += job.profit;
            }
        }
        
        // Print the output
        System.out.println("Number of jobs done: " + numJobsDone);
        System.out.println("Maximum profit: " + maxProfit);
    }
}

