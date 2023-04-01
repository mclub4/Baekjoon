import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int compare = Math.abs(o1) - Math.abs(o2);
                if(compare != 0 ) return compare;
                return o1 > o2 ? 1: -1;
            }
        });
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i<n; i++){
            int tmp = Integer.parseInt(br.readLine());
            if(tmp == 0){
                if(queue.isEmpty()) System.out.println(0);
                else System.out.println(queue.poll());
            }
            else{
                queue.offer(tmp);
            }

        }
    }
}