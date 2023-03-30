import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int answer1 = gcd(a,b);
        int answer2 = (a*b)/answer1;

        System.out.println(answer1);
        System.out.println(answer2);

    }

    public static int gcd(int a, int b){
        if(b == 0) return a;
        else return gcd(b, a%b);
    }
}