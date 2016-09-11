//https://www.hackerrank.com/challenges/no-prefix-set
import java.util.*;

class TrieNode {
	//'j' - 'a' = 9; but we need to +1 to get total number of characters from a->j
	private static final int SIZE = 10; 

	TrieNode[] childs;
	public boolean isWord;

	TrieNode() {
		childs = new TrieNode[SIZE];
		isWord = false;
	}	
}

public class Solution {
	static String addName(TrieNode root, char[] name) {
		TrieNode curNode = root;

		for(int i=0; i<name.length; i++) {
			char c = name[i];
			int idx = c - 'a';

			if(curNode.childs[idx] == null) {
				curNode.childs[idx] = new TrieNode();				
			} else if(i==name.length-1){
				return new String(name);
			}

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