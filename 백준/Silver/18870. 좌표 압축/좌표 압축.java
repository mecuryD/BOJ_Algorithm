import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 좌표의 개수
			
		int[] arr = new int[N];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) { // 키 값이 작은 순으로 오름차순
				return Integer.compare(o1, o2);
			}
		});
		
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken()); // X_i
			if(!map.containsKey(arr[i])) { // O(1)로 시간복잡도를 줄였다
				map.put(arr[i], 0);
				pq.offer(arr[i]);
			}
		}
		
		// 좌표 압축
		int idx = 0;
		while(!pq.isEmpty()) {
			int n = pq.poll();
			map.replace(n, idx++);
		}
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for(int i : arr)
			sb.append(map.get(i)+" ");
		System.out.print(sb);
	}
}