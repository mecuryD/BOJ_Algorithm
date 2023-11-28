import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N; // 게임판의 행 개수
	static int M; // 게임판의 열 개수
	static int R; // 라운드 횟수
	static int result = 0; // 최종 점수
	
	static int[][] map; // 원본 게임판
	static int[][] board; // 실제 게임판
	static boolean[][] isDown; // 도미노가 넘어졌는지 여부 확인
	
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		
		map = new int[N+1][M+1];
		board = new int[N+1][M+1];
		isDown = new boolean[N+1][M+1];
		for(int i=1; i<=N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				board[i][j] = map[i][j];
			}
		}
		
		// 게임 수행
		for(int i=0; i<R; i++) {
			// 공격수는 원하는 위치의 도미노를 넘어뜨릴 수 있다
			stk = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			int dir = getDirection(stk.nextToken().charAt(0));
			result += doPlay(r, c, dir);
			
			// 수비수는 원하는 위치의 도미노를 다시 세울 수 있다
			stk = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(stk.nextToken());
			c = Integer.parseInt(stk.nextToken());
			isDown[r][c] = false; 
		}
		
		// 결과 출력
		System.out.println(result);
		print();
	}
	
	// 특정 위치의 도미노를 넘어트린다
	public static int doPlay(int r, int c, int dir) {
		int score = 0; // 공격수의 점수
	
		Queue<Domino> queue = new ArrayDeque<Domino>(); // 넘어뜨릴 도미노를 넣는 큐
		queue.offer(new Domino(r, c, map[r][c])); // 처음 넘어뜨릴 도미노를 큐에 먼저 삽입한다
		score++; // 점수를 증가시킨다
		
		while(!queue.isEmpty()) {
			// 도미노 하나를 꺼내어 넘어트린다
			Domino cur = queue.poll();
			isDown[cur.r][cur.c] = true;
			// 만약 도미노의 길이가 1이상이라면, 연속적으로 도미노를 넘어뜨린다
			for(int i=1; i<cur.len; i++) {
				int nr = cur.r + dr[dir] * i;
				int nc = cur.c + dc[dir] * i;
				if(nr<1 || nc<1 || nr>N || nc>M) break; // 격자를 벗어난다
				if(isDown[nr][nc]) continue; // 이미 넘어져 있는 도미노
				// 아직 넘어져 있지 않은 도미노에 대해
				// 길이가 1보다 크다면 연쇄작용을 위해 큐에 삽입
				if(board[nr][nc]>1) {
					queue.offer(new Domino(nr, nc, board[nr][nc]));
				}
				isDown[nr][nc] = true; // 도미노를 넘어트리고
				score++; // 점수 증가				
			}
		}
		
		return score;
	}
	
	// 게임판의 상태 출력
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(isDown[i][j]) sb.append("F ");
				else sb.append("S ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	// 도미노를 넘어트리는 방향을 반환한다
	public static int getDirection(char d) {
		switch(d) {
			case 'E' :
				return 0;
			case 'W' :
				return 1;
			case 'S' :
				return 2;
			case 'N' :
				return 3;
		}
		return -1;
	}
	
	// 도미노 클래스
	static class Domino {
		int r;
		int c;
		int len;
		
		public Domino(int r, int c, int len) {
			super();
			this.r = r;
			this.c = c;
			this.len = len;
		}
	}
}