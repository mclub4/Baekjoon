import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
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