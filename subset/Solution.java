import java.util.*;

public class Solution {
	public List<List<Integer>> subsets(int[] A){
		int N = A.length;
		Arrays.sort(A);

		boolean[] marked = new boolean[N];
		Arrays.fill(marked, false);

		List<List<Integer>> list = new ArrayList<List<Integer>>();

		while(true) {
			List<Integer> tmpL = new ArrayList<Integer>();
			for(int i=0; i<N; i++) {
				if(marked[i]) {
					tmpL.add(A[i]);
				}
			}
			list.add(tmpL);

			//increase to 1
			int idx = N-1;
			
			while(idx >= 0 && marked[idx]) {
				marked[idx] = false;
				--idx;
			}

			if(idx == -1) break;
			else marked[idx] = true;
		}	 

		return list;
	}	
}