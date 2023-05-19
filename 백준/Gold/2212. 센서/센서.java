import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        ArrayList<Integer> node = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++){
            node.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(node);

        ArrayList<Integer> diff = new ArrayList<>();
        int prev = node.get(0);
        for(int i = 1; i<n; i++){
            int cur = node.get(i);
            diff.add(cur - prev);
            prev = cur;
        }
        Collections.sort(diff, Collections.reverseOrder());

        int result = 0;
        for(int i = k-1; i<n-1; i++){
            result += diff.get(i);
        }

        System.out.println(result);
    }
}
