//https://www.hackerrank.com/challenges/coin-on-the-table
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K= scanner.nextInt();
        scanner.nextLine();

        if(N == 0 || M == 0) System.out.println(-1);

        char[][] matrix = new char[N][M];

        int foundI = -1, foundJ = -1;

        for(int i=0; i<N; i++) {
        	char[] tmp = scanner.nextLine().toCharArray();
        	for(int j=0; j<M; j++) {
        		matrix[i][j] = tmp[j];
        		if(matrix[i][j] == '*') {
        			foundI = i;
        			foundJ = j;
        		}
        	}
        }

        int[][][] cost = new int[N][M][K+1];

    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++){
    			cost[i][j][0] = (i==0 && j==0) ? 0 : Integer.MAX_VALUE;
    		}
    	}

        for(int x=1; x<=K; x++){
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<M; j++){
        			int n1 = i >= 1 && cost[i-1][j][x-1] != Integer.MAX_VALUE ? ((matrix[i-1][j] == 'D') ? cost[i-1][j][x-1] : cost[i-1][j][x-1] + 1) : Integer.MAX_VALUE;
        			int n2 = i <= N-2 && cost[i+1][j][x-1] != Integer.MAX_VALUE ? ((matrix[i+1][j] == 'U') ? cost[i+1][j][x-1] : cost[i+1][j][x-1] + 1) : Integer.MAX_VALUE;
        			int n3 = j >= 1 && cost[i][j-1][x-1] != Integer.MAX_VALUE ? ((matrix[i][j-1] == 'R') ? cost[i][j-1][x-1] : cost[i][j-1][x-1] + 1) : Integer.MAX_VALUE;
        			int n4 = j <= M-2 && cost[i][j+1][x-1] != Integer.MAX_VALUE ? ((matrix[i][j+1] == 'L') ? cost[i][j+1][x-1] : cost[i][j+1][x-1] + 1) : Integer.MAX_VALUE;
        			int n5 = cost[i][j][x-1];
        			cost[i][j][x] = Math.min(n1, Math.min(n2, Math.min(n3, Math.min(n4, n5))));
        		}
        	}
        }

        int out = cost[foundI][foundJ][K] == Integer.MAX_VALUE ? -1 : cost[foundI][foundJ][K];

        System.out.println(out);
    }
}