//http://geeksquiz.com/given-sorted-array-number-x-find-pair-array-whose-sum-closest-x/
import java.util.*;

class Solution {
	//find pair whose sum is closest to K
	public int[] findClosestPair(int[] A, int K){		
		int[] out = new int[2];

		int N = A.length;

		if(N <= 1) return out;

		int s = 0, e = N-1;

		int closestSum = Integer.MAX_VALUE;

		int found1 = 0, found2 = 0;

		//beware of overflow for A[s] + A[e]...
		while(s < e) {
			if(A[s] + A[e] == K) {
				out[0] = A[s];
				out[1] = A[e];
				return out;
			} 
			else if(A[s] + A[e] > K) {
				if(closestSum > Math.abs(A[s] + A[e] - K)){
					closestSum = Math.abs(A[s] + A[e] - K);
					found1 = A[s];
					found2 = A[e];
				}
				e--;
			} else {
				if(closestSum > Math.abs(A[s] + A[e] - K)){
					closestSum = Math.abs(A[s] + A[e] - K);
					found1 = A[s];
					found2 = A[e];
				}
				s++;
			}

		}

		out[0] = found1;
		out[1] = found2;

		return out;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		for(int x=0; x<T; x++) {
			int N = scanner.nextInt();
			int K = scanner.nextInt();

			int[] A = new int[N];
			for(int i=0; i<N; i++) {
				A[i] = scanner.nextInt();
			}

			Solution solution = new Solution();
			System.out.println(Arrays.toString(solution.findClosestPair(A, K)));
		}
	}
}