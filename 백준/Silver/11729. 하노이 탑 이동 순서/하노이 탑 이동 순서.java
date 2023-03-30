import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        sb.append(Math.round(Math.pow(2,n)) -1 + "\n");

        hanoi(n, 1, 3, 2);

        System.out.println(sb);
    }

    public static void hanoi(int object, int start, int end, int via){
        if(object == 1){
            sb.append(start + " " + end + "\n");
            return;
        }
        hanoi(object-1, start, via, end);
        sb.append(start + " " + end + "\n");
        hanoi(object-1, via, end, start);
    }
}