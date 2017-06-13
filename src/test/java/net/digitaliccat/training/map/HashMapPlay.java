package net.digitaliccat.training.map;


import org.junit.Before;
import org.junit.Test;
import org.mockito.asm.tree.analysis.Value;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class HashMapPlay {

    private HashMap<String, String> countryToCapital;

    @Before
    public void setUp() {
        countryToCapital = new HashMap<>();
        countryToCapital.put("USA", "Washington, D.C");
        countryToCapital.put("Thailand", "Bangkok");
        countryToCapital.put("Switzerland", "Bern");
        countryToCapital.put("Germany", "Berlin");
        countryToCapital.put("Japan", "Tokyo");
        countryToCapital.put("Naboo", "Theed");
    }

    @Test
    public void playWithConstructors() {
        Map<String, String> a = new HashMap<>();
        Map<String, String> b = new HashMap<>(37); // prime numbers are good candidate for the capacity

        // NOT RECOMMENDED!!
        // ONLY use it when you KNOW what you are doing here
        // See: http://wiki.c2.com/?DoubleBraceInitialization
        Map<String, String> c = new HashMap<String, String>(){
            {
                put("1", "one");
                put("2", "two");
                put("3", "three");
            }
        };

        Map<String, String> d = new HashMap<>(c); // Equals to putAll()

        assertEquals(0, a.size());
        assertEquals(0, b.size());
        assertEquals(3, c.size());
        assertEquals(3, d.size());
    }

    @Test
    public void playWithOrdinaryPut() {
        Map<String, String> accessTokenToTmnIdMap = new HashMap<>();
        accessTokenToTmnIdMap.put("123", "Gique");
        accessTokenToTmnIdMap.put("567", "Big");
        accessTokenToTmnIdMap.put("890", "Amy");
        accessTokenToTmnIdMap.put("123", "Yuu");

        assertEquals(3, accessTokenToTmnIdMap.size());
        assertEquals("Yuu", accessTokenToTmnIdMap.get("123"));
    }

    @Test
    public void whenYourKeyIsNotGoodEnoughToBePut() {
        Map<NotSoGoodObject, String> map = new HashMap<>();
        map.put(new NotSoGoodObject(1), "one");
        map.put(new NotSoGoodObject(2), "two");
        map.put(new NotSoGoodObject(3), "three");
        assertNull(map.get(new NotSoGoodObject(1)));
    }

    @Test
    public void whenYourKeyIsStillNotSoGoodEnoughToBePut() {
        Map<StillNotSoGoodObject, String> map = new HashMap<>();
        map.put(new StillNotSoGoodObject(1), "one");
        map.put(new StillNotSoGoodObject(2), "two");
        map.put(new StillNotSoGoodObject(11), "eleven");
        map.put(new StillNotSoGoodObject(12), "twelve");

        assertEquals(2, map.size());
        assertEquals("eleven", map.get(new StillNotSoGoodObject(1)));
        assertEquals("twelve", map.get(new StillNotSoGoodObject(52)));
    }

    @Test
    public void goodEnoughKey() {
        Map<GoodObject, String> map = new HashMap<>();
        map.put(new GoodObject(1), "one");
        map.put(new GoodObject(2), "two");
        map.put(new GoodObject(11), "eleven");
        map.put(new GoodObject(12), "twelve");

        assertEquals(4, map.size());
        assertEquals("one", map.get(new GoodObject(1)));
        assertNull(map.get(new GoodObject(52)));
    }

    @Test
    public void iterateOverHashMapUsingKeySet() {
        // Can use iterator or stream up to your desire
        // Theoretically, this offers O(n) performance but not really effective because it's really
        // n + (1*n) = 2n
        for (String country : countryToCapital.keySet()) {
            System.out.printf("Capital of %s is %s%n", country, countryToCapital.get(country));
        }
    }

    @Test
    public void iterateOverHashMapUsingMapEntry() {
        // Can use iterator or stream up to your desire
        // This is exactly O(n)
        for (Map.Entry<String, String> entry : countryToCapital.entrySet()) {
            System.out.printf("Capital of %s is %s%n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void iterateOverHashMapUsingStreamForEachAndLambda() {
        countryToCapital.forEach((k,v) -> {
            System.out.printf("Capital of %s is %s%n", k, v);
        });
        // For more information see this post on SO:
        // https://stackoverflow.com/questions/46898/how-to-efficiently-iterate-over-each-entry-in-a-map
    }

}

interface ValueInterface {
    int getValue();
}

class NotSoGoodObject implements ValueInterface {
    private int value;

    NotSoGoodObject(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}

class StillNotSoGoodObject implements ValueInterface {
    private int value;

    StillNotSoGoodObject(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value % 10;
    }

    public boolean equals(Object obj) {
        return this.value % 10 == ((StillNotSoGoodObject) obj).getValue() % 10;
    }
}

class GoodObject implements ValueInterface {
    private int value;

    GoodObject(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodObject)) return false;

        GoodObject that = (GoodObject) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}