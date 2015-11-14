import java.util.*;

public class Solution {
	public static final int M = 1005;
	static int[] cost = new int[M];
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		Solution solution = new Solution();

		cost[0] = 0;
		for(int i=1; i<M; i++) {
			int n1 = i>=5 ? 1 + cost[i-5] : Integer.MAX_VALUE;
			int n2 = i>=2 ? 1 + cost[i-2] : Integer.MAX_VALUE;
			int n3 = i>=1 ? 1 + cost[i-1] : Integer.MAX_VALUE;

			cost[i] = Math.min(n1, Math.min(n2, n3));
		}

		for(int i=0; i<T; i++) {
			int N = scanner.nextInt();

			int[] A = new int[N];
			int minElem = Integer.MAX_VALUE;

			for(int j=0; j<N; j++){				
				A[j] = scanner.nextInt();				
				minElem = Math.min(minElem, A[j]);
			}

			int minOps = Integer.MAX_VALUE;

			for(int t=minElem-5; t<=minElem; t++) {
				int nOps = 0;

				for(int j=0; j<N; j++) {
					nOps += cost[A[j]-t];									
				}			

				minOps = Math.min(minOps, nOps);					
			}				
			
			System.out.println(minOps);
		}		
	}
}