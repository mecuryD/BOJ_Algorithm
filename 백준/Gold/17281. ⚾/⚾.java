import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [BOJ17281] ⚾
public class Main{

	static int N; // 이닝 수 N
	static int maxScore = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 이닝 수 N
		int[][] player = new int[9][N]; // 각 선수가 각 이닝에서 얻는 결과 배열
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<9; j++) {
				// j번째 선수가 i번째 이닝에서 얻는 결과
				player[j][i] = Integer.parseInt(stk.nextToken());
			}
		}
		
		int[] list = new int[9]; // 타순
		int flag = 0 | 1<<0; // 선수가 선택되었는지 방문 체크, 비트마스킹
		
		// 순열, 비트마스킹
		perm(list, player, 0, flag);
		System.out.println(maxScore);

	}
	
	public static void perm(int[] list, int[][] player, int cnt, int flag) {
		// 기저 구문
		if(cnt==9) {
			// 점수 계산
			int score = calScore(list, player);
			// 현재 최대 점수보다 클 때만 업데이트
			if(maxScore<score) 
				maxScore = score;
			return;
		}
		
		// 실행 구문
		for(int i=0; i<9; i++) {
			if((flag & 1<<i) !=0)
				continue; // i번째 타자가 이미 선택되었으면 continue
			
			if(cnt==3) cnt++; // 4번째 타자는 1번으로 결정되었으니 5번 타자로 넘어간다
			list[cnt] = i; // i번째 타자 선택
			perm(list, player, cnt+1, flag | 1<<i); // 순열의 다음 원소 선택, 재귀 호출
		}
	}

	public static int calScore(int[] list, int[][] player) {
		int pIdx = -1; // 현재 타석에 있는 선수 인덱스
		int out = 0; // 현재 아웃의 개수
		int score = 0; // 아인타 팀이 얻는 점수
		int[] base = new int[3]; // 베이스 상태
		
		int inning = 0; // 현재 이닝
		while(inning<N) { // N개의 이닝동안 점수를 계산한다
			pIdx = (pIdx+1) % 9; 
			int result = player[list[pIdx]][inning]; // 타석에 있는 선수의 결과
			switch(result) {
			case 0: // 아웃
				out++;
				break;
			case 1: // 안타
				score += base[2]; // 3루에 다른 선수가 있었으면
				base[2] = 0; // 홈으로 보낸다

				base[2] = base[1]; // 2루에 다른 선수가 있었으면
				base[1] = 0;// 3루로 보낸다
				
				base[1] = base[0]; // 1루에 다른 선수가 있었으면
				base[0] = 0; // 2루로 보낸다
				
				base[0] = 1; // 현재 타자를 1루에 보낸다
				break;
				
			case 2: // 2루타
				score += base[2]; // 3루에 다른 선수가 있었으면
				base[2] = 0;// 홈으로 보낸다
				
				score+= base[1]; // 2루에 다른 선수가 있었으면
				base[1] = 0; // 홈으로 보낸다
				
				base[2] = base[0]; // 1루에 다른 선수가 있었으면 
				base[0] = 0; // 3루로 보낸다
				
				base[1]=1; // 현재 타자를 2루에 보낸다
				break;
			case 3: // 3루타
				for(int i=0; i<3; i++) // 각 베이스에 선수가 있었으면
					score += base[i]; // 홈으로 보낸다
				base = new int[3];
				base[2] = 1; // 현재 타자를 3루에 보낸다
				break;
			case 4: // 홈런
				for(int i=0; i<3; i++) // 각 베이스에 선수가 있었으면
					score += base[i]; // 홈으로 보낸다

				score++; // 홈런
				base = new int[3]; // 현재 타자를 한 바퀴 돌아 홈으로 보낸다
				break;
			}
			
			if(out==3) { // 3아웃 발생
				out = 0;
				inning++;
				base = new int[3];
			}
		}
		return score;
	}
}