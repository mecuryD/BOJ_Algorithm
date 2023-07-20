import java.util.Scanner;

// 10807번. 개수 세기
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/* input*/
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i<n; i++) {
			arr[i] = sc.nextInt();
			}
		int key = sc.nextInt();

		/* count */
		int count = 0;
		for(int i=0; i<n; i++) {
			if(arr[i]==key) {
				count++;
			}
		}
		System.out.println(count);
	}
}