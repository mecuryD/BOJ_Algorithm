import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//제출 확인
public class Main { // 월말평가 2번. 업무 평가
	public static void main(String[] args) throws IOException { // 프로그램이 시작되는 위치
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 값 입력
		int N = Integer.parseInt(br.readLine()); // 1분기 전체 시간 N 입력
		Stack<int[]> stack = new Stack<int[]>(); // 업무를 저장할 스택 생성
		
		int res = 0; // 김삼성의 업무 평가 점수
		for(int i=0; i<N; i++) { // 김삼성은 매 분마다 업무를 확인한다
			StringTokenizer stk = new StringTokenizer(br.readLine(), " "); // N분째에 주어진 업무 정보 입력
			boolean isExist = Integer.parseInt(stk.nextToken())==1; // 새로운 업무가 있는지 확인			
			if(isExist) { // 새로운 업무가 있는 경우 : 새로운 업무 수행
				// 스택에 새로운 업무 추가
				int score = Integer.parseInt(stk.nextToken()); // 업무의 만점
				int time = Integer.parseInt(stk.nextToken()); // 업무에 필요한 시간
				stack.push(new int[] {score, time}); // 스택에 새로운 업무 추가
			}
			
			int[] work; // 현재 수행해야 할 업무 저장
			if(!stack.isEmpty()) { // 남아 있는 업무가 있다면				
				work = stack.peek(); // 가장 최근에 받은 업무를 꺼내
				work[1]--; // 1분 동안 수행한다
				if(work[1]==0) { // 업무가 완료되었다면
					stack.pop();// 업무 리스트에서 제외하고
					res += work[0]; // 업무 평가 점수에 반영
				}
			}
		}
		System.out.println(res); // 결과 출력
	}
}