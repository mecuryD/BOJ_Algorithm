// [프로그래머스] 더 맵게
// 섞은 음식의 스코빌 = 가장 맵지 않은 음식의 스코빌 + (두 번째로 맵지 않은 음식 스코빌 * 2)
// 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞을 때, 필요한 최소 횟수를 구하라
// 만약 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우 -1 반환

// 우선순위 큐를 사용, 낮은 순서대로 나오도록
// 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우 => 우선순위 큐에 1개만 남는 경우
import java.util.*;

class Solution {

    static int result = 0;
    
    public int solution(int[] scoville, int K) {
        // 우선순위 큐 초기화
        // 만약에 Integer로 안 되면 Long 값으로 처리해보기
        PriorityQueue<Long> pq = new PriorityQueue<Long>(new Comparator<Long>(){
            @Override
            public int compare(Long o1, Long o2){
                if(o1 > o2) return 1;
                else return -1;
            }
        });
        
        for(long s : scoville){
            pq.offer(s);
        }
        
        // 우선순위 큐를 순회하면서 음식을 섞는다
        while(!pq.isEmpty()){
            
            // 가장 맵지 않은 음식의 스코빌 지수가 K 이상이라면 최소 횟수 리턴
            if(pq.peek() >= K){
                return result;
            }
            
            // 가장 맵지 않은 음식의 스코빌 지수가 K 미만이고
            // 음식이 1개만 남았다면 불가능한 경우이므로 -1을 리턴한다
            if(pq.size() == 1){
                return -1;
            }
            
            // 두 음식을 섞는다
            Long aFood = pq.poll();
            Long bFood = pq.poll();
            pq.offer(aFood + (bFood * 2));
            result++;
        }
    
        return result;
    }
}