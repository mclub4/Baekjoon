import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long answer = gcd(a,b);

        for(int i = 0; i<answer; i++) sb.append(1);

        System.out.print(sb);

    }

    public static long gcd(long a, long b){
        if(b == 0) return a;
        else return gcd(b, a%b);
    }
}