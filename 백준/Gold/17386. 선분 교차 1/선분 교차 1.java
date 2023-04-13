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

        if(ccw(a,b,c)*ccw(a,b,d) < 0 && ccw(a,c,d)*ccw(b,c,d) < 0) System.out.print(1);
        else System.out.print(0);
    }

    public static long ccw(int[] a, int[] b, int[] c){
        long x1 = a[0];
        long y1 = a[1];

        long x2 = b[0];
        long y2 = b[1];

        long x3 = c[0];
        long y3 = c[1];

        return (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3) < 0 ? 1 : -1;
    }

}