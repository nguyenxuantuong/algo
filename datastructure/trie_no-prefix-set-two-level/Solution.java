//https://www.hackerrank.com/challenges/no-prefix-set
import java.util.*;

class TrieNode {
	private static final int SIZE = 10;

	TrieNode[] childs;
	public boolean isWord;

	TrieNode() {
		childs = new TrieNode[SIZE + SIZE*SIZE];
		isWord = false;
	}	
}

public class Solution {
	private static final int SIZE = 10;

	static String addName(TrieNode root, char[] name) {
		TrieNode curNode = root;
		int N = name.length;

		for(int i=0; i<(N+1)/2; i++) {
			char c1 = name[2*i];
			int idx =0;

			if(2*i+1 >= N) {
				idx = c1 - 'a';
			} else {
				char c2 = name[2*i+1];
				idx = SIZE + (c1-'a')*SIZE + (c2-'a');
			}			 

			if(idx >= SIZE && curNode.childs[idx/SIZE-1] != null && curNode.childs[idx/SIZE-1].isWord) {
				return new String(name);
			}

			//end of string
			if(2*i+1 >= N-1){
				if(idx < SIZE) {
					for(int j=0; j<SIZE; j++) {
						if(curNode.childs[SIZE*(idx+1)+j] != null) {
							return new String(name);			
						}
					}
				} 

				if(curNode.childs[idx] != null) return new String(name);
			}

			if(curNode.childs[idx] == null) {
				curNode.childs[idx] = new TrieNode();				
			}  

			//we passed through one word node
			curNode = curNode.childs[idx];
			if(curNode.isWord) {
				return new String(name);
			}
		}

		curNode.isWord = true;
		return null;
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		Solution solution = new Solution();

		int N = scanner.nextInt();
		scanner.nextLine();

		String badString = null;
		TrieNode root = new TrieNode();

		for(int i=0; i<N; i++) {
			String line = scanner.nextLine();
			String output = addName(root, line.toCharArray());

			if(output != null) {
				badString = output;				
				break;
			}
		}

		if(badString != null) {
			System.out.println("BAD SET");
			System.out.println(badString);
		} else {
			System.out.println("GOOD SET");
		}

		scanner.close();
	}
}