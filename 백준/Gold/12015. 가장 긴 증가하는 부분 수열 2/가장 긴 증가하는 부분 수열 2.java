import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];
    static boolean end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] seq = new int[n];
        int[] LIS = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i<n; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        LIS[0] = seq[0];
        int length = 1;

        for(int i = 1; i<n; i++){
            int cur = seq[i];

            if(LIS[length-1] < cur){
                length++;
                LIS[length-1] = cur;
            }
            else{
                int low = 0;
                int high = length;
                while(low<high){
                    int mid = (low+high)/2;
                    if(LIS[mid] < cur) low = mid + 1;
                    else high = mid;
                }
                LIS[low] = cur;
            }

        }

        System.out.println(length);
    }
}
