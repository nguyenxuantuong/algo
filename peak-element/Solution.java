import java.util.*;

public class Solution {
	public int findPeakELement(int[] A) {
		int N = A.length;

		boolean[] peakL = new boolean[N];
		boolean[] peakR = new boolean[N];

		Arrays.fill(peakL, false);
		Arrays.fill(peakR, false);

		for(int i=0; i<N; i++) {
			peakL[i] = i==0 ? true : A[i] > A[i-1];
			peakR[i] = i==N-1 ? true : A[i] > A[i+1];
		}

		for(int i=0; i<N; i++) {
			if(peakL[i] && peakR[i]) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int[] A = new int[N];
		
		for(int i=0; i<N; i++) {
			A[i] = scanner.nextInt();
		}

		Solution solution = new Solution();
		System.out.println(solution.findPeakELement(A));
	}
}