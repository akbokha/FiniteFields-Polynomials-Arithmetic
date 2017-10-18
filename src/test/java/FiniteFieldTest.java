<<<<<<< HEAD
import org.junit.Assert;
=======
>>>>>>> origin/master
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FiniteFieldTest {
<<<<<<< HEAD
    @Test
    public void addMulTable() throws Exception {
        //test for Finite field f = x^2 + x + 1 in mod 2
        //there are four elements: 0,1,x,x+1
        int modP = 2;
        ArrayList<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(1);
        input.add(1);

        PolynomialModP inputP = new PolynomialModP(input, modP);
        FiniteField inputField = new FiniteField(inputP, modP);

        //0
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(0);
        PolynomialModP poly1 = new PolynomialModP(list1, modP);
        FiniteField element1 = new FiniteField(poly1, modP);

        //1
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        PolynomialModP poly2 = new PolynomialModP(list2, modP);
        FiniteField element2 = new FiniteField(poly2, modP);

        //x
        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(0);
        list3.add(1);
        PolynomialModP poly3 = new PolynomialModP(list3, modP);
        FiniteField element3 = new FiniteField(poly3, modP);

        //x + 1
        ArrayList<Integer> list4 = new ArrayList<>();
        list4.add(1);
        list4.add(1);
        PolynomialModP poly4 = new PolynomialModP(list4, modP);
        FiniteField element4 = new FiniteField(poly4, modP);


        PolynomialModP[][] expResult = new PolynomialModP[4][4];
        for (int i = 0; i < 4; i++) {
            expResult[i][0] = poly1;
        }
        for (int j = 0; j < 4; j++) {
            expResult[0][j] = poly1;
        }

        expResult[1][1] = poly2;
        expResult[1][2] = poly3;
        expResult[1][3] = poly4;
        expResult[2][1] = poly3;
        expResult[2][2] = poly4;
        expResult[2][3] = poly2;
        expResult[3][1] = poly4;
        expResult[3][2] = poly2;
        expResult[3][3] = poly3;


        ArrayList<PolynomialModP> allElements = new ArrayList<>();
        allElements.add(poly1);
        allElements.add(poly2);
        allElements.add(poly3);
        allElements.add(poly4);

        PolynomialModP[][] result = inputField.AddMulTable(allElements, 2);
        Assert.assertArrayEquals(expResult, result);
    }

}
=======

    @Test
    public void findElements() throws Exception{

        //construct finite field R = Z/2Z, f = x^2 + x + 1
        int p = 2;
        ArrayList<Integer> terms = new ArrayList<>();
        terms.add(1);
        terms.add(1);
        terms.add(1);
        PolynomialModP poly = new PolynomialModP(terms, p, false);
        FiniteField field = new FiniteField(poly, p);
        System.out.println("Field: R = Z/2Z, f = x^2 + x + 1");

        //construct expected ArrayList of elements of the field
        ArrayList<PolynomialModP> field_elements = new ArrayList<>();
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add(0);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        coefficients.set(0, 1);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        coefficients.set(0, 0);
        coefficients.add(1, 1);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        coefficients.set(0, 1);
        field_elements.add(new PolynomialModP(coefficients, p, false));

        //get result and compare to expected result
        ArrayList<PolynomialModP> result = field.findElements();
        for(int i = 0; i < field_elements.size(); i++){
            System.out.println("Expected element " + i + ": " + field_elements.get(i).toString());
            System.out.println("Result element " + i + ": " + result.get(i).toString());
            assertEquals(field_elements.get(i).toString(), result.get(i).toString());
        }

    }


}
>>>>>>> origin/master
