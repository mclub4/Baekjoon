import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer sb = new StringTokenizer(br.readLine());
        final int numA = Integer.parseInt(sb.nextToken());
        final int numB = Integer.parseInt(sb.nextToken());

        final double numP = 1 / (1 + Math.pow(10, (numB - numA) / 400.0));

        System.out.print(numP);
        br.close();
    }
}