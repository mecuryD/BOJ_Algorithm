import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T, N, R, min;
	static int[][] map;
	static boolean[] sel; // 재료 선택 여부저장
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// input
			N = Integer.parseInt(br.readLine());
			R = N / 2; // 요리에 사용되는 재료의 개수
			map = new int[N][N];
			for(int i=0; i<N; i++){
				StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			// 최솟값을 찾기 위해 min 초기화
			min = Integer.MAX_VALUE;

			// 재료 선택
			sel = new boolean[N];
			comb(0,0);
			sb.append(String.format("#%d %d\n",t, min));
			
		}
		System.out.print(sb.toString());
	}
	
	public static void comb(int cnt, int start) {
		// 기저 구문
		if(cnt==R) {
			int idx = 0;
			int idx2 = 0;
			int[] food = new int[R];
			int[] food2 = new int[R];
			
			// 요리 A와 B에 쓰이는 재료 구분
			for(int i=0; i<N; i++) {
				if(sel[i]) food[idx++] = i;
				else food2[idx2++] = i;
			}
			
			int sum1 = 0;
			// 요리 A와 B의 맛 계산
			for(int i : food) {
				for(int j : food) {
					sum1 += map[i][j];
				}
			}
			int sum2 = 0;
			for(int i : food2) {
				for(int j : food2) {
					sum2 += map[i][j];
				}
			}
			// 두 요리의 맛 차이 비교
			int res = Math.abs(sum1-sum2);
			min = (res<min)? res : min;
			return;
		}	
		// 실행 구문
		for(int i=start; i<N; i++) {
			sel[i] = true; // i번째 재료 선택
			comb(cnt+1, i+1); // 재귀 호출, 다음 원소 선택
			sel[i] = false;
		}
	}
}