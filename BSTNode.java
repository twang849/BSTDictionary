
public class BSTNode {
	private Record record;
	private BSTNode left;
	private BSTNode right;
	private BSTNode parent;
	
	//The constructor for the class.
	public BSTNode(Record item) {
		record = item;
		left = null;
		right = null;
		parent = null;
	}

	//Returns the Record object stored in this node.
	public Record getRecord() {	
		return record;
	}

	//Stores the given record in this node.
	public void setRecord (Record d) {
		record = d; 
	}

	//Returns the left child.
	public BSTNode getLeftChild() {
		return left;
	}

	//Return the right child.
	public BSTNode getRightChild() {
		return right;
	}

	//Returns the parent.
	public BSTNode getParent() {
		return parent;
	}

	//Sets the left child to the specified value.
	public void setLeftChild(BSTNode u) {
		left = u;
	}

	//Sets the right child to the specified value.
	public void setRightChild(BSTNode u) {
		right = u;
	}

	//Sets the parent to the specified value.
	public void setParent(BSTNode u) {
		parent = u;
	}

	//Returns true if this node is a leaf; false otherwise. A node is a
	//leaf if both of its children are null. 
	public boolean isLeaf() {	 	
		if (left == null && right == null) return true;
		else return false;
	}

}
