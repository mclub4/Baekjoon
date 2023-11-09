import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long n = Integer.parseInt(br.readLine());
        long result = 1;
        long k = (long)1e12;

        for(long i=1; i<=n; i++) {
            result *=i;
            while(result%10==0)
                result/=10;
            result%=k;
        }

        result %= 100000;
        if(result<10000) sb.append(0);
        if(result<1000) sb.append(0);
        if(result<100) sb.append(0);
        if(result<10) sb.append(0);
        sb.append(result);
        System.out.print(sb);

    }
}