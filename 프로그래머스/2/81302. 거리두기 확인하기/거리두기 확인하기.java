// [프로그래머스] 거리두기 확인하기
// 문제 조건 : 각 대기실 별로 거리두기를 지키고 있으면 1, 한 명이라도 지키고 있지 않으면 0 리턴

import java.io.*;
import java.util.*;
import java.lang.*;

class Solution {
    
    static int[] dr = {-1, 1, 0, 0}; // 행 방향 벡터
    static int[] dc = {0, 0, -1, 1}; // 열 방향 벡터

    static char[][][] map; // 대기실
    static int[] answer = new int[]{1, 1, 1, 1, 1}; // 정답 배열

    public int[] solution(String[][] places) {
        // 대기실 입력
        map = new char[5][5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                for(int k=0; k<5; k++){
                    map[i][j][k] = places[i][j].charAt(k);
                }
            }
        }

        // 각 대기실 별로 거리두기 준수 여부를 확인한다
        for(int i=0; i<5; i++){
            if(checkRoom(i)) answer[i] = 0;
        }
        
        return answer;
    }

    // n번째 대기실의 거리 준수 여부를 확인한다
    public static boolean checkRoom(int n){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(map[n][i][j]=='P'){
                    // 미준수자가 한 명이라도 발견되면 TRUE 리턴
                    boolean[][] visited = new boolean[5][5];
                    visited[i][j] = true;

                    if(dfs(n, i, j, new int[]{i,j}, visited)) return true;
                }
            }
        }
        return false;
    }

     // 응시자의 맨해튼 거리를 체크한다
    public static boolean dfs(int n, int r, int c, int[] o, boolean[][] visited){

        // 동서남북을 살펴본다
        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            int manDis = Math.abs(o[0]-nr) + Math.abs(o[1]-nc);

            if(nr<0 || nc<0 || nr>4 || nc>4) continue;  // 배열 범위 초과
            if(visited[nr][nc]) continue;               // 이미 체크한 지점인 경우
            if(manDis > 2) continue;                    // 맨해튼 거리 조건에서 벗어나는 경우

            if(map[n][nr][nc]=='P') return true;        // 거리두기 미준수자 리턴
            if(map[n][nr][nc]=='O'){                    // 빈 자리인 경우 이동한다
                visited[nr][nc] = true;
                if(dfs(n, nr, nc, o, visited)) return true;    // 거리두기 미준수자를 발견하면 리턴
                visited[nr][nc] = false;
            }
        }

        return false;
    }
}