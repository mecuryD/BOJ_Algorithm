import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/* input */
		int max = -1;
		int idx = 0;
		for (int i=1; i<10; i++) {
			int num = sc.nextInt();
			if (max<num) {
				max = num;
				idx = i;
			}
		}
		System.out.println(max);
		System.out.println(idx);
	}
}
