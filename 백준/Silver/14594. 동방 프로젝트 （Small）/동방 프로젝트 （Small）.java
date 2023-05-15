import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int start;
        int end;

        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o){
            if(this.start == o.start) return Integer.compare(this.end, o.end);
            else return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int result = n;
        int count = 0;
        if(m!=0){
            ArrayList<Node> nodelist = new ArrayList<>();

            for(int i = 0; i<m; i++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                nodelist.add(new Node(start, end));
            }

            Collections.sort(nodelist);

//            for(int i = 0; i<nodelist.size(); i++){
//                Node tmp = nodelist.get(i);
//                System.out.println(tmp.start + "," + tmp.end);
//            }

            int start = nodelist.get(0).start;
            int end = nodelist.get(0).end;

            count = end-start;

            for(int i = 1; i<nodelist.size(); i++){
                Node tmp = nodelist.get(i);
                int nx = tmp.start;
                int ny = tmp.end;
                if(start<=nx && end<=ny){
                    int tmp2;
                    if(nx>=start && nx<=end) tmp2 = end;
                    else tmp2 = nx;
                    end = ny;
                    count += (end-tmp2);
                }
                else if(end<=ny){
                    start = end;
                    end = ny;
                }
            }

        }

        System.out.println(result-count);
    }
}
