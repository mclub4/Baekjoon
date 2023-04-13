import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dirx = {1,-1,0,0};
    static int[] diry = {0,0,1,-1};
    static int[][] startboard;
    static int[] attack;
    static int n,k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        startboard = new int[n][n];

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j<n; j++){
                startboard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        attack = new int[k];

        for(int i = 0; i<k; i++){
           attack[i] =  Integer.parseInt(st.nextToken());
        }

        int result = shot(0, startboard, startboard);
        System.out.print(result);
    }

    public static int shot(int tried, int[][] board, int[][] initial){
        if(tried == k) return 0;
        int answer = 0;

        for(int i = 0; i<n; i++){
            int score = 0;
            int[][] curboard = new int[n][n];
            for(int k=0; k<n; k++){
                for(int l=0; l<n; l++){
                    curboard[k][l] = board[k][l];
                }
            }

            int[][] realboard = new int[n][n];
            for(int k=0; k<n; k++){
                for(int l=0; l<n; l++){
                    realboard[k][l] = initial[k][l];
                }
            }
            
            for(int j = 0; j<n; j++){
                if(curboard[i][j] == 0) continue;
                else if(curboard[i][j]>=1 && curboard[i][j]<=9){
                    int tmp = curboard[i][j];
                    tmp -= attack[tried];
                    if(tmp>0) curboard[i][j] = tmp;
                    else{
                        score += realboard[i][j];
                        curboard[i][j] = 0;
                        int health = realboard[i][j]/4;
                        for(int k = 0; k<4; k++){
                            int x = i + diry[k];
                            int y = j + dirx[k];
                            if(x>=0 && x<n && y>=0 && y<n){
                                if(curboard[x][y] == 0){
                                    curboard[x][y] = health;
                                    realboard[x][y] = health;
                                }
                            }
                        }
                    }
                    break;
                }
                else if(curboard[i][j]>=10){
                    score += curboard[i][j];
                    curboard[i][j] = 0;
                    break;
                }
            }

            int diff = score + shot(tried+1, curboard, realboard);
            if(answer<diff) answer = diff;
        }
        return answer;
    }

}