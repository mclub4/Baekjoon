import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] suffix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        while(st.hasMoreTokens()){
            sb.append(st.nextToken());
        }
        String P = sb.toString();

        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");

        while(st.hasMoreTokens()){
            sb.append(st.nextToken());
        }
        String T = sb.toString();

        suffix = new int[P.length()];
        getpi(P);
        long count = KMP(T+T,P);
        long num = gcd(n, count);

        System.out.println(count/num + "/" + n/num);
    }

    public static long gcd(long a, long b){
        if(b == 0) return a;
        else return gcd(b, a%b);
    }
    public static void getpi(String P){
        int j = 0;
        for(int i = 1; i<P.length(); i++){
            while (j>0 && P.charAt(i) != P.charAt(j)) j =  suffix[j-1];
            if(P.charAt(j) == P.charAt(i)){
                suffix[i] = ++j;
            }
        }
    }

    public static long KMP(String T, String P){
        int j = 0;
        long count = 0;
        for(int i = 0; i<T.length()-1; i++){
            while(j>0 && T.charAt(i) != P.charAt(j)){
                j = suffix[j-1];
            }
            if(T.charAt(i) == P.charAt(j)){
                if(j == P.length()-1){
                    count ++;
                    j = suffix[j];
                }
                else ++j;
            }
        }
//        if(count == T.length()) return 1;
        return count;
    }
}