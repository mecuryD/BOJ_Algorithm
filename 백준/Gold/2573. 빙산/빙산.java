import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준2573] 빙산
public class Main {

    static int N;
    static int M;
    static int ice = 0; // 빙산이 차지하는 칸의 개수
    static int time = 0; // 흐른 시간
    static boolean isDivided = false; // 두 영역으로 분리되었는지 확인하는 변수
    static int[][] map; // N x M 빙산 배열

    public static void main(String[] args) throws IOException {
        // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N][M];
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
                if(map[i][j] > 0){
                    ice++;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 빙산이 녹기 시작한다
        while(ice > 0){
            time++;

            // 1년이 지나 빙산이 녹는다
            int len = queue.size();
            int[][] copyMap =  copy();

            for(int i=0; i<len; i++){
                // 빙산 1개를 꺼낸다
                int[] cur = queue.poll();

                // 동서남북으로 붙어있는 바다 개수만큼 녹는다
                int cnt = 0;
                if(map[cur[0]][cur[1]+1]==0) cnt++;
                if(map[cur[0]][cur[1]-1]==0) cnt++;
                if(map[cur[0]+1][cur[1]]==0) cnt++;
                if(map[cur[0]-1][cur[1]]==0) cnt++;

                // 빙산이 다 녹지 않았으면 다시 큐에 삽입한다;
                if(map[cur[0]][cur[1]] > cnt){
                    copyMap[cur[0]][cur[1]] = map[cur[0]][cur[1]] - cnt;
                    queue.offer(new int[]{cur[0], cur[1]});
                }else{
                    copyMap[cur[0]][cur[1]] = 0;
                    ice--;
                }
            }
            map = copyMap;
            // 빙산이 두 덩어리로 분리되었는지 확인한다
            if(ice > 0) isDivided = checkDivision(queue.peek());
            if(isDivided) break;
        }

        // 결과 출력
        if(isDivided) System.out.print(time);
        else System.out.print(0);
    }

    // 두 영역이 분리되었는지 체크한다
    public static boolean checkDivision(int[] start){
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        int cnt = 1;
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            // 연결된 빙산 조각을 탐색한다
            for(int i=0; i<4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                // 아직 탐색되지 않은 빙산 조각이면 큐에 삽입한다
                if(map[nr][nc]>0 && !visited[nr][nc]){
                    queue.offer(new int[]{nr,nc});
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }
        
        // 탐색된 빙산 조각이 전체 조각 수와 다르면 덩어리가 분리된 것이다
        if(cnt==ice) return false;
        else return true;
    }

    // 배열 복사 메서드
    public static int[][] copy(){
        int[][] copyMap = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }
}