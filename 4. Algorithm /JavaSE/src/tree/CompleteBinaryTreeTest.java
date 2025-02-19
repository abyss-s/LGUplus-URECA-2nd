package tree;

public class CompleteBinaryTreeTest {
	public static void main(String[] args) {
		int size = 9;
		CompleteBinaryTree<Character> tree = new CompleteBinaryTree<>(size);
		for (int i = 0; i < size; i++) {
			tree.add(((char) ('a' + i)));
		}

		tree.preOrder(1);
		System.out.println();
		tree.inOrder(1);
		System.out.println();
		tree.postOrder(1);
	}

}
