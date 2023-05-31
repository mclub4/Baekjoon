import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] input = new int[n+1];

        ArrayList<Integer>[] singer = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            singer[i] = new ArrayList<>();
        }

        StringBuilder sb = new StringBuilder();
        boolean warning = false;

        for(int i = 0; i<m; i++){
            String[] tmp = br.readLine().split(" ");
            int num = Integer.parseInt(tmp[0]);
            for(int j = 1; j<num; j++){
                for(int k = j+1; k<num+1; k++){
                    //PD들의 가수들을 각각 연결시켜주고 진입차수 증가
                    int start = Integer.parseInt(tmp[j]);
                    int end = Integer.parseInt(tmp[k]);
                    // 1-2, 다음 피디에서 2-1 이런식으로 엇갈려서 나오면 0출력
                    if(singer[end].contains(start)) warning = true;
                    singer[start].add(end);
                    input[end] ++;
                }
            }
        }

        if(warning) System.out.println(0);
        else{
            //위상정렬
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 1; i<n+1; i++){
                if(input[i] == 0) queue.add(i);
            }
            int num = 0;
            while(!queue.isEmpty()){
                int now = queue.poll();
                num++;
                sb.append(now + "\n");
                for(int next : singer[now]){
                    input[next] --;
                    if(input[next] == 0) queue.add(next);
                }
            }
            if(num != n) System.out.println(0);
            System.out.println(sb);
        }
    }
}
