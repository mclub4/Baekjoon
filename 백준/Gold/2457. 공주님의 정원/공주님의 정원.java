import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Flower implements Comparable<Flower> {
    int start;
    int end;

    public Flower(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Flower o) {
        if (this.start == o.start) return o.end - this.end;
        return this.start - o.start;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Flower[] flowers = new Flower[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            String c = st.nextToken();
            String d = st.nextToken();
            String day1 = String.valueOf(b).length() == 1 ? "0" + b : b;
            String day2 = String.valueOf(d).length() == 1 ? "0" + d : d;
            int start = Integer.parseInt(a + day1);
            int end = Integer.parseInt(c + day2);
            flowers[i] = new Flower(start, end);
        }

        int answer = 0;
        int start = 301;
        int end = 0;
        int deadline = 1201;
        int idx = 0;
        Arrays.sort(flowers);

        while (true) {
            if (start >= deadline) break;
            boolean flag = false;
            for (int i = idx; i < n; i++) {
                if (flowers[i].start > start) break;
                if (flowers[i].end > end) {
                    end = flowers[i].end;
                    idx = i + 1;
                    flag = true;
                }
            }

            if (flag) {
                start = end;
                answer++;
            } else break;
        }

        System.out.println(end < deadline ? "0" : answer);

    }
}