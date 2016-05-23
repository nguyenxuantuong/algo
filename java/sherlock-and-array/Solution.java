//https://www.hackerrank.com/challenges/sherlock-and-array
import java.util.*;
import java.io.*;

public class Solution {
	public String hasEquilibrium(int[] A) {
		int N = A.length;

		int sum = 0;
		for(int i=0; i<N; i++) {
			sum += A[i];
		}

		int total = 0;
		for(int i=0; i<N; i++) {			
			if(total == sum - A[i] - total) {
				return "YES";
			}
			total += A[i];
		}

		return "NO";
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		Solution solution = new Solution();

		for(int i=0; i<T; i++) {
			int N = scanner.nextInt();
			int[] A = new int[N];

			for(int j=0; j<N; j++) {
				A[j] = scanner.nextInt();
			}

			System.out.println(solution.hasEquilibrium(A));
		}

		scanner.close();
	}
}