import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] convey = new int[2 * n];
        boolean[] robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            convey[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 1;
        while (true) {
            //Step 1
            int tmp = convey[2 * n - 1];
            for (int i = 2 * n - 2; i >= 0; i--) {
                convey[i + 1] = convey[i];
            }
            convey[0] = tmp;

            for (int i = n - 2; i >= 0; i--) {
                robot[i + 1] = robot[i];
            }
            robot[0] = false;
            robot[n - 1] = false;

            //Step2
            for (int i = n - 2; i >= 0; i--) {
                if (convey[i + 1] >= 1 && !robot[i + 1] && robot[i]) {
                    robot[i + 1] = robot[i];
                    convey[i + 1]--;
                    robot[i] = false;
                }
            }
            robot[n - 1] = false;

            //Step3
            if (convey[0] > 0) {
                convey[0]--;
                robot[0] = true;
            }

            //Step4
            int num = 0;
            for (int i = 0; i < convey.length; i++) {
                if (convey[i] == 0) num++;
            }

            if (num >= k) break;

            answer++;
        }

        System.out.println(answer);
    }
}