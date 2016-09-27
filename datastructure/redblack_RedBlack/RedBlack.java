//https://community.topcoder.com/stat?c=problem_statement&pm=1748&rd=4580
import java.util.*;

class TreeNode {
	public static enum COLOR {RED, BLACK};

	int val;
	TreeNode left;
	TreeNode right;
	TreeNode parent;
	public COLOR color;

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, COLOR color) {
		this.val = val; 
		this.color = color;
	}

	public boolean isRed() {
		return this.color == COLOR.RED;
	}

	// public String toString() {
	// 	TreeNode node = this;

	// 	return node.val + ":" + (node.color == TreeNode.COLOR.RED ? "RED" : "BLACK") + ":" +
	// 		(node.parent == null ? "null" : node.parent.val);
	// }
}

public class RedBlack {
	public int nTwists = 0;

	// void inOrder(StringBuffer sb, TreeNode node) {
	// 	if(node == null) return;		
	// 	sb.append(node.toString() + ",");
	// 	inOrder(sb, node.left);		
	// 	inOrder(sb, node.right);
	// }	
	
	// void printTree(TreeNode root) {
	// 	StringBuffer sb = new StringBuffer();
	// 	inOrder(sb, root);
	// 	System.out.println(sb.toString());
	// }	

	//NOTE: this kind of rotation is different from AVL rotate in the sense that 
	// you will need to keep track of parent of node; and then assign parent.left = node | parent.right = node
	// also; because there are both pointer to parent; and to child; remember to update both of them; 
	public TreeNode rRotate(TreeNode node) {
		TreeNode nP = node.parent;
		boolean isLeft = nP != null && nP.left == node;

		TreeNode nL = node.left;
		node.left = nL.right;

		//NOTE: don't forget to assign parent of nL.right here -- quite tricky
		if(nL.right != null) nL.right.parent = node;

		nL.right = node;
		nL.parent = node.parent;
		node.parent = nL;

		if(isLeft) nP.left = nL; 
		else if(nP != null) nP.right = nL;

		return nL;
	}

	public TreeNode lRotate(TreeNode node) {
		TreeNode nP = node.parent;
		boolean isLeft = nP != null && nP.left == node;

		TreeNode nR = node.right;
		node.right = nR.left;
		if(nR.left != null) nR.left.parent = node;

		nR.left = node;
		nR.parent = node.parent;
		node.parent = nR;

		if(isLeft) nP.left = nR; 
		else if(nP != null) nP.right = nR;
		
		return nR;
	}

	public TreeNode insert(TreeNode node, int key) {
		if(node == null) {
			return new TreeNode(key, TreeNode.COLOR.RED);
		}

		//insert 
		TreeNode curNode = node;

		//newly inserted node
		TreeNode newNode = null;

		while(true) {
			if(curNode.val > key) {
				if(curNode.left == null) {
					newNode = new TreeNode(key, TreeNode.COLOR.RED);
					newNode.parent = curNode;
					curNode.left = newNode;
					break;
				} else curNode = curNode.left;
			} else {
				if(curNode.right == null) {
					newNode = new TreeNode(key, TreeNode.COLOR.RED);
					newNode.parent = curNode;
					curNode.right = newNode;					
					break;
				} else curNode = curNode.right;
			}
		}

		TreeNode x = newNode;		

		//if(key == 9) printTree(node);
		while(x.parent != null && x.isRed() && x.parent.isRed()) {			
			TreeNode gParent = x.parent.parent;
			// if(key == 1) {
			// 	System.out.println("HERE");
			// 	printTree(node);
			// }

			//on right branches
			if(x.parent == gParent.right) {
				if(x == x.parent.right) {					
					gParent = lRotate(gParent);		
					//if(key == 9) printTreeEmpty(node);
					gParent.right.color = TreeNode.COLOR.BLACK;
					x = gParent;
					++nTwists;					
				} else {
					rRotate(x.parent);
					gParent = lRotate(gParent);
					gParent.right.color = TreeNode.COLOR.BLACK;
					x = gParent;
					++nTwists;	
				}
			} else {
				if(x == x.parent.left) {
					gParent = rRotate(gParent);
					gParent.left.color = TreeNode.COLOR.BLACK;
					x = gParent;
					++nTwists;		
				} else {
					lRotate(x.parent);
					gParent = rRotate(gParent);					
					gParent.left.color = TreeNode.COLOR.BLACK;
					x = gParent;
					++nTwists;
				}
			}
		}

		if(x.parent == null) return x; 
		else return node;
	}

	public int numTwists(int[] keys) {
		if(keys.length == 0) return 0;
		TreeNode root = null;

		for(int i=0; i<keys.length; i++) {
			root = insert(root, keys[i]);			
			root.color = TreeNode.COLOR.BLACK;			
			// System.out.print("I:" + keys[i] + ";");			
			// printTree(root);
		}

		return nTwists;
	}

	public static void main(String[] args){		
		//15,14, 3, 4, 11, 2, 1, 12, 6, 9, 7, 13, 8
		int[] A = new int[]{ 6,8,10,12,4,2,18,14,16,19,7,15,9,17,13,5,11,3,1 };
		RedBlack solution = new RedBlack();
		System.out.println(solution.numTwists(A));
	}
}