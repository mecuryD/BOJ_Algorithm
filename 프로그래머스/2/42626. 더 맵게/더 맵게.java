import java.util.*;

// [프로그래머스] 더 맵게
class Solution {

    static int result = 0;
    
    public int solution(int[] scoville, int K) {
        // 우선순위 큐 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int s : scoville){
            pq.offer(s);
        }
        
        // 최소 스코빌 지수 K를 넘지 않는 음식이 두 개 이상 존재하는 경우, 두 음식을 섞는다  
        while(pq.size() > 1 && pq.peek() < K){
            int aFood = pq.poll();
            int bFood = pq.poll();
            pq.offer(aFood + (bFood * 2));
            result++;
        }
        
        // 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우 -1을 리턴
        if(pq.peek() < K){
            return -1;
        }
    
        return result;
    }
}