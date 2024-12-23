import java.lang.annotation.Target;

public class BinarySearchTree {
	private BSTNode root;
	
	// Constructor
	public BinarySearchTree() {
		root = new BSTNode((new Record(null, null)));
	}
	
	// Returns the root of this tree
	public BSTNode getRoot() {
		return root;
	}
	
	// Retrieves node with given key
	public BSTNode get(BSTNode r, Key k) {
		if (r.isLeaf()) return null;
		if (k.compareToKey(r.getRecord().getKey()) == 0) return r;
		if (k.compareToKey(r.getRecord().getKey()) == -1) return get (r.getLeftChild(), k);
		else return get(r.getRightChild(), k);
	}
	
	// Inserts a record into the BST
	public void insert (BSTNode r, Record d) throws DictionaryException {
		BSTNode node = this.getNode(r, d.getKey());
		if (!node.isLeaf()) throw new DictionaryException("Record already exists.");
		node.setRecord(d);
		BSTNode left = new BSTNode(null);
		BSTNode right = new BSTNode(null);
		left.setParent(node);
		right.setParent(node);
		node.setLeftChild(left);
		node.setRightChild(right);
	}
	
	// Remove record with given key from the BST
	public void remove (BSTNode r, Key k) throws DictionaryException {
		BSTNode leaf, other, targetNode, parent, replacementNode;
		targetNode = this.getNode(r, k);
		
		if (targetNode.isLeaf()) {
			throw new DictionaryException("Tree does not store a record with given key"); }
	
		if (targetNode.getLeftChild().isLeaf() || targetNode.getRightChild().isLeaf()) {
			if (targetNode.getLeftChild().isLeaf()) {
				leaf = targetNode.getLeftChild();
				other = targetNode.getRightChild();
			} else {
				leaf = targetNode.getRightChild();
				other = targetNode.getLeftChild();
			}
			parent = targetNode.getParent();
			if (parent != null) {
				if (parent.getLeftChild() == targetNode)
					parent.setLeftChild(other);	
				else parent.setRightChild(other);
				other.setParent(parent);
			} else {
				this.root = other;
				this.root.setParent(null);
			}
		} else {
			replacementNode = this.smallest(targetNode.getRightChild());
			if (replacementNode.isLeaf()) {
				targetNode.setRecord(null);
				targetNode.setLeftChild(null);
				targetNode.setRightChild(null);
			} else {
				targetNode.setRecord(replacementNode.getRecord());
				remove(replacementNode, replacementNode.getRecord().getKey());
			}
		}
	}
	
	// Find the smallest node in this subtree
	public BSTNode smallest(BSTNode r) {
		if (r == null || r.getRecord() == null) return null;
		BSTNode node = r;
		if (node.isLeaf()) return node;
		else {
			while (!node.isLeaf()) {
				node = node.getLeftChild();
			} 
			return node.getParent();
		}
	}
	
	// Find the largest node in this subtree
	public BSTNode largest(BSTNode r) {
		if (r == null || r.getRecord() == null) return null;
		BSTNode node = r;
		while (!node.isLeaf()) {
			node = node.getRightChild();
		}
		return node.getParent();
	}
	
	// Find the next node in this subtree
	public BSTNode successor(BSTNode r, Key k) {
		BSTNode node = this.get(r, k);
		if (!node.getRightChild().isLeaf()) return smallest(node.getRightChild());
		else {
			node = node.getParent();
			while (node != null && node.getRecord().getKey().compareToKey(k) == -1) {
				node = node.getParent();
			}
			return node;
		}
	}
	
	// Find the previous node in this subtree
	public BSTNode predecessor(BSTNode r, Key k) {
		BSTNode node = this.get(r, k);
		if (!node.getLeftChild().isLeaf()) return largest(node.getLeftChild());
		else {
			node = node.getParent();
			while (node != null && node.getRecord().getKey().compareToKey(k) == 1) {
				node = node.getParent();
			}
			return node;
		}
	}
	
	// Private helper get method that returns a node in all cases
	private BSTNode getNode(BSTNode r, Key k) {
		if (r.isLeaf()) return r;
		if (k.compareToKey(r.getRecord().getKey()) == 0) return r;
		if (k.compareToKey(r.getRecord().getKey()) == -1) return getNode(r.getLeftChild(), k);
		else return getNode(r.getRightChild(), k);
	}		
}
