import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main{
	
	static int N, K;
	static Queue<Integer> q;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			q.offer(i); // 1부터 N까지 큐에 삽입
		}
		
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		while(q.size()!=1) { // 마지막 하나가 남을 때까지 반복
			int tmp = q.poll();
			if(cnt % K == K-1) { // K번째 수
				sb.append(tmp+", ");
			}else {				
				q.offer(tmp);
			}
			cnt++;
		}
		sb.append(q.poll()+">");
		System.out.println(sb.toString());
	}
}