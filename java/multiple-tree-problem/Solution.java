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

	void inOrder(TreeNode node) {
		if(node == null) return;		
		inOrder(node.left);
		System.out.print(node.val + " ");
		inOrder(node.right);
	}

	//http://www.geeksforgeeks.org/perfect-binary-tree-specific-level-order-traversal/
	public void specificLevelOrderTravesal(){
		Scanner scanner = new Scanner(System.in);		
		
		TreeNode root = buildTree(scanner);
		if(root == null) return;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		List<Integer> visitedNodes = new ArrayList<Integer>();

		while(!queue.isEmpty())	{
			TreeNode top1 = null, top2 = null;
			if(!queue.isEmpty()) top1 = queue.poll();
			if(!queue.isEmpty()) top2 = queue.poll();

			if(top1 != null) visitedNodes.add(top1.val);
			if(top2 != null) visitedNodes.add(top2.val);

			if(top1.left != null) queue.add(top1.left);
			if(top2 != null && top2.right != null) queue.add(top2.right);
			if(top1.right != null) queue.add(top1.right);
			if(top2 != null && top2.left != null) queue.add(top2.left);
		}

		for(int i=0; i<visitedNodes.size(); i++) {
			System.out.print(visitedNodes.get(i) + " ");
		}

		System.out.println();
	}

	//keeping list of node within each level and then travese later
	public void specificLevelOrderTravesal2(){ 
		Scanner scanner = new Scanner(System.in);
		TreeNode root = buildTree(scanner);

		if(root == null) return;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> levelQ = new LinkedList<Integer>();

		queue.add(root);
		levelQ.add(0);

		List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

		while(!queue.isEmpty()){
			TreeNode top = queue.poll();
			int level = levelQ.poll();

			if(list.size() == level) {
				ArrayList<Integer> tmpL = new ArrayList<Integer>();
				tmpL.add(top.val);
				list.add(tmpL);
			} else {
				list.get(level).add(top.val);
			}

			if(top.left != null) {
				queue.add(top.left);
				levelQ.add(level+1);
			}

			if(top.right != null) {
				queue.add(top.right);
				levelQ.add(level+1);
			}
		}

		int L = list.size();

		StringBuffer sb = new StringBuffer();

		for(int i=0; i<L; i++) {
			List<Integer> tmpL = list.get(i);

			int N = tmpL.size();

			if(N==1){
				sb.append(tmpL.get(0) + " ");
				continue;
			}

			for(int j=0; j<N/2; j++){
				sb.append(tmpL.get(j) + " " + tmpL.get(N-1-j) + " ");
			}
		}

		System.out.println(sb.substring(0, sb.length()-1).toString());
	} 

	//http://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not/
	public boolean isFullBinaryTree(TreeNode node){	
		if(node == null) return true;

		if(node.left == null && node.right == null) return true;
		if(node.left != null && node.right != null) 
			return isFullBinaryTree(node.left) && isFullBinaryTree(node.right);

		return false;
	}

	//http://www.geeksforgeeks.org/check-whether-binary-tree-complete-not-set-2-recursive-solution/
	public boolean isCompleteTree(TreeNode node) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		if(node == null) return true;

		queue.add(node);

		boolean stopComplete = false;

		while(!queue.isEmpty()){
			TreeNode top = queue.poll();
			
			if(top.left != null) queue.add(top.left);
			if(top.right != null) queue.add(top.right);
			
			if(top.left == null && top.right != null) return false;
			if(stopComplete && ((top.left != null) || (top.right != null))) return false;

			if(top.left == null || top.right == null) {
				stopComplete = true;
			}
		}

		return true;
	}

	public void checkCompleteTree() {
		Scanner scanner = new Scanner(System.in);
		TreeNode root = buildTree(scanner);				
		System.out.println(isCompleteTree(root));
	}

	//http://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
	public int[] maxPathSum(TreeNode node) {
		if(node == null) return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};

		int[] tmp1 = maxPathSum(node.left);
		int[] tmp2 = maxPathSum(node.right);

		int n1 = node.val;
		int n2 = node.left != null ? node.val + tmp1[0] : Integer.MIN_VALUE;
		int n3 = node.right != null ? node.val + tmp2[0] : Integer.MIN_VALUE;
		int n4 = node.left != null && node.right != null ? node.val + tmp1[0] + tmp2[0] : Integer.MIN_VALUE;

		int max1 = Math.max(n1, Math.max(n2, n3));
		int max2 = Math.max(max1, n4);

		return new int[]{max1, max2};
	}

	public void getMaxPathSum(){
		Scanner scanner = new Scanner(System.in);
		TreeNode root = buildTree(scanner);	
		if(root == null) return;
		System.out.println(maxPathSum(root)[1]);
	}

	//http://www.geeksforgeeks.org/find-minimum-depth-of-a-binary-tree/
	public int minDepth(TreeNode node) {
		if(node == null) return -1;
		if(node.left == null && node.right == null) return 0;
		int n1 = node.left != null ? 1+ minDepth(node.left) : Integer.MAX_VALUE;
		int n2 = node.right != null ? 1 + minDepth(node.right) : Integer.MAX_VALUE;
		return Math.min(n1, n2);
	}

	public void getMinDepth(){
		Scanner scanner = new Scanner(System.in);
		TreeNode root = buildTree(scanner);			
		System.out.println(minDepth(root));
	}

	//http://www.geeksforgeeks.org/given-a-binary-tree-how-do-you-remove-all-the-half-nodes/
	public TreeNode removeHalfNode(TreeNode node) {
		if(node == null) return null;
		if(node.left == null && node.right == null) return node;
		if(node.left == null || node.right == null) return node.left == null ? removeHalfNode(node.right) : removeHalfNode(node.left);
		TreeNode n1 = removeHalfNode(node.left);
		TreeNode n2 = removeHalfNode(node.right);
		node.left = n1;
		node.right = n2;
		return node;
	}

	public void removeHalfNodeTest(){
		Scanner scanner = new Scanner(System.in);
		TreeNode root = buildTree(scanner);		
		inOrder(root);
		System.out.println();
		inOrder(removeHalfNode(root));
	}

	public static void main(String[] args){
		Solution solution = new Solution();

		//solution.specificLevelOrderTravesal();
		//solution.specificLevelOrderTravesal2();
		//solution.checkCompleteTree();
		//solution.getMaxPathSum();
		//solution.getMinDepth();
		solution.removeHalfNodeTest();
	}
}