import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] standard = {{1,1}, {1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        System.out.println(fibo(standard, n-1)[0][0]);

    }

    public static long[][] fibo(long[][] arr, long n){
        if(n == 0 || n==1) return arr;

        long[][] ret = fibo(arr, n/2);

        ret = multiply(ret, ret);

        if(n%2 != 0){
            ret = multiply(ret, standard);
        }

        return ret;
    }

    public static long[][] multiply(long[][] arr, long[][] origin){
        long[][] answer = new long[2][2];

        answer[0][0] = (arr[0][0]*origin[0][0] + arr[0][1]*origin[1][0])%1000000007;
        answer[0][1] = (arr[0][0]*origin[0][1] + arr[0][1]*origin[1][1])%1000000007;
        answer[1][0] = (arr[1][0]*origin[0][0] + arr[1][1]*origin[1][0])%1000000007;
        answer[1][1] = (arr[1][0]*origin[0][1] + arr[1][1]*origin[1][1])%1000000007;

        return answer;
    }
}