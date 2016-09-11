//https://www.hackerrank.com/challenges/contacts
import java.util.*;

class TrieNode {	
	public TrieNode[] childs;	
	public int countPrefix;

	TrieNode() {		
		childs = new TrieNode[26];
		countPrefix = 0;			
	}	
}

public class Solution {	
	static void addName(TrieNode root, char[] name) {		
		TrieNode curNode = root;

		for(int i=0; i<name.length; i++) {
			curNode.countPrefix++;
			char c = name[i];
			int idx = c - 'a';
			if(curNode.childs[idx] == null) curNode.childs[idx] = new TrieNode();
			curNode = curNode.childs[idx];
		}	
		curNode.countPrefix++;	
	}

	static int findPartial(TrieNode root, char[] name) {		
		TrieNode curNode = root;

		for(int i=0; i<name.length; i++) {			
			char c = name[i];
			int idx = c - 'a';
			if(curNode.childs[idx] == null) return 0;
			curNode = curNode.childs[idx];
		}		

		return curNode.countPrefix;		
	}	

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		Solution solution = new Solution();

		int N = scanner.nextInt();
		scanner.nextLine();

		TrieNode root = new TrieNode();

		for(int i=0; i<N; i++) {
			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			if(tokens.length == 2) {
				if(tokens[0].equals("add")) {					
					addName(root, tokens[1].toCharArray());
				} else if(tokens[0].equals("find"))  {					
					System.out.println(findPartial(root, tokens[1].toCharArray()));
				}
			}
		}

		scanner.close();		
	}
}