//http://www.geeksforgeeks.org/swap-nodes-in-a-linked-list-without-swapping-data/
import java.util.*;

class ListNode {
	int val;
	ListNode next;

	ListNode(int val){
		this.val = val;
	}
}

public class Solution {
	public ListNode buildList(String line) {
		String[] tokens = line.split(" ");
		ListNode head = null;
		ListNode cur = null;

		for(int i=0; i<tokens.length; i++) {
			ListNode tmp = new ListNode(Integer.parseInt(tokens[i]));
			if(cur == null) {
				cur = tmp;
				head = cur;
			}
			else {
				cur.next = tmp;
				cur = cur.next;
			}
		} 

		return head;
	}

	public void printList(ListNode head){
		while(head != null){
			if(head.next != null) {
				System.out.print(head.val + "->");	
			} else {
				System.out.print(head.val);
			}
			
			head = head.next;
		}

		System.out.println();
	}

	public ListNode swapNodes(ListNode head, int key1, int key2){
		//same key; do nothing
		if(key1 == key2) return head;

		if(head == null || head.next == null) return head;

		//assume all key in linked list are distinct
		ListNode newHead = new ListNode(0);
		newHead.next = head;

		ListNode pre = newHead, cur = newHead.next;

		ListNode node1 = null, node2 = null, pre1 = null, pre2 = null;

		while(cur != null){
			if(cur.val == key1 || cur.val == key2){
				if(node1 == null) {
					node1 = cur;
					pre1 = pre;
				} else if(node2 == null) {
					pre2 = pre;
					node2 = cur;
				} else {
					break;
				}
			}

			pre = cur;
			cur = cur.next;
		}


		//one key does not exist
		if(node1 == null || node2 == null) return newHead.next;

		//swap
		if(node1 != pre2){
			ListNode next1 = node1.next, next2 = node2.next;
			pre1.next = node2; node2.next = next1; 
			pre2.next = node1; node1.next = next2;
		} else {
			ListNode next2 = node2.next;
			pre1.next = node2; 
			node2.next = node1;
			node1.next = next2;
		}		

		return newHead.next;
	}

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
		
		int key1 = scanner.nextInt();
		int key2 = scanner.nextInt();
		scanner.nextLine();

		ListNode head = solution.buildList(scanner.nextLine());
		
		solution.printList(solution.swapNodes(head, key1, key2));

		scanner.close();
	}
}