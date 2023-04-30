import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String tmp = br.readLine();
        while(!tmp.equals(".")){
            int[] suffix = maketable(tmp);
            int repeat = tmp.length() - suffix[tmp.length()-1];
            if(tmp.length()%repeat == 0){
                int answer = tmp.length()/repeat;
                System.out.println(answer);
            }
            else System.out.println(1);

            tmp = br.readLine();
        }
    }

    public static int[] maketable(String P){
        int j = 0;
        int[] suffix = new int[P.length()];
        for(int i = 1; i<P.length(); i++){
            while(P.charAt(j) != P.charAt(i) && j>0){
                j = suffix[j-1];
            }
            if(P.charAt(j) == P.charAt(i)){
                suffix[i] = ++j;
            }
        }
        return suffix;
    }
}