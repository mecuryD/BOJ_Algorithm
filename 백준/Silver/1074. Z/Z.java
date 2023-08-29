import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ1074] Z
public class Main {

	static int cnt = 0;
	static int N, R, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		// 이분 탐색 (w.DFS)
		binarySearch(0, 0, (int)(Math.pow(2, N)));
	}
	
	public static boolean binarySearch(int sr, int sc, int size) {
		// 기저 구문
		if(size==1 && sr==R && sc==C) {
			System.out.println(cnt);
			return true;
		}
		
		// 실행 구문 : Z방향으로 탐색
		int half = size/2;
		boolean res = false;
		if(sr+half>R) {
			if(sc+half>C) {
				res = binarySearch(sr,sc,half);				
			}else {
				cnt += half*half;
				res = binarySearch(sr,sc+half,half);				
			}
		}else{
			cnt += size * size / 2;
			if(sc+half>C) {
				res = binarySearch(sr+half,sc,half);				
			}else {
				cnt += half*half;
				res = binarySearch(sr+half,sc+half,half);
			}
		}
		
		if(res) return true;
		return false;
	}
}