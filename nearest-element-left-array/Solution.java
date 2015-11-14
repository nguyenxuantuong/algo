//http://www.geeksforgeeks.org/find-the-nearest-smaller-numbers-on-left-side-in-an-array/
import java.util.*;

public class Solution {
	public int[] nearestSmallerLeftSide(int[] A){
		int N = A.length;

		Stack<Integer> stack = new Stack<Integer>();

		int[] out = new int[N];

		for(int i=0; i<N; i++){
			while(!stack.isEmpty() && stack.peek() >= A[i]) stack.pop();
			if(stack.isEmpty()) {
				out[i] = -1;
			} else {
				out[i] = stack.peek();				
			}

			stack.push(A[i]);
		}

		return out;
	}

	//keeping trace
	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
		
		int T = scanner.nextInt();

		for(int x = 0; x < T; x++) {
			int N = scanner.nextInt();
		
			int[] A = new int[N];

			for(int i=0; i<N; i++){
				A[i] = scanner.nextInt();
			}

			System.out.println(Arrays.toString(solution.nearestSmallerLeftSide(A)));
		}
		
		scanner.close();
	}
}