package net.digitaliccat.training.list;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ArrayListPlay {

    @Test
    public void playWithConstructors() {
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>(20);
        List<Integer> c = new ArrayList<>(Arrays.asList(1, 2, 3)); // same with addAll()

        assertEquals(0, a.size());
        assertEquals(0, b.size());
        assertEquals(3, c.size());
    }

    @Test
    public void sizeLimitation() {
        // Size Limitation is Integer.MAX_VALUE
        List<Boolean> maxSize = new ArrayList<>(Integer.MAX_VALUE/4);
    }

    @Test
    public void playWithAdd() {
        List<String> stringList = new ArrayList<>();
        stringList.add("string item");
        // Cannot add other type that does not match the generic
//        stringList.add(new Integer(2));
    }

    @Test
    public void playWithAddAll() {
        List<String> list = new ArrayList<>();
        Collection<String> someCollection = new ArrayList<>(Arrays.asList("hello", "from", "pluto"));

        list.addAll(someCollection);
        // The easiest way to print a list is to just print a list
        System.out.println(list);
    }

    @Test
    public void playWithGet() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        // get by index is the only way to get the object out of the list
        assertEquals(new Integer(3), list.get(2));
    }

    @Test
    public void playWithRemoveByIndex() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        // remove with index returns an object
        String removed = list.remove(2);

        assertEquals("third", removed);
    }

    @Test
    public void playWithRemoveByObject() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "fourth", "second", "third", "fourth", "fifth"));

        // remove with object return just a boolean
        boolean isRemoved = list.remove("fourth");

        assertEquals(isRemoved, true);

        // and one more thing, remove() does remove the first object first
        assertEquals(5, list.size());
        assertEquals("second", list.get(1)); // the second element is "second' instead of "fourth"
        assertEquals("fourth", list.get(3)); // the second "fourth" is still there
    }

}
