import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			switch(stk.nextToken()) {
			case "push" :
				stack.push(Integer.parseInt(stk.nextToken()));
				break;
			case "pop" :
				if(!stack.isEmpty())
					System.out.println(stack.pop());
				else
					System.out.println("-1");
				break;
			case "top" :
				if(!stack.isEmpty())
					System.out.println(stack.peek());
				else
					System.out.println("-1");
				break;
			case "size" :
				System.out.println(stack.size());
				break;
			case "empty" :
				if(stack.isEmpty())
					System.out.println("1");
				else
					System.out.println("0");
				break;
			}
		}
	}
}