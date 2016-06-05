//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
import java.util.*;

class Solution {
	public int maxProfit(int[] A) {
		int N = A.length;
		if(N == 0) return 0;
		
		int[] plus = new int[N];
		int[] minus = new int[N];

		minus[0] = -A[0];

		for(int i=1; i<N; i++) {
			plus[i] = Math.max(plus[i-1], minus[i-1] + A[i]);
			minus[i] = Math.max(minus[i-1], i>=2 ? plus[i-2] - A[i] : -A[i]);
		}

		return plus[N-1];
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();

		int N = scanner.nextInt();		
		int[] A = new int[N];

		for(int i=0; i<N; i++) {			
			A[i] = scanner.nextInt();							
		}		

		System.out.println(solution.maxProfit(A));
	}
}