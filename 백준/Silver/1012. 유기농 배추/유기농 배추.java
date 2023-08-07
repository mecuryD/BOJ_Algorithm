import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	static int M, N, K;
	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0}; // 행방향, 북동남서
	static int[] dc = {0, 1, 0, -1}; // 열방향, 북동남서
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수 T
		
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			M = Integer.parseInt(stk.nextToken()); // x좌표 
			N = Integer.parseInt(stk.nextToken()); // y좌표
			K = Integer.parseInt(stk.nextToken()); // 배추가 심어져 있는 위치
			
			map = new int[N][M];
			for(int i=0; i<K; i++) { // K개의 배추 입력
				stk = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(stk.nextToken()); // 열 크기
				int y = Integer.parseInt(stk.nextToken()); // 행 크기
				map[y][x] = 1; // 배추 표시
			}
			
			// 배추 영역 탐색
			int cnt=0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[r][c]==1) {
						dfs(r,c);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
	
	public static void dfs(int r, int c) {
		// 범위 초과했다면 리턴
		if(r<0 || c<0 || r>=N || c>=M) {
			return;
		}
		
		// 이미 체크했거나, 배추가 없는 지역이면 리턴
		if(map[r][c]==0) { 
			return;
		}
		
		// 방문 체크
		map[r][c] = 0;
		
		// 4방향 탐색
		dfs(r-1,c); // 북
		dfs(r,c+1); // 동
		dfs(r+1,c); // 남
		dfs(r,c-1); // 서	
	}
}