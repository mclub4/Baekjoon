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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while(t>0){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            board = new char[n+2][m+2];
            key = new boolean[26];

            String tmp = "";
            for(int i = 0; i<n+2; i++){
                if(i!=0 && i!=n+1) tmp = br.readLine();
                for(int j = 0; j<m+2; j++){
                    if(i==0 || i==n+1) board[i][j] = '.';
                    else if(j == 0 || j==m+1) board[i][j] = '.';
                    else board[i][j] = tmp.charAt(j-1);
                }
            }

            tmp = br.readLine();
            for(int i = 0; i<tmp.length(); i++){
                if(tmp.charAt(i) == '0') break;
                key[tmp.charAt(i) - 'a'] = true;
            }

            System.out.println(bfs(0,0));

            t--;
        }
    }

    public static int bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a,b});

        boolean[][] visited = new boolean[n+2][m+2];
        visited[a][b] = true;
        int document = 0;

        ArrayList<Position>[] door = new ArrayList[26];

        for(int i = 0; i<26; i++){
            door[i] = new ArrayList<>();
        }

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i = 0; i<4; i++){
                int nx = x + dirx[i];
                int ny = y + diry[i];

                if(nx<0 || ny<0 || nx>=n+2 || ny>=m+2) continue;
                if(board[nx][ny] == '*' || visited[nx][ny]) continue;

                int doorCheck = board[nx][ny] - 'A';
                int keyCheck = board[nx][ny] - 'a';
                visited[nx][ny] = true;

                if(board[nx][ny] == '.') queue.add(new int[]{nx,ny});
                else if(board[nx][ny] == '$'){
                    queue.add(new int[]{nx,ny});
//                    System.out.println(nx + ", " + ny + "에 있는 문서를 훔쳤지롱");
                    document ++;
                }
                else if(doorCheck>=0 && doorCheck<=25){
                    if(key[doorCheck]) queue.add(new int[]{nx, ny});
                    else{
//                        System.out.println(nx + ", " + ny + "에서 열쇠가 없어서 대기중 ㅠㅠ" + doorCheck);
                        door[doorCheck].add(new Position(nx, ny));
                    }
                }
                else if(keyCheck>=0 && keyCheck<=25){
                    key[keyCheck] = true;

//                    System.out.println(keyCheck + "의 키를 얻어서 " + door[keyCheck].size() + "개의 문을 연다!");
                    queue.add(new int[]{nx, ny});
                    for(int j = 0; j<door[keyCheck].size();){
                        Position tmp = door[keyCheck].get(j);
                        int tmpx = tmp.x;
                        int tmpy = tmp.y;
//                        System.out.println(tmpx + ", " + tmpy + " : 문이 열렸다!");
                        queue.add(new int[]{tmpx, tmpy});
                        door[keyCheck].remove(tmp);
                    }
                }
            }
        }

        return document;
    }
}
