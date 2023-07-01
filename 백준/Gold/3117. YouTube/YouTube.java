import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] sparsetable;
    static int[] student;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        sparsetable = new int[k+1][32];

        st = new StringTokenizer(br.readLine(), " ");
        student = new int[n+1];
        for(int i = 1; i<n+1; i++){
            student[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i<k+1; i++){
            sparsetable[i][0] = Integer.parseInt(st.nextToken());
        }

        makeTable(k);

        StringBuilder sb = new StringBuilder();
        for(int start:student){
            if(start == 0) continue;
            sb.append(query(m-1, start) + " ");
        }

        System.out.println(sb);
    }

    public static void makeTable(int n){
        for(int i = 1; i<31; i++){
            for(int j = 1; j<n+1; j++){
                int next = sparsetable[j][i-1];
                sparsetable[j][i] = sparsetable[next][i-1];
            }
        }
    }

    public static long query(long n, int x){
        for(int i = 0; i<31; i++){
            if((n & (1<<i)) != 0){
                x = sparsetable[x][i];
            }
        }

        return x;
    }
}