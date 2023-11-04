import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] gameboard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String str = br.readLine();
            if(str.equals("end")) break;
            gameboard = new char[3][3];

            int x = 0;
            int o = 0;

            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    char tmp = str.charAt(i*3+j);
                    gameboard[i][j] = tmp;
                    if(tmp == 'X') x++;
                    if(tmp == 'O') o++;
                }
            }

            if(x < o) sb.append("invalid\n");
            else if(x - o >=2)sb.append("invalid\n");
            else if(bingo('X') && bingo('O')) sb.append("invalid\n");
            else if(bingo('O') && (x-o)>0) sb.append("invalid\n");
            else if(bingo('X') && (x==o)) sb.append("invalid\n");
            else if(bingo('X') || bingo('O')) sb.append("valid\n");
            else if(x+o == 9) sb.append("valid\n");
            else sb.append("invalid\n");

        }


        System.out.println(sb);
    }

    public static boolean bingo(char ch){
        for(int i = 0; i<3; i++){
            if(gameboard[i][0] == ch && gameboard[i][1] == ch && gameboard[i][2] == ch) return true;
        }
        for(int i = 0; i<3; i++){
            if(gameboard[0][i] == ch && gameboard[1][i] == ch && gameboard[2][i] == ch) return true;
        }
        if(gameboard[0][0] == ch && gameboard[1][1] == ch && gameboard[2][2] == ch) return true;
        if(gameboard[0][2] == ch && gameboard[1][1] == ch && gameboard[2][0] == ch) return true;

        return false;
    }


}