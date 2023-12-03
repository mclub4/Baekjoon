import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Integer> query = new ArrayList<>();

        //DDR 쿼리 저장
        while(true){
            int tmp = Integer.parseInt(st.nextToken());
            if(tmp == 0) break;
            query.add(tmp);
        }

        int n = query.size();
        //dp[i][j][k] : i번째 DDR 쿼리를 처리하는 시점에서, 왼발이 j에 있고 오른발이 k에 있을때 힘의 최솟값
        int[][][] dp = new int[n+1][5][5];

        //최솟값을 구하는 것이니 최댓값으로 초기화. 이때, MAX_VALUE로 해버리면 오버플로우가 날 수도 있으니 적당히 큰값으로
        for(int i = 0; i<n+1; i++){
            for(int j = 0; j<5; j++){
                for(int k = 0; k<5; k++){
                    dp[i][j][k] = 2147483643;
                }
            }
        }

        //초기에는 두 발 모두 중앙에 있다고 했고 그때는 힘이 0이다.
        dp[0][0][0] = 0;

        for(int i = 1; i<n+1; i++){
            int next = query.get(i-1);

            for(int j = 0; j<5; j++){
                for(int k = 0; k<5; k++){
                    //(i-1)번째에서 왼발을 j위치에서 next로 옮기는 경우 힘의 최솟값
                    if(next != k) dp[i][next][k] = Math.min(dp[i][next][k], dp[i-1][j][k] + distance(j, next));
                    //(i-1)번째에서 오른발을 k위치에서 next로 옮기는 경우 힘의 최솟값
                    if(next != j) dp[i][j][next] = Math.min(dp[i][j][next], dp[i-1][j][k] + distance(k, next));
                }
            }
        }

        int min = Integer.MAX_VALUE;

        //제일 작은 값을 찾는다
        for(int j = 0; j<5; j++){
            for(int k = 0; k<5; k++){
                min = Math.min(dp[n][j][k],min);
            }
        }


        System.out.println(min);
    }

    //before은 이동하기 전의 위치, after는 오른발의 위치
    public static int distance(int before, int after){
        //before와 after가 같으면 제자리를 한번 더 누른 것이기 때문에 힘이 1
        if(before == after) return 1;
        //before가 0이었다면 중앙에서 이동한 것이므로 힘이 2
        else if(before == 0) return 2;
        //before와 after의 차이 절댓값이 4라면 완전 반대 방향으로 움직인 것이므로 힘이 4
        else if(Math.abs(before-after) == 2) return 4;
        //나머지 경우는 힘이 3
        else return 3;
    }
}