import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt(); 
		int N = sc.nextInt(); 
		int M = sc.nextInt(); 
		sc.close();

		if (K * N - M < 0) {
			System.out.println(0);
		} else
			System.out.println(K * N - M);
	}
}