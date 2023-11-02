import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws  IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");

        int m = Integer.parseInt(st.nextToken());

        boolean[] broke = new boolean[10];

        if(m != 0){
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i<m; i++){
                int a = Integer.parseInt(st.nextToken());
                broke[a] = true;
            }
        }

        int target = Math.abs(n - 100);

        if(target != 0){
            for(int i = 0; i<=999899; i++){
                String str = String.valueOf(i);

                boolean pressed = false;
                for(int j = 0; j<str.length(); j++){
                    if(broke[str.charAt(j) - '0']){
                        pressed = true;
                        break;
                    }
                }
                if(!pressed){
                    int gap = Math.abs(n - i);
                    target = Math.min(target, (gap + str.length()));
                }
            }
        }

        System.out.print(target);


    }
}
