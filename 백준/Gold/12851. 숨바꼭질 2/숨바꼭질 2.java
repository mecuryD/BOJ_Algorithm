import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준12851] 숨바꼭질2
public class Main{

	static int N;
	static int K;
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken()); // 수빈 위치
		K = Integer.parseInt(stk.nextToken()); // 동생 위치 

		if(N < K){ // 동생이 수빈이보다 오른쪽에 있는 경우
			answer = bfs();
		}else { // 동생이 수빈이보다 왼쪽에 있는 경우
			answer = new int[] {N-K, 1};
		}
		
		// 결과 출력
		System.out.println(answer[0]);
		System.out.println(answer[1]);
	}
	
	// 수빈이가 동생을 찾는 가장 빠른 시간을 찾는다
	public static int[] bfs() {
		Queue<Pos> queue = new ArrayDeque<Pos>();
		boolean[][] visited = new boolean[2][100001];
		
		queue.offer(new Pos(N, 0));
		visited[0][N] = true;
		
		while(!queue.isEmpty()) {	
			// 수빈이가 움직인다
			int size = queue.size();
			visited[1] = Arrays.copyOf(visited[0], 100001);
			
			for(int i=0; i<size; i++) {
				Pos cur = queue.poll();	
				// 동생을 발견한다
				if(cur.x == K) { 
					int count = 1;
					// 같은 시간에 동생을 찾는 방법의 수를 모두 구한다
					for(int j=i+1; j<size; j++) {
						Pos tmp =  queue.poll();
						if(tmp.x == K) count++;
					}
					
					return new int[] {cur.t, count};
				}
				
				// 동생을 발견하지 못한 경우
				if(cur.x-1 >= 0 && !visited[0][cur.x-1]) {
					queue.offer(new Pos(cur.x-1, cur.t+1));
					visited[1][cur.x-1] = true;
				}
				if(cur.x+1 < 100001 && !visited[0][cur.x+1]) {
					queue.offer(new Pos(cur.x+1, cur.t+1));
					visited[1][cur.x+1] = true;
				}
				if(cur.x*2 < 100001 && !visited[0][cur.x*2]) {
					queue.offer(new Pos(cur.x*2, cur.t+1));
					visited[1][cur.x*2] = true;
				}
			}
			
			visited[0] = visited[1];
		}
		return null;
	}
	
	static class Pos{
		int x; // 위치
		int t; // 시간
		
		public Pos(int x, int t) {
			this.x = x;
			this.t = t;
		}
	}
}
