import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());
        

        boolean[] broken = new boolean[10];

        if(m!=0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i<m; i++){
                int tmp = Integer.parseInt(st.nextToken());
                broken[tmp] = true;
            }
        }

        int target = Math.abs(n - 100);

        if(target != 0){
            for(int i = 0; i<=999999; i++){
                String str = String.valueOf(i);

                boolean pressed = false;

                for(int j = 0; j<str.length(); j++){
                    if(broken[str.charAt(j) - '0']){
                        pressed = true;
                        break;
                    }
                }

                if(!pressed){
                    int gap = Math.abs(n-i); //+ - 누르는 횟수
                    target = Math.min(target, (gap + str.length()));
                }
            }
        }

        System.out.println(target);
    }
}