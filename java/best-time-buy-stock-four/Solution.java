import java.util.*;

public class Solution {
    public int maxProfit(int k, int[] P) {
		int N = P.length;
		if(N == 0 || k == 0) return 0;

		if(k >= N/2) {
			int out = 0;
			for(int i=0; i<N-1; i++) {
				out += Math.max(0, P[i+1] - P[i]);
			}

			return out;
		}

		int[][] minus = new int[N][k+1];
		int[][] plus = new int[N][k+1];

		for(int j=1; j<=k; j++) {
			minus[0][j] = -P[0];						
		}
		
		for(int i=1; i<N; i++) {
			for(int j=1; j<=k; j++) {
				plus[i][j] = Math.max(plus[i-1][j], minus[i-1][j] + P[i]);
				minus[i][j] = Math.max(minus[i-1][j], plus[i-1][j-1] - P[i]);				
			}
		}

		return plus[N-1][k];
    }

    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();

		int N = scanner.nextInt();		
		int k = scanner.nextInt();

		int[] A = new int[N];

		for(int i=0; i<N; i++) {			
			A[i] = scanner.nextInt();							
		}		

		System.out.println(solution.maxProfit(k, A));
    }
}