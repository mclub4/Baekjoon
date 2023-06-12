import java.util.Scanner;
 
public class Main {
	public static void main(String args[]) {
 
		Scanner scan = new Scanner(System.in);
 
		int a = scan.nextInt();
 
		for (int i = 1; i <= a; i++) {
			int c = scan.nextInt();
			int d = scan.nextInt();
			System.out.println("Case #" + i + ": " + (c + d));
		}
 
		scan.close();
	}
}