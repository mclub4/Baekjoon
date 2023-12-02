import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append("2000\n");
        for(int i = 0; i<1000; i++) sb.append("1 ");
        for(int i = 0; i<1000; i++) sb.append("1000 ");
        System.out.println(sb);
    }
}
