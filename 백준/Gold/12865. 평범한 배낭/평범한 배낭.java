import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준] 평범한 배낭
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		// 테이블 정의
		int[][] table = new int[N+1][K+1];
		for(int i=1; i<=N; i++) {
			stk = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(stk.nextToken());
			int V = Integer.parseInt(stk.nextToken());
			
			// 다이나믹 프로그래ㅐ밍
			for(int j=1; j<=K; j++) {
				if(j<W) {
					// i번째 물건의 무게가 배낭 용량 j보다 작은 경우
					table[i][j] = table[i-1][j];
				}else {
					// i번째 물건의 무게가 배낭 용량 j보다 같거나 큰 경우
					// i번째 물건을 담는 경우, 담지 않는 경우를 고려하여 가치가 더 큰 값 선택
					table[i][j] = Math.max(table[i-1][j], table[i-1][j-W] + V);
				}
			}
		}
		
		System.out.print(table[N][K]);
	}

}