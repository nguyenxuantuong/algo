import java.util.*;

public class Solution {
	public int maxProduct(int[] A, int K) {
		int N = A.length;

		int[][] maxA = new int[N][K+1];
		int[][] minA = new int[N][K+1];

		for(int i=0; i<N; i++) {
			maxA[i][0] = 1;
			minA[i][0] = 1;
		}

		maxA[0][1] = A[0];
		minA[0][1] = A[0];

		for(int i=1; i<N; i++) {
			for(int j=1; j<= Math.min(i+1, K); j++) {
				int n1 = A[i] > 0 ? A[i] * maxA[i-1][j-1] : A[i] * minA[i-1][j-1];
				int n2 = maxA[i-1][j];
				maxA[i][j] = i >= j ? Math.max(n1, n2) : n1;

				n1 = A[i] > 0 ? A[i] * minA[i-1][j-1] : A[i] * maxA[i-1][j-1];
				n2 = minA[i-1][j];
				minA[i][j] = i >= j ? Math.min(n1, n2) : n2;				
			}
		}		

		return maxA[N-1][K];
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		int T = scanner.nextInt();

		for(int j=0; j<T; j++) {
			int N = scanner.nextInt();
			int K = scanner.nextInt();

			int[] A = new int[N];
			for(int i=0; i<N; i++) {
				A[i] = scanner.nextInt();
			}

			Solution solution = new Solution();
			System.out.println(solution.maxProduct(A, K));
		}		

		scanner.close();
	}
}