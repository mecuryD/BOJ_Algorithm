import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	static int time = 0;
	static int oldCheeze = 0;
	static int cntCheeze = 0;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j]==1) {
					cntCheeze++;
				}
			}
		}
		
		// 입력된 치즈가 한 개도 없는 경우
		if(cntCheeze==0) {
			System.out.println(0);
			System.out.println(0);
		}else {
			// 입력된 치즈가 적어도 한 개가 있는 경우
			while(true) {
				time++;
					
				// 치즈가 모두 녹기 한 시간 전에 남아있는 치즈 조각의 수를 저장해둔다
				oldCheeze = cntCheeze;
//				System.out.println("oldcheeze = "+oldCheeze);
				
				// 바깥쪽에 있는 공기에 대해 BFS 탐색
				Queue<Info> q = new ArrayDeque<Info>(); // BFS를 위한 큐 생성
				q.offer(new Info(0,0)); // BFS 시작 지점
				
				boolean[][] visited = new boolean[N][M];
				visited[0][0] = true;
				
				while(!q.isEmpty()) {
					// 위치 하나를 꺼낸다
					Info cur = q.poll();
					
					// 4방 탐색
					int nr, nc;
					for(int i=0; i<4; i++) {
						nr = cur.r + dr[i];
						nc = cur.c + dc[i];
						if(nr<0 ||  nc<0 || nr>=N || nc>=M) continue; // 배열 범위 초과
						if(map[nr][nc]!=0 || visited[nr][nc]) continue; // 공기가 아니거나 이미 방문한 지점이면
						q.offer(new Info(nr, nc)); // 방문하지 않았다면 큐에 추가해준다
						visited[nr][nc] = true;
					}
				}
				
//				System.out.println("BFS탐색 완료");
//				for(int i=0; i<N; i++) {
//					for(int j=0; j<M; j++) {
//						System.out.print(visited[i][j] + " ");
//					}
//					System.out.println();
//				}
				
				// 공기 중에서도 방문 처리가 된 곳만 4방향 탐색하며 치즈를 녹인다
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(map[i][j]==0 && visited[i][j]) {
							int nr, nc;
							for(int k=0; k<4; k++) {
								nr = i+dr[k];
								nc = j+dc[k];
								if(nr<0 ||  nc<0 || nr>=N || nc>=M) continue; // 배열 범위 초과
								if(map[nr][nc]==1) { // 공기와 접촉한 치즈이고, 아직 녹지 않았다면
									map[nr][nc] = -1; // 치즈를 녹인다
									cntCheeze--; // 남아 있는 치즈의 개수를 1만큼 줄인다 
								}
							}
						}
					}
				}
				
				if(cntCheeze==0) { // 모든 치즈가 녹았다면
					break;
				}
				
				// 녹은 치즈를 공기로 바꾸어준다
				// 치즈를 녹일 때 공기로 바꾸어 주면 안되므로 모든 과정이 끝나고 바꿔준다
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(map[i][j] == -1)
							map[i][j] = 0;
					}
				}
			}
			System.out.println(time);
			System.out.println(oldCheeze);
		}
	}
	
	static class Info{
		int r, c;

		public Info(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Info [r=" + r + ", c=" + c + "]";
		}
	}
}