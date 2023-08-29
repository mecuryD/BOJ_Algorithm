import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ 1987] 알파벳
public class Main {
	static int R, C;
	static int res = 0; // 말이 지날 수 있는 최대 칸 수 
	static int[] dr = {-1,1,0,0}; // 4방향 행 벡터
	static int[] dc = {0,0,-1,1}; // 4방향 열 벡터
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(stk.nextToken()); // 세로 R칸
		C = Integer.parseInt(stk.nextToken()); // 가로 C칸
		char[][] map = new char[R][]; // 2차원 보드 배열
		for(int i=0; i<R; i++) { // 2차원 보드 입력
			map[i] = br.readLine().toCharArray();
		}
		// 말이 지날 수 있는 최대 칸 수 계산 (DFS 알고리즘)
		boolean[] alpha = new boolean[26]; // 알파벳 방문 관리 배열
		alpha[map[0][0]-'A'] = true; // (0,0) 시작점에 있는 알파벳 방문 처리
		dfs(map, alpha, 1, 0, 0);
		// 결과 출력
		System.out.println(res);
	}
	
	// DFS 알고리즘
	public static void dfs(char[][] map, boolean[] alpha, int cnt, int r, int c) {
		int nr, nc, idx;
		boolean isExist = false;
		for(int i=0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			// 보드 범위를 벗어나는 위치라면 continue
			if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
			// 이미 방문한 위치라면 continue
			idx = map[nr][nc] - 'A';
			if(alpha[idx]) continue;
			// 보드 범위 내 && 아직 방문하지 않은 위치라면 방문
			alpha[idx] = true; // 해당 칸을 방문하고
			dfs(map, alpha, cnt+1, nr, nc); // 다음 칸으로 이동
			alpha[idx] = false; // 원상 복원
			isExist = true;
		}
		if(!isExist) { // 더 이상 갈 수 없다면
			res = (res<cnt) ? cnt:res; // 최댓값 갱신
		}
	}
}