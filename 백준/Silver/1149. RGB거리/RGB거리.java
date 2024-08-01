import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준] RGB 거리
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 테이블 정의
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int[][] table = new int[2][3];
		table[0][0] = Integer.parseInt(stk.nextToken());
		table[0][1] = Integer.parseInt(stk.nextToken());
		table[0][2] = Integer.parseInt(stk.nextToken());
		
		// 다이나믹 프로그래밍
		for(int i=1; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			table[1][0] = Integer.parseInt(stk.nextToken()) + Math.min(table[0][1], table[0][2]);
			table[1][1] = Integer.parseInt(stk.nextToken()) + Math.min(table[0][0], table[0][2]);
			table[1][2] = Integer.parseInt(stk.nextToken()) + Math.min(table[0][0], table[0][1]);
			table[0] = table[1].clone();
		}
		
		// 결과 출력
		int result = Math.min(table[0][0], Math.min(table[0][1], table[0][2]));
		System.out.print(result);
	}
}