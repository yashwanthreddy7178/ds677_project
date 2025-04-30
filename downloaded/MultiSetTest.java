package util;

import org.junit.Before;
import org.junit.Test;
import util.MultiSet;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MultiSetTest {

    MultiSet<Integer> i;

    @Before
    public void setup() {
        i = new MultiSet<>();
    }

    @Test
    public void testEmptyMultiSet() {
        assertEquals(0, i.size());
    }

    @Test
    public void testFilledMultiSet1() {
        MultiSet<Integer> j = new MultiSet<>(Arrays.asList(1,2,3));
        assertEquals(3, j.size());
        assertEquals(1, j.get(1));
        assertEquals(1, j.get(2));
        assertEquals(1, j.get(3));
    }

    @Test
    public void testFilledMultiSet2() {
        MultiSet<Integer> j = new MultiSet<>(Arrays.asList(1,2,2,3,3,3));
        assertEquals(3, j.size());
        assertEquals(1, j.get(1));
        assertEquals(2, j.get(2));
        assertEquals(3, j.get(3));
    }
    @Test
    public void testPutSize1() {
        i.put(1);
        assertEquals(1, i.size());
    }

    @Test
    public void testPutSize2() {
        i.put(1);
        i.put(1);
        assertEquals(1, i.size());
    }

    @Test
    public void testPutSize3() {
        i.put(1);
        i.put(2);
        assertEquals(2, i.size());
    }

    @Test
    public void testPutGet1() {
        assertEquals(0, i.get(1));
    }

    @Test
    public void testPutGet2() {
        i.put(1);
        assertEquals(1, i.get(1));
    }

    @Test
    public void testPutGet3() {
        i.put(1);
        i.put(1);
        assertEquals(2, i.get(1));
    }

    @Test
    public void testUnion1() {
        MultiSet<Integer> o = new MultiSet<>();
        MultiSet<Integer> result = i.union(o, (a,b) -> a+b);
        assertEquals(0, result.size());
    }

    @Test
    public void testUnion2() {
        i.put(1);
        MultiSet<Integer> o = new MultiSet<>();
        MultiSet<Integer> result = i.union(o, (a,b) -> a+b);
        assertEquals(1, result.size());
        assertEquals(1, result.get(1));
    }

    @Test
    public void testUnion3() {
        i.put(1);
        i.put(1);
        MultiSet<Integer> o = new MultiSet<>();
        MultiSet<Integer> result = i.union(o, (a,b) -> a+b);
        assertEquals(1, result.size());
        assertEquals(2, result.get(1));
    }

    @Test
    public void testUnion4() {
        MultiSet<Integer> o = new MultiSet<>();
        o.put(1);
        MultiSet<Integer> result = i.union(o, (a,b) -> a+b);
        assertEquals(1, result.size());
        assertEquals(1, result.get(1));
    }

    @Test
    public void testUnion5() {
        i.put(1);
        MultiSet<Integer> o = new MultiSet<>();
        o.put(1);
        MultiSet<Integer> result = i.union(o, (a,b) -> a+b);
        assertEquals(1, result.size());
        assertEquals(2, result.get(1));
    }

    @Test
    public void testUnion6() {
        i.put(1);
        MultiSet<Integer> o = new MultiSet<>();
        o.put(1);
        MultiSet<Integer> result = i.union(o, Math::max);
        assertEquals(1, result.size());
        assertEquals(1, result.get(1));
    }
}
