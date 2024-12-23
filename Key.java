
public class Key {
	private int type;
	private String label;
	
	// Constructor
	public Key (String theLabel, int theType) {
		label = theLabel.toLowerCase();
		type = theType;
	}
	
	// Return this key's label
	public String getLabel() {
		return label;
	}

	// Return this key's type
	public int getType() {
		return type;
	}
	
	// Compare if this key is less than, equal to, or greater than given key
	public int compareToKey(Key k) {
		if (this.label.equals(k.label)) {
				if (this.type == k.type) return 0;
				if (this.type < k.type) return -1;
				else return 1;
		}
		
		if (this.label.compareTo(k.label) > 0) return 1;
		else if (this.label.compareTo(k.label) < 0) return -1;
		else {
			System.out.println("Error");
			return 0;
		}
	}
}
