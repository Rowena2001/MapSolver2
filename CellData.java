/**
 *  @Rowena CS1027 | 251073629
 *  This class represents the data items that will be stored in the circular array.
 *  Each object of this class stores two things: an identifier and a value.
 */

public class CellData<T> {
	
	private T id;
	private int value;
	
	/**
	 * Constructor for the class. Initializes id to theId and value to theValue.
	 */
	public CellData(T theId, int theValue) {
		id = theId;
		value = theValue;
	}
	
	/**
	 * Returns instance variable value.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns instance variable id.
	 */
	public T getId() {
		return id;
	}
	
	/**
	 * Assigns newValue to instance variable value.
	 */
	public void setValue(int newValue) {
		value = newValue;
	}
	
	/**
	 * Assigns the value of newId to instance variable id.
	 */
	public void setId(T newId) {
		id = newId;
	}

}
