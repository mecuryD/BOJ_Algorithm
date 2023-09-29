import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main{

	static int resCnt = 0;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 map = new char[5][5];
		for(int i=0; i<5; i++) {
			String line = br.readLine();
			for(int j=0; j<5; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		// 25명 중에서 7명을 뽑는다
		comb(0, 0, new int[7]);
		
		// 결과 출력
		System.out.println(resCnt);
	}
	
	
	public static void comb(int cnt, int start, int[] selected) {
		// 기저 조건
		if(cnt==7) {
			// 7명의 좌표를 가져오고, 임도연파가 4명 이상 포함되어 있는지 확인한다
			int[][] pos = new int[7][2];
			int[][] posMap = new int[5][5];
			boolean flag = getPos(selected, pos, posMap); // 7명의 좌표를 가져온다
			if(!flag) return; // 임도연파가 4명 이상 포함된 경우
			
			// 최소 조건에 부합하므로, 연결되어 있는지 확인
			// 연결되어 있는 경우에만 소문난 칠공주의 경우의 수 +1
			if(isConnected(pos, posMap)) resCnt++;
			return;
		}
		
		// 실행 구문
		for(int i=start; i<25; i++) {
			selected[cnt] = i;
			comb(cnt+1, i, selected);
		}
	}
	
	public static boolean getPos(int[] selected, int[][] pos, int[][] posMap) {
		int r, c;
		int check = 0;
		
		// 각각의 좌표를 가져오고, 이후 BFS로 연결 여부를 확인해야 하므로 posMap 행렬에 1로 표시한다
		for(int i=0; i<7; i++) {
			pos[i][0] = selected[i] / 5;
			pos[i][1] = selected[i] % 5;;
			posMap[pos[i][0]][pos[i][1]] = 1;
			if(map[pos[i][0]][pos[i][1]] == 'S') check++;
		}
		
		// 이다솜파의 학생이 4명보다 적으면
		if(check<4)
			return false;
		return true;
	}
	
	public static boolean isConnected(int[][] pos, int[][] posMap) {
		// 4 방향 벡터
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		// BFS 탐색으로 연결 여부 확인
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[5][5];
		q.offer(new int[] {pos[0][0], pos[0][1]});
		visited[pos[0][0]][pos[0][1]] = true;
		
		int flag = 7;
		while(!q.isEmpty()) {
			// 소문난 7공주를 한 명 꺼낸다
			int[] cur = q.poll();
			flag--;
			
			// 상하좌우로 인접해 있는 7공주를 찾는다
			int nr, nc;
			for(int i=0; i<4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				if(nr<0 || nc<0 || nr>=5 || nc>=5) continue;
				if(!visited[nr][nc] && posMap[nr][nc]==1) { // 소문난 7공주라면
					q.offer(new int[] {nr,nc});
					visited[nr][nc] = true;
				}
			}
		}
		
		// 소문난 7공주가 모두 연결되어 있지 않다면
		if(flag > 0) return false;
        
		return true;
	}
}