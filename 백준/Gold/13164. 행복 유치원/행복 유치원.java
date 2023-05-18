import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int prev = Integer.parseInt(st.nextToken());
        ArrayList<Integer> diff = new ArrayList<>();

        for(int i = 1; i<n; i++){
            int kid = Integer.parseInt(st.nextToken());
            diff.add(kid - prev);
            prev = kid;
        }

        Collections.sort(diff, Collections.reverseOrder());

        int result = 0;
        for(int i = k -1; i<diff.size(); i++){
            result += diff.get(i);
        }

        System.out.println(result);

    }
}
