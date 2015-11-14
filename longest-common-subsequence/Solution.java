import java.util.*;

public class Solution {
	public int longestSequence(String s, String o){
		char[] sc = s.toCharArray();
		char[] oc = o.toCharArray();

		int N = sc.length;
		int M = oc.length;

		if(N == 0 || M == 0) return 0;

		int[][] L = new int[N][M];
		for(int i=0; i<N; i++){
			Arrays.fill(L[i], 0);
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++){
				int n1 = sc[i] == oc[j] ? ((i>=1 && j>= 1) ? 1 + L[i-1][j-1] : 1) : 0;
				int n2 = i >= 1 ? L[i-1][j] : 0;
				int n3 = j >= 1 ? L[i][j-1] : 0;

				L[i][j] = Math.max(n1, Math.max(n2, n3));
			}
		}

		return L[N-1][M-1];
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		Solution solution = new Solution();		

		String s = scanner.nextLine();
		String o = scanner.nextLine();

		System.out.println(solution.longestSequence(s, o));
	}
}