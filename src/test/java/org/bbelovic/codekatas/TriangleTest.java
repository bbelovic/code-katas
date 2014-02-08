package org.bbelovic.codekatas;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class TriangleTest {

    private static final Triangle TRIANGLE_1 = new Triangle(new int [][] {{0}, {1, 2}, {3, 4, 5}});
    private static final Triangle TRIANGLE_2 = new Triangle(new int [][] {{0}, {1, 2}, {3, 4, 5}});
    private static final Triangle TRIANGLE_3 = new Triangle(new int [][] {{0}, {1, 2}, {3, 4, 5}});
    private static final Triangle TRIANGLE_4 = new Triangle(new int [][] {{0}, {0, 0}, {0, 0, 0}});

    @Test
    public void equals_implementation_should_be_reflexive() {
        assertTrue(TRIANGLE_1.equals(TRIANGLE_1));
    }

    @Test
    public void equals_implementation_should_be_symmetric() {
        assertTrue(TRIANGLE_1.equals(TRIANGLE_2));
        assertTrue(TRIANGLE_2.equals(TRIANGLE_1));
        assertFalse(TRIANGLE_1.equals(TRIANGLE_4));
        assertFalse(TRIANGLE_4.equals(TRIANGLE_1));
    }

    @Test
    public void equals_implementation_should_be_transitive() {
        assertTrue(TRIANGLE_1.equals(TRIANGLE_2));
        assertTrue(TRIANGLE_2.equals(TRIANGLE_3));
        assertTrue(TRIANGLE_1.equals(TRIANGLE_3));
    }

    @Test
    public void hashCode_implementation_should_return_same_value_for_instances_that_are_equal_according_to_equals() {
         if (TRIANGLE_1.equals(TRIANGLE_2)) {
             assertEquals(TRIANGLE_1.hashCode(), TRIANGLE_2.hashCode());
         }
    }

    @Test
    public void should_return_size_of_triangle() {
        assertEquals(TRIANGLE_1.getSize(), 3);
    }

    @Test
    public void should_return_element_from_triangle_at_specified_position() {
        assertEquals(TRIANGLE_1.getElementAtPosition(0, 0), 0);
        assertEquals(TRIANGLE_1.getElementAtPosition(1, 0), 1);
        assertEquals(TRIANGLE_1.getElementAtPosition(1, 1), 2);
        assertEquals(TRIANGLE_1.getElementAtPosition(2, 0), 3);
        assertEquals(TRIANGLE_1.getElementAtPosition(2, 1), 4);
        assertEquals(TRIANGLE_1.getElementAtPosition(2, 2), 5);
    }

    @Test
    public void should_throw_exception_when_row_index_is_out_of_range() {
        try {
            TRIANGLE_1.getElementAtPosition(-1, 1);
            fail("Should fail on incorrect row index");
        } catch (IndexOutOfBoundsException ex) {
            // expected
        }
        try {
            TRIANGLE_1.getElementAtPosition(3, 1);
            fail("Should fail on incorrect row index");
        } catch (IndexOutOfBoundsException ex) {
            // expected
        }

    }
}
