import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [BOJ15863] 감시
public class Main{

	static int N;
	static int M;
	static int cntCCTV;
	static int minArea = 999;
	static ArrayList<Info> list;
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken()); // 사무실 크기 N
		M = Integer.parseInt(stk.nextToken()); // 사무실 크기 M
		
		int[][] map = new int[N][M];
		list = new ArrayList<Info>();
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(0 < map[i][j] && map[i][j] < 6) { // cctv
					list.add(new Info(i, j, map[i][j]));
				}
			}
		}
		
		// CCTV의 방향에 따른 사각지대 넓이 탐색, 깊이우선탐색
		cntCCTV = list.size();
		dfs(0, map);
		
		// 결과 출력
		System.out.println(minArea);

	}
	
	public static boolean dfs(int cnt, int[][] map) {
		// 기저 조건
		if(cnt==cntCCTV) {

			// 모든 CCTV의 방향이 결정되면 사각 지대  크기 계산
			int area = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==0) area++;
				}
			}
			
			// 사각 지대 크기 업데이트
			minArea = (area < minArea) ? area : minArea;
			if(minArea==0) return true; // 더 이상 진행하는 것이 의미 없다
			return false;
		}
		
		
		// 실행 구문
		// CCTV 종류에 따라 가능한 모든 방향 탐색
		int[][] copyMap = null;
		Info cur = list.get(cnt);
		switch(cur.t) {
		case 1: // 1번 CCTV
			for(int i=0; i<4; i++) {
				copyMap = mapCopy(map);
				viewCCTV1(cur.r, cur.c, i, copyMap);
//				if(dfs(cnt+1, copyMap)) return true; // 다음 CCTV 탐색
				dfs(cnt+1, copyMap);
			}
			break;
		case 2: // 2번 CCTV
			for(int i=0; i<2; i++) {
				copyMap = mapCopy(map);
				viewCCTV2(cur.r, cur.c, i, copyMap);
//				if(dfs(cnt+1, copyMap)) return true; // 다음 CCTV 탐색
				dfs(cnt+1, copyMap);
			}
			break;
		case 3: // 3번 CCTV
			for(int i=0; i<4; i++) {
				copyMap = mapCopy(map);
				viewCCTV3(cur.r, cur.c, i, copyMap);
//				if(dfs(cnt+1, copyMap)) return true; // 다음 CCTV 탐색
				dfs(cnt+1, copyMap);
			}
			break;
		case 4: // 4번 CCTV
			for(int i=0; i<4; i++) {
				copyMap = mapCopy(map);
				viewCCTV4(cur.r, cur.c, i, copyMap);
//				if(dfs(cnt+1, copyMap)) return true; // 다음 CCTV 탐색
				dfs(cnt+1, copyMap);
			}
			break;
		case 5: // 5번 CCTV
			copyMap = mapCopy(map);
			viewCCTV5(cur.r, cur.c, copyMap);
//			if(dfs(cnt+1, copyMap)) return true; // 다음 CCTV 탐색
			dfs(cnt+1, copyMap);
			break;
		}
		
		return false;
	}
	
	// 1번 CCTV
	public static void viewCCTV1(int r, int c, int dir, int[][] map) {
		int[] dr = {0, -1, 0, 1};
		int[] dc = {1, 0, -1, 0};
		
		while(true) {
			r += dr[dir];
			c += dc[dir];
			if(r<0 || r>=N || c<0 || c>=M) break; // 배열 범위 초과
			if(map[r][c]==6) break; // 벽 때문에 더 나아갈 수 없다
			if(0 < map[r][c] && map[r][c] < 6) continue; // 다른 CCTV는 통과
			map[r][c] = -1; // CCTV 감시
		}
	}
	
	// 2번 CCTV
	public static void viewCCTV2(int r, int c, int dir, int[][] map) {
		int[] dr = {0, 0, 1, -1};
		int[] dc = {1, -1, 0, 0};
		
		dir = dir * 2;
		for(int i=0; i<2; i++) {
			int nr = r;
			int nc = c;
			while(true) {
				nr += dr[dir+i];
				nc += dc[dir+i];
				if(nr<0 || nr>=N || nc<0 || nc>=M) break; // 배열 범위 초과
				if(map[nr][nc]==6) break; // 벽 때문에 더 나아갈 수 없다
				if(0 < map[nr][nc] && map[nr][nc] < 6) continue; // 다른 CCTV는 통과
				map[nr][nc] = -1; // CCTV 감시 
			}
		}
	}
	
	// 3번 CCTV
	public static void viewCCTV3(int r, int c, int dir, int[][] map) {
		int[] dr = {0, -1, 0, 1, 0};
		int[] dc = {1, 0, -1, 0, 1};
		
		for(int i=0; i<2; i++) {
			int nr = r;
			int nc = c;
			while(true) {
				nr += dr[dir+i];
				nc += dc[dir+i];
				if(nr<0 || nr>=N || nc<0 || nc>=M) break; // 배열 범위 초과
				if(map[nr][nc]==6) break; // 벽 때문에 더 나아갈 수 없다
				if(0 < map[nr][nc] && map[nr][nc] < 6) continue; // 다른 CCTV는 통과
				map[nr][nc] = -1; // CCTV 감시 
			}
		}
	}
	
	// 4번 CCTV
	public static void viewCCTV4(int r, int c, int dir, int[][] map) {
		int[] dr = {0, -1, 0, 1};
		int[] dc = {1, 0, -1, 0};
		
		for(int i=0; i<4; i++) {
			if(i==dir) continue; // 해당 방향은 CCTV가 감시하지 않음
			int nr = r;
			int nc = c;
			while(true) {
				nr += dr[i];
				nc += dc[i];
				if(nr<0 || nr>=N || nc<0 || nc>=M) break; // 배열 범위 초과
				if(map[nr][nc]==6) break; // 벽 때문에 더 나아갈 수 없다
				if(0 < map[nr][nc] && map[nr][nc] < 6) continue; // 다른 CCTV는 통과
				map[nr][nc] = -1; // CCTV 감시 
			}
		}
	}
	
	// 5번 CCTV
	public static void viewCCTV5(int r, int c, int[][] map) {
		int[] dr = {0, -1, 0, 1};
		int[] dc = {1, 0, -1, 0};
		
		for(int i=0; i<4; i++) {
			int nr = r;
			int nc = c;
			while(true) {
				nr += dr[i];
				nc += dc[i];
				if(nr<0 || nr>=N || nc<0 || nc>=M) break; // 배열 범위 초과
				if(map[nr][nc]==6) break; // 벽 때문에 더 나아갈 수 없다
				if(0 < map[nr][nc] && map[nr][nc] < 6) continue; // 다른 CCTV는 통과
				map[nr][nc] = -1; // CCTV 감시 
			}
		}
	}
	
	// 맵 복사
	public static int[][] mapCopy(int[][] map){
		int[][] copyMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}
	
	static class Info {
		int r; // row
		int c; // column
		int t; // cctv type
		
		public Info(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Info[r=" + r + ", c=" + c + ", t=" + t + "]";
		}
	}
}