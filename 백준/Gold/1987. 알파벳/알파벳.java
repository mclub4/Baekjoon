import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int r,c;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static char[][] board;
    static boolean[][] visited;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];

        for(int i = 0; i<r; i++){
            String tmp = br.readLine();
            for(int j = 0; j<c; j++){
                board[i][j] = tmp.charAt(j);
            }
        }

        ArrayList<Character> alphabet = new ArrayList<>(c);
        visited = new boolean[r][c];
        alphabet.add(board[0][0]);
        visited[0][0] = true;
        dfs(new int[]{0,0}, alphabet, 1);

        System.out.println(max);
    }

    public static void dfs(int[] start, ArrayList<Character> alphabet, int count){
        max = Math.max(count,max);
        for(int i = 0; i<4; i++){
            int nx = start[0] + dirx[i];
            int ny = start[1] + diry[i];
            if(nx>=0 && ny>=0 && nx<r && ny<c){
                if(!visited[nx][ny]){
                    if(alphabet.contains(board[nx][ny])) continue;
                    visited[nx][ny] = true;
                    alphabet.add(board[nx][ny]);
                    dfs(new int[]{nx,ny}, alphabet, count+1);
                    alphabet.remove(alphabet.indexOf(board[nx][ny]));
                    visited[nx][ny] = false;
                }
            }
        }
    }


}
