import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int[][] matrix = new int[n][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] answer = squre(matrix, b);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                sb.append(answer[i][j]%1000 + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static int[][] squre(int[][] matrix, long b){
        if(b == 0 || b==1) return matrix;

        int[][] ret = squre(matrix, b/2);

        ret = multiply(ret, ret);

        if(b%2 != 0){
            ret = multiply(ret, matrix);
        }

        return ret;
    }

    public static int[][] multiply(int[][] matrix, int[][] matrix2){
        int[][] tmp = new int[n][n];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                for(int k = 0; k<n; k++){
                    tmp[i][j] += ((matrix[i][k]*matrix2[k][j])%1000);
                    tmp[i][j] %= 1000;
                }
            }
        }

        return tmp;
    }
}