//https://www.hackerrank.com/challenges/maximise-sum
import java.util.*;
import java.io.*;


public class Solution {
	public long solution(long[] A, long M) {
		int N = A.length;		

		long mod = A[0] % M;
		long maxMOD = mod;

		TreeSet<Long> treeSet = new TreeSet<Long>();
		treeSet.add(mod);

		for(int i=1; i<N; i++) {
			mod = (A[i] + mod) % M;
			maxMOD = Math.max(maxMOD, mod);

			//min value in the tree			
			Long leastHigher = treeSet.higher(mod);
			if(leastHigher != null) {
				maxMOD = Math.max(maxMOD, (mod + M - leastHigher) % M);
			}			

			treeSet.add(mod);
		}		

		return maxMOD;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		Solution solution = new Solution();

		for(int i=0; i<T; i++) {
			int N = scanner.nextInt();
			long M = scanner.nextLong();
			long[] A = new long[N];

			for(int j=0; j<N; j++) {
				A[j] = scanner.nextLong();
			}

			System.out.println(solution.solution(A, M));
		}

		scanner.close();
	}
}