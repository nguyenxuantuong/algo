import java.util.*;

public class Solution {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M = scanner.nextInt();

		int[] C = new int[M];
		for(int i=0; i<M; i++){
			C[i] = scanner.nextInt();
		}

		Arrays.sort(C);

		long[][] A= new long[N+1][M];
		for(int i=0; i<M; i++) {
			A[0][i] = 1;
		}

		for(int i=1; i<=N; i++) {
			A[i][0] = i%C[0] == 0 ? 1 : 0;
		}

		for(int i=1; i<=N; i++) {
			for(int j=1; j<M; j++) {				
				long tmp = 0;

				for(int t=0; t<=i/C[j]; t++) {
					tmp += A[i-t*C[j]][j-1];
				}		

				A[i][j] = tmp;
			}
		}

		System.out.println(A[N][M-1]);
	}
}