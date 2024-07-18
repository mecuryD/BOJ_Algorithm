// [프로그래머스] 광물 캐기
class Solution {
    
    int min = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        // 곡괭이 별로 피로도를 5개 단위로 계산
        int mLen = minerals.length;
        int pLen = (mLen % 5 == 0) ? (mLen/5) : (1 + mLen/5);
        int[] dPick = new int[pLen];
        int[] iPick = new int[pLen];
        int[] sPick = new int[pLen];
        
        int[] diamond = new int[]{1, 1, 1};
        int[] iron = new int[]{5, 1, 1};
        int[] stone = new int[]{25, 5, 1};
        
        for(int i=0; i<mLen; i++){
            // 광물 식별
            int mineral = 0;
            if(minerals[i].equals("iron")) mineral = 1;
            else if(minerals[i].equals("stone")) mineral = 2;
            
            int idx = i / 5;
            dPick[idx] += diamond[mineral];
            iPick[idx] += iron[mineral];
            sPick[idx] += stone[mineral];
        }

        // DFS 완전탐색(w.백트래킹)
        // 광물 5개 단위로 곡괭이를 선택할 때의 최소 피로도 계산
        dfs(0, 0, pLen, picks, dPick, iPick, sPick);

        return min;
        
    }
    

    public void dfs(int idx, int sum, int pLen, int[] picks,  int[] dPick, int[] iPick, int[] sPick){
        // 모든 광물을 다 캐거나, 더 사용할 곡괭이가 없으면 결과 반영
        if(idx==pLen || picks[0]+picks[1]+picks[2]==0){
            min = (sum < min) ? sum : min;
            return;
        }
        
        // 다이아몬드 곡괭이
        if(picks[0] > 0 && sum + dPick[idx] < min){
            picks[0]--;
            dfs(idx+1, sum + dPick[idx], pLen, picks, dPick, iPick, sPick);
            picks[0]++;
        }
        
        // 철 곡괭이
        if(picks[1] > 0 && sum + iPick[idx] < min){
            picks[1]--;
            dfs(idx+1, sum + iPick[idx], pLen, picks, dPick, iPick, sPick);
            picks[1]++;
        }
            
        // 돌 곡괭이
        if(picks[2] > 0 && sum + sPick[idx] < min){
            picks[2]--;
            dfs(idx+1, sum + sPick[idx], pLen, picks, dPick, iPick, sPick);
            picks[2]++;
        }
    }
}