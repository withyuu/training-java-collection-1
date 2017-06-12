package net.digitaliccat.training.list;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        List<Boolean> maxSize = new ArrayList<>(Integer.MAX_VALUE/128);
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


    @Test
    public void playWithSet() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "fourth", "second", "third", "fourth", "fifth"));

        list.set(1, null);
        list.set(5, "sixth");

        assertNull(list.get(1));
        assertEquals("sixth", list.get(5));
    }

    @Test
    public void iterateOverArrayListWithForIndex() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        // Most flexible way to iterate and also the longest and error prone
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void iterateOverArrayListWithForEach() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        // More elegant way to iterate. It says "For each string s in list"
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void iterateOverArrayListWithIterators() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        // Iterator is like a pointer that move to each element one by one in the collection
        System.out.println("--- With Iterator ---");
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        // ListIterator has more capabilities of going back and forth
        System.out.println("--- With ListIterator going forward ---");
        ListIterator<String> litr = list.listIterator();
        while (litr.hasNext()) {
            System.out.println(litr.next());
        }
        System.out.println("--- and going backward ---");
        while (litr.hasPrevious()) {
            System.out.println(litr.previous());
        }

    }

    @Test(expected = ConcurrentModificationException.class)
    public void youCannotChangeStateWhileIterating() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        Iterator<String> itr = list.iterator();
        itr.next();
        itr.next();
        list.add("sixth");
        // list.remove(1); // remove() yields the same result
        itr.next(); // Oops! ConcurrentModificationException
    }

    @Test(expected = IllegalStateException.class)
    public void youMustCallNextBeforeRemove() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        Iterator<String> itr = list.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.remove();
        itr.remove(); // Oops! IllegalStateException
    }

    @Test
    public void iterateOverArrayListWithStream() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        // Introduced in Java 8
        // Functional Programming style

        // list.stream().forEach(System.out::println) // Can be replaced with just list.forEach()
        list.forEach(System.out::println);

        // More on this in Java 8 course (if I have a chance to teach one.)
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenYouReallyNeedToReturnTheWholeCollection() {
        List<String> list = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));
        List<String> immutableList = Collections.unmodifiableList(list);

        immutableList.add("sixth");
    }
}
