import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for(int i = 0; i<T; i++){
            long a = 2;
            long b = Integer.parseInt(br.readLine());

            if(b != 1){
                long result = 1;
                b -= 2;
                long c = 1000000007;
                while(b>0){
                    if(b%2 != 0) result = result*a%c;
                    a *= a;
                    a %= c;
                    b /= 2;
                }
                sb.append(result + "\n");
            }
            else sb.append(1 + "\n");
        }
        System.out.println(sb);
    }
}