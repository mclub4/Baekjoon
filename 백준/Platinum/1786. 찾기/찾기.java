import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String T,P;
    static int[] pi;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = br.readLine();
        P = br.readLine();

        pi = new int[P.length()];
        maketable();
        KMP();

        StringBuilder sb = new StringBuilder();
        sb.append(list.size() + "\n");
        for(int i = 0; i<list.size(); i++){
            sb.append(list.get(i) + " ");
        }
        System.out.println(sb);
    }

    public static void maketable(){
        int j = 0;
        for(int i = 1; i<P.length(); i++){
            while(j>0 && P.charAt(j) != P.charAt(i)){
                j = pi[j-1];
            }
            if(P.charAt(i) == P.charAt(j)){
                pi[i] = ++j;
            }
        }
    }

    public static void KMP(){
        int j = 0;
        for(int i = 0; i<T.length(); i++){
            while(j>0 && P.charAt(j) != T.charAt(i)){
                j = pi[j-1];
            }
            if(P.charAt(j) == T.charAt(i)){
                if(j == P.length()-1){
                    list.add(i - P.length() + 2);
                    j = pi[j];
                }
                else j++;
            }
        }
    }
}