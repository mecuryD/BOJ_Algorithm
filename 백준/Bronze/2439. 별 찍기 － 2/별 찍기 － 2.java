import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n-i-1; j++) { // 공백 출력
				System.out.print(" ");
			}
			
			for(int j=n-i-1; j<n; j++) { // 별 출력
				System.out.print("*");
			}
			System.out.println("");
		}
	}
}