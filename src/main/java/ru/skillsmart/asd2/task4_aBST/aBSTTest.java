package ru.skillsmart.asd2.task4_aBST;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class aBSTTest {

    @Test
    public void bstSizeTest() {
        assertEquals(1, new aBST(0).Tree.length);
        assertEquals(3, new aBST(1).Tree.length);
        assertEquals(7, new aBST(2).Tree.length);
        assertEquals(15, new aBST(3).Tree.length);
    }

    @Test
    public void fillWholeTableTest() {
        aBST aBST = new aBST(3);
        assertEquals(0, aBST.AddKey(8));
        assertEquals(0, aBST.AddKey(8));
        assertEquals(1, aBST.AddKey(4));
        assertEquals(2, aBST.AddKey(12));
        assertEquals(3, aBST.AddKey(2));
        assertEquals(4, aBST.AddKey(6));
        assertEquals(5, aBST.AddKey(10));
        assertEquals(6, aBST.AddKey(14));
        assertEquals(7, aBST.AddKey(1));
        assertEquals(8, aBST.AddKey(3));
        assertEquals(9, aBST.AddKey(5));
        assertEquals(10, aBST.AddKey(7));
        assertEquals(11, aBST.AddKey(9));
        assertEquals(12, aBST.AddKey(11));
        assertEquals(13, aBST.AddKey(13));
        assertEquals(14, aBST.AddKey(15));
        assertEquals(-1, aBST.AddKey(16));
        assertEquals("[8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15]", Arrays.toString(aBST.Tree));
    }

    @Test
    public void findKeyIndexTest() {
        aBST aBST = new aBST(3);
        aBST.AddKey(8);
        aBST.AddKey(4);
        aBST.AddKey(12);
        aBST.AddKey(6);
        aBST.AddKey(10);
        aBST.AddKey(14);
        aBST.AddKey(5);
        aBST.AddKey(7);
        aBST.AddKey(9);
        aBST.AddKey(15);

        assertEquals(0, aBST.FindKeyIndex(8));
        assertEquals(-3, aBST.FindKeyIndex(2));
        assertEquals(-3, aBST.FindKeyIndex(1));
        assertEquals(14, aBST.FindKeyIndex(15));
        assertNull(aBST.FindKeyIndex(25));
    }
}
