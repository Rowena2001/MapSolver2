/**
 *  @Rowena CS1027 | 251073629
 *  This class implements an ordered list using a circular array.
 *  This class must implement the interface SortedListADT.java.
 */

public class OrderedCircularArray<T> implements SortedListADT<T> {
	
	private CellData[] list;
	private int front; // position of the first data item; the data item with the smallest value
	private int rear; // index of the last data item; the data item with the largest value
	private int count; 
	
	public OrderedCircularArray() {
		list = (CellData<T>[])(new CellData[5]);
		front = 1;
		rear = 0;
		count = 0;
	}
	
	/**
	 *  Adds a new CellData object storing the given id and value to the ordered list.
	 */
	@Override
    public void insert(T id, int value) {
        CellData<T> newCell = new CellData(id, value);

        if (count >= list.length) { // doubles array if not enough space
            expandCapacity();
        }

        // searches for correct index to insert the new element
        int temp = front;
        do {
            if (list[temp] == null) {
                list[temp] = newCell;
                break;
            }
            if (list[temp] != null && value < list[temp].getValue()) {
                int i = rear;
                while (i != (temp - 1) % list.length) {
                    list[(i + 1) % list.length] = list[i];
                    i = (i - 1) % list.length;
                }
                list[temp] = newCell;
                break;
            }
            temp = (temp + 1) % list.length;
        } while (temp != front);
        ++count;
        rear = (front + count - 1) % list.length;
    }
	
    public void printlist() {
        System.out.println("front: " + front + " |rear: " + rear + " |length: " + list.length + " |count: " + count);
        int temp = front;
        do {
            if (list[temp] != null) {
                System.out.println("index: " + temp + " |id: " + list[temp].getId() + " |value: " + list[temp].getValue());
            } else {
                System.out.println("index: " + temp + " |id: null |value: null");
            }
            temp = (temp + 1) % list.length;
        } while (temp != front);
    }
	
	/**
	 *  Creates a larger list and copies all entries from the original list.
	 */
    public void expandCapacity() {
        CellData[] temp = new CellData[list.length * 2];
        for (int i = front; i < list.length * 2; i++) {
            temp[i] = list[i % list.length];
            if (i % list.length == rear) {
                rear = i;
                break;
            }
        }
        list = temp;
    }
    
	/**
	 *  Returns the integer value of the CellData object with the specified id.
	 */
    public int getValue(T id) throws InvalidDataItemException {
        int i = front;
        do {
            if (list[i] == null) {
                throw new InvalidDataItemException("Cell data not found. ");
            }
        	// System.out.println(list[i].getId() + "|" + id + "|" + list[i].getId().equals(id));
            if (list[i].getId().equals(id)) {
                return list[i].getValue();
            }
            i = (i + 1) % list.length;
        } while (i != front);
        return 0;
    }
    
	/**
	 *  Removes from the ordered list the CellData object with the specified id.
	 */
    public void remove(T id) throws InvalidDataItemException {
        int i = front;
        do {
            if (list[i] == null) {
                throw new InvalidDataItemException("Cell data not found. ");
            }
            if (list[i].getId().equals(id)) {
                int j = i;
                while (j != rear) {
                    list[j] = list[(j + 1) % list.length];
                    j = (j + 1) % list.length;
                }
                list[rear] = null;
                --count;
                rear = (front + count - 1) % list.length;
                break;
            }
            i = (i + 1) % list.length;
        } while (i != front);
    }
    
	/**
	 *  Changes the value attribute of the CellData object with the given id to the specified newValue.
	 */
    public void changeValue(T id, int newValue) throws InvalidDataItemException {
        int i = front;
        do {
            if (list[i] == null) {
                throw new InvalidDataItemException("Cell data not found. ");
            }
            if (list[i].getId().equals(id)) {
                remove(id);
                insert(id, newValue);
                break;
            }
            i = (i + 1) % list.length;
        } while (i != front);
    }
    
	/**
	 *  Removes and returns the id or the CellData object in the ordered list with smallest associated value.
	 */
    public T getSmallest() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("List is empty. ");
        }
        T id = (T) list[front].getId();
        list[front] = null;
        --count;
        front = (front + 1) % list.length;
        return id;
    }
    
	/**
	 *  Returns true if the ordered list is empty and it returns false otherwise.
	 */
    public boolean isEmpty() {
    	return (count == 0);
    }
    
	/**
	 *  Returns the number of data items in the ordered list.
	 */
    public int size() {
    	return count;
    }
    
	/**
	 *  Returns the value of instance variable front.
	 */
    public int getFront() {
    	return front;
    }
    
	/**
	 *  Returns the value of instance variable rear.
	 */
    public int getRear() {
    	return rear;
    }
}