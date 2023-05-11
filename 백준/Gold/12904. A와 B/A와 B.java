import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int result = 0;

        while(a.length()!=b.length()){
            if(b.charAt(b.length()-1) == 'A'){
                b = b.substring(0, b.length()-1);
            }
            else{
                b = b.substring(0, b.length()-1);
                StringBuffer sb = new StringBuffer(b);
                b = sb.reverse().toString();
            }
        }

        if(a.equals(b)) System.out.println(1);
        else System.out.println(0);
    }
}
