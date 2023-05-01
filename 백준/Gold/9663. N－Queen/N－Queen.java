import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int count = 0;
    static int[] queen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        queen = new int[n];
        nqueen(0);
        System.out.println(count);
    }

    public static void nqueen(int depth){
        if(depth == n){
            count ++;
            return;
        }

        for(int i = 0; i<n; i++){
            queen[depth] = i;
            if(check(depth)) nqueen(depth+1);
        }
    }

    public static boolean check(int depth){
        //같은 행에 위치했는가?
        for(int i = 0; i<depth; i++){
            if(queen[depth] == queen[i]) return false;
        }
        //같은 대각선에 위치했는가?
        for(int i = 0; i<depth; i++){
            if(Math.abs(depth-i) == Math.abs(queen[depth] - queen[i])) return false;
        }
        return true;
    }
}