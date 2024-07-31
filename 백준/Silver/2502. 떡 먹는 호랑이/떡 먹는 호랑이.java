import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// [백준] 떡 먹는 호랑이
public class Main{

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int D = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		
		// DP 테이블 초기화
		int[] table = new int[31];
		table[2] = 1;
		
		for(int i=3; i<=30; i++) {
			table[i] = table[i-1] + table[i-2];
		}
		
		// 수식을 이용하여 A, B를 구한다
		for(int i=1; i<K; i++) {
			if((K - table[D-1] * i) % table[D] == 0) {
				System.out.println(i);
				System.out.println((K - table[D-1] * i) / table[D]);
				break;
			}
		}
	}
}