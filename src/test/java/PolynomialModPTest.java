import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PolynomialModPTest {
    @Test
    public void removeLC0() throws Exception {
        int mod1 = 3;

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(6);
        list1.add(0);
        PolynomialModP poly1 = new PolynomialModP(list1, mod1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(9);
        list2.add(12);
        PolynomialModP poly2 = new PolynomialModP(list2, mod1);

        assertEquals(poly1, poly2);

        list2.add(11);
        PolynomialModP poly3 = new PolynomialModP(list2, mod1);

        assertNotEquals(poly1, poly3);
    }

    @Test
    public void negate() throws Exception {
        int mod1 = 3;

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(4);
        list1.add(7);
        PolynomialModP poly1 = new PolynomialModP(list1, mod1);

        ArrayList<Integer> list1Neg = new ArrayList<>();
        list1Neg.add(-1);
        list1Neg.add(-3);
        list1Neg.add(-4);
        list1Neg.add(-7);
        PolynomialModP poly1Neg = new PolynomialModP(list1Neg, mod1);

        assertEquals(poly1, poly1Neg.negate());

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1345);
        list2.add(2786);
        list2.add(45620);
        list2.add(745810);
        PolynomialModP poly2 = new PolynomialModP(list2, mod1);

        ArrayList<Integer> list2Neg = new ArrayList<>();
        list2Neg.add(-1345);
        list2Neg.add(-2786);
        list2Neg.add(-45620);
        list2Neg.add(-745810);
        PolynomialModP poly2Neg = new PolynomialModP(list2Neg, mod1);

        assertEquals(poly2, poly2Neg.negate());

        assertNotEquals(poly1, poly2);
    }

    @Test
    public void scalarMultiple() throws Exception {
        int mod1 = 3;
        int multiplier = 6;

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(4);
        list1.add(7);
        PolynomialModP poly1 = new PolynomialModP(list1, mod1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(multiplier * 1);
        list2.add(multiplier * 3);
        list2.add(multiplier * 4);
        list2.add(multiplier * 7);
        PolynomialModP poly2 = new PolynomialModP(list2, mod1);

        assertEquals(poly1.scalarMultiple(multiplier), poly2);
    }
    
    @Test
    public void intModP() throws Exception {
        int modP = 4;
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(-1);
        IntegerModP minusInteger = new IntegerModP(-1, modP);
        assertEquals(3, minusInteger.getNumber());  
    }
    
    @Test
    public void intModP2() throws Exception {
        int modP = 4;
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        IntegerModP minusInteger = new IntegerModP(1, modP);
        assertEquals(1, minusInteger.getNumber());  
    }

    /*@Test
    public void longDivision() throws Exception {
        int mod1 = 3;

        ArrayList<Integer> toTestList1 = new ArrayList<>();
        toTestList1.add(-3);
        toTestList1.add(10);
        toTestList1.add(-5);
        toTestList1.add(3);
        PolynomialModP toTestPoly1 = new PolynomialModP(toTestList1, mod1);

        ArrayList<Integer> toTestList2 = new ArrayList<>();
        toTestList2.add(1);
        toTestList2.add(3);
        PolynomialModP toTestPoly2 = new PolynomialModP(toTestList2, mod1);

        ArrayList<Integer> expectedRemainderList1 = new ArrayList<>();
        expectedRemainderList1.add(4);
        expectedRemainderList1.add(-2);
        expectedRemainderList1.add(1);
        PolynomialModP expectedRemainderPoly = new PolynomialModP(expectedRemainderList1, mod1);

        ArrayList<Integer> expectedQuotientList1 = new ArrayList<>();
        expectedQuotientList1.add(-7);
        PolynomialModP expectedQuotientPoly = new PolynomialModP(expectedQuotientList1, mod1);

        assertEquals(toTestPoly1.longDivision(toTestPoly2)[0], expectedQuotientPoly);
        assertEquals(toTestPoly1.longDivision(toTestPoly2)[1], expectedRemainderPoly);
    }*/
  
}
