import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준16236] 아기 상어
public class Main{	
	static int N; // 공간의 크기
	static int sharkSize; 	  // 현재 아기 상어의 크기
	static int sharkEat;      // 현재 아기 상어가 먹은 물고기 수
	static int sharkTime; // 아기 상어가 움직인 시간
	
	static int[] sharkStart; // 상어의 출발 위치
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, -1, 0, 1};

	static int[][] map; // N x N 공간
	
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j]==9) {
					sharkStart = new int[] {i, j};
					map[i][j] = 0;
				}
			}
		}
		
		// 아기 상어 초기화
		sharkEat = 0;
		sharkSize = 2;
		sharkTime = 0;
		
		// 아기 상어가 움직이면서 먹을 수 있는 물고기를 찾는다
		Queue<Fish> queue = new ArrayDeque<Fish>();
		boolean[][] visited = new boolean[N][N];
		
		queue.offer(new Fish(sharkStart[0], sharkStart[1], 0));
		visited[sharkStart[0]][sharkStart[1]] = true;
		
		PriorityQueue<Fish> pq = new PriorityQueue<Fish>(new Comparator<Fish>() {
			@Override
			public int compare(Fish o1, Fish o2) {
				if(o1.r == o2.r) return (o1.c - o2.c); 
				else return (o1.r - o2.r);
			}
		});
		
		while(!queue.isEmpty()) {
			// 동일한 타임 뎁스만 큐에서 뽑는다
			int size = queue.size();
			for(int i=0; i<size; i++) {
				// 상어 위치를 뽑는다
				Fish cur = queue.poll();
				// 현재 상어 위치에서 상하좌우를 살펴, 먹을 수 있는 물고기를 찾는다
				for(int j=0; j<4; j++) {
					int nr = cur.r + dr[j];
					int nc = cur.c + dc[j];
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue;	  		 // 맵 범위 이탈
					if(visited[nr][nc] || sharkSize < map[nr][nc]) continue; // 이미 방문한 곳이거나, 아기 상어보다 큰 물고기
					if(0 < map[nr][nc] && map[nr][nc] < sharkSize) {  		 // 먹을 수 있는 물고기가 발견되면
						pq.offer(new Fish(nr, nc, cur.t+1));		  		 // 우선순위 큐에 넣는다
					}
					
					queue.offer(new Fish(nr, nc, cur.t+1));
					visited[nr][nc] = true;
				}
			}
			
			// 만약 먹을 수 있는 물고기가 있다면
			if(!pq.isEmpty()) {
				// 물고기를 먹는다
				Fish fish = pq.poll();
				map[fish.r][fish.c] = 0;
				
				sharkEat++;
				sharkTime = fish.t;
				
				queue.clear();
				queue.offer(new Fish(fish.r, fish.c, fish.t));
				visited = new boolean[N][N];

				// 아기 상어가 성장했는지 확인한다
				if(sharkEat==sharkSize) {
					sharkEat=0;
					sharkSize++;
				}
			}
			
			// 우선순위 큐 초기화
			pq.clear();
		}
		
		// 결과 출력
		System.out.println(sharkTime);
	}
	
	static class Fish {
		int r;
		int c;
		int t;
		
		public Fish(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
