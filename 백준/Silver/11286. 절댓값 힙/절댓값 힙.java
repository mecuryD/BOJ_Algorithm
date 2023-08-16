import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 절댓값 힙 (우선순위 큐)
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int abs1 = Math.abs(o1);
				int abs2 = Math.abs(o2);
				if(abs1!=abs2) { // 절댓값이 같지 않으면
					return abs1 - abs2;
				}else { // 절댓값이 같으면
					return o1-o2;
				}
			}
			
		});

		// 주어진 회수만큼 답 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num!=0) { // 배열에 x 추가 
				pq.offer(num);
			}else { // 배열에서 절댓값이 가장 작은 값 출력 및 제거
				if(pq.isEmpty()) {
					sb.append("0\n");
				}else {
					int top = pq.poll();
					sb.append(top+"\n");
				}
			}
		}	
		// 출력
		System.out.print(sb.toString());
	}
}