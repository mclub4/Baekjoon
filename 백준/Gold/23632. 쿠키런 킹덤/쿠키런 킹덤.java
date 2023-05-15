import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] input;
    static ArrayList<Integer>[] product;
    static ArrayList<Integer>[] plan;
    static ArrayList<Integer> result = new ArrayList<>();
    static int t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[] building = new int[m];

        for(int i = 0; i<m; i++){
            building[i] = Integer.parseInt(st.nextToken());
        }

        product = new ArrayList[n+1];
        plan = new ArrayList[n+1];

        product[0] = new ArrayList<>();
        plan[0] = new ArrayList<>();

        for(int i = 1; i<n+1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int ret = Integer.parseInt(st.nextToken());
            product[i] = new ArrayList<>();
            plan[i] = new ArrayList<>();
            for(int j = 0; j<ret; j++){
                product[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        input = new int[n+1];
        for(int i = 0; i<n-m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            int ret = Integer.parseInt(st.nextToken());
            input[idx] = ret;
            for(int j = 0; j<ret; j++){
                int rs = Integer.parseInt(st.nextToken());
                plan[rs].add(idx);
            }
        }

        topologysort();
        System.out.println(result.size());

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<result.size(); i++){
            sb.append(result.get(i) + " ");
        }
        System.out.println(sb);
    }

    public static void topologysort(){
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 1; i<input.length; i++){
            if(input[i] == 0) queue.add(new int[]{i, 0});
        }

        boolean[] check = new boolean[input.length];

        while(!queue.isEmpty()){
            int[] element = queue.poll();
            int v = element[0];
            int time = element[1];
            if(time>t) break;

            result.add(v);
            for(int i = 0; i<product[v].size(); i++){
                int resource = product[v].get(i);

                if(check[resource]) continue;
                check[resource] = true;

                for(int j = 0; j<plan[resource].size(); j++){
                    int building = plan[resource].get(j);
                    input[building]--;
                    if(input[building] == 0) queue.add(new int[]{building, time+1});
                }
            }
        }
    }
}