//http://www.geeksforgeeks.org/find-closest-leaf-binary-tree/
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

	static HashMap<TreeNode, TreeNode> nodeParent = new HashMap<TreeNode, TreeNode>();
	static HashMap<TreeNode, Integer> closestDistance = new HashMap<TreeNode, Integer>();

	public TreeNode dfs(TreeNode node, int key) {
		if(node == null) return null;
		if(node.val == key) return node;

		TreeNode n1 = dfs(node.left, key);
		TreeNode n2 = dfs(node.right, key);
		if(node.left != null) nodeParent.put(node.left, node);
		if(node.right != null) nodeParent.put(node.right, node);
		return n1 != null ? n1 : (n2 != null ? n2 : null);
	}

	public int findClosestDistance(TreeNode node) {
		//leaf node
		if(node.left == null && node.right == null) {
			closestDistance.put(node, 0);
			return 0;
		}

		int n1 = node.left != null ? 1 + findClosestDistance(node.left) : Integer.MAX_VALUE;
		int n2 = node.right != null ? 1 + findClosestDistance(node.right) : Integer.MAX_VALUE;
		int n = Math.min(n1, n2);

		closestDistance.put(node, n);
		return n;
	}

	public int closestDistance(TreeNode root, int key) {		
		nodeParent = new HashMap<TreeNode, TreeNode>();		
		closestDistance = new HashMap<TreeNode, Integer>();

		TreeNode node = dfs(root, key);
		if(node == null) return -1; //non existing key

		findClosestDistance(root);

		nodeParent.put(root, null);
		int out = Integer.MAX_VALUE;

		int count = 0;
		while(node != null) {
			out = Math.min(out, count + closestDistance.get(node));
			node = nodeParent.get(node);
			count++;
		}

		return out;
	}
	

	//http://www.geeksforgeeks.org/find-sum-left-leaves-given-binary-tree/
	public int sumLeftLeaf(TreeNode node, boolean isLeft) {
		if(node == null) return 0;
		if(isLeft && node.left == null && node.right == null) return node.val;			
		return sumLeftLeaf(node.left, true) + sumLeftLeaf(node.right, false);
	}

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();

		int key = scanner.nextInt();
		TreeNode root = solution.buildTree(scanner);

		int[] keyArray = new int[]{10, 9, 3, 6, 5};
		for(int i=0; i<keyArray.length; i++) {
			System.out.println(solution.closestDistance(root, keyArray[i]));
		}

		System.out.println(solution.sumLeftLeaf(root, false));
	}
}