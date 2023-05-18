import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int white = 0;
    static int blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        board = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        coloring(0,0,n);

        System.out.println(white);
        System.out.println(blue);
    }

    public static void coloring(int x, int y, int size){
        if(checkColor(x,y,size)){
            if(board[x][y] == 0) white++;
            else if(board[x][y] == 1) blue++;
            return;
        }

        int newsize = size/2;
        coloring(x,y, newsize);
        coloring(x+newsize, y, newsize);
        coloring(x, y+newsize, newsize);
        coloring(x+newsize, y+newsize, newsize);
    }

    public static boolean checkColor(int x, int y, int size){
        int color = board[x][y];
        for(int i = x; i<x+size; i++){
            for(int j = y; j<y+size; j++){
                if(board[i][j] != color) return false;
            }
        }
        return true;
    }
}
