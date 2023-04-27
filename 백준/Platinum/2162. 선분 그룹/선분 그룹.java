import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Line{
        long x1,y1,x2,y2;

        public Line(long x1, long y1, long x2, long y2){
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        Line[] nodelist = new Line[n+1];
        parent = new int[n+1];

        for(int i = 1; i<n+1; i++){
            parent[i] = i;
        }

        for(int i = 1; i<n+1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            nodelist[i] = new Line(x1, y1, x2, y2);
        }
        
        for(int i = 1; i<n+1; i++){
            for(int j = 1; j<n+1; j++){
                if(find(i) != find(j)){
                    if(cross(nodelist[i], nodelist[j])){
                        union(i,j);
                    }
                }
            }
        }

        int[] cnt = new int[n+1];
        int max = 0;
        int size = 0;
        for(int i = 1; i<n+1; i++){
            cnt[parent[i]]++;
        }
        for(int i = 1; i<n+1; i++){
            if(max < cnt[i]) max = cnt[i];
            if(cnt[i] != 0) size++;
        }

        System.out.println(size);
        System.out.println(max);
    }

    public static boolean cross(Line line1, Line line2){
        //a<0 또는 a = 0
        long a = ccw(line1.x1, line1.y1, line1.x2, line1.y2, line2.x1, line2.y1) * ccw(line1.x1, line1.y1, line1.x2, line1.y2, line2.x2, line2.y2);
        //b<0 또는 b = 0
        long b = ccw(line2.x1, line2.y1, line2.x2, line2.y2, line1.x1, line1.y1) * ccw(line2.x1, line2.y1, line2.x2, line2.y2, line1.x2, line1.y2);
        if(a == 0 && b == 0)return overlap(line1, line2);
        if(a<=0 && b<=0) return true;
        return false;
    }

    public static boolean overlap(Line line1, Line line2){
        if(Math.max(line1.x1, line1.x2) < Math.min(line2.x1, line2.x2)) return false;
        if(Math.max(line1.y1, line1.y2) < Math.min(line2.y1, line2.y2)) return false;
        if(Math.max(line2.x1, line2.x2) < Math.min(line1.x1, line1.x2)) return false;
        if(Math.max(line2.y1, line2.y2) < Math.min(line1.y1, line1.y2)) return false;
        return true;
    }

    public static int ccw(long x1, long y1, long x2, long y2, long x3, long y3){
        long result = (x1*y2+x2*y3+x3*y1) - (x2*y1+x3*y2+x1*y3);
        if(result>0) return -1;
        else if(result == 0) return 0;
        else return 1;
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);
        
        if(parent_a != parent_b){
            if (parent_a > parent_b) {
                parent[parent_a] = parent_b;
            } else {
                parent[parent_b] = parent_a;
            }
        }
    }
}