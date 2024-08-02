import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// [백준] 1로 만들기 2
public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 테이블 정의
		int[] table = new int[N+1];
		int[] used = new int[N+1];
		Arrays.fill(table, 9999999);
		table[1] = 0;
		
		// 다이나믹 프로그래밍
		for(int i=2; i<=N; i++) {
			table[i] = table[i-1] + 1;
			used[i] = i-1;
			
			if(i%2==0 && (table[i/2]+1)<table[i]) {
				table[i] = table[i/2] + 1;
				used[i] = i/2;
			}

			if(i%3==0 && (table[i/3]+1)<table[i]) {
				table[i] = table[i/3] + 1;
				used[i] = i/3;
			}
		}
		
		// 결과 출력
		System.out.println(table[N]);
		
		StringBuilder sb = new StringBuilder();
		int cur = N;
		sb.append(cur).append(" ");
		while(used[cur] > 0) {
			sb.append(used[cur]).append(" ");
			cur = used[cur];
		}

		System.out.print(sb);
	}
}