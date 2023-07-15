import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	/* Main */
	public static void main(String[] args) {
		int n, m;
		int[] a, b;
		
		/* input*/
		Scanner scanner = new Scanner(System.in);
		
		// input A		
		n = scanner.nextInt();
		a = new int[n];
		for(int i=0;i<n;i++) {
			a[i] = scanner.nextInt();
		}
		
		// input B
		m = scanner.nextInt();
		b = new int[m];
		for(int i=0;i<m;i++) {
			b[i] = scanner.nextInt();
		}
		
		/* copy & sort */
		Arrays.sort(a);
		
		/* search */
		for (int i=0; i<m; i++) {
			if (search(a, b[i])) { // 존재하는 경우
				System.out.println('1');
			}else { // 존재하지 않는 경우
				System.out.println('0');
			}
		}
	}
	
	/* Binary Search */
	public static boolean search(int[] array, int value) {
		
		int low = 0; 				  // 탐색 범위의 왼쪽 끝
		int high = array.length - 1;  // 탐색 범위의 오른쪽 끝
		
		while(low<=high) { // low가 high보다 커지기 전까지 반복

			int mid = (low+high) / 2; // 중간 위치
			
			if (value < array[mid]) { // 중간 값보다 작은 경우
				high = mid - 1;
			}
			else if(value > array[mid]) { // 중간 값보다 큰 경우
				low = mid + 1;
			}
			else{ // 중간 값과 동일한 경우
				return true;
			}
		}
		// 찾고자 하는 값이 존재하지 않는 경우
		return false;
	}
}