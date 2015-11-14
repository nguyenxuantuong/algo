//https://leetcode.com/problems/longest-increasing-subsequence/
import java.util.*;

public class Solution {
	public int binarySearch(int[] C, int key) {
		if(key <= C[1])	return 0;
		
		int s = 1, e = C.length-1;

		int out = 0;

		while(s <= e) {
			int mid = (s + e)/2;
			if(C[mid] >= key) {
				e = mid - 1;
			} else {
				out = mid;
				s = mid + 1;
			}
		}	

		return out;
	}

	//O(nlog(n)) version
	public int lengthOfLIS(int[] A){
		int N = A.length;
		if(N == 0) return 0;

		int[] C = new int[N+1];
		Arrays.fill(C, Integer.MAX_VALUE);

		int out = 1;
		C[1] = A[0]; //length = 1; min of last element = A[0]

		for(int i=1; i<N; i++) {
			int idx = binarySearch(C, A[i]);
			out = Math.max(out, idx + 1);
			C[idx+1] = Math.min(C[idx+1], A[i]);
		}

		return out;
	}

	//O(n^2) version
	public int lengthOfLISSlow(int[] A){
		int N = A.length;
		if(N == 0) return 0;

		int[] L = new int[N];

		for(int i=0; i<N; i++) {
			int maxL = 1;

			for(int j=0; j<i; j++) {				
				if(A[j] < A[i]) {
					maxL = Math.max(maxL, 1 + L[j]);
				}				
			}

			L[i] = maxL;
		}

		int out = 1;
		for(int i=0; i<N; i++){
			out = Math.max(out, L[i]);
		}

		return out;
	}

    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();		

		int[] A = new int[N];		

		for(int i=0; i<N; i++) {
			A[i] = scanner.nextInt();
		}
		
		Solution solution = new Solution();
		System.out.println(solution.lengthOfLIS(A));		
    }
}