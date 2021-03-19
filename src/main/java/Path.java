
import java.util.Arrays;
import java.util.Iterator;

public class Path {

    //Size of list
    private int size = 0;

    //Default capacity of list is 10
    private static final int DEFAULT_CAPACITY = 10;

    //This array will store all elements added to list
    private CC elements[];

    //Default constructor
    public Path(CC inital) {
        elements = new CC[DEFAULT_CAPACITY];
        elements[size++] = inital;

    }

    //Add method
    public boolean add(CC next) {
        if (size == elements.length) {
            ensureCapacity();
        }

        CC last = elements[0];
        for (int i = 1; i < elements.length; i++) {
            try {
                last = get(i);
            } catch (Exception e) {
                break;
            }
        }
        if (!contains(next)) {
            if (last.addNextNode(next)) {
                elements[size++] = next;
            }
        }
        return false;
    }

    //Get method
    @SuppressWarnings("unchecked")
    public CC get(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        return (CC) elements[i];
    }

    //Remove method
    @SuppressWarnings("unchecked")
    public CC remove(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        Object item = elements[i];
        int numElts = elements.length - (i + 1);
        System.arraycopy(elements, i + 1, elements, i, numElts);
        size--;
        return (CC) item;
    }

    //Get Size of list
    public int size() {
        return size;
    }

    //Print method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elements[i].toString());
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    public boolean contains(CC cc) {
        CC last = elements[0];
        for (int i = 0; i < elements.length; i++) {
            try {
                last = get(i);
                if (last.hashCode() == cc.hashCode()) {
                    return true;
                }
            } catch (Exception e) {
                break;
            }
        }
        return false;
    }
}
