import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		
		// INPUT
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		int[][] map = new int[N][M];
		boolean[][] v = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		for(int i=0; i<N; i++) {			
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j]==2) { // BFS 시작 위치
					queue.offer(new int[] {i,j});
					map[i][j] = 0;
					v[i][j] = true;
				}
			}
		}
		
		// BFS
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		while(!queue.isEmpty()) {
			int[] cur = queue.poll(); // 위치 벡터를 하나 꺼낸다
			int val = map[cur[0]][cur[1]];
			
			int nr, nc;
			for(int i=0; i<4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
				if(v[nr][nc] || map[nr][nc]==0) continue;
				map[nr][nc] = val + 1;
				v[nr][nc] = true;
				queue.offer(new int[] {nr,nc});
			}
		}
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!v[i][j] && map[i][j]!=0) sb.append("-1 ");
				else sb.append(map[i][j] + " ");

			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}