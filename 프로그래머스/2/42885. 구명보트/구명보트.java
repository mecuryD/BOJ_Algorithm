import java.util.*;

// [프로그래머스] 구명보트
class Solution {
    public int solution(int[] people, int limit) {
        // 배열 정렬
        Arrays.sort(people);
        
        // 최소 구명보트 개수를 구한다
        int start = 0;
        int end = people.length-1;
        
        int result = 0;
        while(start <= end){
            // 두 명이 함께 탈 수 있는 경우
            if(people[start] <= (limit - people[end])) {   
                start++;
            }
            
            end--;
            result++;
        }
        
        return result;
    }
}