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
    public void product() throws Exception {
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

        assertEquals(poly1.product(multiplier), poly2);
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
        int mod1 = 11;

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
    
    @Test
    public void polyConstruction() throws Exception {
        int modP = 4;
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add(2);
        coefficients.add(7);
        coefficients.add(-3);
        PolynomialModP poly = new PolynomialModP(coefficients, modP);
        ArrayList<IntegerModP> coefficientsModP = new ArrayList<>();
        coefficientsModP.add(new IntegerModP(2, 4));
        coefficientsModP.add(new IntegerModP(7, 4));
        coefficientsModP.add(new IntegerModP(-3, 4));
        assertEquals(coefficientsModP, poly.getTerms());  
    }
    
    @Test
    public void polySum() throws Exception {
        int modP = 4;
        
        // - 3x^2 + 7x + 2 results in x^2 + 3x + 2
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add(2);
        coefficients.add(7);
        coefficients.add(-3);
        PolynomialModP poly1 = new PolynomialModP(coefficients, modP);
        
        // -9x + 6 results in 3x + 2
        ArrayList<Integer> coefficients2 = new ArrayList<>();
        coefficients2.add(6);
        coefficients2.add(-9);
        PolynomialModP poly2 = new PolynomialModP(coefficients2, modP);
        
        PolynomialModP polyResult = poly1.sum(poly2);
        // (x^2 + 3x + 2) + (3x + 2) should result in x^2 + 2x + 0
        ArrayList<IntegerModP> coefficientsModP = new ArrayList<>();
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(2, 4));
        coefficientsModP.add(new IntegerModP(1, 4));
        assertEquals(coefficientsModP, polyResult.getTerms());  
    }
    
    @Test
    public void polyDifference() throws Exception {
        int modP = 4;
        
        // - 3x^2 + 7x + 2 results in x^2 + 3x + 2
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add(2);
        coefficients.add(7);
        coefficients.add(-3);
        PolynomialModP poly1 = new PolynomialModP(coefficients, modP);
        
        // -9x + 6 results in 3x + 2
        ArrayList<Integer> coefficients2 = new ArrayList<>();
        coefficients2.add(6);
        coefficients2.add(-9);
        PolynomialModP poly2 = new PolynomialModP(coefficients2, modP);
        
        PolynomialModP polyResult = poly1.difference(poly2);
        // (x^2 + 3x + 2) - (3x + 2) should result in x^2 + 0x + 0
        ArrayList<IntegerModP> coefficientsModP = new ArrayList<>();
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(1, 4));
        assertEquals(coefficientsModP, polyResult.getTerms());  
    }
    
    @Test
    public void polyProduct() throws Exception {
        int modP = 4;
        
        // - 3x^2 + 7x + 2 results in x^2 + 3x + 2
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add(2);
        coefficients.add(7);
        coefficients.add(-3);
        PolynomialModP poly1 = new PolynomialModP(coefficients, modP);
        
        // -9x + 6 results in 3x + 2
        ArrayList<Integer> coefficients2 = new ArrayList<>();
        coefficients2.add(6);
        coefficients2.add(-9);
        PolynomialModP poly2 = new PolynomialModP(coefficients2, modP);
        
        PolynomialModP polyResult = poly1.product(poly2);
        // (x^2 + 3x + 2) * (3x + 2) should result in 0x^2 + 1x + 0
        // but it will actually result in 1x + 0, since we remove leading coefficients that are 0
        ArrayList<IntegerModP> coefficientsModP = new ArrayList<>();
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(1, 4));
        assertEquals(coefficientsModP, polyResult.getTerms());

        //3X
        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(0);
        list3.add(3);
        PolynomialModP poly3 = new PolynomialModP(list3, modP);
        System.out.println(poly3);

        //X^2
        ArrayList<Integer> list4 = new ArrayList<>();
        list4.add(0);
        list4.add(0);
        list4.add(1);
        PolynomialModP poly4 = new PolynomialModP(list4, modP);
        System.out.println(poly4);

        //X^2*3X = 3X^3
        ArrayList<Integer> list5 = new ArrayList<>();
        list5.add(0);
        list5.add(0);
        list5.add(0);
        list5.add(3);
        PolynomialModP poly5 = new PolynomialModP(list5, modP);
        assertEquals(poly5, poly3.product(poly4));
    }
  
}
