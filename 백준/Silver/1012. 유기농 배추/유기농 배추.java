import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	static int T, M, N, K;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dx = {0, 1, 0, -1}; // 북동남서
	static int[] dy = {-1, 0, 1, 0};
	
	// BFS
	public static void bfs(int x, int y) {
		Queue<int[]> qu = new LinkedList<int[]>();
		qu.add(new int[] {x,y});
		while(!qu.isEmpty()) {
			x = qu.peek()[0];
			y = qu.peek()[1];
			visit[x][y] = true;
			qu.poll();
			
			for(int i=0; i<4; i++) {
				int cx = x + dx[i];
				int cy = y + dy[i];
				
				if(cx<0 || cy<0 || cx>=M || cy>=N) continue; // 범위 초과
				if(visit[cx][cy] || map[cx][cy]==0) continue; // 이미 방문한 곳이거나 배추가 없는 위치
				
				qu.offer(new int[] {cx, cy}); // 큐 삽입
				visit[cx][cy] = true; // 방문 처리
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 테스트케이스 개수 T
		
		for(int test_case=1; test_case<=T; test_case++) { // 테스트케이스 개수만큼 반복
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			M = Integer.parseInt(stk.nextToken()); // x좌표 
			N = Integer.parseInt(stk.nextToken()); // y좌표
			K = Integer.parseInt(stk.nextToken()); // 배추가 심어져 있는 위치
			
			map = new int[M][N];
			visit = new boolean[M][N];
			for(int i=0; i<K; i++) { // K개의 배추 입력
				stk = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(stk.nextToken()); // 열 크기
				int y = Integer.parseInt(stk.nextToken()); // 행 크기
				map[x][y] = 1; // 배추 표시
			}
			
			/* BFS */
			int cnt = 0;
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==1 && !visit[i][j]) {
						bfs(i,j);
						cnt++;						
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
