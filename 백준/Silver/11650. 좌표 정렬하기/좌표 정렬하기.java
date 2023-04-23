import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static class Position implements Comparable<Position>{
        int x;
        int y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Position o) {
            if(this.x == o.x) return this.y - o.y;
            else return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Position> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Position(x,y));
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n; i++){
            sb.append(list.get(i).x + " " + list.get(i).y + "\n");
        }

        System.out.println(sb);
    }
}