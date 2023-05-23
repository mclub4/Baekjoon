import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Node{
        int end;
        int cost;

        public Node(int end, int cost){
            this.end = end;
            this.cost = cost;
        }
    }
    static ArrayList<Node>[] nodelist;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        nodelist = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            nodelist[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodelist[start].add(new Node(end, cost));
            nodelist[end].add(new Node(start, cost));
        }

        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
//            bfs(start,k);
            System.out.println(bfs(start, k));
        }
    }

    public static int bfs(int start, int k){
        int video = 0;
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

//        System.out.println("초기 값 설정 : " + start);
        for(int i = 0; i<nodelist[start].size(); i++){
            if(nodelist[start].get(i).cost >= k){
//                System.out.println(nodelist[start].get(i).end);
                queue.add(nodelist[start].get(i));
                visited[nodelist[start].get(i).end] = true;
                video ++;
            }
        }
        visited[start] = true;

//        System.out.println("이후 값");
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int end = node.end;
//            System.out.println("여기서 시작 : " + end);

            for(int i = 0; i<nodelist[end].size(); i++){
                if(nodelist[end].get(i).cost >= k && !visited[nodelist[end].get(i).end]){
//                    System.out.println(nodelist[end].get(i).end);
                    queue.add(nodelist[end].get(i));
                    visited[nodelist[end].get(i).end] = true;
                    video ++;
                }
            }
        }

        return video;
    }
}
