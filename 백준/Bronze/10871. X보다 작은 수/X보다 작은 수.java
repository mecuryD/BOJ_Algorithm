import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/* input */
		int n = sc.nextInt();
		int x = sc.nextInt();

		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		/* check */
		for(int data : arr) { // 향상된 for문 (접근 속도 빠름)
			if(data < x) {
				System.out.print(data+" ");
			}
		}
	}
}
