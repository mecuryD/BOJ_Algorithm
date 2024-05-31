import java.util.*;

class Solution {
    
    static int len;  // 던전 개수
    static int max ; // 탐험 가능한 최대 던전 수
    
    public int solution(int k, int[][] dungeons) {
        // 변수 초기화
        max = 0;
        len = dungeons.length;
        boolean[] visit = new boolean[len];
        
        // 완전 탐색 (재귀)
        adventure(k, 0, visit, dungeons);
        return max;
    }
    
    // 각 던전을 순열 순서에 맞게 탐험
    public void adventure(int k, int count, boolean[] visit, int[][] dungeons){
        // 다음 던전 이동
        boolean flag = true;
        for(int i=0; i<len; i++){
            if(visit[i] || dungeons[i][0] > k){
                continue;
            }
            
            visit[i] = true;
            adventure(k-dungeons[i][1], count+1, visit, dungeons);
            visit[i] = false;
            
            flag = false;
        }
        
        // max 값 비교
        max = (max < count) ? count : max;
    }
}