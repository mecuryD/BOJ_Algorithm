import java.util.Scanner;

public class Main {

	static int num;
	public static void main(String[] args) {
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");

		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		chatbot(0);
	}
	
	public static void chatbot(int cnt) { // 매 번 변경되는 값 관리
		// 현재 재귀 반복 횟수에 따라 출력되어야 할 문자열 처리
		String str = "";
		for(int i=0; i<cnt; i++) {
			str+="____";
		}
		
		// 매 반복마다 가장 먼저 출력되는 텍스트
		System.out.println(str+"\"재귀함수가 뭔가요?\"");
		if(cnt == num) {
			// 주어진 재귀 횟수가 끝났을 때 출력되는 텍스트
			System.out.println(str+"\"재귀함수는 자기 자신을 호출하는 함수라네\"");
		}else {
			// 일반적인 경우 출력되는 텍스트
			System.out.println(str+"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
			System.out.println(str+"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
			System.out.println(str+"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			
			// 재귀 호출
			chatbot(cnt+1);
		}
		System.out.println(str+"라고 답변하였지."); // 반드시 마지막에 출력 되어야 하는 문장
	}
}
