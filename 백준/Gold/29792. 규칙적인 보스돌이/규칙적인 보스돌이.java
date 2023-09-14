import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] damage = new long[n];

        for(int i = 0; i<n; i++){
            damage[i] = Long.parseLong(br.readLine());
        }

        long[][] boss = new long[k][2];

        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            boss[i][0] = Long.parseLong(st.nextToken());
            boss[i][1] = Long.parseLong(st.nextToken());
        }

        int tmp = 1<<k;
        Long[] answer = new Long[n];
        Arrays.fill(answer, (long)0);
        for(int i = 0; i<n; i++){
            for(int j = 0; j<tmp; j++){
                long time = 0;
                long reward = 0;
                for(int u = 0; u<k; u++){
                    if((j & (1<<u)) == 0) continue;

                    time += boss[u][0]/damage[i];
                    if(boss[u][0]%damage[i] != 0){
                        time++;
                    }
                    reward += boss[u][1];
                }

                if(time > 900) continue;
                answer[i] = Math.max(answer[i], reward);
            }
        }

        int total = 0;

        Arrays.sort(answer, Collections.reverseOrder());
        for(int i = 0; i<m; i++){
            total += answer[i];
        }

        System.out.println(total);
    }
}
