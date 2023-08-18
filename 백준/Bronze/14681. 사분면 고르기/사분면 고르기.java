import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
        
		int X = in.nextInt();
		int Y = in.nextInt();
		
		if(X > 0) {
			if(Y > 0) {
				System.out.print(1);
			} 
			else {
				System.out.print(4);
			}
		} 
		
		else {
			if(Y > 0) {
				System.out.print(2);
			} 
			else {
				System.out.print(3);
			}
		}
	}
 
}