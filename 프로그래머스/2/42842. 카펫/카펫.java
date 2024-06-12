// [프로그래머스] 카펫
// 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 같은 색으로 칠해져 있는 격자 모양 카펫
// 갈색 격자, 노란 격자의 수가 주어질 때 카펫의 [가로, 세로] 크기를 배열에 담아 리턴
// 가로 길이는 세로 길이와 같거나 세로 길이보다 길다

// [1단계] ((갈색 격자 개수 + 대각 모서리 2개) / 2) 계산식으로 테두리 가로, 세로 1쌍의 격자의 수 계산
// [2단계] ((1단계 결과 + 1 (홀수인 경우)) / 2) 계산식으로 테두리 가로, 세로의 길이를 동일하게 설정한다
// [3단계] 세로 길이는 줄이고, 가로 길이는 늘려가며 노란 격자의 수와 맞는지 확인한다
//        노란 격자의 수는 ((가로 테두리 길이-1) x (세로 테두리 길이-1))로 계산한다  
class Solution {
    public int[] solution(int brown, int yellow) {
        // 테두리 1쌍의 개수를 구한다
        int outline = (brown + 2) / 2;
        
        // 테두리 길이 초기화
        int width = (int) Math.ceil((outline + 1) / 2.0);
        int height = outline - width + 1;
        
        // 노란 격자의 수가 일치하는지 확인한다
        while(height > 2){
          // 노란 격자의 개수 계산
          if((width-2) * (height-2) == yellow){
            break;
          }
          
          width++;
          height--;
        }
        return new int[]{width, height};
    }
}