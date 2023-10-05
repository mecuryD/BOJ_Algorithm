import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// [BOJ1753] 최단경로
public class Main {

	static int INF = 9999999;
	static class Node{
		int no;
		int weight;
		
		public Node(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return "Node [no=" + no + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(stk.nextToken()); // 정점의 개수 V
		int E = Integer.parseInt(stk.nextToken()); // 간선의 개수 E
		int start = Integer.parseInt(br.readLine().trim()); // 시작 정점
		
		ArrayList<Node>[] graph = new ArrayList[V+1]; // 방향 그래프
		for(int i=1; i<=V; i++) { 
			graph[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<E; i++) { // 간선 정보 입력
			stk = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(stk.nextToken()); // 출발 정점
			int v = Integer.parseInt(stk.nextToken()); // 도착 정점
			int w = Integer.parseInt(stk.nextToken()); // 가중치
			
			graph[u].add(new Node(v,w));
		}

		// 거리 배열 초기화
		int[] distance = new int[V+1];
		Arrays.fill(distance, INF);
		
		// 다익스트라를 위한 우선순위 큐, 방문 배열
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});
		boolean[] visited = new boolean[V+1];
		
		// 시작 정점에서 출발한다
		pq.offer(new Node(start, 0));
		visited[start] = true; // 방문 처리
		distance[start] = 0; // 시작 정점으로의 비용 초기화
		
		
		// 다익스트라
		while(!pq.isEmpty()) { // 연결된 모든 정점에 대해 수행
			// 출발지에서 가장 가까운 정점을 꺼낸다
			Node cur = pq.poll();
			// 방문처리
			visited[cur.no] = true; 
			// 현재 정점과 연결된 다른 정점들의 경로값 업데이트
			for(Node node : graph[cur.no]) {
				if(visited[node.no]) continue; // 이미 최소경로가 계산된 정점이면 continue
				if(distance[node.no] > distance[cur.no] + node.weight) {
					distance[node.no] = distance[cur.no] + node.weight;
					pq.offer(new Node(node.no, distance[node.no])); // 큐에 삽입
				} 
			}
		}
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++) {
			if(distance[i]==INF) {
				sb.append("INF");
			}else {
				sb.append(distance[i]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}