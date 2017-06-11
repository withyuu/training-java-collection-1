package net.digitaliccat.training.list;


import java.util.Arrays;

public class MyOwnList {

    private int size;

    private Object[] collection;

    public MyOwnList() {
        size = 0;
        collection = new Object[10];
    }

    public void add(Object obj) {
        ensureCapacity();

        collection[size] = obj;
        ++size;
    }

    public void add(int index, Object obj) {
        checkRangeForAdd(index);

        ensureCapacity();
        System.arraycopy(collection, index, collection, index + 1, size - index);
        collection[index] = obj;
        ++size;
    }

    public Object remove(int index) {
        checkRange(index);

        Object ret = collection[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(collection, index + 1, collection, index,
                    numMoved);

        // clear item
        collection[--size] = null;

        return ret;
    }

    public Object get(int index) {
        checkRange(index);
        return collection[index];
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size + 1 > collection.length) {
            increaseSize();
        }
    }

    private void increaseSize() {
        collection = Arrays.copyOf(collection, collection.length * 2);
    }

    private void checkRange(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void checkRangeForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return String.format("Index %d out of bound for the list of size %d", index, size);
    }

}
