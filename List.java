/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }
    /** Returns the number of elements in this list. */
    public int getSize() {
    return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        Node newNode = new Node(new CharData(chr),first);
        first = newNode;
        size++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        if (size == 0) return "()";
        // Starting from the first node, iterates through this list
        // and builds the string incrementally
        String str = "(";
        Node current = first;
        while (current != null) {
        str = str + current.toString() + " ";
        current = current.next;
    }
    return str.substring(0, str.length() - 1) + ')';
}

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public  int indexOf(char chr) {
        if (size == 0)
        return -1;
        Node current =first;
        int counter =0;
        while(current!=null){
            if(current.getCp().equals(chr)){
                return counter;
            }
            current = current.next;
            counter++;
        }
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        int index = this.indexOf(chr);
        if (index == -1){
            addFirst(chr);
            return;
        }
        Node current = first;
        boolean found = false;
        while (current != null && found == false){
            if (current.cp.chr == chr){
                found = true;
                current.cp.count ++;
            }
            current = current.next;
        }
    }
    
    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    //checking if the chr exists in the list if not returning false
    public boolean remove(char chr) {
        if(indexOf(chr)==-1)
        return false; 
        //cheking if the chr exist at the first part of the list if yes removing it and returning true
        if(first.getCp().equals(chr)){
            first =first.next;
            return true;
        }
        ////cheking if the chr exist at the rest of the list if yes removing it and returning true
        Node currentNext = first.next;
        Node current = first;
        while(currentNext!=null){
            if(currentNext.getCp().equals(chr)){
                current.next = currentNext.next;
                return true;
            }
            current = current.next;
            currentNext = currentNext.next;
        }
        return false;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        if((index > this.size) ||(index<0))
        throw new IndexOutOfBoundsException();
        int counter = 0;
        Node current = first;
        while(index!=counter){
            if(counter == index){
            return current.cp;
            }
            current = current.next;
            counter ++;
        }
        return current.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }


    
    /**public static void main(String[] args) {
        // Create a new list
        List list = new List();

        // Add some characters to the list
        list.addFirst('a');
        list.addFirst('b');
        list.addFirst('c');

        // Print the list
        //System.out.println("List: " + list);
        System.out.println("List: " + list);
        //printing the Index of a 
        String Index=String.valueOf(list.indexOf('a'));
        System.out.println("the index of a " +Index);
        
        //testing the update method
        list.update('a');
        System.out.println("suppose to change count a  to 2 :" + list);
        list.update('d');
        System.out.println("suppose to add node with chr d :" + list);

         // testing remove 
        list.remove('d');
        System.out.println("suppose to remove node with chr d :" + list);

        //testing the indeOf method
        int index = list.indexOf('b');
        String test = String.valueOf(index);
        System.out.println("index of b should be 1 :" + test);
    }
    //* */
}
