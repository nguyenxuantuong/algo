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

class TreeTraversal {
	Stack<TreeNode> stack = new Stack<TreeNode>();

	TreeTraversal(TreeNode node){
		TreeNode cur = node;
		while(cur != null){
			stack.push(cur);
			cur = cur.left;
		}			
	}

	TreeNode peek() {
		return stack.isEmpty() ? null : stack.peek();
	}

	TreeNode pop() {
		if(stack.isEmpty()) return null;

		TreeNode top = stack.pop();			

		if(top.right != null) {
			TreeNode cur = top.right;
			while(cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
		}

		return top;
	}

	boolean isEmpty(){
		return stack.isEmpty();
	}
}

//inorder traversal but from right -> left (decreasing) order
class TreeTraversalReverse {
	Stack<TreeNode> stack = new Stack<TreeNode>();

	TreeTraversalReverse(TreeNode node) {		
		TreeNode cur = node;

		while(cur != null) {
			stack.push(cur);
			cur = cur.right;
		}
	}

	boolean isEmpty(){
		return stack.isEmpty();
	}

	TreeNode peek() {
		return stack.isEmpty() ? null : stack.peek();
	}

	TreeNode pop() {
		TreeNode top = stack.pop();

		if(top.left != null) {
			TreeNode cur = top.left;
			while(cur != null) {
				stack.push(cur);
				cur = cur.right;
			}
		}

		return top;
	}
}

public class Solution {	
	//build tree from file scanner
	TreeNode readNext(Scanner scanner) {
		if(!scanner.hasNext()) return null;

		String tok = scanner.next();		

		if(!tok.equals("null")) {
			return new TreeNode(Integer.parseInt(tok));
		}

		return null;
	}

	TreeNode buildTree(Scanner scanner){
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode root = readNext(scanner);

		if(root == null) return null;

		queue.add(root);
		while(!queue.isEmpty()){		
			TreeNode top = queue.poll();

			TreeNode left = readNext(scanner);
			TreeNode right = readNext(scanner);

			top.left = left; top.right = right;
			if(left != null) queue.add(left);
			if(right != null) queue.add(right);
		}		

		return root;
	}

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

	//find common nodes from both trees
	public List<Integer> findCommonNodes(TreeNode node1, TreeNode node2){
		Stack<TreeNode> stack1 = new Stack<TreeNode>();

		List<Integer> outL = new ArrayList<Integer>();
		if(node1 == null || node2 == null) return outL;

		TreeTraversal treeTraversal1 = new TreeTraversal(node1);
		TreeTraversal treeTraversal2 = new TreeTraversal(node2);

		while(!treeTraversal1.isEmpty() && !treeTraversal2.isEmpty()){
			TreeNode tmp1 = treeTraversal1.peek();
			TreeNode tmp2 = treeTraversal2.peek();			

			if(tmp1.val > tmp2.val){
				treeTraversal2.pop();
			} else if(tmp1.val < tmp2.val){
				treeTraversal1.pop();
			} else {
				outL.add(tmp1.val);
				treeTraversal1.pop();
				treeTraversal2.pop();
			}
		}

		return outL;
	}

	public List<Integer> findCommonNodes2(TreeNode node1, TreeNode node2){
		Stack<TreeNode> stack1 = new Stack<TreeNode>();

		List<Integer> outL = new ArrayList<Integer>();
		if(node1 == null || node2 == null) return outL;

		TreeTraversalReverse treeTraversal1 = new TreeTraversalReverse(node1);
		TreeTraversalReverse treeTraversal2 = new TreeTraversalReverse(node2);

		while(!treeTraversal1.isEmpty() && !treeTraversal2.isEmpty()){
			TreeNode tmp1 = treeTraversal1.peek();
			TreeNode tmp2 = treeTraversal2.peek();			

			if(tmp1.val > tmp2.val){
				treeTraversal1.pop();
			} else if(tmp1.val < tmp2.val){
				treeTraversal2.pop();
			} else {
				outL.add(tmp1.val);
				treeTraversal1.pop();
				treeTraversal2.pop();
			}
		}

		return outL;
	}

	public void printList(List<Integer> outL) {
		for(int i=0; i<outL.size(); i++) {
			if(i < outL.size()-1){
				System.out.print(outL.get(i) + " ");
			} else {
				System.out.print(outL.get(i));
			}
		}

		System.out.println();
	}

	public static void main(String[] args){
		Solution solution = new Solution();	

		// try {
		// 	Scanner scanner1 = new Scanner(new File("tree1.txt"));
		// 	Scanner scanner2 = new Scanner(new File("tree2.txt"));

		// 	TreeNode root1 = solution.buildTree(scanner1);
		// 	TreeNode root2 = solution.buildTree(scanner2);

		// 	List<Integer> list = solution.findCommonNodes(root1, root2);
		// 	solution.printList(list);
		// } catch (Exception ex) {
		// 	ex.printStackTrace();
		// }				

		Scanner scanner = new Scanner(System.in);
		String line1 = scanner.nextLine();
		String line2 = scanner.nextLine();

		TreeNode root1 = solution.buildTree(line1);
		TreeNode root2 = solution.buildTree(line2);

		List<Integer> list = solution.findCommonNodes(root1, root2);
		solution.printList(list);	

		List<Integer> list1 = solution.findCommonNodes2(root1, root2);
		solution.printList(list1);	
	}
}