import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] lines = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lines, (o1, o2) -> {
            if(o1[0] == o2[0]){
                return Integer.compare(o1[1], o2[1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        
        int start = lines[0][0];
        int end = lines[0][1];
        int result = (end-start);

        for(int i = 1; i<n; i++){
            if(start<=lines[i][0] && end<=lines[i][1]){
                int tmp;
                if(lines[i][0]>=start && lines[i][0]<=end) tmp = end;
                else tmp = lines[i][0];
                end = lines[i][1];
                result += (end - tmp);
            }
            else if(end<=lines[i][1]){
                start = end;
                end = lines[i][1];
            }
        }

        System.out.print(result);
    }
}