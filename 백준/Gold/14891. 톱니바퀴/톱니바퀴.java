import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] gear = new char[5][8];
    static int[] d = new int[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
            gear[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            int rotate = Integer.parseInt(st.nextToken());
            d = new int[5];
            d[cur] = rotate;

            check(cur);
            rotate();
        }

        int answer = 0;
        if (gear[1][0] == '1') answer += 1;
        if (gear[2][0] == '1') answer += 2;
        if (gear[3][0] == '1') answer += 4;
        if (gear[4][0] == '1') answer += 8;

        System.out.println(answer);

    }

    public static void check(int now) {
        for (int i = now - 1; i > 0; i--) {
            if (gear[i][2] != gear[i + 1][6]) {
                d[i] = -d[i + 1];
            } else break;
        }

        for (int i = now + 1; i <= 4; i++) {
            if (gear[i - 1][2] != gear[i][6]) {
                d[i] = -d[i - 1];
            } else break;
        }
    }

    public static void rotate() {
        for (int i = 1; i < 5; i++) {
            if (d[i] == -1) {
                char tmp = gear[i][0];
                for (int j = 0; j < 7; j++) {
                    gear[i][j] = gear[i][j + 1];
                }
                gear[i][7] = tmp;
            } else if (d[i] == 1) {
                char tmp = gear[i][7];
                for (int j = 7; j > 0; j--) {
                    gear[i][j] = gear[i][j - 1];
                }
                gear[i][0] = tmp;
            }
        }
    }


}
