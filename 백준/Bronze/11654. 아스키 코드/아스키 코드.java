import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char a = (char)br.readLine().charAt(0);
        int num = (int)a;
        System.out.println(num);
    }
}