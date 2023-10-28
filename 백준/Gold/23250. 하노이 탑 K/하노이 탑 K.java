import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static BigInteger compare;
    static String answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        compare = new BigInteger(st.nextToken());

        hanoi_tower(n, compare, 1, 3);

        System.out.println(answer);
    }

    public static void hanoi_tower(int n, BigInteger k, int start, int end){
        BigInteger skip = new BigInteger("1").shiftLeft(n-1);
        if(n == 0) return;
        if(k.compareTo(skip) == -1 || k.compareTo(skip) == 0){
            answer = start + " " + end;
            hanoi_tower(n-1, k, start, 6-start-end);
        }
        else{
            hanoi_tower(n-1, k.subtract(skip), 6-start-end, end);
        }
    }
}