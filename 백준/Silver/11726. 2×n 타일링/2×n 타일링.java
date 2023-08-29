import java.io.IOException;
import java.util.Scanner;
// [BOJ11726] 2xn 타일링
public class Main {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 2xn 직사각형
		int[] dp = new int[N+1]; // dp-table
		if(N==1) {
			System.out.println(1);
		}else {			
			dp[1] = 1;
			dp[2] = 2;
			for(int i=3; i<=N; i++) {
				// 오버플로우를 대비하여 10007로 미리 나눠줘야 한다
				dp[i] = (dp[i-1] + dp[i-2]) % 10007;
			}
			System.out.println(dp[N]);
		}
	}
}
