import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> house = new ArrayList<>();
    static ArrayList<int[]> choice = new ArrayList<>();
    static int[][] city;
    static boolean[] visited;
    static int n,m;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<n; j++){
                int tmp = Integer.parseInt(st.nextToken());
                city[i][j] = tmp;
                if(tmp == 1) house.add(new int[]{i,j});
                if(tmp == 2) chicken.add(new int[]{i,j});
            }
        }

        visited = new boolean[chicken.size()];

        if(m != 0)  dfs(0,0);
        else result = 0;

        System.out.println(result);
    }

    public static void dfs(int start, int depth){
        if(depth == m){
            int distance = 0;
            for(int[] house_pos : house){
                int tmp = Integer.MAX_VALUE;
                for(int[] chicken_pos : choice){
                    int abs = Math.abs(house_pos[0] - chicken_pos[0]) + Math.abs(house_pos[1] - chicken_pos[1]);
                    tmp = Math.min(tmp, abs);
                }
                distance += tmp;
            }
            result = Math.min(distance, result);
            return;
        }

        for(int i = start; i<chicken.size(); i++){
            if(depth == 0) choice = new ArrayList<>();
            visited[i] = true;
            int[] test = chicken.get(i);
            choice.add(test);
            dfs(i+1, depth+1);
            choice.remove(test);
            visited[i] = false;
        }
    }
}