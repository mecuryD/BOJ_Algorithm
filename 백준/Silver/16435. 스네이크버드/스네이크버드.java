import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [BOJ16435] 스네이크버드
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken());
		int L = Integer.parseInt(stk.nextToken());
		
		int[] fruit = new int[N];
		stk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) // 과일 입력, O(N)
			fruit[i] = Integer.parseInt(stk.nextToken());
		
		Arrays.sort(fruit); // 오름차순 정렬
		
		for(int i=0; i<N; i++) { // 스네이크 버드가 과일을 먹음, O(N)
			if(fruit[i] > L) break; // 과일이 스네이크버드보다 높으면 더 이상 못 먹는다
			L++; // 과일을 먹고 스네이크 버드 길이 +1
		}
		System.out.println(L);
    }
}