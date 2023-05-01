import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] a = new int[2];
        int[] b = new int[2];
        int[] c = new int[2];
        int[] d = new int[2];

        a[0] = Integer.parseInt(st.nextToken());
        a[1] = Integer.parseInt(st.nextToken());
        b[0] = Integer.parseInt(st.nextToken());
        b[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        c[0] = Integer.parseInt(st.nextToken());
        c[1] = Integer.parseInt(st.nextToken());
        d[0] = Integer.parseInt(st.nextToken());
        d[1] = Integer.parseInt(st.nextToken());

        long test1 = ccw(a,b,c)*ccw(a,b,d);
        long test2 = ccw(a,c,d)*ccw(b,c,d);
        if(test1 == 0 && test2 == 0) System.out.println(overlap(a,b,c,d));
        else if(test1<=0 && test2<=0) System.out.println(1);
        else System.out.println(0);
    }

    public static long ccw(int[] a, int[] b, int[] c){
        long x1 = a[0];
        long y1 = a[1];

        long x2 = b[0];
        long y2 = b[1];

        long x3 = c[0];
        long y3 = c[1];

        long answer = (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3);

        if(answer == 0) return 0;
        else return answer < 0 ? 1 : -1;
    }

    public static int overlap(int[] a, int[] b, int[] c, int[] d){
        if(Math.max(a[0], b[0]) < Math.min(c[0], d[0])) return 0;
        if(Math.max(a[1], b[1]) < Math.min(c[1], d[1])) return 0;
        if(Math.max(c[0], d[0]) < Math.min(a[0], b[0])) return 0;
        if(Math.max(c[1], d[1]) < Math.min(a[1], b[1])) return 0;
        return 1;
    }


}