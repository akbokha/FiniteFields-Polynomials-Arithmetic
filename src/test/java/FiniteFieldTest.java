import org.testng.annotations.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FiniteFieldTest {

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
