import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] damage = new long[n];

        for (int i = 0; i < n; i++) {
            damage[i] = Long.parseLong(br.readLine());
        }

        long[][] reward = new long[k][2];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            reward[i][0] = Long.parseLong(st.nextToken());
            reward[i][1] = Long.parseLong(st.nextToken());
        }

        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long[][] dp = new long[n][901];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                boolean canKill = false;
                long killTime = 0;
                if (reward[j][0] % damage[i] == 0 && reward[j][0] / damage[i] <= 900) {
                    canKill = true;
                    killTime = reward[j][0] / damage[i];
                } else if (reward[j][0] % damage[i] != 0 && (reward[j][0] / damage[i]) + 1 <= 900) {
                    canKill = true;
                    killTime = reward[j][0] / damage[i] + 1;
                }

//                System.out.println(killTime + " " + i + "qj");
                if (canKill) {
                    for (int t = 900; t >= killTime; t--) {
                        dp[i][t] = Math.max(dp[i][t], dp[i][(int) (t - killTime)] + reward[j][1]);
                    }
                }
            }

            long meso = 0;

            for (int t = 0; t <= 900; t++) {
                meso = Math.max(meso, dp[i][t]);
            }

//            System.out.println(i + "번째  " + meso);
            pq.add(meso);
        }

        int answer = 0;

        for (int i = 0; i < m; i++) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}
