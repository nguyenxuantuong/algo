//http://www.geeksforgeeks.org/how-to-sort-a-linked-list-that-is-sorted-alternating-ascending-and-descending-orders/
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

	//merge two linked list
	public ListNode mergeList(ListNode head1, ListNode head2){
		ListNode cur1 = head1, cur2 = head2;

		ListNode head = new ListNode(0);
		ListNode cur = head;

		while(cur1 != null || cur2 != null){
			if(cur1 == null){
				cur.next = cur2;
				cur = cur.next;
				cur2 = cur2.next;
			} else if(cur2 == null) {
				cur.next = cur1;
				cur = cur.next;
				cur1 = cur1.next;
			} else {
				if(cur1.val > cur2.val){
					cur.next = cur2;
					cur = cur.next;
					cur2 = cur2.next;
				} else {
					cur.next = cur1;
					cur = cur.next;
					cur1 = cur1.next;
				}
			}
		}

		return head.next;
	}	

	//revese list
	public ListNode reveseList(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode slow = head, fast = head.next;
		slow.next = null;

		while(fast != null) {
			ListNode tmp = fast.next;
			fast.next = slow;
			slow = fast;
			fast = tmp;
		}

		return slow;
	}

	public ListNode sortList(ListNode head){
		if(head == null || head.next == null || head.next.next == null) return head;
		ListNode head1 = head, head2 = head.next;
		ListNode cur1 = head1, cur2 = head2;

		while((cur1.next != null && cur1.next.next != null) || (cur2.next != null && cur2.next.next != null)) {						
			if(cur1.next != null && cur1.next.next != null){
				cur1.next = cur1.next.next;
				cur1 = cur1.next;				
			}
			
			if(cur2.next != null && cur2.next.next != null){
				cur2.next = cur2.next.next;			
				cur2 = cur2.next;
			}			
		}

		cur1.next = null;
		cur2.next = null;

		ListNode mList = mergeList(head1, reveseList(head2));

		return mList;
	}

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
		
		ListNode head = solution.buildList(scanner.nextLine());
		
		solution.printList(solution.sortList(head));

		scanner.close();
	}
}