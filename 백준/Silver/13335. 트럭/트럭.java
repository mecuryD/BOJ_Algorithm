import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/* input */
		int n = sc.nextInt();
		int w = sc.nextInt();
		int l = sc.nextInt();
		int[][] arr = new int[2][n];
		for(int i=0; i<n; i++) {
			arr[0][i] = sc.nextInt(); 	// 트럭의 무게
			arr[1][i] = w; 				// 트럭이 건너야 할 다리의 길이
		}

		/* calculate */
		int time = 0;
		int count = 0;
		int start = 0;
		int finish = 0;
		int weight = 0;
		
		// 모든 트럭이 다리를 건너면 종료
		while(count<n){ 
			time++;
			weight = 0;
		
			// 현재 다리 위에 있는 트럭 한 칸씩 이동
			for(int i=start; i<=finish; i++) { 
				arr[1][i]--;
			}
			
			// 맨 앞의 트럭이 다리를 건넜으면 다리를 건너는 트럭 리스트에서 제외
			if(arr[1][start] < 0) {
				start++;
				count++;
				if(count == n) {
					break;
				}
			}
			
			// 현재 다리 하중 계산
			for(int i=start; i<=finish; i++) { 
				weight += arr[0][i];
			}
			
			// 대기 중인 트럭 1대의 무게가 남은 하중보다 작다면 트럭 리스트에 추가
			if(time==1) {
				continue;
			}
			if(finish<n-1 && weight+arr[0][finish+1] <= l) {
				finish++;
				arr[1][finish]--;
			}
		}
		System.out.println(time);
	}

}
