import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken()); // 도화지의 세로 크기 N
		int M = Integer.parseInt(stk.nextToken()); // 도화지의 가로 크기 M
		int[][] map = new int[N][M]; // NxM 크기 도화지
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 너비 우선 탐색, 그림의 개수와 가장 큰 그림의 개수를 업데이트
		int cnt = 0;
		int maxArea = 0;
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		boolean[][] visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 새로운 그림을 발견하면
				if(map[i][j]==1 && !visited[i][j]) {
					int area = 0; // 현재 그림의 넓이
					Queue<int[]> q = new ArrayDeque<int[]>();
					q.offer(new int[] {i,j});
					visited[i][j] = true;
					while(!q.isEmpty()) {
						// 위치를 하나 꺼낸다
						int[] cur = q.poll();
						area++;
						// 4방향 탐색
						for(int k=0; k<4; k++) {
							int nr = cur[0] + dr[k];
							int nc = cur[1] + dc[k];
							if(nr<0 || nc<0 || nr>=N || nc>=M) continue; // 배열 범위 초과
							if(map[nr][nc]==0 || visited[nr][nc]) continue; // 그림이 아니거나, 이미 방문한 지점이면
							// 아직 방문하지 않은 그림 영역을 발견하면
							visited[nr][nc] = true; // 방문 처리
							q.offer(new int[] {nr,nc}); // 큐에 삽입
						}
					}
					
					// 그림의 영역 넓이 비교
					maxArea = (maxArea<area) ? area : maxArea;
					cnt++;
				}
			}
		}	
		// 결과 출력
		System.out.println(cnt);
		System.out.println(maxArea);
	}
}