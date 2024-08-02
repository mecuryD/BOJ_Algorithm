import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준] 구간 합 구하기 4
public class Main{

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		int[] numbers = new int[N+1];
		stk = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			numbers[i] = Integer.parseInt(stk.nextToken());
		}
		
		// 테이블 정의
		int[] table = new int[N+1];
		table[1] = numbers[1];
		
		// 다이나믹 프로그래밍
		for(int i=2; i<=N; i++) {
			table[i] += table[i-1] + numbers[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int start =  Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			sb.append(table[end]-table[start-1]).append("\n");
		}
		
		// 결과 출력
		System.out.print(sb);
	}
}