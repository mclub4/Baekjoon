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
