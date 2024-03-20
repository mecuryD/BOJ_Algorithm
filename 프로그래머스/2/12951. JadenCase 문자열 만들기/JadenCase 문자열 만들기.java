// JadenCase 문자열 만들기
// Java에서 제공하는 함수를 충분히 활용하자
// Java에서는 모든 문자를 대/소문자로 변환하는 toUpperCase(), toLowerCase()를 제공한다
class Solution {
    public String solution(String s) {
        String answer = "";
        String[] sentence = s.toLowerCase().split("");
        boolean isFirst = true;

        for(String sen : sentence){
            // 단어의 첫 문자일 경우
            if(isFirst){
                isFirst = false;
                sen = sen.toUpperCase();
            }
            
            // 공백인 경우
            if(sen.equals(" ")) {
                isFirst = true;
            }
            
            answer += sen;
        }
        
        return answer;
    }
}
