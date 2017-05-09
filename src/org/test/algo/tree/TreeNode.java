package org.test.algo.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

	Object data;

	TreeNode right;

	TreeNode left;

	TreeNode(Object data) {
		this.data = data;
	}

	TreeNode(Object data, TreeNode left, TreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public static Object value(TreeNode node) {
		return node == null ? null : node.data;
	}
	
	public String toString() {
		return "TreeNode[Data:" + data + ", Left:" + value(left) + ", Right:"
				+ value(right) + "]";
	}

	public static void printInOrder(TreeNode node) {
		if (node != null) {
			System.out.print(node.data);
			printInOrder(node.left);
			printInOrder(node.right);
		}
	}

	public static void printPreOrder(TreeNode node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.print(node.data);
			printInOrder(node.right);
		}
	}

	public static void printPostOrder(TreeNode node) {
		if (node != null) {
			printInOrder(node.left);
			printInOrder(node.right);
			System.out.print(node.data);
		}
	}

	public static void printLevelOrder(TreeNode node) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			System.out.print(current.data);
			if (current.left != null)
				queue.offer(current.left);
			if (current.right != null)
				queue.offer(current.right);

		}
	}
	/**
	 * Prepare tree using level order insert
	 * 
	 * @param array
	 * @param start
	 * @param size
	 * @return
	 */
	public static TreeNode createTree(int[] array, int start, int size) {
		if (start >= size) {
			return null;
		}
		TreeNode root = new TreeNode(array[start]);
		int left = 2 * start + 1;
		int right = 2 * start + 2;

		if (left <= size) {
			root.left = createTree(array, left, size);
		}
		if (right <= size) {
			root.right = createTree(array, right, size);
		}
		return root;
	}
}
