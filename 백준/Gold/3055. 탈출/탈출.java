import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static boolean res = false; // 최종 상태
	static int[] start, end; // 고슴도치 위치, 비버 굴
	static int[] dr = {-1, 1, 0, 0}; // 4방 탐색, 행 벡터
	static int[] dc = {0, 0, -1, 1}; // 4방 탐색, 열 벡터
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(stk.nextToken());
		int C = Integer.parseInt(stk.nextToken());

		char[][] map = new char[R][C];
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j]=='S') { // 고슴도치
					start = new int[2];
					start[0] = i;
					start[1] = j;
				}
			}
		}

		// 고슴도치가 비버 굴을 향해 출발, 너비 우선 탐색
		Queue<int[]> gq = new ArrayDeque<int[]>(); // 고슴도치의 위치 관리
		boolean[][] visited = new boolean[R][C];
		gq.offer(start); // 고슴도치의 초기 위치 삽입
		map[start[0]][start[1]] = '.';
		visited[start[0]][start[1]] = true; // 고슴도치의 초기 위치 방문 체크

		int time = 0;
		loop: 
			while(true) { // 매 분마다		
				time++; // 시간 증가
				// 물을 확장시킨다
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						if(map[i][j]=='-')
							map[i][j] = '*';
					}
				}

				// 고슴도치의 이동을 막기 위해, 물이 있는 위치를 차례로 꺼내 다음 시간에 확장될 위치를 표시
				int nr, nc;
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						// 현재 시점에 물이 확장되는 곳으로 표시되어 있다면
						if(map[i][j]=='*') {
							// 4방 탐색, 비어 있는 칸이라면 물이 확장될 수 있다
							for(int k=0; k<4; k++) {
								nr = i + dr[k];
								nc = j + dc[k];
								if(nr<0 || nc<0 || nr>=R || nc>=C) continue; // 배열 범위 초과
								if(map[nr][nc]=='.') { // 물이 확장될 수 있다면
									map[nr][nc] = '-'; // 미리 표시해둔다
								}
							}
						}
					}
				}
				// 고슴도치의 가능한 위치를 차례로 꺼내, 다음 이동할 위치를 큐에 삽입
				int size = gq.size();
				boolean flag = false;
				for(int i=0; i<size; i++) {
					// t분에 고슴도치가 존재할 수 있는 위치를 꺼낸다
					int[] cur = gq.poll();
					// 4방 탐색, 비어 있는 칸이라면 이동할 수 있다
					for(int j=0; j<4; j++) {
						nr = cur[0] + dr[j];
						nc = cur[1] + dc[j];
						if(nr<0 || nc<0 || nr>=R || nc>=C) continue; // 배열 범위 초과
						if(!visited[nr][nc] && map[nr][nc] == '.') { // 고슴도치가 이동할 수 있다면
							gq.offer(new int[] {nr, nc}); // 큐에 추가
							visited[nr][nc] = true; // 방문 처리
							flag = true;
						}
						if(map[nr][nc]=='D') { // 비버 굴을 찾았다면
							res = true;
							break loop;
						}
					}
				}
				
				if(!flag) {
					res = false;
					break loop;
				}
			}

		// 결과 출력
		if(res) { // 비버 굴을 찾았다면
			System.out.println(time);
		}else { // 비버 굴을 못 찾았다면
			System.out.println("KAKTUS");
		}
	}
}