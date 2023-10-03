import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// [BOJ2239] 스도쿠
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[9][9]; // 스도쿠 맵
		ArrayList<int[]> list = new ArrayList<int[]>(); // 채워지지 않은 칸의 위치 저장
		for(int i=0; i<9; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<9; j++) {
				map[i][j] = line[j] - '0';
				if(map[i][j]==0) {
					list.add(new int[] {i,j});
				}
			}
		}
		
		// 스도쿠 게임 시작, DFS
		dfs(0, map, list);		
	}
	
	// 스도쿠 게임, 깊이 우선 탐색
	public static boolean dfs(int idx, int[][] map, ArrayList<int[]> list) {
		// 기저 조건
		if(idx==list.size()) { // 만약 모든 칸을 다 채웠다면
			// 결과 출력
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			// 프로그램 종료
			return true;
		}
		
		// 실행 구문
		// 사용할 수 있는 숫자가 있는지 탐색
		int[] cur = list.get(idx);
		boolean[] selected = new boolean[10];
		if(checkValid(cur[0], cur[1], selected, map) > 0) {
			// 사용할 수 있는 숫자가 하나라도 있다면, 제일 작은 수부터 넣고 다음 수 탐색
			for(int i=1; i<=9; i++) {
				if(!selected[i]) {
					// 제일 작은 수를 넣는다
					map[cur[0]][cur[1]] = i;
					// 다음 칸 탐색, 재귀 호출
					if(dfs(idx+1, map, list))
						return true;
					// 원상 복원
					map[cur[0]][cur[1]] = 0; 
				}
			}
		}
		return false;
	}
	
	
	// 사용 가능한 숫자를 찾는 메서드
	public static int checkValid(int r, int c, boolean[] selected, int[][] map) {
		int flag = 9;
		
		// 가로줄 체크
		for(int i=0; i<9; i++) {
			if(map[r][i]>0 && !selected[map[r][i]]) {
				selected[map[r][i]] = true;
				flag--;
			}
		}
		// 세로줄 체크
		for(int i=0; i<9; i++) {
			if(map[i][c]>0 && !selected[map[i][c]]) {
				selected[map[i][c]] = true;
				flag--;
			}
		}
		// 3x3 구역 체크
		int sr = (r/3)*3; // 구역의 시작 행
		int sc = (c/3)*3; // 구역의 시작 열
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(map[sr+i][sc+j]>0 && !selected[map[sr+i][sc+j]]) {					
					selected[map[sr+i][sc+j]] = true;
					flag--;
				}
			}
		}
		
		// flag : 사용 가능한 숫자의 개수
		return flag;
	}
}
