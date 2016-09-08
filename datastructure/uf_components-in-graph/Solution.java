//https://www.hackerrank.com/challenges/components-in-graph
import java.util.*;

public class Solution {
	static int[] parent;
	static int[] count;

	static public int FIND(int x) {
		if(x != parent[x]) {
			parent[x] = FIND(parent[x]);
		}

		return parent[x];
	}

	static public void UNION(int x, int y) {
		int rootX = FIND(x);
		int rootY = FIND(y);

		//two disconntected component
		if(rootX != rootY) {
			parent[rootX] = rootY;
			count[rootY] = count[rootX] + count[rootY];
		}		
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		Solution solution = new Solution();

		int N = scanner.nextInt();
		
		parent = new int[2*N];
		count  = new int[2*N];

		for(int i=0; i<2*N; i++) {
			parent[i] = i;
			count[i] = 1;
		}

		for(int i=0; i<N; i++)  {
			//0 index
			int x = scanner.nextInt() - 1;
			int y = scanner.nextInt() - 1;			
			solution.UNION(x, y);			
		}		


		int maxC = Integer.MIN_VALUE, minC = Integer.MAX_VALUE;

		for(int i=0; i<N; i++) {
			int cnt = count[FIND(i)];			
			if(cnt > 1) {
				maxC = Math.max(maxC, cnt);
				minC = Math.min(minC, cnt);	
			}
			
		}

		System.out.println(minC + " " + maxC);

		scanner.close();
	}
}