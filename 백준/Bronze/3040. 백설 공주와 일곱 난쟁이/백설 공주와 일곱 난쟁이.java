import java.util.Scanner;

public class Main{

	static int[] arr; // 아홉 난쟁이가 쓴 모자 번호 저장
	static int[] arr2; // 일곱 난쟁이가 쓴 모자 번호 저장
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arr = new int[9];
		arr2 = new int[7];		
		for(int i=0; i<9; i++) {arr[i] = sc.nextInt();}
		
		comb(0,0,0);
	}
	
	public static boolean comb(int cnt, int start, int sum) {
		// 기저 조건
		if(cnt==7) {
			if(sum==100) {
				for(int i=0; i<7; i++) {
					System.out.println(arr2[i]);
				}
				return true;
			}
			return false;
		}
		
		// 실행 구문
		for(int i=start; i<9; i++) {
			arr2[cnt] = arr[i];
			if(comb(cnt+1, i+1, sum+arr2[cnt])) {
				return true;
			}
		}
		return false;
	}
}