//https://www.hackerrank.com/challenges/self-balancing-tree
import java.util.*;

class Node {
	int val;
	int ht;
	Node left;
	Node right;
}

public class Solution {
	static Node createNode(int val) {
		Node newNode = new Node();
		newNode.val = val;
		newNode.ht = 0;
		return newNode;
	}	

	static int getHeight(Node node) {
		return node == null ? -1 : node.ht;
	}

	static Node leftRotate(Node node) {
		Node newNode = node.right;		
		int nodeHeight = 1 + Math.max(getHeight(newNode.left), getHeight(node.left));
		int newNodeHeight = 1 + Math.max(getHeight(newNode.right), nodeHeight);
		node.right = newNode.left;	
		newNode.left = node;
		node.ht = nodeHeight;
		newNode.ht = newNodeHeight;
		return newNode;
	}


	static Node rightRotate(Node node) {
		Node newNode = node.left;
		int nodeHeight = 1 + Math.max(getHeight(newNode.right), getHeight(node.right));
		int newNodeHeight = 1 + Math.max(getHeight(newNode.left), nodeHeight);
		node.left = newNode.right;
		newNode.right = node;		
		node.ht = nodeHeight;
		newNode.ht = newNodeHeight;
		return newNode;
	}

	static Node doubleLeftRotate(Node node) {
		node.right = rightRotate(node.right);
		return leftRotate(node);
	}

	static Node doubleRightRotate(Node node) {
		node.left = leftRotate(node.left);
		return rightRotate(node);
	}

	static Node rebalanceTree(Node node) {
		int lH = node.left == null ? -1 : node.left.ht;
		int rH = node.right == null ? -1 : node.right.ht;

		if(lH + 2 <= rH) {
			Node nR = node.right;
			if(getHeight(nR.right) > getHeight(nR.left)) {
				return leftRotate(node);
			} else {
				return doubleLeftRotate(node);
			}			
		} else if(rH + 2 <= lH) {
			Node nL = node.left;
			if(getHeight(nL.left) > getHeight(nL.right)) {
				return rightRotate(node);
			} else {
				return doubleRightRotate(node);
			}
		} else {
			//tree is balanaced - just update height of current node
			node.ht = Math.max(lH, rH) + 1;		
			return node;
		}		
	}

	static Node insert(Node node, int val) {	
		if(node == null) return createNode(val);

		if(node.val == val) return node;

 		if(node.val > val) {
 			node.left = insert(node.left, val);		
		} else {
			node.right = insert(node.right, val);
		}		

		return rebalanceTree(node);
	}
}