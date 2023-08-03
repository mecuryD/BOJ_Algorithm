import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int M,S;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		/* 입력  */
		StringTokenizer stk = new StringTokenizer(br.readLine());
		M = Integer.parseInt(stk.nextToken());
		String oper = "";
		int x = 0;
		
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			
			oper = stk.nextToken(); // oper : 수행할 연산 종류
			if(!oper.equals("all") && !oper.contentEquals("empty")) {
				x = Integer.parseInt(stk.nextToken()); // x : 연산에 사용할 숫자
			}	

			switch(oper) {
			case "add":
				S |= 1<<(x-1); // x 추가
				break;
			case "remove":
				S = S & ~(1<<(x-1));
				break;
			case "toggle" :
				S = S ^ (1<<(x-1)); // x가 있으면 제거하고, 없으면 추가
				break;
			case "check" :
				sb.append((S & (1<<(x-1))) != 0 ? 1 : 0); // x가 있으면 1, 없으면 0 출력
				sb.append("\n");
				break;
			case "all" :
				S = -1; // S에 1부터 20까지 모두 추가
				break;
			case "empty" :
				S &= 0; // 공집합으로 변경
				break;
			}
		}
		System.out.println(sb.toString());
	}
}