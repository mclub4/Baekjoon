import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static long m;
    static long[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        house = new long[n];

        for(int i = 0; i<n; i++){
            house[i] = Long.parseLong(br.readLine());
        }

        long low = 1;
        long high = house[n-1] - house[0] + 1;
        long mid = 0;

        Arrays.sort(house);

        while(low<high){
            mid = low + (high - low)/2;

//            System.out.println("low : " + low + ", high : " + high + ", mid : " + mid);
            if(count(mid)>=m){
                low = mid +1;
            }
            else high = mid;
        }

        if(low == 1) System.out.println(1);
        else System.out.println(low-1);
    }

    public static long count(long distance){
        long start = house[0];
        long count = 1;

        for(int i = 1; i<n; i++){
            if(house[i] - start >= distance){
                count ++;
                start = house[i];
            }
        }

//        System.out.println("거리 : " + distance + ", 공유기 개수 : " + count);
        return count;
    }
}
