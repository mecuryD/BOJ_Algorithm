import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [백준21608] 상어초등학교
public class Main {

	static int N; // 교실의 크기
	static int S; // 학생의 수
	static ArrayList<Integer> stdOrder; // 학생을 앉히는 순서
	static ArrayList<Integer>[] students; // 학생 관계

	static boolean[][] isSitted; // 빈 자리인지 확인
	static HashMap<Integer, int[]> posMap; // 앉아있는 학생의 위치
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = N * N;

		stdOrder = new ArrayList();
		students = new ArrayList[S + 1];
		for (int i = 0; i < S; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			int std = Integer.parseInt(stk.nextToken()); // 학생의 번호

			stdOrder.add(std);
			students[std] = new ArrayList<Integer>();
			for (int j = 0; j < 4; j++) {
				int tar = Integer.parseInt(stk.nextToken()); // 좋아하는 학생의 번호
				students[std].add(tar);
			}
		}

		// S명의 학생에 대해 반복
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>(new Comparator<Pos>() {
			@Override
			public int compare(Pos o1, Pos o2) {
				if(o1.near != o2.near) {
					return -(o1.near - o2.near); // 인접 칸 내림차순
				}else if(o1.empty != o2.empty) {
					return -(o1.empty - o2.empty); // 빈 자리 내림차순
				}else if(o1.r != o2.r){
					return o1.r - o2.r; // 행 오름차순
				}else {
					return o1.c - o2.c; // 열 오름차순
				}
			}
		});

		isSitted = new boolean[S + 1][S + 1];
		posMap = new HashMap<Integer, int[]>();
		// 첫 번째 학생은 무조건 (2,2)에 앉게 된다
		posMap.put(stdOrder.get(0), new int[] {2,2});
		isSitted[2][2] = true;
		
		for (int i = 1; i < S; i++) {
			int stdNum = stdOrder.get(i);
			int[][] cntLike = new int[N+1][N+1];
			
			// 앉힐 수 있는 자리를 탐색한다
			for (int std : students[stdNum]) { // i번째 학생이 좋아하는 학생들 중
				if (posMap.containsKey(std)) { // 이미 앉아 있는 학생이 있다면
					int[] pos = posMap.get(std);
					// 주변 4개의 자리에 대해
					for(int j=0; j<4; j++) {
						int nr = pos[0] + dr[j];
						int nc = pos[1] + dc[j];
						if(nr<1 || nc<1 || nr>N || nc>N) continue;
						if(isSitted[nr][nc]) continue;
						cntLike[nr][nc]++;
						pq.offer(new Pos(nr, nc, cntLike[nr][nc], getCntEmpty(nr,nc)));
					}
				}
			}

			// 최종적으로 앉힐 자리를 뽑는다
			// 만약 좋아하는 학생이 한 명도 안 앉아 있다면, 비어 있는 자리에 앉힌다
			// 만약 좋아하는 학생이 한 명이라도 앉아 있으면 조건에 맞게 앉힌다
			Pos cur = (pq.isEmpty()) ? getPosition() : pq.poll();
			posMap.put(stdNum, new int[] { cur.r, cur.c });
			isSitted[cur.r][cur.c] = true;
			pq.clear();
		}
		
		// 점수 계산
		System.out.println(getScore());
	}
	
	// 빈 자리의 개수를 반환하는 메서드
	public static int getCntEmpty(int r, int c) {
		int cnt = 0;
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr<1 || nc<1 || nr>N || nc>N) continue;
			if(!isSitted[nr][nc]) cnt++;
		}
		return cnt;
	}

	// 빈 자리를 탐색하는 메서드
	public static Pos getPosition() {
		Pos pos = new Pos(-1,-1,-1,-1);
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(!isSitted[i][j]) {
					int cnt=0;
					// 주변에 비어있는 칸 개수 탐색
					for(int k=0; k<4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if(nr<1 || nc<1 || nr>N || nc>N) continue;
						if(!isSitted[nr][nc]) cnt++;
					}
					// 빈 칸의 개수가 더 많은 경우, 업데이트
					if(cnt > pos.empty) {
						pos = new Pos(i, j, -1, cnt);
					}
				}
			}
		}
		return pos;
	}

	// 최종 점수 계산 메서드
	public static int getScore() {
		int score = 0;
		// S명의 학생들에 대해 
		for(int i=1; i<=S; i++) { 
			// i번째 학생이 좋아하는 학생 몇 명과 인접해있는지 계산
			int cnt = 0;
			int[] cur = posMap.get(i); 
			for(int n : students[i]) {
				int[] next = posMap.get(n); // 좋아하는 학생의 위치
				if(isNext(cur, next)) cnt++;
			}
			
			if(cnt>0) score += (int)Math.pow(10, cnt-1);
		}
		return score;
	}
	
	// 두 위치의 거리 계산 메서드
	public static boolean isNext(int[] pos1, int[] pos2) {
		int dist = Math.abs(pos1[0]-pos2[0]) + Math.abs(pos1[1]-pos2[1]);
		if(dist==1) return true;
		return false;
	}
	
static class Pos {
		int r;
		int c;
		int near; // 인접한 좋아하는 학생 수
		int empty; // 비어있는 칸

		public Pos(int r, int c, int near, int empty) {
			super();
			this.r = r;
			this.c = c;
			this.near = near;
			this.empty = empty;
		}
	}
}