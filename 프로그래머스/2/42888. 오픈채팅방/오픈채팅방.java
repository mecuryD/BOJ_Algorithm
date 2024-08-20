// [프로그래머스] 오픈채팅방
import java.util.*;

class Solution {
    
    public String[] solution(String[] record) {
        String[] message = new String[]{"님이 들어왔습니다.", "님이 나갔습니다."};
        Queue<Info> buffer = new ArrayDeque<Info>(); 
        Map<String, String> nickname = new HashMap<String, String>();
        
        // 기록 각각에 대해 닉네임은 해쉬로, 입퇴장정보는 큐로 관리한다
        for(String r : record){
            String[] chat = r.split(" ");
            
            if(chat[0].equals("Change")){
                nickname.put(chat[1], chat[2]);
            }else if(chat[0].equals("Enter")){
                nickname.put(chat[1], chat[2]);
                buffer.offer(new Info(chat[1], 0));
            }else{
                buffer.offer(new Info(chat[1], 1));
            }
        }
        
        // 관리자가 최종적으로 확인하는 메세지를 배열에 추가한다
        int length = buffer.size();
        String[] result = new String[length];
        for(int i=0; i<length; i++){
            Info info = buffer.poll();
            result[i] = nickname.get(info.id) + message[info.state];
        }
        
        return result;
    }
    
    // 입퇴장 정보 클래스
    public class Info {
        String id; // 유저 ID
        int state; // 0:입장, 1:퇴장
        
        public Info(String id, int state){
            this.id = id;
            this.state = state;
        }
    }
}