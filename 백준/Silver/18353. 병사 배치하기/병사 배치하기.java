import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [백준18353] 병사 배치하기
public class Main{
	
	// [문제설명]
	// N명의 병사가 무작위로 나열
	// 전투력이 높은 병사부터 앞쪽에 오도록 내림차순 배치를 해야 한다
	// 배치 과정에서는 특정 위치의 병사를 열외시키는 방법을 이용한다
	
	// [아이디어]
	// 가장 긴 감소하는 부분 수열처럼 DP를 이용하면 될 것 같다
	// 현재 인덱스까지 가장 긴 감소하는 부분 수열의 길이를 구하는 로직을 이용하자

	static int N; // 병사의 수
	static int[] dp; // 가장 긴 감소하는 부분 수열의 길이
	static int[] soldiers; // 병사들의 정보

	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		soldiers = new int[N];
		for(int i=0; i<N; i++) {
			soldiers[i] = Integer.parseInt(stk.nextToken());
		}
		
		// DP Programming
		dp = new int[N];
		for(int i=0; i<N; i++) {
			
			int len = 0;
			for(int j=0; j<i; j++) {
				if(soldiers[j] > soldiers[i] && dp[j] > len) {
					len = dp[j];				
				}
			}
			
			dp[i] = len + 1;
		}
		
		// 가장 큰 값을 찾아 결과 출력
		int max = 0;
		for(int d : dp) {
			max = (max < d) ? d : max;
		}
		
		System.out.print(N - max);
	}
}