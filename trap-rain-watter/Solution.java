import java.util.*;

public class Solution {
    public int trap(int[] A) {
		int N = A.length;

		int[] LH = new int[N];
		int[] RH = new int[N];

		for(int i=0; i<N; i++) {
			LH[i] = i == 0 ? 0 : (A[LH[i-1]] > A[i] ? LH[i-1] : i);			
		} 

		for(int i=N-1; i>=0; i--) {
			LR[i] = i == N-1 ? (N-1) : (A[LR[i+1]] > A[i] ? LR[i+1] : i);
		}

		int idx = 0;

		int total = 0;

		while(idx < N) {
			int lIdx = LH[idx];
			int rIdx = LR[idx];

			idx = rIdx + 1;
		}

		return total;
    }
}