import java.util.*;

// [프로그래머스] 타겟 넘버
class Solution {
    
    static int result = 0;
    
    public int solution(int[] numbers, int target) {
        int n = numbers.length;
        // dfs(n, target, 0, 0, numbers);
        bfs(n, target, numbers);
        return result;
    }
    
    
    // DFS 완전 탐색 (17분)
    public void dfs(int n, int target, int idx, int sum, int[] numbers){
        if(idx==n){
            if(sum==target){
                result++;
            }
            return;
        }
        
        // idx번째 숫자를 더한다
        dfs(n, target, idx + 1, sum + numbers[idx], numbers);
        
        // idx번째 숫자를 뺀다
        dfs(n, target, idx + 1, sum - numbers[idx], numbers);
    }
    
    // BFS 완전 탐색
    public void bfs(int n, int target, int[] numbers){
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{0, numbers[0]});
        queue.offer(new int[]{0, (-1) * numbers[0]});
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0]==n-1){
                if(cur[1]==target){
                    result++;
                }
                continue;
            }
            
            queue.offer(new int[]{cur[0] + 1, cur[1] + numbers[cur[0] + 1]});
            queue.offer(new int[]{cur[0] + 1, cur[1] - numbers[cur[0] + 1]});
        }
    }
    
}