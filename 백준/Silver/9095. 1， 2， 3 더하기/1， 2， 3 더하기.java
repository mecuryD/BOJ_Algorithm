import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		int[] table = new int[11]; // dp-table
		// Bottom-up Table
		table[1] = 1;
		table[2] = 2;
		table[3] = 4;
		for(int i=4; i<11; i++) 
			table[i] = table[i-1] + table[i-2] + table[i-3];
		
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(table[N]);
		}	
	}
}
