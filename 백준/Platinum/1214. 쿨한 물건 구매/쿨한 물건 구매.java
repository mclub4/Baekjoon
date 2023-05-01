import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int D,P,Q;
        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        if(P<Q){
            int tmp = Q;
            Q = P;
            P = tmp;
        }
        if(D%P == 0 || D%Q == 0) System.out.println(D);
        else{
            int P_num = D/P+1;
            int cost = P*P_num;
            int answer = 0;

            for(int i = P_num-1; i>=0; i--){
                if(i>=P) continue;
                int Q_num;
                if((D-P*i)%Q != 0) Q_num = (D-P*i)/Q+1;
                else  Q_num = (D-P*i)/Q;
                int tmp_cost = Q_num*Q + P*i;
                if(tmp_cost >= D){
                    answer = Math.min(tmp_cost, cost);
//                    System.out.println("P : " + i + " Q : " + Q_num);
//                    System.out.println(answer);
                    cost = answer;
                }
            }
            System.out.println(answer);
        }
    }
}