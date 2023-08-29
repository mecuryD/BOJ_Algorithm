import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ1149] RGB거리
public class Main {
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 집의 개수
		int[][] map = new int[N][3]; // 각 집을 빨강, 초록, 파랑으로 칠하는 비용
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// 메모이제이션
		int idx, min, sum;
		for(int i=1; i<N; i++) {
			for(int j=0; j<3; j++) {
				min = Integer.MAX_VALUE;
				for(int k=0; k<3; k++) {
					if(k==j) continue; // 인접한 집과 동일한 색은 칠할 수 없다
					sum = map[i][j] + map[i-1][k];
					min = (sum < min) ? sum : min; // i번째 집을 j번째 색으로 칠하는 최소 비용
				}
				map[i][j] = min;
			}
		}
		// 가장 마지막 줄에서 최소 값 찾기
		int res = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			res = (map[N-1][i] < res) ? map[N-1][i] : res;
		}
		System.out.println(res);
	}
}