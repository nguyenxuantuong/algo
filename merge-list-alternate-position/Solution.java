//http://www.geeksforgeeks.org/merge-a-linked-list-into-another-linked-list-at-alternate-positions/
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

	public static void main(String[] args){
		Solution solution = new Solution();

		Scanner scanner = new Scanner(System.in);
		
		ListNode head1 = solution.buildList(scanner.nextLine());
		ListNode head2 = solution.buildList(scanner.nextLine());

		ListNode cur = head1;
		while(cur != null && head2 != null) {
			ListNode next1 = cur.next;
			ListNode next2 = head2.next;

			cur.next = head2;
			head2.next = next1;

			head2 = next2;
			cur = next1;
		}		

		solution.printList(head1);
		solution.printList(head2);

		scanner.close();
	}
}