import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int count = 0;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        move(0,0, (int) Math.pow(2,n));
        System.out.println(count);
    }

    public static void move(int x, int y, int size){
        if(size == 1){
            return;
        }
        int newsize = size/2;
        if(r<x+newsize && c<y+newsize){
            move(x,y, newsize);
        }
        else if(r<x+newsize && y+newsize<=c){
            count += (size*size)/4;
            move(x, y+newsize, newsize);
        }
        else if(x+newsize<=r && c<y+newsize){
            count += ((size*size)/4)*2;
            move(x+newsize, y, newsize);
        }
        else if(x+newsize<=r && y+newsize<=c){
            count += ((size*size)/4)*3;
            move(x+newsize, y+newsize, newsize);
        }
    }
}
