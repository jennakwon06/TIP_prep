package library;

/* One node of a binary tree. The data element stored is a single 
 * character.
 */
public abstract class TreeNode {
	public int data;
	public TreeNode left;
	public TreeNode right;

	private int size = 0;

	public TreeNode(int d) {
		data = d;
		size = 1;
	}

	abstract int size();

	abstract int height();

	public abstract TreeNode clone();
} 
