import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 정점 클래스
	static class Vertex implements Comparable<Vertex>{
		int num, weight;
		public Vertex(int num, int weight) {
			super();
			this.num = num; // 정점 번호
			this.weight = weight; // 정점의 비용
		}

		@Override
		public int compareTo(Vertex o) { // 우선순위 큐에서 비용 기준으로
			return Integer.compare(this.weight, o.weight); // 작은 것부터 오름차순 정렬
		}
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(stk.nextToken()); // 정점의 개수 V
		int E = Integer.parseInt(stk.nextToken()); // 간선의 개수 E
		
		int A, B, C;
		ArrayList<int[]>[] adjList = new ArrayList[V+1]; // 인접 리스트
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		for(int i=0; i<E; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(stk.nextToken()); // 정점 A
			B = Integer.parseInt(stk.nextToken()); // 정점 B
			C = Integer.parseInt(stk.nextToken()); // 두 정점의 가중치
			
			adjList[A].add(new int[] {B, C}); // A와 연결된 정점과 가중치 저장
			adjList[B].add(new int[] {A, C}); // B와 연결된 정점과 가중치 저장
		}
		
		boolean[] visited = new boolean[V+1]; // 정점 방문 관리 배열
		int[] minEdge = new int[V+1]; // 현재 정점의 최소 비용 배열
		Arrays.fill(minEdge, Integer.MAX_VALUE); // 각 정점의 최소 비용을 ∞로 초기화
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(); // 정점을 담을 우선순위 큐
		minEdge[1] = 0; // 임의 정점에서 시작
		pq.offer(new Vertex(1, minEdge[1])); // 시작 정점을 큐에 삽입
		
		int cnt = 0; // 현재 선택된 정점의 수
		int result = 0; // 가중치의 총 합
		int min, minVertex = 0; // 최소 비용 정점 정보
		
		while(true) {
			// 1. 최소 비용 정점을 찾는다
			Vertex cur = pq.poll();
			if(visited[cur.num]) continue; // 이미 방문한 정점이면 continue
			minVertex = cur.num;
			min = cur.weight;
			
			// 2. 찾은 정점을 방문하고, 가중치를 총 합에 반영
			visited[cur.num] = true;
			result += cur.weight;
			if(++cnt==V) break; // V개의 정점이 모두 연결되면 break
			
			// 3. 현재 정점과 인접한 정점에 대해, 최소 비용 업데이트
			for(int[] ver : adjList[minVertex]) {
				if(!visited[ver[0]] && minEdge[ver[0]]>ver[1]) {
					minEdge[ver[0]] = ver[1]; // 최소 비용 업데이트
					pq.offer(new Vertex(ver[0], minEdge[ver[0]])); // 큐에 넣어준다
				}
			}
		}
		System.out.println(result);
	}
}