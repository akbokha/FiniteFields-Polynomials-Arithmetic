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
        PolynomialModP poly1 = new PolynomialModP(list1, mod1, true);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(9);
        list2.add(12);
        PolynomialModP poly2 = new PolynomialModP(list2, mod1, true);

        assertEquals(poly1, poly2);

        list2.add(11);
        PolynomialModP poly3 = new PolynomialModP(list2, mod1, true);

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
        PolynomialModP poly1 = new PolynomialModP(list1, mod1, true);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(multiplier * 1);
        list2.add(multiplier * 3);
        list2.add(multiplier * 4);
        list2.add(multiplier * 7);
        PolynomialModP poly2 = new PolynomialModP(list2, mod1, true);

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
    public void intModPSum() throws Exception {
        int modP = 4;
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(-1);
        IntegerModP zero = new IntegerModP(0, modP);
        IntegerModP three = new IntegerModP(3, modP);
        assertEquals(3, zero.add(three).getNumber());  
    }
    
    @Test
    public void intModP2() throws Exception {
        int modP = 4;
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        IntegerModP minusInteger = new IntegerModP(1, modP);
        assertEquals(1, minusInteger.getNumber());  
    }

    @Test
    public void longDivision() throws Exception {
        int mod1 = 2;

        ArrayList<Integer> toTestList1 = new ArrayList<>();
        toTestList1.add(1);
        toTestList1.add(0);
        toTestList1.add(0);
        toTestList1.add(1);
        PolynomialModP toTestPoly1 = new PolynomialModP(toTestList1, mod1, true);

        ArrayList<Integer> toTestList2 = new ArrayList<>();
        toTestList2.add(1);
        toTestList2.add(1);
        toTestList2.add(1);
        PolynomialModP toTestPoly2 = new PolynomialModP(toTestList2, mod1, true);

        ArrayList<Integer> expectedRemainderList1 = new ArrayList<>();
        expectedRemainderList1.add(0);
        PolynomialModP expectedRemainderPoly1 = new PolynomialModP(expectedRemainderList1, mod1, true);

        ArrayList<Integer> expectedQuotientList1 = new ArrayList<>();
        expectedQuotientList1.add(1);
        expectedQuotientList1.add(1);
        PolynomialModP expectedQuotientPoly1 = new PolynomialModP(expectedQuotientList1, mod1, true);

        assertEquals(toTestPoly1.longDivision(toTestPoly2)[0], expectedQuotientPoly1);
        assertEquals(toTestPoly1.longDivision(toTestPoly2)[1], expectedRemainderPoly1);

        int mod2 = 7;
        ArrayList<Integer> toTestList3 = new ArrayList<>();
        toTestList3.add(1);
        toTestList3.add(0);
        toTestList3.add(1);
        toTestList3.add(1);
        toTestList3.add(3);
        toTestList3.add(6);
        PolynomialModP toTestPoly3 = new PolynomialModP(toTestList3, mod2, true);

        ArrayList<Integer> toTestList4 = new ArrayList<>();
        toTestList4.add(6);
        toTestList4.add(5);
        toTestList4.add(3);
        PolynomialModP toTestPoly4 = new PolynomialModP(toTestList4, mod2, true);

        ArrayList<Integer> expectedRemainderList2 = new ArrayList<>();
        expectedRemainderList2.add(2);
        expectedRemainderList2.add(3);
        PolynomialModP expectedRemainderPoly2 = new PolynomialModP(expectedRemainderList2, mod2, true);

        ArrayList<Integer> expectedQuotientList2 = new ArrayList<>();
        expectedQuotientList2.add(1);
        expectedQuotientList2.add(1);
        expectedQuotientList2.add(0);
        expectedQuotientList2.add(2);
        PolynomialModP expectedQuotientPoly2 = new PolynomialModP(expectedQuotientList2, mod2, true);

        assertEquals(toTestPoly3.longDivision(toTestPoly4)[0], expectedQuotientPoly2);
        assertEquals(toTestPoly3.longDivision(toTestPoly4)[1], expectedRemainderPoly2);
    }
    
    @Test
    public void polyConstruction() throws Exception {
        int modP = 4;
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add(2);
        coefficients.add(7);
        coefficients.add(-3);
        PolynomialModP poly = new PolynomialModP(coefficients, modP, true);
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
        PolynomialModP poly1 = new PolynomialModP(coefficients, modP, true);
        
        // -9x + 6 results in 3x + 2
        ArrayList<Integer> coefficients2 = new ArrayList<>();
        coefficients2.add(6);
        coefficients2.add(-9);
        PolynomialModP poly2 = new PolynomialModP(coefficients2, modP, true);
        
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
        PolynomialModP poly1 = new PolynomialModP(coefficients, modP, true);
        
        // -9x + 6 results in 3x + 2
        ArrayList<Integer> coefficients2 = new ArrayList<>();
        coefficients2.add(6);
        coefficients2.add(-9);
        PolynomialModP poly2 = new PolynomialModP(coefficients2, modP, true);
        
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
        
        //3X
        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(0);
        list3.add(3);
        PolynomialModP poly3 = new PolynomialModP(list3, modP, true);

        //X^2
        ArrayList<Integer> list4 = new ArrayList<>();
        list4.add(0);
        list4.add(0);
        list4.add(1);
        PolynomialModP poly4 = new PolynomialModP(list4, modP, true);

        //X^2*3X = 3X^3
        ArrayList<Integer> list5 = new ArrayList<>();
        list5.add(0);
        list5.add(0);
        list5.add(0);
        list5.add(3);
        PolynomialModP poly5 = new PolynomialModP(list5, modP, true);
        assertEquals(poly5, poly3.product(poly4));
    }
    
    @Test
    public void polyProduct2() throws Exception {
        int modP = 4;
        
        // - 3x^2 + 7x + 2 results in x^2 + 3x + 2
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add(2);
        coefficients.add(7);
        coefficients.add(-3);
        PolynomialModP poly1 = new PolynomialModP(coefficients, modP, true);
        
        // -9x + 6 results in 3x + 2
        ArrayList<Integer> coefficients2 = new ArrayList<>();
        coefficients2.add(6);
        coefficients2.add(-9);
        PolynomialModP poly2 = new PolynomialModP(coefficients2, modP, true);
        
        PolynomialModP polyResult = poly1.product(poly2);
        // (x^2 + 3x + 2) * (3x + 2) should result in 3x^3 + 3x^2 + 0x + 0
        ArrayList<IntegerModP> coefficientsModP = new ArrayList<>();
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(3, 4));
        coefficientsModP.add(new IntegerModP(3, 4));
        assertEquals(coefficientsModP, polyResult.getTerms());
    }
    
        @Test
    public void polyProduct3() throws Exception {
        int modP = 4;
        
        // - 3x results in x
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add(0);
        coefficients.add(-3);
        PolynomialModP poly1 = new PolynomialModP(coefficients, modP, true);
        
        // -9x^3 + 4x - 6 results in 3x^3 + 2
        ArrayList<Integer> coefficients2 = new ArrayList<>();
        coefficients2.add(-6);
        coefficients2.add(4);
        coefficients2.add(0);
        coefficients2.add(-9);
        PolynomialModP poly2 = new PolynomialModP(coefficients2, modP, true);
        
        PolynomialModP polyResult = poly1.product(poly2);
        // (x) * (3x^3 + 2) should result in 3x^4 + 2x
        ArrayList<IntegerModP> coefficientsModP = new ArrayList<>();
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(2, 4));
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(0, 4));
        coefficientsModP.add(new IntegerModP(3, 4));
        assertEquals(coefficientsModP, polyResult.getTerms());
    }
    
    /*  ------------  ExtEuclid does not terminate -------------
    @Test
    public void polyGCD() throws Exception {
        // considering K[X}/(x^2+1)
        int modP = 10;
        
        // x^2+1
        ArrayList<Integer> coeff = new ArrayList<>();
        coeff.add(1);
        coeff.add(0);
        coeff.add(1);
        PolynomialModP d = new PolynomialModP(coeff, modP);
        
        // x^3
        ArrayList<Integer> coeff2 = new ArrayList<>();
        coeff2.add(0);
        coeff2.add(0);
        coeff2.add(0);
        coeff2.add(1);
        PolynomialModP a = new PolynomialModP(coeff2, modP);
        
        // 1
        ArrayList<Integer> coeff3 = new ArrayList<>();
        coeff3.add(1);
        PolynomialModP gcd = new PolynomialModP(coeff3, modP);
        assertEquals(gcd, a.polyGCD(d));
    }*/

    @Test
    public void ExtEuclid0() throws Exception {
        int modP = 10;

        // extended gcd(x^2, 2x^3 + x) --> gcd(x^2, 2x^3 + x) = x = 8x * x^2 + 1 * 2x^3 + x

        // a = x^2
        ArrayList<Integer> coefficientsA = new ArrayList<>();
        coefficientsA.add(0);
        coefficientsA.add(0);
        coefficientsA.add(1);
        PolynomialModP a = new PolynomialModP(coefficientsA, modP, true);

        // b = 2x^3 + x
        ArrayList<Integer> coefficientsB = new ArrayList<>();
        coefficientsB.add(0);
        coefficientsB.add(1);
        coefficientsB.add(0);
        coefficientsB.add(2);
        PolynomialModP b = new PolynomialModP(coefficientsB, modP, true);

        //expected answer x = 8x
        ArrayList<Integer> coefficientsX = new ArrayList<>();
        coefficientsX.add(0);
        coefficientsX.add(8);
        PolynomialModP expX = new PolynomialModP(coefficientsX, modP, true);

        //expected answer y = 1
        ArrayList<Integer> coefficientsY = new ArrayList<>();
        coefficientsY.add(1);
        PolynomialModP expY = new PolynomialModP(coefficientsY, modP, true);

        ArrayList<PolynomialModP> result = a.ExtEuclid(b);
        PolynomialModP x = result.get(0);
        PolynomialModP y = result.get(1);
        assertEquals(x, expX);
        assertEquals(y, expY);

    }

    @Test
    public void Euclid() throws Exception {
        int modP = 10;

        // gcd( a = x^2, b = x^2 + 2x^4) = x^2

        // a = x^2
        ArrayList<Integer> coefficientsA = new ArrayList<>();
        coefficientsA.add(0);
        coefficientsA.add(0);
        coefficientsA.add(1);
        PolynomialModP a = new PolynomialModP(coefficientsA, modP, true);
        System.out.println("Polynomial a: " + a.toString());

        // b = x^2 + 2x^4
        ArrayList<Integer> coefficientsB = new ArrayList<>();
        coefficientsB.add(0);
        coefficientsB.add(0);
        coefficientsB.add(1);
        coefficientsB.add(0);
        coefficientsB.add(2);
        PolynomialModP b = new PolynomialModP(coefficientsB, modP, true);

        // expected answer gcd = x^2
        ArrayList<Integer> coefficientsGCD = new ArrayList<>();
        coefficientsGCD.add(0);
        coefficientsGCD.add(0);
        coefficientsGCD.add(1);
        PolynomialModP expGCD = new PolynomialModP(coefficientsGCD, modP, true);

        // get result and compare to expected
        PolynomialModP result = a.Euclid(b);
        assertEquals(expGCD, result);

    }

    @Test
    public void congMod() throws CloneNotSupportedException {
        int mod = 3;
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(0);
        list1.add(0);
        list1.add(1);
        PolynomialModP pol1 = new PolynomialModP(list1, mod);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        PolynomialModP pol2 = new PolynomialModP(list2, mod);

        ArrayList<Integer> mod1 = new ArrayList<>();
        mod1.add(1);
        mod1.add(1);
        PolynomialModP pol3 = new PolynomialModP(mod1, mod);

        assert(pol1.isCongMod(pol2, pol3));
    }
            
}
