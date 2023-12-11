import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws IOException{
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for(int i=1; i<=N; i++){
			queue.offer(i);
		}
		
		// 요세푸스 순열을 구한다
		StringBuffer sb = new StringBuffer();
		sb.append("<");
		for(int i=1; i<N; i++) {
			for(int j=1; j<K; j++) {
				queue.offer(queue.poll());
			}
			sb.append(queue.poll() + ", ");
		}
		sb.append(queue.poll() + ">");
		
		// 결과 출력
		System.out.println(sb);
	}
}