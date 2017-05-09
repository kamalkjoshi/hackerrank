package org.test.algo;

public class AddNumberLinkedList {

	// linked list node class
	static class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	static class LinkedList {

		Node head;

		/* Utility function to print a linked list */

		void printList(Node head) {
			while (head != null) {
				System.out.print(head.data + " ");
				head = head.next;
			}
			System.out.println("");
		}

		/**
		 * Assumption - both list to be of same size
		 * 
		 * @param head1
		 * @param head2
		 * @return
		 */
		public Node addTwoLists0(Node head1, Node head2) {
			// End of recursion
			if (head2.next == null || head1.next == null) {
				int sum = head1.data + head2.data;
				Node node = new Node(sum);
				node.next = null;
				return node;
			}

			Node oldNode = addTwoLists0(head1.next, head2.next);
			int carry = oldNode.data / 10;
			oldNode.data = oldNode.data % 10;
			int currentdata = carry + head1.data + head2.data;
			Node node = new Node(currentdata);
			node.next = oldNode;

			return node;
		}

	}

	public static void main(String[] args) {

		LinkedList list = new LinkedList();

		// creating first list
		list.head = new Node(7);
		list.head.next = new Node(5);
		list.head.next.next = new Node(9);
		list.head.next.next.next = new Node(4);

		System.out.print("First List is ");
		list.printList(list.head);

		LinkedList list2 = new LinkedList();
		// creating seconnd list
		list2.head = new Node(8);
		list2.head.next = new Node(4);
		list2.head.next.next = new Node(3);
		list2.head.next.next.next = new Node(6);
		System.out.print("Second List is ");
		list.printList(list2.head);

		// add the two lists and see the result
		Node rs = list.addTwoLists0(list.head, list2.head);
		System.out.print("Resultant List is ");
		list.printList(rs);

	}

}
