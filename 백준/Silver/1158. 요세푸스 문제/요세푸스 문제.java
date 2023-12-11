import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException{
		// 값 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for(int i=1; i<=N; i++){
			queue.offer(i);
		}
		
		// 요세푸스 순열을 구한다
		StringBuffer sb = new StringBuffer();
		sb.append("<");
		for(int i=1; i<N; i++) {
			for(int j=1; j<K; j++) {
				int tmp = queue.poll();
				queue.offer(tmp);
			}
			sb.append(String.format("%d, ", queue.poll()));
		}
		sb.append(String.format("%d>", queue.poll() ));
		
		// 결과 출력
		System.out.println(sb);
	}
}