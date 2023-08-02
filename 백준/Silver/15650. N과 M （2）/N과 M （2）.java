import java.util.Scanner;
public class Main {

	public static int N,M;
	public static int[] arr;
	
	public static int cnt;
	public static int[] res;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 입력
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N]; // 1부터 N까지의 자연수 저장
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		
		res = new int[M]; // 만들어진 수열 저장
		
		// 수열 생성
		comb(0,0);
	}
	
	public static void comb(int cnt, int start) {
		// 기저 조건
		if(cnt==M) { // 길이 M인 수열이 완성되었다면
			for(int i=0; i<M; i++) {
				System.out.print(res[i] + " ");
			}
			System.out.println();
			return;
		}
		
		// 유도 조건
		for(int i=start; i<N; i++) {
			res[cnt] = arr[i]; // 현재 위치의 숫자를 결과 배열에 저장
			comb(cnt+1, i+1); // 수열의 다음 칸을 뽑는다 (※ start는 고정 값)
		}
	}
}