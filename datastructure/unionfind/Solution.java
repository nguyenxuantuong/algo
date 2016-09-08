import java.util.*;

public class Solution {
	static int[] parent;

	public void UNION(int x, int y) {
		parent[FIND(y)] = parent[FIND(x)];
	}

	public int FIND(int x) {
		if(x != parent[x]) {			
			parent[x] = FIND(parent[x]);			
		}

		return parent[x];
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		Solution solution = new Solution();

		int N = scanner.nextInt();
		parent = new int[N];

		for(int i=0; i<N; i++) {
			parent[i] = i;
		}

		int M = scanner.nextInt();

		for(int i=0; i<M; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();			
			solution.UNION(x, y);			
		}

		Set<Integer> set = new HashSet<Integer>();
		for(int i=0; i<N; i++) {
			//IMPORTANT: need to use solution.FIND here because parent[i] might not refer to root yet
			set.add(solution.FIND(parent[i]));
		}

		System.out.println(set.size());

		scanner.close();
	}
}