import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/* input */
		int n = sc.nextInt();
		int min = 1000000;
		int max = -1000000;
		
		for(int i=0; i<n; i++) {
			int num = sc.nextInt();
			
			/* Min, Max */
			min = num < min ? num : min;
			max = num > max ? num : max;
		}
		System.out.printf("%d %d",min, max);
	}

}