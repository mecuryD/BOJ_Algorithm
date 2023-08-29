import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [Boj1697] 숨바꼭질
public class Main {
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken()); // 수빈이가 있는 위치
		int K = Integer.parseInt(stk.nextToken()); // 동생이 있는 위치
		
		int[] visited = new int[100001];

		// 수빈이가 동생한테 가는 최단 시간 계산(BFS 알고리즘)
		Queue<Integer> q = new ArrayDeque<Integer>(); // 큐 생성
		q.offer(N); // 수빈이의 위치를 큐에 삽입
		visited[N] = 1; // 방문 처리
		
		int pos, npos;
		while(!q.isEmpty()) {
			pos = q.poll(); // 원소 하나를 큐에서 뽑는다

			npos = pos-1; // X-1에 대해 탐색
			if(npos>=0 && visited[npos]==0) { // 배열 범위에 있고, 방문하지 않았다면
				visited[npos] = visited[pos] + 1;
				q.offer(npos);
			}
			
			npos = pos+1; // X+1에 대해 탐색
			if(npos<=100000 && visited[npos]==0) { // 배열 범위에 있고, 방문하지 않았다면
				visited[npos] = visited[pos] + 1;
				q.offer(npos);
			}
			
			npos = pos*2; // 2X에 대해 탐색
			if(npos<=100000 && visited[npos]==0) { // 배열 범위에 있고, 방문하지 않았다면
				visited[npos] = visited[pos] + 1;
				q.offer(npos);
			}
		}
		System.out.println(visited[K]-1);
	}
}