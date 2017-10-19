
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FiniteFieldTest {
    
    @Test
    public void noInverse() throws Exception {
        // Z/2Z[X]
        int modP = 2;
        // d = x^2 + 1
        ArrayList<Integer> coeff = new ArrayList<>();
        coeff.add(1);
        coeff.add(0);
        coeff.add(1);
        PolynomialModP d = new PolynomialModP(coeff, modP);
        
        // a = x + 1 (elment we try to find the inverse for
        ArrayList<Integer> coeff2 = new ArrayList<>();
        coeff2.add(1);
        coeff2.add(1);
        PolynomialModP a = new PolynomialModP(coeff2, modP);
        
        // considering finite field: Z/2Z[X]/(x^2+1)
        FiniteField field = new FiniteField(d, modP);
        
        // polynomial should have no inverse (indicated by returning null object) in in this finite field
        assertEquals(null, field.inverse(a));
    }
    
        @Test
    public void inverse() throws Exception {
        // example from math.stackexchange.com (question: 505580)
        // Z/2Z[X]
        int modP = 2;
        // d = x^8 + x^4+ x^3 + x + 1
        ArrayList<Integer> coeff = new ArrayList<>();
        coeff.add(1); // 1
        coeff.add(1); // x
        coeff.add(0);
        coeff.add(1); // x^3
        coeff.add(1); // x^4
        coeff.add(0);
        coeff.add(0);
        coeff.add(0);
        coeff.add(1); // x^8
        PolynomialModP d = new PolynomialModP(coeff, modP);
        
        // a = x^6 + x^4 + x + 1
        ArrayList<Integer> coeff2 = new ArrayList<>();
        coeff2.add(1); // 1
        coeff2.add(1); // x
        coeff2.add(0);
        coeff2.add(0);
        coeff2.add(1); // x^4
        coeff2.add(0);
        coeff2.add(1); // x^6
        PolynomialModP a = new PolynomialModP(coeff2, modP);
        
        // considering finite field: Z/2Z[X]/(x^8 + x^4+ x^3 + x + 1)
        FiniteField field = new FiniteField(d, modP);
        
        // inverse of a in Z/2Z[X]/(x^8 + x^4+ x^3 + x + 1) should be: (x^7 + x^6+ x^3 + x)
        ArrayList<Integer> coeff3 = new ArrayList<>();
        coeff3.add(0);
        coeff3.add(1); // x
        coeff3.add(0);
        coeff3.add(1); // x^3
        coeff3.add(0);
        coeff3.add(0);
        coeff3.add(1); // x^6
        coeff3.add(1); // x^7
        PolynomialModP inverse = new PolynomialModP(coeff3, modP);
        
        // polynomial should have no inverse (indicated by returning null object) in in this finite field
        assertEquals(inverse, field.inverse(a));
    }
    
    @Test
    public void product() throws Exception {
        // example from finite field arithmetic wiki (Rijndael's finite field)
        // Z/2Z[X]
        int modP = 2;
        // d = x^8 + x^4+ x^3 + x + 1
        ArrayList<Integer> coeff = new ArrayList<>();
        coeff.add(1); // 1
        coeff.add(1); // x
        coeff.add(0);
        coeff.add(1); // x^3
        coeff.add(1); // x^4
        coeff.add(0);
        coeff.add(0);
        coeff.add(0);
        coeff.add(1); // x^8
        PolynomialModP d = new PolynomialModP(coeff, modP);
        
        // considering finite field: Z/2Z[X]/(x^8 + x^4+ x^3 + x + 1)
        FiniteField field = new FiniteField(d, modP);
        
        // x = x^6 + x^4 + x + 1
        ArrayList<Integer> coeff2 = new ArrayList<>();
        coeff2.add(1); // 1
        coeff2.add(1); // x
        coeff2.add(0);
        coeff2.add(0);
        coeff2.add(1); // x^4
        coeff2.add(0);
        coeff2.add(1); // x^6
        PolynomialModP x = new PolynomialModP(coeff2, modP);
        
        // y = x^7 + x^6 + x^3 + x
        ArrayList<Integer> coeff3 = new ArrayList<>();
        coeff3.add(0);
        coeff3.add(1); // x
        coeff3.add(0);
        coeff3.add(1); // x^3
        coeff3.add(0);
        coeff3.add(0);
        coeff3.add(1); // x^6
        coeff3.add(1); // x^7
        PolynomialModP y = new PolynomialModP(coeff3, modP);
        
        // polynomial: x^0 (1)
        ArrayList<Integer> one = new ArrayList<>();
        one.add(1);
        PolynomialModP polyOne = new PolynomialModP(one, modP);
       
        // x * y mod d should result in the polyomial: x^0 (1)
        assertEquals(polyOne, field.product(x, y));
    }
    
    @Test
    public void quotient() throws Exception {
                // example from finite field arithmetic wiki (Rijndael's finite field)
        // Z/2Z[X]
        int modP = 2;
        // d = x^8 + x^4+ x^3 + x + 1
        ArrayList<Integer> coeff = new ArrayList<>();
        coeff.add(1); // 1
        coeff.add(1); // x
        coeff.add(0);
        coeff.add(1); // x^3
        coeff.add(1); // x^4
        coeff.add(0);
        coeff.add(0);
        coeff.add(0);
        coeff.add(1); // x^8
        PolynomialModP d = new PolynomialModP(coeff, modP);
        
        // considering finite field: Z/2Z[X]/(x^8 + x^4+ x^3 + x + 1)
        FiniteField field = new FiniteField(d, modP);
        
        // x = x^6 + x^4 + x + 1
        ArrayList<Integer> coeff2 = new ArrayList<>();
        coeff2.add(1); // 1
        coeff2.add(1); // x
        coeff2.add(0);
        coeff2.add(0);
        coeff2.add(1); // x^4
        coeff2.add(0);
        coeff2.add(1); // x^6
        PolynomialModP x = new PolynomialModP(coeff2, modP);
        
        // y = x^7 + x^6 + x^3 + x
        ArrayList<Integer> coeff3 = new ArrayList<>();
        coeff3.add(0);
        coeff3.add(1); // x
        coeff3.add(0);
        coeff3.add(1); // x^3
        coeff3.add(0);
        coeff3.add(0);
        coeff3.add(1); // x^6
        coeff3.add(1); // x^7
        PolynomialModP y = new PolynomialModP(coeff3, modP);
        
        // z  = polynomial: x^0 (1)
        ArrayList<Integer> one = new ArrayList<>();
        one.add(1);
        PolynomialModP z = new PolynomialModP(one, modP);
       
        // x * y mod d should result in the polyomial z: x^0 (1)
        // so z / y should result in x
        assertEquals(x, field.quotient(z, y));
    }

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

        //polynomial mod x^2 + x + 1
        ArrayList<Integer> polynomialModList = new ArrayList<>();
        polynomialModList.add(1);
        polynomialModList.add(1);
        polynomialModList.add(1);

        FiniteField[][] expResult = new FiniteField[4][4];
        for (int i = 0; i < 4; i++) {
            expResult[i][0] = element1;
        }
        for (int j = 0; j < 4; j++) {
            expResult[0][j] = element1;
        }

        expResult[1][1] = element2;
        expResult[1][2] = element3;
        expResult[1][3] = element4;
        expResult[2][1] = element3;
        expResult[2][2] = element4;
        expResult[2][3] = element2;
        expResult[3][1] = element4;
        expResult[3][2] = element2;
        expResult[3][3] = element3;


        ArrayList<PolynomialModP> allElements = new ArrayList<>();
        allElements.add(poly1);
        allElements.add(poly2);
        allElements.add(poly3);
        allElements.add(poly4);

        FiniteField[][] result = inputField.AddMulTable(allElements, polynomialModList,2);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                assertEquals(expResult[i][j].getPolynomial(), result[i][j].getPolynomial());
            }
        }
    }



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

    @Test
    public void findElements2() throws Exception{

        //construct finite field R = Z/2Z, f = x^3 + x + 1
        int p = 2;
        ArrayList<Integer> terms = new ArrayList<>();
        terms.add(1);
        terms.add(1);
        terms.add(0);
        terms.add(1);
        PolynomialModP poly = new PolynomialModP(terms, p, false);
        FiniteField field = new FiniteField(poly, p);
        System.out.println("Field: R = Z/2Z, f = x^3 + x + 1");

        //construct expected ArrayList of elements of the field
        ArrayList<PolynomialModP> field_elements = new ArrayList<>();
        ArrayList<Integer> coefficients = new ArrayList<>();
        //0
        coefficients.add(0);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        //1
        coefficients.set(0, 1);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        //x
        coefficients.set(0, 0);
        coefficients.add(1, 1);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        //x+1
        coefficients.set(0, 1);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        //x^2
        coefficients.set(0,0);
        coefficients.set(1,0);
        coefficients.add(1);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        //x^2+1
        coefficients.set(0,1);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        //x^2+x
        coefficients.set(0,0);
        coefficients.set(1,1);
        field_elements.add(new PolynomialModP(coefficients, p, false));
        //x^2+x+1
        coefficients.set(0,1);
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
