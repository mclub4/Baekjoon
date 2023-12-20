import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //input
        Scanner scanner = new Scanner(System.in);
        int[] result = new int[5];

        int p = scanner.nextInt();
        int area = scanner.nextInt();
        int cnt = p * area;

        for (int i = 0; i < 5; i++) {
            int temp = scanner.nextInt();
            result[i] = temp - cnt;
        }

        for (int i : result) {
            System.out.println(i);
        }
    }
}