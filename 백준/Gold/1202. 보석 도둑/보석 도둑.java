import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Jewelry implements Comparable<Jewelry>{
        long price;
        long weight;

        public Jewelry(long weight, long price){
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(Jewelry o) {
            if(o.weight == this.weight){
                if(this.price>o.price) return -1;
                if(this.price == o.price) return 0;
                else return 1;
            }
            return Long.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Jewelry> jewelries = new ArrayList<>(n);
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            jewelries.add(new Jewelry(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }
        Collections.sort(jewelries);

        ArrayList<Long> bags = new ArrayList<>(k);
        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine(), " ");
            bags.add(Long.parseLong(st.nextToken()));
        }
        Collections.sort(bags);

        int idx = 0;

        PriorityQueue<Long> queue = new PriorityQueue<>(Comparator.reverseOrder());
        long cost = 0;

        for(int i = 0; i<k; i++){
            while(idx<n && bags.get(i) >= jewelries.get(idx).weight){
                queue.add(jewelries.get(idx).price);
                idx ++;
            }
            if(!queue.isEmpty()) cost += queue.poll();

        }

        System.out.println(cost);
    }
}

//아래는 이분탐색과 UF로 구현한 코드
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static class Jewel {
        int weight, value;

        public Jewel(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(value, weight);
        }

        Arrays.sort(jewels, Comparator.comparing((Jewel j) -> j.value).reversed());

        int[] bags = new int[K];
        parent = new int[K + 1];
        for (int i = 0; i < K; ++i) {
            int capacity = Integer.parseInt(br.readLine());
            bags[i] = capacity;
            parent[i] = i;
        }
        Arrays.sort(bags);

        parent[K] = K;

        long ans = 0;
        for (Jewel jewel : jewels) {
            int idx = lowerBound(bags, jewel.weight);
            if (idx < 0) {
                idx = -idx - 1;
            }
            int f = find(idx);
            if (f == K) continue;
            ans += jewel.value;
            union(f, f + 1);
        }

        System.out.println(ans);
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parent[x] = y;
    }

    public static int lowerBound(int[] data, int target) {
        int begin = 0;
        int end = data.length;

        while (begin < end) {
            int mid = (begin + end) / 2;

            if (data[mid] >= target) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return end;
    }
}

