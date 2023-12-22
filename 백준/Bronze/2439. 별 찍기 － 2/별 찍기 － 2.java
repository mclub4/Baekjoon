import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int line = sc.nextInt();
		
		for(int i = 0; i < line; i++) {
			for (int j = line-1; j > i; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j < i+1; j ++) {
				System.out.print("*");
			}
			System.out.println();
		}
		sc.close();
	}
}