import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int num = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = (int) Math.pow(2, Integer.parseInt(br.readLine()));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken())-1;
        int y = Integer.parseInt(st.nextToken())-1;

        map = new int[n][n];
        map[n-y-1][x] = -1;

        tile(0,0,n);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void tile(int x, int y, int size){
        num ++;
        int ns = size/2;
        if(check(x,y,ns)) map[x+ns-1][y+ns-1] = num;
        if(check(x+ns,y,ns)) map[x+ns][y+ns-1] = num;
        if(check(x,y+ns,ns)) map[x+ns-1][y+ns] = num;
        if(check(x+ns,y+ns,ns)) map[x+ns][y+ns] = num;

        if(size == 2) return;

        tile(x,y,ns);
        tile(x+ns,y,ns);
        tile(x,y+ns, ns);
        tile(x+ns, y+ns, ns);
    }

    public static boolean check(int x, int y, int size){
        for(int i = x; i<x+size; i++){
            for(int j = y; j<y+size; j++){
                if(map[i][j] != 0) return false;
            }
        }

        return true;
    }
}