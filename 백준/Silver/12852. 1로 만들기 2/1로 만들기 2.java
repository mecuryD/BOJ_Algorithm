import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// [백준] 1로 만들기 2
public class Main{

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 테이블 정의
		int[] table = new int[N+1];
		Arrays.fill(table, 9999999);
		table[1] = 0;
		
		List<Integer>[] used = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			used[i] = new ArrayList<Integer>();
			
		}
		used[1].add(1);
		
		// 다이나믹 프로그래밍
		for(int i=2; i<=N; i++) {
			int flag = 1;
			table[i] = table[i-1] + 1;
			
			if(i%2==0 && (table[i/2]+1)<table[i]) {
				table[i] = table[i/2] + 1;
				flag = 2;
			}

			if(i%3==0 && (table[i/3]+1)<table[i]) {
				table[i] = table[i/3] + 1;
				flag = 3;
			}

			used[i].add(i);
			switch(flag) {
				case 1:
					for(int n : used[i-1]) {
						used[i].add(n);
					}
					break;
				case 2:
					for(int n : used[i/2]) {
						used[i].add(n);
					}
					break;
				case 3:
					for(int n : used[i/3]) {
						used[i].add(n);
					}
					break;
			}
			
		}
		
		// 결과 출력
		Collections.sort(used[N]);
		Integer[] result = used[N].toArray(new Integer[0]);
		
		StringBuilder sb = new StringBuilder();
		for(int i=result.length-1; i>=0; i--) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(table[N]);
		System.out.print(sb);
	}
}