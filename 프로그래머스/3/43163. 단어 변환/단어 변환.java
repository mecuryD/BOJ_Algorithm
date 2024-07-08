import java.util.*;

// [프로그래머스] 단어 변환
class Solution {
    
    public int solution(String begin, String target, String[] words) {
        // words 배열에 target이 존재하는지 체크
        isExist(target, words);
        
        // begin을 포함한 새로운 단어 배열, 그래프 생성
        int cnt = words.length;
        int newCnt = words.length + 1;
        String[] newWords = new String[newCnt];
        ArrayList<Integer>[] graph = new ArrayList[newCnt];
        
        newWords[0] = begin;
        for(int i=0; i<cnt; i++){
            newWords[i+1] = words[i];
        }
        
        for(int i=0; i<newCnt; i++){
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int i=0; i<newCnt; i++){            
            for(int j=i+1; j<newCnt; j++){
                int diff = 0;
                for(int k=0; k<begin.length(); k++){
                    if(newWords[i].charAt(k) != newWords[j].charAt(k)){
                        diff++;
                    }     
                }
                
                if(diff==1){
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        // 최단 거리 탐색
        Queue<Word> queue = new ArrayDeque<Word>();
        boolean[] visited = new boolean[newCnt];
        queue.offer(new Word(0, 0));
        visited[0] = true;
        
        while(!queue.isEmpty()){
            Word cur = queue.poll();
            visited[cur.idx] = true;
            
            if(target.equals(newWords[cur.idx])){
                return cur.level;
            }
            
            for(int next : graph[cur.idx]){
                if(!visited[next]){
                    queue.offer(new Word(next, cur.level + 1));
                }
            }
        }
    
        return 0;
    }
    
    // 단어가 배열에 존재하는지 체크한다
    public boolean isExist(String str, String[] words){
        for(String word : words){
            if(word.equals(str)){
                return true;
            }
        }
        return false;
    }

    
    class Word {
        int idx;
        int level;
        
        public Word(int idx, int level){
            this.idx = idx;
            this.level = level;
        }
    }
}