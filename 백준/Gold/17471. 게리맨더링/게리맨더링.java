import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준17471] 게리맨더링
public class Main {

	static int N; // 구역의 개수
	static int total; // 전체 인구수
	static int min; // 두 선거구역의 인구 차이 최소값
	
	static int[] people; // 각 구역의 인구수
	static ArrayList<Integer>[] graph; // 그래프
	
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역의 개수
		
		total = 0;
		people = new int[N+1]; // 각 구역의 인구 수
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			people[i] = Integer.parseInt(stk.nextToken());
			total += people[i];
		}
		
		graph = new ArrayList[N+1]; // 양방향 그래프
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i=1; i<=N; i++) { // 양방향 그래프 입력
			stk = new StringTokenizer(br.readLine(), " ");
			int count = Integer.parseInt(stk.nextToken());

			for(int j=0; j<count; j++) {
				int to = Integer.parseInt(stk.nextToken());
				graph[i].add(to);
				graph[to].add(i);
			}
		}
		
		// 선거구역을 나누고, 각 구역이 연결되어 있는지 확인한다
		min = total;
		subset(1, 0, 0, 0, new boolean[N+1]);
		
		// 결과 출력
		if(min==total) System.out.println(-1);
		else System.out.println(min);
	}
	
	// 부분 집합으로 선거구역을 나눈다
	public static void subset(int start, int cnt, int sel, int sum, boolean[] selected) {
		// 모든 구역이 선택되었다면
		if(cnt==N) {
			// 최소값이 아니거나, 하나의 구역을 포함하지 않는다면 리턴
			int diff = Math.abs(2 * sum - total);
			if(min < diff || sel==0 || sel==N) return;
			
			// 최소값이라면 각 구역이 연결되었는지 확인한다
			// 한 구역이라도 연결되지 않으면 리턴
			if(!check(selected, sel, true)) return;
			if(!check(selected, N-sel, false)) return;
			
			// 각 구역이 연결되었다면 최소값 갱신 후 리턴
			min = diff;
			return;
		}
		
		// 아직 구역이 남아있다면
		for(int i=start; i<=N; i++) {
			selected[i] = true; // 선택되는 경우
			subset(i+1, cnt+1, sel+1, sum + people[i], selected);
			
			selected[i] = false; // 선택되지 않는 경우
			subset(i+1, cnt+1, sel, sum, selected);
		}
	}
	
	// 각 구역이 연결되었는지 확인한다
	public static boolean check(boolean[] selected, int cnt, boolean flag) {
		// 너비 우선 탐색
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N+1];
		
		// 구역의 시작점을 찾는다
		for(int i=1; i<=N; i++) {
			if(selected[i] == flag) {
				queue.offer(i);
				visited[i] = true;
				break;
			}
		}
		
		// 너비 우선 탐색으로 연결된 구역을 탐색한다
		int num = 1;
		while(!queue.isEmpty()) {
			// 구역 1개를 꺼낸다
			int cur = queue.poll();
			
			// 인접한 구역을 탐색한다
			for(int next : graph[cur]) {
				// 아직 방문하지 않은 구역만 큐에 삽입한다
				if(!visited[next] && selected[next]==flag) {
					queue.offer(next);
					visited[next] = true;
					num++;
					
				}				
			}
		}
		
		// 만약 탐색되지 않은 구역이 있으면, 유효하지 않은 선거구역
		if(num != cnt) return false;		
		return true;
	}
}
