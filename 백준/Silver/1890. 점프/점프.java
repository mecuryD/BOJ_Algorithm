import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준] 점프
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 테이블 정의
		long[][] table = new long[N][N];
		table[0][0] = 1;
		
		// 다이나믹 프로그래밍
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==N-1&&j==N-1) break;
				
				// 아래로 점프하는 경우
				if(i+map[i][j]<N) table[i+map[i][j]][j] += table[i][j];

				// 오른쪽으로 점프하는 경우
				if(j+map[i][j]<N) table[i][j+map[i][j]] += table[i][j];
			}
		}
        
        // 결과 출력
		System.out.print(table[N-1][N-1]);
	}
	
}