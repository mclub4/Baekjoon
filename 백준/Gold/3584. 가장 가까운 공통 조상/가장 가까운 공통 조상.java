import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t != 0){
            int n = Integer.parseInt(br.readLine());
            parent = new int[n+1];


            for(int i = 0; i<n-1; i++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                parent[b] = a;
            }

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean[] checkparent = new boolean[n+1];

            while(a != 0){
                checkparent[a] = true;
                a = parent[a];
            }

            while(b != 0){
                if(checkparent[b]) break;
                b = parent[b];
            }

            System.out.println(b);
            t--;
        }
    }
}