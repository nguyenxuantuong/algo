//https://www.hackerrank.com/challenges/merging-communities
import java.util.*;

public class Solution {
	static int[] parent;
	static int[] count;
	static int[] rank;

	static void UNION(int x, int y) {		
		int rootX = FIND(x);
		int rootY = FIND(y);

		int rankX = rank[rootX], rankY = rank[rootY];
		if(rankX > rankY) {
			int tmp = rootX;
			rootX = rootY;
			rootY = tmp;
		}

		//merge two sets
		if(rootX != rootY) {
			parent[rootX] = rootY;
			count[rootY] = count[rootY] + count[rootX];

			if(rankX == rankY) rank[rootY]++;
		}		
	}

	static int FIND(int x) {
		if(x != parent[x]) {
			parent[x] = FIND(parent[x]);
		}

		return parent[x];
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		Solution solution = new Solution();

		int N = scanner.nextInt();
		int Q = scanner.nextInt();
		scanner.nextLine();

		parent = new int[N];
		count = new int[N];
		rank = new int[N];
		
		for(int i=0; i<N; i++) {
			parent[i] = i;
			count[i] = 1;
			rank[i] = 1;
		}

		for(int i=0; i<Q; i++) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if(tokens[0].equals("M") && tokens.length == 3) {
				int x = Integer.parseInt(tokens[1]) - 1;
				int y = Integer.parseInt(tokens[2]) - 1;

				UNION(x, y);
			} else if(tokens[0].equals("Q") && tokens.length == 2) {
				int p = Integer.parseInt(tokens[1]) - 1;
				System.out.println(count[FIND(p)]);
			}
		}

		scanner.close();
	}
}