import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [BOJ2579] 계단 오르기
public class Main{
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 계단의 개수
		int[] arr = new int[N+1]; // 각 계단의 점수
		for(int i=1; i<=N; i++) { 
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// Bottom-Up DP
		int[] dp = new int[N+1]; // dp-table
        dp[1] = arr[1]; // 첫 번째 계단
        if(N>=2) { // 두 번째 계단
        	dp[2] = arr[1] + arr[2];
        }
        
		for(int i=3; i<=N; i++) {
			dp[i] = arr[i] + Math.max(dp[i-2], arr[i-1] + dp[i-3]);
		}
			
		System.out.println(dp[N]);
	}
}