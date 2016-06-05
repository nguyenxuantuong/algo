// https://leetcode.com/problems/coin-change/

import java.util.*;

public class Solution {
    public int coinChange(int[] C, int amount) {
        int N = C.length;

        int[][] A = new int[N][amount+1];

        for(int i=0; i<N; i++) {
        	Arrays.fill(A[i], Integer.MAX_VALUE);
        }
        
        for(int i=0; i<N; i++) {
        	A[i][0] = 0;
        }

        for(int j=1; j<=amount; j++) {
        	A[0][j] = j % C[0] == 0 ? (j / C[0]) : Integer.MAX_VALUE;
        }
        
        
        for(int i=1; i<N; i++) {
        	for(int j=1; j<=amount; j++) {
        		if(j >= C[i]){
        			A[i][j] = Math.min(A[i][j-C[i]] == Integer.MAX_VALUE ? Integer.MAX_VALUE : A[i][j-C[i]] + 1, A[i-1][j]);
        		} else {
        			A[i][j] = A[i-1][j];
        		}        		
        	}
        }

        return A[N-1][amount] == Integer.MAX_VALUE ? -1 : A[N-1][amount];
    }

    public static void main(String[] args){
    	Scanner scanner = new Scanner(System.in);

    	int amount = scanner.nextInt();
		int M = scanner.nextInt();

		int[] C = new int[M];
		for(int i=0; i<M; i++){
			C[i] = scanner.nextInt();
		}

		Solution solution = new Solution();
		System.out.println(solution.coinChange(C, amount));
    }
}