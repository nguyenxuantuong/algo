//different way to traversal tree (recursive and non recursive function)

import java.util.*;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val){
		this.val = val;
	}
}

public class Solution {	
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

	public void inOrderTravesalRecursive(TreeNode node, List<Integer> outL) {
		if(node == null) return;

		if(node.left != null) inOrderTravesalRecursive(node.left, outL);
		outL.add(node.val);
		if(node.right != null) inOrderTravesalRecursive(node.right, outL);
	}

	//recursive function to find preOrder List
	public List<Integer> getInOrderList(TreeNode root) {
		List<Integer> outL = new ArrayList<Integer>();
		inOrderTravesalRecursive(root, outL);
		return outL;
	}

	public void preOrderTravesalRecursive(TreeNode node, List<Integer> outL) {
		if(node == null) return;

		outL.add(node.val);
		if(node.left != null) preOrderTravesalRecursive(node.left, outL);		
		if(node.right != null) preOrderTravesalRecursive(node.right, outL);
	}

	//recursive function to find preOrder List
	public List<Integer> getPreOrderList(TreeNode root) {
		List<Integer> outL = new ArrayList<Integer>();
		preOrderTravesalRecursive(root, outL);
		return outL;
	}

	public void postOrderTravesalRecursive(TreeNode node, List<Integer> outL) {
		if(node == null) return;
		
		if(node.left != null) postOrderTravesalRecursive(node.left, outL);		
		if(node.right != null) postOrderTravesalRecursive(node.right, outL);

		outL.add(node.val);
	}

	//recursive function to find preOrder List
	public List<Integer> getPostOrderList(TreeNode root) {
		List<Integer> outL = new ArrayList<Integer>();
		postOrderTravesalRecursive(root, outL);
		return outL;
	}

	//in-order travesal using stack
	public List<Integer> inOrderUsingStack(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = node;

		List<Integer> outL = new ArrayList<Integer>();
		if(node == null) return outL;

		while(cur != null) {
			stack.push(cur);
			cur = cur.left;
		}

		while(!stack.isEmpty()){
			TreeNode top = stack.pop();
			outL.add(top.val);

			if(top.right != null) {
				cur = top.right;

				while(cur != null) {
					stack.push(cur);
					cur = cur.left;
				}
			}
		}

		return outL;
	}

	public List<Integer> preOrderUsingStack(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		List<Integer> outL = new ArrayList<Integer>();

		TreeNode cur = node;

		while(cur != null) {
			outL.add(cur.val);
			stack.push(cur);
			cur = cur.left;
		}

		while(!stack.isEmpty()){
			TreeNode top = stack.pop();
			cur = top.right;

			while(cur != null) {
				outL.add(cur.val);
				stack.push(cur);
				cur = cur.left;
			}
		}

		return outL;
	}

	//this approaches work fine for preOrder cause node 
	//which is visited can be pop immediately from the stack
	public List<Integer> preOrderUsingStack2(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		List<Integer> outL = new ArrayList<Integer>();
		if(node == null) return outL;

		stack.push(node);
		while(!stack.isEmpty()) {
			TreeNode top = stack.pop();
			outL.add(top.val);

			if(top.right != null) stack.push(top.right);
			if(top.left != null) stack.push(top.left);
		}

		return outL;
	}

	public List<Integer> inOrderUsingStackHashMap(TreeNode node) {
		HashSet<TreeNode> hVisited = new HashSet<TreeNode>(); 

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(node);

		List<Integer> outL = new ArrayList<Integer>();
		if(node == null) return outL;

		while(!stack.isEmpty()){
			TreeNode top = stack.peek();

			boolean visitedL = top.left == null || hVisited.contains(top.left);
			boolean visitedR = top.right == null || hVisited.contains(top.right);
			boolean visitedNode = hVisited.contains(top);

			if(!visitedL) {
				stack.push(top.left);
			} else if(!visitedNode) {
				hVisited.add(top);
				outL.add(top.val);
			} else if(!visitedR) {
				stack.push(top.right);
			} else {
				stack.pop();
			}					
		}

		return outL;
	}

	//other way to handle
	public List<Integer> inOrderUsingStackHashMap2(TreeNode node) {
		HashSet<TreeNode> hVisited = new HashSet<TreeNode>(); 

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(node);

		List<Integer> outL = new ArrayList<Integer>();
		
		while(!stack.isEmpty()){
			TreeNode top = stack.peek();

			boolean visitedL = top.left == null || hVisited.contains(top.left);		

			//other way to handle 
			if(!visitedL){
				stack.push(top.left);
			} else {
				outL.add(top.val); //visit current node
				hVisited.add(top); //marked this node as visisted
				stack.pop(); //remove current node -- we don't need it anymore

				//visit top right if any
				if(top.right != null) {	
					stack.push(top.right);
				}
			}	 		 
		}

		return outL;
	}

	public List<Integer> postOrderUsingStack(TreeNode node) {
		HashSet<TreeNode> hVisited = new HashSet<TreeNode>();

		List<Integer> outL = new ArrayList<Integer>();

		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(node == null) return outL;

		stack.push(node);
		while(!stack.isEmpty()){
			TreeNode top = stack.peek();

			boolean isLeaf = top.left == null && top.right == null;

			if(isLeaf || hVisited.contains(top)){
				outL.add(top.val);
				stack.pop();
				continue;
			}

			hVisited.add(top);

			if(top.right != null) stack.push(top.right);
			if(top.left != null) stack.push(top.left);
		}

		return outL;
	}

	//this version don't use any extra memory in term of visisted table
	public List<Integer> postOrderUsingStackNonVistedFlag(TreeNode node){ 
		List<Integer> outL = new ArrayList<Integer>();
		if(node == null) return outL;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(node);

		TreeNode lastVisited = null;

		while(!stack.isEmpty()){
			TreeNode top = stack.peek();
			boolean isLeaf = top.left == null && top.right == null;
			boolean finishedNode = top.right != null ? lastVisited == top.right : (top.left != null ? lastVisited == top.left : true);			

			if(isLeaf || finishedNode) {
				outL.add(top.val);
				stack.pop();
				lastVisited = top;
				continue;
			}			

			if(top.right != null) stack.add(top.right);
			if(top.left != null) stack.add(top.left);
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
		Scanner scanner = new Scanner(System.in);		
		TreeNode root = solution.buildTree(scanner);
		
		// solution.printList(solution.getInOrderList(root));
		// solution.printList(solution.inOrderUsingStack(root));
		// solution.printList(solution.inOrderUsingStackHashMap(root));
		// solution.printList(solution.inOrderUsingStackHashMap2(root));

		// solution.printList(solution.getPreOrderList(root));
		// solution.printList(solution.preOrderUsingStack(root));
		// solution.printList(solution.preOrderUsingStack2(root));		

		solution.printList(solution.getPostOrderList(root));
		solution.printList(solution.postOrderUsingStack(root));
		solution.printList(solution.postOrderUsingStackNonVistedFlag(root));
	}
}