import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준15724] 주지수
public class Main {

	static int N; // 영토의 행 크기
	static int M; // 영토의 열 크기
	static int K; // 궁금해하는 직사각형 범위의 개수

	static int[][] map; // N x M 영토
	static StringBuilder sb = new StringBuilder(); // 결과 저장

	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) { // 영토 입력
			stk = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken()) + map[i][j - 1];
			}
		}

		// 누적 합 배열 계산
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			// 직사각형 범위 입력
			stk = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(stk.nextToken());
			int y1 = Integer.parseInt(stk.nextToken());
			int x2 = Integer.parseInt(stk.nextToken());
			int y2 = Integer.parseInt(stk.nextToken());
			// 직사각형 범위 내에 살고 있는 사람 수의 합을 구한다
			int count = 0;
			for (int j = x1; j <= x2; j++) {
				count += map[j][y2] - map[j][y1 - 1];
			}
			sb.append(count + "\n");
		}

		// 결과 출력
		System.out.println(sb);
	}
}