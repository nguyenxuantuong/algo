import java.util.*;
import java.io.*;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val){
		this.val = val;
	}
}

public class Solution {	
	//build tree from input line -- tokenizer
	TreeNode readNext(StringTokenizer tokenizer) {
		if(!tokenizer.hasMoreTokens()) return null;
		String tok = tokenizer.nextToken();		

		if(!tok.equals("null")) {
			return new TreeNode(Integer.parseInt(tok));
		}

		return null;
	}

	TreeNode buildTree(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line, " ");

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode root = readNext(tokenizer);

		if(root == null) return null;

		queue.add(root);
		while(!queue.isEmpty()){		
			TreeNode top = queue.poll();

			TreeNode left = readNext(tokenizer);
			TreeNode right = readNext(tokenizer);

			top.left = left; top.right = right;
			if(left != null) queue.add(left);
			if(right != null) queue.add(right);
		}		

		return root;
	}

	//this is still O(n) complexity due to that we still need to go to both branches
	public TreeNode findCommonAncestor(TreeNode node, int key1, int key2){
		if(node == null) return null;
		if(node.val == key1 || node.val == key2) return node;

		TreeNode n1 = node.left != null ? findCommonAncestor(node.left, key1, key2) : null;
		TreeNode n2 = node.right != null ? findCommonAncestor(node.right, key1, key2) : null;

		return (n1 != null && n2 != null) ? node : (n1 != null ? n1 : n2);
	}

	//optimize version; key1 < key2; this is a bit hard to comprehense
	public TreeNode findCommonAncestor2(TreeNode node, Integer key1, Integer key2) {
		if(node == null) return null;

		if((key1 != null && node.val == key1) || (key2 != null && node.val == key2)) return node;
		if(key1 == null && key2 == null) return null;

		TreeNode n1 = null, n2 = null;
	
		if(key1 != null && node.val < key1) {
			return findCommonAncestor2(node.right, key1, key2);
		} else if(key2 != null && node.val > key2) {
			return findCommonAncestor2(node.left, key1, key2);
		} else {
			n1 = node.left != null ? findCommonAncestor2(node.left, key1, null) : null;
			n2 = node.right != null ? findCommonAncestor2(node.right, null, key2): null;

			return (n1 != null && n2 != null) ? node : (n1 != null ? n1 : n2);
		}
	}

	//this is easiest solution to comprehense
	public TreeNode findCommonAncestor3(TreeNode node, int key1, int key2){
		if(node == null) return null;

		if(node.val < key1){
			return findCommonAncestor3(node.right, key1, key2);
		} else if(node.val > key2) {
			return findCommonAncestor3(node.left, key1, key2);
		}

		return node;
	}

	//iterative solution
	public TreeNode findCommonAncestor4(TreeNode node, int key1, int key2){
		if(node == null) return null;

		TreeNode cur = node;
		while(cur != null){
			if(cur.val < key1) {
				cur = cur.right;
			} else if(cur.val > key2){
				cur = cur.left;
			} else {
				return cur;
			}
		}

		return cur;
	}

	public TreeNode searchBST(TreeNode node, int key) {
		if(node == null) return null;

		if(node.val > key) return node.left != null ? searchBST(node.left, key) : null;
		if(node.val < key) return node.right != null ? searchBST(node.right, key): null;

		return node;
	}

	public static void main(String[] args){
		Solution solution = new Solution();			

		Scanner scanner = new Scanner(System.in);
		
		int key1 = scanner.nextInt();
		int key2 = scanner.nextInt();

		int minKey = Math.min(key1, key2);
		int maxKey = Math.max(key1, key2);

		scanner.nextLine(); //read the rest
		TreeNode root = solution.buildTree(scanner.nextLine());
		
		System.out.println(solution.findCommonAncestor(root, minKey, maxKey).val);
		System.out.println(solution.findCommonAncestor2(root, minKey, maxKey).val);
		System.out.println(solution.findCommonAncestor3(root, minKey, maxKey).val);
		System.out.println(solution.findCommonAncestor4(root, minKey, maxKey).val);
	}
}