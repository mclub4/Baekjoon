import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Position{
        int x,y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n,m;
    static char[][] board;
    static boolean[] key;
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static int[] start = new int[2];
    static int[] goal = new int[2];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        key = new boolean[26];

        for(int i = 0; i<n; i++){
            String tmp = br.readLine();
            for(int j = 0; j<m; j++){
                board[i][j] = tmp.charAt(j);
                if(board[i][j] == '@'){
                    start[0] = i;
                    start[1] = j;
                }
                if(board[i][j] == '!'){
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }

        int count = bfs(start[0], start[1]);
        System.out.println(count);
        System.out.println(sb);
    }

    public static int bfs(int a, int b){
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(a,b));

        boolean[][] visited = new boolean[n][m];
        visited[a][b] = true;

        ArrayList<Position>[] door = new ArrayList[26];

        for(int i = 0; i<26; i++){
            door[i] = new ArrayList<>();
        }

        int count = 0;

        while(!queue.isEmpty()){
            Position cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            count ++;
            sb.append((x+1) + " " + (y+1) + "\n");
            if(x == goal[0] && y == goal[1]) return count;


            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(board[nx][ny] == '#' || visited[nx][ny]) continue;

                int doorCheck = board[nx][ny] - 'A';
                int keyCheck = board[nx][ny] - 'a';
                visited[nx][ny] = true;


                if(board[nx][ny] == '*' || board[nx][ny] == '!') queue.add(new Position(nx, ny));
                else if(doorCheck>=0 && doorCheck<=25){
                    if(key[doorCheck]) queue.add(new Position(nx, ny));
                    else{
                        door[doorCheck].add(new Position(nx, ny));
                    }
                }
                else if(keyCheck>=0 && keyCheck<=25){
                    key[keyCheck] = true;
                    queue.add(new Position(nx, ny));
                    for(int j = 0; j<door[keyCheck].size();){
                        Position tmp = door[keyCheck].get(j);
                        int tmpx = tmp.x;
                        int tmpy = tmp.y;
                        queue.add(new Position(tmpx, tmpy));
                        door[keyCheck].remove(tmp);
                    }
                }
            }
        }

        return -1;
    }
}
