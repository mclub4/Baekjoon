import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] sparsetable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());

        sparsetable = new int[m+1][19];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i<m+1; i++){
            sparsetable[i][0] = Integer.parseInt(st.nextToken());
        }

        makeTable(m);

        int q = Integer.parseInt(br.readLine());
        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            System.out.println(query(n,x));
        }

    }

    public static void makeTable(int m){
        for(int i = 1; i<19; i++){
            for(int j = 1; j<m+1; j++){
                int next = sparsetable[j][i-1];
                sparsetable[j][i] = sparsetable[next][i-1];
            }
        }
    }

    public static int query(int n, int x){
        for(int i = 0; i<19; i++){
            if((n & (1<<i)) != 0){
                x = sparsetable[x][i];
            }
        }

        return x;
    }
}