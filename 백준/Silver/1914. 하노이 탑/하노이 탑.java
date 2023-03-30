import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        BigInteger big = new BigInteger("2");

        System.out.println(big.pow(n).subtract(new BigInteger("1")));
        
        if(n<=20) hanoi(n, 1, 3, 2);

        System.out.println(sb);
    }

    public static void hanoi(int object, int start, int end, int via){
        if(object == 1){
            sb.append(start + " " + end + "\n");
            return;
        }
        hanoi(object-1, start, via, end);
        sb.append(start + " " + end + "\n");
        hanoi(object-1, via, end, start);
    }
}