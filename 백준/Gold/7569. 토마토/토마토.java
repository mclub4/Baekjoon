import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dirx = {1,-1,0,0,0,0};
    static int[] diry = {0,0,1,-1,0,0};
    static int[] dirz = {0,0,0,0,1,-1};
    static int[][][] box;
    static Queue<int[]> queue = new LinkedList<>();
    static int m,n,h;

    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        box = new int[n][m][h];

        for(int k = 0; k<h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k] == 1) queue.add(new int[]{i, j, k});
                }
            }
        }
        System.out.println(bfs());

    }

    public static int bfs(){
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int x1 = temp[0];
            int y1 = temp[1];
            int z1 = temp[2];
            for(int i = 0; i<6;i++){
                int x2 = x1 + dirx[i];
                int y2 = y1 + diry[i];
                int z2 = z1 + dirz[i];
                if(x2<0 || x2>=n || y2<0 || y2>=m || z2<0 || z2>=h) continue;
                if(box[x2][y2][z2] == 0){
                    box[x2][y2][z2] = box[x1][y1][z1] + 1;
                    queue.add(new int[]{x2, y2, z2});
                }
            }
        }

        int max = -1;
        for(int k = 0; k<h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (box[i][j][k] == 0) return -1;
                    max = Math.max(box[i][j][k], max);
                }
            }
        }

        return max-1;
    }
}
