import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true) {
            int n =sc.nextInt();
            int m =sc.nextInt();
            if(n==0 && m==0) break;
            if(n>m) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}