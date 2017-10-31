package com.robby.others;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Robby
 */
public class MyMathTest {

    private MyMath math;

    public MyMathTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        math = new MyMath();
    }

    @After
    public void tearDown() {
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Test of add method, of class MyMath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAdd01() throws Exception {
        System.out.println("Test Case 01");
        Integer value1 = 0;
        Integer value2 = 0;
        Integer expResult = 0;
        Integer result = math.add(value1, value2);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class MyMath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAdd02() throws Exception {
        System.out.println("Test Case 02");
        Integer value1 = 1_005;
        Integer value2 = 1_577;
        Integer expResult = 2_582;
        Integer result = math.add(value1, value2);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class MyMath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAdd03() throws Exception {
        System.out.println("Test Case 03");
        Integer value1 = 2_000_000_000;
        Integer value2 = 1_000_000_000;
        expectedException.expect(Exception.class);
        Integer result = math.add(value1, value2);
    }

    /**
     * Test of add method, of class MyMath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAdd04() throws Exception {
        System.out.println("Test Case 04");
        Integer value1 = -5_000;
        Integer value2 = 2_500;
        Integer expResult = 2_500;
        Integer result = math.add(value1, value2);
        assertEquals(expResult, result);
    }
}
