import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] player = new int[n+1]; //a+b의 합이 x임을 알고있으므로 a능력치만 기록한다. (굳이 b를 볼 필요 없음)
        for(int i = 1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            player[i] = Integer.parseInt(st.nextToken());
            Integer.parseInt(st.nextToken());
        }

        boolean[][][] dp = new boolean[n+1][k+1][x*k+1]; //dp[n][k][x*k] : n번째까지 봤을때 k명을 택했을때 a 능력치를 가지고 x*k 합을 만들 수 있는가?
        dp[1][1][player[1]] = true; //dp[1][1][player[1]] -> 1번째까지 봤을때 1명을 택했을때 player[1]의 능력치를 가지고 언제나 player[1]합을 만들 수 있음



        //첫번째는 이제 볼 필요 없음. 오로지 한경우 밖에 없기 때문에
        for(int i = 2; i<n+1; i++){
            //1명만 택했을때 player[i]의 합은 언제나 만들 수 있음
            dp[i][1][player[i]] = true;
            for(int j = 1; j<=k; j++){
                for(int s = 0; s<=x*k; s++){
//                    System.out.println(i + "번째 까지 봤을때 " + j + "명을 택했을때 " + s + "가 될 수 있는지 판단합니다");
                    //i-1번째까지 봤을때 j명을 가지고 s를 만들었다면 당연히 i번째까지 봤을떄도 만들 수 있음
                    //i-1번째까지 봤을때 j-1명가지고 s-player[i]의 합을 만들었다면 당연히 i번째에서 j명가지고 s를 만들 수 있음.
                    if(s < player[i]){
//                        System.out.println(dp[i-1][j][s] + "입니다.작아서 이럼 ㅈㅅ");
                        dp[i][j][s] = dp[i-1][j][s];
                    }
                    else{
//                        System.out.println(dp[i-1][j][s] + "와 " + dp[i-1][j-1][s-player[i]] + " 추신 : " + (s-player[i]));
                        dp[i][j][s] = dp[i-1][j][s] || dp[i-1][j-1][s-player[i]];
                    }
                }
            }
            dp[i][1][player[i]] = true;
        }

        int ans = 0;
        for(int i = 0; i<=x*k; i++){
            //모든 플레이어를 확인했을때, k명을 가지고 i의 합을 만들 수 있으면 가능한 경우임
            if(dp[n][k][i]){
                //a+b는 항상 일정하므로, 전체 합은 언제나 x*k임. 따라서, a의 합이 i라면 b의 합은 x*k-i
                //따라서 팀의 능력치는 i*(x*k-i)임. 그 중 최댓값을 찾아 출력함.
//                System.out.println(i + "택함");
                ans = Math.max(ans, i*(x*k-i));
            }
        }
        System.out.println(ans);
    }
}