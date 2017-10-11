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
        System.out.println(polyl.toString());
        System.out.println(poly1Neg.toString());
        assertEquals(polyl.negate(), poly1Neg);
    }

    @Test
    public void product() throws Exception {
    }

    @Test
    public void longDivision() throws Exception {
    }

}
