import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [백준] 2xN 타일링
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 테이블 정의
		int[] table = new int[N+1];
		table[1] = 1;
		if(N>1) table[2] = 2;
		
		// 다이나믹 프로그래밍
		for(int i=3; i<=N; i++) {
			table[i] = (table[i-1] + table[i-2]) % 10007;
		}
		
		// 결과 출력
		System.out.print(table[N]);
	}
}