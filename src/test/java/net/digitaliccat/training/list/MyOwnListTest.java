package net.digitaliccat.training.list;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MyOwnListTest {

    // System Under Test
    private MyOwnList sut;

    @Before
    public void setUp() {
        sut = new MyOwnList();
    }

    @Test
    public void initialSizeShouldBeZero() {
        assertEquals(0, sut.size());
    }

    @Test
    public void addOneItem() {
        String first = "first";
        sut.add(first);

        assertEquals("first", sut.get(0));
        assertEquals(1, sut.size());
    }

    @Test
    public void addOneItemAtFirstIndex() {
        addDummyItemsToList(sut, 10);
        String first = "first";
        sut.add(0, first);

        assertEquals("first", sut.get(0));
        assertEquals(0, sut.get(1));
        assertEquals(18, sut.get(10));
        assertEquals(11, sut.size());
    }

    @Test
    public void addOneItemAtLastIndex() {
        addDummyItemsToList(sut, 10);
        String last = "last";
        sut.add(sut.size(), last);

        assertEquals(last, sut.get(10));
        assertEquals(0, sut.get(0));
        assertEquals(18, sut.get(9));
        assertEquals(11, sut.size());
    }

    @Test
    public void addOneItemAtTheMiddle() {
        addDummyItemsToList(sut, 15);
        String middle = "middle";
        sut.add(10, middle);

        assertEquals(middle, sut.get(10)); // verify added item
        assertEquals(0, sut.get(0)); // verify the first item
        assertEquals(18, sut.get(9)); // verify item before the added item
        assertEquals(20, sut.get(11)); // verify item after the added item
        assertEquals(28, sut.get(15)); // verify the last item
        assertEquals(16, sut.size());
    }

    @Test
    public void addItemMoreThanInitialCapacity() {
        addDummyItemsToList(sut, 11);

        assertEquals(20, sut.get(sut.size() - 1));
        assertEquals(11, sut.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addItemAtIndexLargerThanSizeShouldThrowException() {
        sut.add(1, "I cannot be added, sigh");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexLargerThanSizeShouldThrowException() {
        addDummyItemsToList(sut, 11);
        sut.get(12);
    }

    @Test
    public void removeOneItemAtFirstIndex() {
        addDummyItemsToList(sut, 5);
        Object obj = sut.remove(0);

        assertEquals(0, obj);
        assertEquals(2, sut.get(0));
        assertEquals(4, sut.size());
    }

    @Test
    public void removeOneItemAtLastIndex() {
        addDummyItemsToList(sut, 22);
        Object obj = sut.remove(21);

        assertEquals(42, obj);
        assertEquals(40, sut.get(sut.size() - 1));
        assertEquals(21, sut.size());
    }

    @Test
    public void removeOneItemAtTheMiddle() {
        addDummyItemsToList(sut, 17);
        Object obj = sut.remove(9);

        assertEquals(18, obj); // verify removed item
        assertEquals(0, sut.get(0)); // verify the first item
        assertEquals(16, sut.get(8)); // verify item before the removed item
        assertEquals(20, sut.get(9)); // verify item after the removed item
        assertEquals(32, sut.get(15)); // verify the last item
        assertEquals(16, sut.size());
    }


    private void addDummyItemsToList(MyOwnList list, int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            list.add(new Integer(i*2));
        }
    }

}
