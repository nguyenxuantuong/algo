import java.util.*;

public class Solution {
	public int[] findMaxSubArraySequence(int[] A) {
		int N = A.length;

		if(N == 0) return new int[]{0, 0};		

		int sum = 0;
		int maxNeg = Integer.MIN_VALUE;

		int maxE = 0;
		int maxSeq = Integer.MIN_VALUE;

		for(int i=0; i<N; i++) {
			if(A[i] > 0) {
				sum += A[i];
			} else {
				maxNeg = Math.max(maxNeg, A[i]);
			}			

			maxE = i == 0 ? A[i] : Math.max(maxE + A[i], A[i]);
			maxSeq = Math.max(maxSeq, maxE);
		}

		return new int[]{maxSeq, sum > 0 ? sum : maxNeg};
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		Solution solution = new Solution();

		for(int i=0; i<T; i++) {
			int N = scanner.nextInt();

			int[] A = new int[N];
			for(int j=0; j<N; j++) {
				A[j] = scanner.nextInt();
			}

			int[] results = solution.findMaxSubArraySequence(A);
			System.out.println(results[0] + " " + results[1]);
		}
	}
}