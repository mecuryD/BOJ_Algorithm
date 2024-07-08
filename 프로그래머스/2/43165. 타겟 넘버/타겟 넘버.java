// [프로그래머스] 타겟 넘버
class Solution {
    
    static int result = 0;
    
    public int solution(int[] numbers, int target) {
        int n = numbers.length;
        dfs(n, target, 0, 0, numbers);
        return result;
    }
    
    
    // DFS 완전 탐색
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
}