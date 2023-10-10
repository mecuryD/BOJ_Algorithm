import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// [BOJ1504] 특정한 최단 경로
public class Main {
	static int N, E;
	static int MAX = 999999999;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken()); // 정점의 수
		E = Integer.parseInt(stk.nextToken()); // 간선의 수
		
		graph = new int[N+1][N+1]; // 그래프
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				graph[i][j] = MAX; // INF 초기화
			}
		}
		
		for(int i=0; i<E; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			graph[a][b] = w; // 양방향 그래프
			graph[b][a] = w;
		}
		
		stk = new StringTokenizer(br.readLine(), " ");
		int desA = Integer.parseInt(stk.nextToken()); // 지나야 하는 정점 1번
		int desB = Integer.parseInt(stk.nextToken()); // 지나야 하는 정점 2번
		
		// desA를 먼저 방문하는 경우
		int[][] d = new int[2][3];
		d[0][0] = dijkstra(1,desA);
		d[0][1] = dijkstra(desA, desB);
		d[0][2] = dijkstra(desB, N);
		// desB를 먼저 방문하는 경우
		d[1][0] = dijkstra(1,desB);
		d[1][1] = dijkstra(desB, desA);
		d[1][2] = dijkstra(desA, N);
		
		// 결과 출력
		int d1 = d[0][0] + d[0][1] + d[0][2];
		int d2 = d[1][0] + d[1][1] + d[1][2];
		if(d[0][0]!=MAX && d[0][1]!=MAX && d[0][2]!=MAX && d1<=d2) {
			System.out.println(d1);
		}else if(d[1][0]!=MAX && d[1][1]!=MAX && d[1][2]!=MAX && d2<=d1) {
			System.out.println(d2);
		}else {
			System.out.println(-1);
		}
	}

	// 우선순위 큐를 이용한 다익스트라
	public static int dijkstra(int s, int e) {
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return Integer.compare(o1.distance, o2.distance);
			}
		});
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, MAX);
		
		// 시작 정점에서 출발한다
		pq.offer(new Point(s, 0));
		dist[s] = 0;
		
		while(!pq.isEmpty()) {
			// 정점 하나를 꺼낸다 
			Point cur = pq.poll();
			// 만약 목적지에 도착했으면 break
			if(cur.idx == e) {
				return cur.distance;
			}
			// 인접한 정점의 최단 경로를 업데이트 한다
			for(int i=1; i<=N; i++) {
				if(dist[i] > graph[cur.idx][i] + dist[cur.idx]) {
					dist[i] = graph[cur.idx][i] + dist[cur.idx];
					pq.offer(new Point(i, dist[i]));
				}
			}
		}
		return dist[e];
	}
	
	
	static class Point {
		int idx;
		int distance;
		public Point(int idx, int distance) {
			super();
			this.idx = idx;
			this.distance = distance;
		}
		@Override
		public String toString() {
			return "Point [idx=" + idx + ", distance=" + distance + "]";
		}
	}
}