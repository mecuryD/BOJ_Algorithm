import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준17532] 여러분의 다리가 되어드리겠습니다!
public class Main {

	static int N;
	static ArrayList<Integer>[] graph;
	
	static int[] parents;
	static boolean[] visited;
	static ArrayList<Integer> roots;
	
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) { // 그래프 초기화
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i=2; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			graph[a].add(b); // 무방향 그래프
			graph[b].add(a); // 무방향 그래프
		}
		
		// 각 정점에 대해 유니온 파인드
		parents = new int[N+1];
		visited = new boolean[N+1];
		roots = new ArrayList<Integer>();
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				unionFind(i);
				roots.add(i);
			}
		}
		
		// 최종적으로 유니크한 루트 값만 
		System.out.println(String.format("%d %d", roots.get(0), roots.get(1)));
	}
	
	public static void unionFind(int root) {
		// 유니온 파인드 세팅
		parents[root] = root;
		
		// 연결된 정점들에 대해 그룹으로 묶어준다
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(root);
		visited[root] = true;
		
		while(!queue.isEmpty()) {
			// 정점 하나를 꺼낸다
			int cur = queue.poll();
			// 연결된 정점들이 미방문상태라면
			for(int idx : graph[cur]) {
				// 큐에 넣고
				if(!visited[idx]) {
					queue.offer(idx);
					visited[idx] = true;
				}
				// 같은 그룹으로 묶어준다
				union(cur, idx);
			}
		}
	}
	
	public static void union(int p, int c) {
		int root = find(p);
		parents[c] = root;
	}
	
	public static int find(int p) {
		if(parents[p]==p) return p;
		else return find(parents[p]);
	}

}