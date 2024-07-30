import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [백준] 내려가기
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][3];
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 최대 누적합
		int[][] table = new int[2][3];
		table[0] = map[0].clone();
		for(int i=1; i<N; i++) {
			table[1][0] = map[i][0] + Math.max(table[0][0], table[0][1]);
			table[1][1] = map[i][1] + Math.max(table[0][2], Math.max(table[0][0], table[0][1]));
			table[1][2] = map[i][2] + Math.max(table[0][1], table[0][2]);
			table[0] = table[1].clone();
		}

		int max = Math.max(table[0][2], Math.max(table[0][0], table[0][1]));
		
		// 최소 누적합
		table[0] = map[0].clone();
		for(int i=1; i<N; i++) {
			table[1][0] = map[i][0] + Math.min(table[0][0], table[0][1]);
			table[1][1] = map[i][1] + Math.min(table[0][2], Math.min(table[0][0], table[0][1]));
			table[1][2] = map[i][2] + Math.min(table[0][1], table[0][2]);
			table[0] = table[1].clone();
		}
		
		int min = Math.min(table[0][2], Math.min(table[0][0], table[0][1]));
		
		// 출력
		System.out.print(String.format("%d %d", max, min));
	}
	
}