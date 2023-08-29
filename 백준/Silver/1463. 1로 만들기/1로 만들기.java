import java.util.Scanner;

// [BOJ1463] 1로 만들기
public class Main {
	public static void main(String[] args){
		// 입력 초기화
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 정수 N
		int[] dp = new int[N+1]; // DP Table
		
		// Bottom-Up DP
		int min;
		for(int i=2; i<=N; i++) {
			min = dp[i-1]+1; // 1을 뺀다
			
			if(i%2==0) // X가 2로 나누어 떨어지면,  2로 나눈다
				min = Math.min(min, dp[i/2]+1);
			
			if(i%3==0) // X가 3으로 나누어 떨어지면, 3으로 나눈다
				min = Math.min(min, dp[i/3]+1);
			
			dp[i] = min; // i값을 1로 만드는 최소 연산 횟수 저장
		}
		
		// 결과 출력
		System.out.println(dp[N]);
	}
}