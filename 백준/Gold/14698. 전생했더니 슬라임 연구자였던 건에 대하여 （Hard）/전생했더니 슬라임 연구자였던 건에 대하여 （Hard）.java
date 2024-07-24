import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            PriorityQueue<Long> queue = new PriorityQueue<>();

            long energy = 1;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                queue.add(Long.parseLong(st.nextToken()));
            }

            while (queue.size() > 1) {
                long q1 = queue.poll();
                long q2 = queue.poll();
                long power = (q1 * q2);

                energy *= power % 1000000007;
                energy %= 1000000007;

                queue.offer(power);
            }

            System.out.println(energy);
        }
    }
}


