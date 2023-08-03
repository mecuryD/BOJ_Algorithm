import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M; // 문서의 개수, 궁금한 문서의 현재 위치
	static StringTokenizer stk;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
		
		for(int test_case=0; test_case<T; test_case++) { // 테스트 케이스 개수만큼 반복
			stk = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(stk.nextToken()); // 문서의 개수
			M = Integer.parseInt(stk.nextToken()); // 궁금한 문서의 현재 위치
			
			stk = new StringTokenizer(br.readLine(), " ");
			int[] arr = new int[N]; // 문서의 중요도를 저장하는 배열 
			Queue<Integer> q = new LinkedList<Integer>(); // 큐 생성
			for(int i=0; i<N; i++) { // 문서의 중요도 저장
				arr[i] = Integer.parseInt(stk.nextToken());
				q.add(arr[i]); // 큐에 저장
			}
			
			Arrays.sort(arr); // 중요도 배열 정렬
			int maxIdx = arr.length-1; // 현재 중요도가 가장 높은 문서의 인덱스

			int cnt = 0; // 문서가 인쇄되는 순서
			int doc = M; // 궁금해하는 문서의 현재 위치
			
			while(true) { // doc이 출력될 때 반복문 탈출
				Integer x = q.poll(); // 큐에서 값을 뺀다
				if(x != arr[maxIdx]) { // 나머지에 중요도가 높은 값이 있을 경우, 다시 큐에 넣어준다
					doc = (doc==0) ? q.size()+1 : doc; // 현재 값이 궁금해하는 문서일 경우, 위치 업데이트
					q.add(x);
				}else { // 현재 값이 중요도가 가장 높을 경우, 카운트 증가
					cnt++;
					maxIdx--;
					if(doc==0) { // 현재 값이 궁금해하는 문서일 경우, 반복문 탈출
						System.out.println(cnt);
						break;
					}
				}
				doc--;
			}			
		}
	}
}