import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [BOJ_10026] 적록색약
public class Main {

	static int N; // 그림의 크기 N	
	static int[] cnt; // 구역 개수 저장 배열
	static int[] dr = {-1,0,1,0}; // 행 방향 벡터
	static int[] dc = {0,-1,0,1}; // 열 방향 벡터
	
	
	public static void main(String[] args) throws IOException {
		// INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N]; // 그림 저장 배열
		for(int i=0; i<N; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				map[i][j] = line[j]; 
			}
		}
		
		// 구역의 개수 계산 (DFS 알고리즘)
		cnt = new int[2];
		for(int i=0; i<2; i++) { // 적록 색약이 아닌 사람(0), 적록 색약인 사람(1)
			boolean[][] visited = new boolean[N][N]; // 방문 관리 배열
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					if(!visited[j][k]) { // 구역 체크가 되지 않았다면
						dfs(i, map, visited, j, k);
						cnt[i]++;
					}
				}
			}			
		}

		
		System.out.println(cnt[0] + " " + cnt[1]);
	}
	
	// DFS 알고리즘
	public static void dfs(int type, char[][] map, boolean[][] visited, int r, int c) {
		int nr, nc;
		for(int i=0; i<4; i++) { // 4방향 탐색
			nr = r + dr[i];
			nc = c + dc[i];
			if(nr<0 || nc<0 || nr>=N || nc>=N || visited[nr][nc]) continue; // 배열 범위를 벗어나거나, 이미 방문한 위치
			if(isSame(type, map[nr][nc], map[r][c])){ // 같은 색이면				
				visited[nr][nc] = true; // 방문 처리
				dfs(type, map, visited, nr, nc); // DFS
			}
		}
	}
	
	// 같은 색인지 확인하는 메서드
	public static boolean isSame(int type, char a, char b) {
		switch(type) {
		case 0:
			if(a==b) return true;
			break;
		case 1:
			if(a==b) return true;
			if(a!='B' && b!='B') return true;
			break;
		}
		return false;
	}
}