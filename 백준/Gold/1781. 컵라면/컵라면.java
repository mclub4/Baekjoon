import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Problem implements Comparable<Problem>{
        long price;
        int deadline;

        public Problem(long price, int deadline){
            this.price = price;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Problem o) {
            if(this.deadline == o.deadline){
                if(this.price < o.price) return -1;
                else if(this.price == o.price) return 0;
                else return 1;
            }
            return o.deadline - this.deadline;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Problem> arr = new ArrayList<>();
        int max = 0;

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            long price = Long.parseLong(st.nextToken());
            max = Math.max(max, deadline);
            arr.add(new Problem(price, deadline));
        }

        Collections.sort(arr);

        PriorityQueue<Long> queue = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;
        int idx = 0;

        for(int i = max; i>0; i--) {
            while(idx<n && i<=arr.get(idx).deadline) {
                queue.add(arr.get(idx).price);
                idx++;
            }
            if(!queue.isEmpty()) {
                answer += queue.poll();
            }
        }

        System.out.println(answer);
    }

}