import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] customer = new int[n];
        boolean[] coffe = new boolean[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<n; i++){
            customer[i] = Integer.parseInt(st.nextToken());
        }

        int cur = 0;
        int people = 0;
        int tmp = 0;
        int dish = 0;
//        int coffe = 0;
        boolean success = true;

        if(n!=0){
            while(cur <= customer[n-1]){
                int depart = customer[people];
//                System.out.println("현재 시간 : " + cur + ", " + "손님은 " + depart + "에 도착하는데 커피 상태는 " + coffe[people] + "고, " + dish + "개의 토기가 준비되어있다.");
                if(cur == depart){
                    if(coffe[people]){
                        people++;
                        cur++;
                        continue;
                    }
                    else{
                        success = false;
                        break;
                    }
                }
                if(tmp<=n-1){
                    if(cur + m >= customer[tmp] && dish>0 && !coffe[tmp]){
                        coffe[tmp] = true;
                        dish --;
                        cur ++;
                        tmp ++;
                        continue;
                    }
                }
                dish ++;
                cur ++;
            }
        }

        if(success) System.out.println("success");
        else System.out.println("fail");
    }
}