import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Interviewer implements Comparable<Interviewer> {
    int first;
    int second;

    public Interviewer(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Interviewer o) {
        return this.first - o.first;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            Interviewer[] interviewers = new Interviewer[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                interviewers[i] = new Interviewer(a, b);
            }

            Arrays.sort(interviewers);

            int answer = 1;
            int king = interviewers[0].second;
            for (int i = 1; i < n; i++) {
                if (interviewers[i].second < king) {
                    answer++;
                    king = interviewers[i].second;
                }
            }
            System.out.println(answer);
        }


    }

}