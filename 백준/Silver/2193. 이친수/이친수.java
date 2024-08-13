import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [백준] 이친수
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 이진수 자릿수
		
		// 테이블 정의하기
		long[] table = new long[N];
		table[0] = 1;
		if(N>1) table[1] = 1;
		
		// 다이나믹 프로그래밍
		for(int i=2; i<N; i++) {
			table[i] = table[i-1] + table[i-2];
		}
		
		// 결과 출력
		System.out.print(table[N-1]);
	}
}