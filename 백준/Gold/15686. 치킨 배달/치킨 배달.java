import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// [BOJ15686] 치킨 배달
public class Main{

	static int N, M;
	static int homeCnt, chickenCnt;
	static int minDis = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		// 입력 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken()); // 도시의 크기 NxM
		M = Integer.parseInt(stk.nextToken()); // 도시의 크기 NxM
		
		int[][] map = new int[N][N]; // NxM 크기의 도시 입력
		ArrayList<Info> homeList = new ArrayList<Info>(); // 도시의 집 관리 리스트
		ArrayList<Info> chickenList = new ArrayList<Info>(); // 도시의 치킨집 관리 리스트
		
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine().trim());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j]==1) homeList.add(new Info(i,j)); // 집이 입력되면 homeList에 저장한다 
				else if(map[i][j]==2) chickenList.add(new Info(i,j)); // 치킨집이 입력되면 chickenList에 저장한다
			}
		}
		
	
		// 각 집과 각 치킨집 사이의 거리를 계산한다
		homeCnt = homeList.size();
		chickenCnt = chickenList.size();
		int[][] dis = new int[homeCnt][chickenCnt];
		for(int i=0; i<homeCnt; i++) {
			Info home = homeList.get(i);
			for(int j=0; j<chickenCnt; j++) {
				Info chicken = chickenList.get(j);
				// 거리 계산
				dis[i][j] = Math.abs(home.r-chicken.r) + Math.abs(home.c-chicken.c);
			}
		}
		
		// 최대 M개를 뽑는 부분 집합을 구한 다음, 도시의 치킨 거리의 최솟값을 구한다
		subset(0, 0, new boolean[chickenCnt], dis);
		
		// 결과 출력
		System.out.println(minDis);
	}
	
	// 부분 집합 생성 메서드
	public static void subset(int cnt, int sel, boolean[] selected, int[][] dis) {
		// 기저 구문
		if(sel==M || cnt==chickenCnt) { // M개의 치킨집이 선택되었거나, 모든 치킨집의 영업 여부가 결정되었으면
			if(sel==0) return;
			int sum = 0;  // 도시의 치킨 거리
			for(int i=0; i<homeCnt; i++) {
				int min = Integer.MAX_VALUE; // 각 집의 치킨 거리
				for(int j=0; j<chickenCnt; j++) { // 영업하는 치킨집과의 거리가
					if(selected[j] && dis[i][j]<min) min = dis[i][j]; // 현재 최솟값보다 작으면 최솟값 업데이트
				}
				
				if(min!=Integer.MAX_VALUE) sum+=min;
			}
			
			minDis = (sum < minDis) ? sum : minDis; // 최소 치킨 거리 업데이트
			return;
		}
		
		// 실행 구문
		selected[cnt] = true;
		subset(cnt+1, sel+1, selected, dis);
		selected[cnt] = false;
		subset(cnt+1, sel, selected, dis);
	}
	
	
	// 치킨집 위치 정보 클래스
	static class Info{
		int r, c;

		public Info(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Info [r=" + r + ", c=" + c + "]";
		}
			
	}
}