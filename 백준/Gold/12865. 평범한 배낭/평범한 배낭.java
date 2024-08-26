import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ12865] 평범한 배낭

public class Main{

	public static void main(String[] args) throws IOException{
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(stk.nextToken()); // 물품의 수 N
		int K = Integer.parseInt(stk.nextToken()); // 준서가 버틸 수 있는 무게 K
		int[] W = new int[N+1]; // 각 물품의 무게 W
		int[] V = new int[N+1]; // 각 물품의 가치 V
		for(int i=1; i<=N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			W[i] = Integer.parseInt(stk.nextToken());
			V[i] = Integer.parseInt(stk.nextToken());
		}
		
		// Bottom-up DP
		int[][] table = new int[N+1][K+1]; // 동적 테이블
		for(int i=1; i<=N; i++) { // 1번째 ~ i번째 물건 고려
			for(int j=1; j<=K; j++) { // 1kg ~ jkg 배낭 고려
				if(j < W[i]) // i번째 물건을 현재 배낭에 넣을 수 없다
					table[i][j] = table[i-1][j];
				else // i번쨰 물건을 현재 배낭에 넣을 수 있다
					table[i][j] = Math.max(table[i-1][j], V[i] + table[i-1][j-W[i]]);
			}
		}
	
		// 결과 출력
		System.out.println(table[N][K]);
	}
}