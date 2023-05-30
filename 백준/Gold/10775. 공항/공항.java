import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        parent = new int[g+1];
        for(int i = 1; i<g+1; i++){
            parent[i] = i;
        }

        int count = 0;
        for(int i = 0; i<p; i++){
            int cur = Integer.parseInt(br.readLine());
            if(find(cur) == 0) break;
            union(cur, find(cur)-1);
            count++;
        }

        System.out.println(count);
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);

        if(parent_a != parent_b){
            if(parent_a<parent_b) parent[parent_b] = parent_a;
            else parent[parent_a] = parent_b;
        }
    }
}
