import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N, M, R;
	public static int[][] map;

	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		// 입력
		input();
		
		// 회전
		for(int i=0; i<R; i++) {
			rotate();
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
	}
	
	public static void rotate() {
		int loop = Math.min(N, M) / 2; // 내부에 존재하는 루프의 수
		for(int i=0; i<loop; i++) { // 각 루프에 대해 회전
			int dir = 0; // 회전 방향
			int x = i; // 시작 행
			int y = i; // 시작 열
			int tmp = map[x][y]; // 시작 위치의 값 저장
			
			int sx = i; // 첫 행
			int sy = i;  // 첫 열
			int ex = N-i; // 마지막 행
			int ey = M-i; // 마지막 열
			
			while(dir<4) {
				int nx = x + dx[dir]; // 방향 따라 1칸 이동
				int ny = y + dy[dir]; // 방향 따라 1칸 이동
				if(nx<sx || nx>=ex || ny<sy || ny>=ey) { // 범위 초과 시
					dir++;
					continue;
				}
				map[x][y] = map[nx][ny];
				x = nx;
				y = ny;
			}
			map[sx+1][sy] = tmp; // 시작 위치 값을 아래 칸에 마지막으로 넣어 준다
		}
	}
}
