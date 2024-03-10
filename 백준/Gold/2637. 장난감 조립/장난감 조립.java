import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int num;
    int count;

    Node(int num, int count) {
        this.num = num;
        this.count = count;
    }
}

public class Main {
    static ArrayList<Node>[] list;
    static int[] input;
    static int[] answer;
    static boolean[] basic;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        basic = new boolean[n + 1];
        input = new int[n + 1];
        answer = new int[n + 1];

        list = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            basic[x] = true;
            list[x].add(new Node(y, k));
            input[y]++;
        }

        answer[n] = 1;
        topologysort();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < answer.length; i++) {
            if (!basic[i]) sb.append(i + " " + answer[i] + "\n");
        }
        System.out.println(sb);
    }

    public static void topologysort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < input.length; i++) {
            if (input[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node nxt : list[cur]) {
                input[nxt.num]--;
                answer[nxt.num] += answer[cur] * nxt.count;
                if (input[nxt.num] == 0) queue.add(nxt.num);
            }
        }
    }
}