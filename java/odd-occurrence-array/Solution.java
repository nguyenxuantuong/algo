//https://codility.com/c/run/training3EWFCK-CYW

import java.util.*;

public class Solution {
	static final int MAX_BIT = 64;

	public int solutionBitSet(int[] A) {
		int[] bitSum = new int[MAX_BIT];

		int N = A.length;

		for(int i=0; i<N; i++) {
			BitSet bs = BitSet.valueOf(new long[]{A[i]});

			for(int j=0; j<MAX_BIT; j++){
				bitSum[j] += bs.get(j) == true ? 1 : 0;
			}
		}	        	

		BitSet out = new BitSet();
		for(int i=0; i<MAX_BIT; i++) {
			out.set(i, bitSum[i] % 2 == 1 ? true : false);
		}

		return (int)out.toLongArray()[0];
    }

    public int solution(int[] A) {
    	int N = A.length;

    	int out = 0;

    	for(int i=0; i<32; i++) {
    		int sum = 0;
    		int tmp = 1 << i;

    		for(int j=0; j<N; j++) {
    			sum += (tmp & A[j]) != 0 ? 1 : 0;
    		}

    		if(sum % 2 != 0) {
    			out = out | tmp;
    		}
    	}

    	return out;
    }

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		Solution solution = new Solution();		

		for(int x = 0; x < T; x++) {
			int N = scanner.nextInt();

			int[] A = new int[N];
			for(int i=0; i<N; i++) {
				A[i] = scanner.nextInt();
			}
			
			//System.out.println(solution.solutionCustomBitSet(A));				
			System.out.println(solution.solution(A));
		}		
	}
}