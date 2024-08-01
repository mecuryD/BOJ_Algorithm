import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// [백준] 1,2,3 더하기
public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		// 테이블 정의
		int[] table = new int[11];
		table[1] = 1;
		table[2] = 2;
		table[3] = 4;
		
		// 다이나믹 프로그래밍
		for(int i=4; i<=10; i++) {
			if(i>1) table[i] += table[i-1];
			if(i>2) table[i] += table[i-2];
			if(i>3) table[i] += table[i-3];
		}
		
		// 테스트케이스 반복
		for(int t=0; t<T; t++) {
			// 입력
			int N = Integer.parseInt(br.readLine());
			
			// 결과 저장
			sb.append(table[N]).append("\n");
		}
		
		// 결과 출력
		System.out.print(sb);
	}
}