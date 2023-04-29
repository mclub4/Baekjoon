import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int[] suffix;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        String P = br.readLine();
        suffix = new int[P.length()+1];
        list = new ArrayList<>();
        maketable(P);
        KMP(T,P);


        System.out.println(list.size());
        for(int i : list){
            System.out.print(i + " ");
        }
    }

    public static void maketable(String P){
        int j = 0;
        for(int i = 1; i<P.length(); i++){
            while(P.charAt(j) != P.charAt(i) && j>0){
                j = suffix[j-1];
            }
            if(P.charAt(j) == P.charAt(i)){
                suffix[i] = ++j;
            }
        }
    }

    public static void KMP(String T, String P){
        int n = T.length();
        int m = P.length();
        int j = 0;
        for(int i = 0; i<n; i++){
            while(j>0 && T.charAt(i) != P.charAt(j)) j = suffix[j-1];
            if(T.charAt(i) == P.charAt(j)){
                if(j == m-1){
                    list.add(i - m + 2);
                    j = suffix[j];
                }
                else j++;
            }
        }
    }


}
