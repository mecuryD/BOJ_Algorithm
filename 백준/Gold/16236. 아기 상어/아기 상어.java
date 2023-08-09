import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, r, c;
	static int size, cnt, res;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0}; // 북서남동
	static int[] dc = {0, -1, 0, 1}; // 북서남동
	
	public static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 공간 크기 변수
		map = new int[N][N]; // 공간 저장 배열
		
		// NxN 공간 입력
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				int tmp = Integer.parseInt(stk.nextToken());
				map[i][j] = tmp; // 공간 위치 입력
				if(tmp==9) { // 상어 초기 위치 저장
					r = i;
					c = j;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// 값 입력
		input(); // NxN 공간 입력
		size = 2; // 상어의 최초 크기
		cnt = 0; // 상어가 지금까지 몇 마리를 먹었는지 저장

		while(true) { // 새로운 위치에서 시작 (Queue, visited 초기화)
			PriorityQueue<int[]> q = new PriorityQueue<int[]>( // 우선순위 큐 생성
				new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						if(o1[2]!=o2[2]) { // 거리가 같지 않으면
							return o1[2]-o2[2]; // 거리 따라 오름차순
						}else {	// 거리가 같으면
							if(o1[0]!=o2[0]) { // 거리는  같고, 행이 같지 않으면
								return o1[0]-o2[0]; // 행에 따라 오름차순
							}else { // 거리와 행이 모두 같으면
								return o1[1]-o2[1]; // 열에 따라 오름차순 정렬
							}
						}
					} 
				}
			);
			boolean[][] visited = new boolean[N][N]; // 방문 배열
			
			q.offer(new int[] {r,c,0}); // 상어 현재 위치 추가
			map[r][c] = 0; // 상어의 초기 위치 표시 해제
			visited[r][c] = true; // 상어의 초기 위치 방문 체크 
			
			// BFS 탐색
			int isExist = 0;
			while(!q.isEmpty()) { // 큐가 빌 때까지 반복
				int[] cur = q.poll(); // 탐색할 위치를 꺼낸다
				if(map[cur[0]][cur[1]]!=0 && map[cur[0]][cur[1]]<size) { // 먹을 수 있는 물고기라면
					map[cur[0]][cur[1]] = 0; // 물고기를 먹는다
					res += cur[2]; // 총 거리에 반영
					isExist++; // 먹은 물고기의 수 갱신
					r = cur[0]; // 상어의 위치 업데이트
					c = cur[1]; // 상어의 위치 업데이트
//					System.out.println("r : "+r+", c : "+c);
					break;
				}
				for(int i=0; i<4; i++) { // 4방향 탐색 (북서남동)
					int nr = cur[0] + dr[i]; // 다음에 탐색할 행
					int nc = cur[1] + dc[i]; // 다음에 탐색할 열
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue; // 범위 초과 시 continue
					if(visited[nr][nc]) continue; // 이미 방문한 곳(=큐에 삽입된 곳)이면 continue
					if(map[nr][nc]>size) continue; // 상어보다 큰 물고기인 경우 continue
					q.offer(new int[] {nr,nc,cur[2]+1}); // 이동 가능한 곳이면 큐에 삽입
					visited[nr][nc] = true; // 큐에 다시 삽입되지 않도록 방문 처리
				}
			};
			
			// TODO : 먹을 수 있는 물고기가 없다는 것을 어떻게 판단할까  -> 새로운 변수
			if(isExist==0) { // 가능한 모든 곳을 탐색했음에도 물고기를 먹지 못한 것이므로
				break; // 반복문 종료
			}
			
			cnt += isExist; // 방금 먹은 물고기 마리 수 반영
//			System.out.println("cnt : "+cnt+", size : " + size);
			if(cnt>=size) { // 상어가 자기 크기만큼 물고기를 먹었다면
				size++; // 크기 증가
				cnt=0; // 먹은 물고기 수 초기화
			}
			
			
		}
		System.out.println(res);
	}
}
