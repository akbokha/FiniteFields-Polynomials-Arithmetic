import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PolynomialModPTest {
    @Test
    public void negate() throws Exception {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list1Neg = new ArrayList<>();
        list1.add(1);
        list1Neg.add(-1);
        list1.add(5);
        list1Neg.add(-5);
        list1.add(9);
        list1Neg.add(-9);
        PolynomialModP polyl = new PolynomialModP(list1, 3);
        PolynomialModP poly1Neg = new PolynomialModP(list1Neg, 3);
        assertEquals(polyl.negate(), poly1Neg);
        System.out.println(poly1Neg.toString());


        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list2Neg = new ArrayList<>();
        list2.add(5732);
        list2Neg.add(-5732);
        list2.add(982);
        list2Neg.add(-982);
        list2.add(1356);
        list2Neg.add(-1356);
        PolynomialModP poly2 = new PolynomialModP(list2, 33);
        PolynomialModP poly2Neg = new PolynomialModP(list2Neg, 33);
        assertEquals(poly2.negate(), poly2Neg);

        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<Integer> list3Neg = new ArrayList<>();
        list3.add(2);
        list3Neg.add(-2);
        list3.add(1);
        list3Neg.add(-1);
        list3.add(0);
        list3Neg.add(-0);
        PolynomialModP poly3 = new PolynomialModP(list3, 1);
        PolynomialModP poly3Neg = new PolynomialModP(list3Neg, 1);
        assertEquals(poly3.negate(), poly3Neg);
        assertNotEquals(poly3, poly3Neg);
        assertNotEquals(poly2, poly3Neg);
    }

    @Test
    public void product() throws Exception {
    }

    @Test
    public void longDivision() throws Exception {
    }

}
