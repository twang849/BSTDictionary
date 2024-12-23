
public class Record {
	private Key theKey;
	private String data;
	
	// Constructor
	public Record(Key k, String theData) {
		theKey = k;
		data = theData;
	}
	
	// Returns this record's key
	public Key getKey() {
		return theKey;
	}
	
	// Returns the data stored in this record
	public String getDataItem() {
		return data;
	}
}
