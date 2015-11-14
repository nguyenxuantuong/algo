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

class InOrderTraversal {
	Stack<TreeNode> stack = new Stack<TreeNode>();

	InOrderTraversal(TreeNode root){
		TreeNode cur = root;

		while(cur!=null){
			stack.push(cur);
			cur = cur.left;
		}
	}

	public boolean isEmpty(){
		return stack.isEmpty();
	}

	public TreeNode pop(){
		if(stack.isEmpty()) return null;
		TreeNode top = stack.pop();

		if(top.right!=null){
			TreeNode cur = top.right;
			while(cur!=null){
				stack.push(cur);
				cur = cur.left;
			}
		}

		return top;
	}

	public TreeNode peek() {
		return stack.peek();
	}
}

class InOrderTraversalReverse{
	Stack<TreeNode> stack = new Stack<TreeNode>();

	InOrderTraversalReverse(TreeNode root){
		TreeNode cur = root;

		while(cur!=null){
			stack.push(cur);
			cur = cur.right;
		}
	}

	public boolean isEmpty(){
		return stack.isEmpty();
	}

	public TreeNode pop(){
		if(stack.isEmpty()) return null;
		TreeNode top = stack.pop();

		if(top.left != null){
			TreeNode cur = top.left;
			while(cur!=null){
				stack.push(cur);
				cur = cur.right;
			}
		}

		return top;
	}

	public TreeNode peek() {
		return stack.peek();
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
	
	public boolean findPairWithGivenSum(TreeNode node, int sum){
		InOrderTraversal traversal1 = new InOrderTraversal(node);
		InOrderTraversalReverse traversal2 = new InOrderTraversalReverse(node);
				
		while(!traversal1.isEmpty() && !traversal2.isEmpty() && traversal1.peek().val < traversal2.peek().val){
			int tmp1 = traversal1.peek().val;
			int tmp2 = traversal2.peek().val;

			if(tmp1 + tmp2 > sum) {
				traversal2.pop();
			} else if(tmp1 + tmp2 < sum) {
				traversal1.pop();
			} else {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args){
		Solution solution = new Solution();			

		Scanner scanner = new Scanner(System.in);
		
		int sum = scanner.nextInt();		
		scanner.nextLine(); //read the rest

		TreeNode root = solution.buildTree(scanner.nextLine());
		System.out.println(solution.findPairWithGivenSum(root, sum));
	}
}