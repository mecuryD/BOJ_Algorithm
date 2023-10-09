import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// [BOJ2146] 다리 만들기
public class Main {
	
	static int N;
	static int minBridge = Integer.MAX_VALUE;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 지도의 크기 N
		int[][] map = new int[N][N]; // NxN 지도
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 섬 번호를 기록해준다
		int numIsland = 1;
		boolean[][] visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					writeNumber(i, j, numIsland, map, visited);
					numIsland++;
				}
			}
		}
		
		// 너비 우선 탐색, 다리를 놓아본다
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!=0) { // 섬에 위치하는 경우
					for(int k=0; k<4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if(nr<0 || nc<0 || nr>=N || nc>=N) continue; // 배열 범위 초과
						if(map[nr][nc]==0) { // 섬과 인접한 바다를 발견하면 다리를 놓아본다
							// 맵 복사
							int[][] copyMap = copy(map);
							// 가장 짧은 길이의 다리를 놓는다
							findBridge(nr, nc, map[i][j], copyMap);
						}
					}
				}
			}
		}
		
		// 첫째 줄에 가장 짧은 다리의 길이를 출력한다
		System.out.println(minBridge-1);
	}
	
	public static void findBridge(int r, int c, int island, int[][] map) {
		Queue<Pos> q = new ArrayDeque<Pos>();
		boolean[][] visited = new boolean[N][N];
		
		// 시작 지점에서 출발한다
		q.offer(new Pos(r, c));
		visited[r][c] = true;
		
		// 두 위치를 잇는 최단 경로 계산
		int dis = 1;
		while(!q.isEmpty()) {
			dis++;
			if(dis > minBridge) return; // 백트래킹, 현재 조합은 가장 짧은 다리가 될 수 없다
						
			int len = q.size();
			for(int i=0; i<len; i++) {				
				Pos cur = q.poll();
				
				// 4방향 탐색
				int nr, nc;
				for(int j=0; j<4; j++) {
					nr = cur.r + dr[j];
					nc = cur.c + dc[j];
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue; // 배열 범위 초과
					if(map[nr][nc]==island) continue; // 출발한 섬과 같은 섬이면 continue
					if(visited[nr][nc]) continue; // 이미 방문한 곳이면 continue

					if(map[nr][nc]==0) { // 바다라면 큐에 삽입
						q.offer(new Pos(nr, nc));
						visited[nr][nc] = true;
					}else { // 가장 가까운 섬을 찾으면, 다리 길이 업데이트
						if(dis<minBridge) {
							minBridge = dis;
						}
						return;						
					}
				}
			}
		}
	}
	
	public static void writeNumber(int r, int c, int numIsland, int[][] map, boolean[][] visited) {
		Queue<Pos> q = new ArrayDeque<Pos>();
		q.offer(new Pos(r, c));
		map[r][c] = numIsland;
		visited[r][c] = true;
		
		// 너비 우선 탐색으로 해당 섬과 인접한 바다를 찾는다
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			int nr, nc;
			for(int i=0; i<4; i++) {
				nr = cur.r + dr[i];
				nc = cur.c + dc[i];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue; // 배열 범위 초과
				if(visited[nr][nc]) continue; // 이미 방문한 곳
				
				if(map[nr][nc]==1) { // 섬 번호를 기록해준다
					map[nr][nc] = numIsland;
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));			
				}
			}
		}
	}
	
	public static int[][] copy(int[][] map){
		int[][] copyMap = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}
	
	static class Pos {
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
	}
}