import java.util.*;

public class Solution {
	public boolean isSingleElement(int[] A, int idx) {
		int N = A.length;
		if(idx < 0 || idx > N-1) return false;

		if(idx == 0) return A[idx] != A[idx+1];
		if(idx == N-1) return A[idx] != A[idx-1];

		return A[idx] != A[idx-1] && A[idx] != A[idx+1];
	} 

	public int findSingleElement(int[] A) {
		if(A.length == 0) return -1;
		if(A.length == 1) return A[0];

		int N = A.length;		

		int s = 0, e = N-1;

		while(s <= e) {
			int mid = (s + e) / 2;
			int idx = mid;
			
			if(mid % 2 == 1) {
				idx = mid - 1;
			}

			if(idx < N-1) {
				if(A[idx] == A[idx+1]) {
					s = idx + 2;					
				} else {
					if(isSingleElement(A, idx)){
						return A[idx];
					} else if (isSingleElement(A, idx+1)){
						return A[idx+1];
					}

					e = idx-1;
				}
			} else {
				return isSingleElement(A, N-1) ? A[N-1] : -1;
			}		
		}

		return -1;
	}

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
	
		int N = scanner.nextInt();
		
		int[] A = new int[N];
		for(int j=0; j<N; j++) {
			A[j] = scanner.nextInt();
		}		

		System.out.println(solution.findSingleElement(A));		
	}
}