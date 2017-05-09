package org.test.algo.tree;

import static org.test.algo.tree.TreeNode.value;

public class LowestCommonAncestor {

	public static void main(String[] args) {
		// testLevelOrderInsert();
		testFindLCA();
	}

	// Assuming both keys exist in tree
	static TreeNode findLCA(TreeNode root, Object first, Object second) {
		if (root == null) {
			return null;
		}
		// If either first or second matches with root's key, report
		// the presence by returning root 
		if (root.data.equals(first) || root.data.equals(second)) {
			System.out.println("Returning root: "+root.data );
			return root;
		}
		TreeNode leftLCA = findLCA(root.left, first, second);
		TreeNode rightLCA = findLCA(root.right, first, second);
		
		// Both sub results are not null.
		if (leftLCA != null && rightLCA != null) {
			System.out.println("=>Returning root for leftLCA & RightLCA " +value(root));
			return root;
		} else if (leftLCA == null) {
			System.out.println("=>Returning RightLCA "+value(rightLCA));
			return rightLCA;
		} else {
			System.out.println("=>Returning leftLCA "+value(leftLCA));
			return leftLCA;
		}
	}

	static void testFindLCA() {
		int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		TreeNode node = TreeNode.createTree(array, 0, array.length);
//		TreeNode.printLevelOrder(node);

		//@formatter:off
		/**
		  		  1
		  	  2	     3
		    4  5   6   7
		  8 
		 */
		//@formatter:on
		//System.out.println(findLCA(node, 5, 6)); // (5,6) -> 1
		//System.out.println(findLCA(node, 8, 5)); // (8,5) ->2
		System.out.println(findLCA(node, 6, 7)); // (6,7)->3
	}

	static void testLevelOrderInsert() {

		int[] array = new int[]{1};
		TreeNode node = TreeNode.createTree(array, 0, array.length);
		TreeNode.printLevelOrder(node);
		System.out.println();
		array = new int[]{1, 2};
		node = TreeNode.createTree(array, 0, array.length);
		TreeNode.printLevelOrder(node);
		System.out.println();
		array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		node = TreeNode.createTree(array, 0, array.length);
		TreeNode.printLevelOrder(node);
		System.out.println();
		array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
		node = TreeNode.createTree(array, 0, array.length);
		TreeNode.printLevelOrder(node);
	}

}
