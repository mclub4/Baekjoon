import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int[] suffix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        boolean[] P = new boolean[360000];
        for(int i = 0; i<n; i++){
            P[Integer.parseInt(st.nextToken())] = true;
        }

        suffix = new int[P.length];

        st = new StringTokenizer(br.readLine(), " ");
        boolean[] T = new boolean[360000*2];
        for(int i = 0; i<n; i++){
            int tmp = Integer.parseInt(st.nextToken());
            T[tmp] = true;
            T[tmp + 360000] = true;
        }

        getPI(P);
        if(KMP(T,P)) System.out.println("possible");
        else System.out.println("impossible");
    }

    public static void getPI(boolean[] P){
        int j = 0;
        for(int i = 1; i<P.length; i++){
            while(j>0 && P[i] != P[j]) j = suffix[j-1];
            if(P[i] == P[j]){
                suffix[i] = ++j;
            }
        }
    }

    public static boolean KMP(boolean[] T, boolean[] P){
        int j = 0;
        for(int i = 0; i<T.length-1; i++){
            while(j>0 && T[i] != P[j]){
                j = suffix[j-1];
            }
            if(T[i] == P[j]){
                if(j == P.length-1){
                    return true;
                }
                else ++j;
            }
        }
        return false;
    }
}