import java.util.*;

// [프로그래머스] 야근 지수
class Solution {
    
    long result = 0;
    
    public long solution(int n, int[] works) {
        // 우선순위 큐에 모든 작업을 넣는다
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return -(o1-o2);
            }
        });
            
        for(int w : works){
            pq.offer(w);
        }
        
        // 남은 작업량이 큰 순서대로 차례로 하나씩 처리한다
        for(int i=0; i<n; i++){
            if(pq.isEmpty()) break;
            int cur = pq.poll();
            if(cur > 1) pq.offer(cur-1);
        }
        
        while(!pq.isEmpty()){
            int left = pq.poll();
            result += left * left;
        }
        return result;
    }
}