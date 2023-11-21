import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class School implements Comparable<School>{
        int price;
        int day;

        public School(int price, int day){
            this.price = price;
            this.day = day;
        }

        @Override
        public int compareTo(School o) {
            if(this.day == o.day) return o.price - this.price;
            return o.day - this.day;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<School> arr = new ArrayList<>();
        int max = 0;

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            max = Math.max(max, day);
            arr.add(new School(price, day));
        }

        Collections.sort(arr);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;
        int idx = 0;

        for(int i = max; i>0; i--){
            while(idx<n && i<=arr.get(idx).day){
                queue.add(arr.get(idx).price);
                idx++;
            }
            if(!queue.isEmpty()){
                answer += queue.poll();
            }
        }

        System.out.println(answer);
    }

}