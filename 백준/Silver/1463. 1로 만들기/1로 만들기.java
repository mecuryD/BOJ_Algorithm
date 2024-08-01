import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// [백준] 1로 만들기
public class Main{

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 테이블 정의 및 초기값 설정
		int[] table = new int[N+1];
		Arrays.fill(table, 999999);
		table[1] = 0;
		
		// 다이나믹 프로그래밍
		for(int i=1; i<=N; i++) {
			// 3으로 나눌 수 있으면 3으로 나눠본다
			if(i % 3 == 0) table[i] = Math.min(table[i], table[i/3] + 1);
			
			// 2로 나눌 수 있으면 2로 나눠본다
			if(i % 2 == 0) table[i] = Math.min(table[i], table[i/2] + 1);
			
			// 1을 뺀다
			table[i] = Math.min(table[i], table[i-1] + 1);
		}
		
		// 결과 출력
		System.out.print(table[N]);
	}
}