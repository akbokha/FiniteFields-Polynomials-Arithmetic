import java.util.ArrayList;
/**
 *
 * @author Abdel K. Bokharouss
 * @author Remco Surtel
 */
public class FiniteField {
    
    private int modP;
    private PolynomialModP polynomial;
    private ArrayList<PolynomialModP> elements;
    
    public FiniteField(PolynomialModP poly, int modP){
        this.polynomial = poly;
        this.modP = modP;
    }

    /**
     * Function to find all elements in a given field.
     *
     * @return field_elements: ArrayList of PolynomialModP's, that contains all elements of the given field.
     */
    public ArrayList<PolynomialModP> findElements() {

        FiniteField field = this; //given field

        ArrayList<PolynomialModP> field_elements = new ArrayList<>(); // initialize ArrayList to be returned
        ArrayList<Integer> terms = new ArrayList<>();
        for(int i = 0; i < field.polynomial.getDegree(); i++){terms.add(0);} // initialize ArrayList with 0s

        field_elements = generateElements(field, field_elements, terms, field.polynomial.getDegree() - 1);

        return field_elements;
    }

    public ArrayList<PolynomialModP> generateElements(FiniteField field, ArrayList<PolynomialModP> field_elements, ArrayList<Integer> terms, int index){

        for(int i = 0; i < field.modP; i++){ //for all possible numbers
            terms.set(index, i); //set the number at given index to i
            if(index == 0){ // if index = 0, add this polynomial to the elements
                field_elements.add(new PolynomialModP(terms, field.modP, false));
            }
            else{ // if index != 0, recurse
                field_elements = generateElements(field, field_elements, terms, index - 1);
            }
        }

        return field_elements; // return the final result

    }
    
    // needed for 2.4.2
    
    public PolynomialModP takeMod(PolynomialModP poly){
        PolynomialModP[] longdivResult = null;
        try {
            longdivResult = poly.longDivision(polynomial);
        } catch (Exception ex) {
            System.err.println("IllegalArgumentException: " + ex.getMessage());
        }
        return longdivResult[1]; // return remainder
    }
    
    public PolynomialModP inverse(PolynomialModP poly) throws CloneNotSupportedException {
        PolynomialModP result = null; // if there is no inverse, return null
        
        ArrayList<Integer> constant = new ArrayList<>();
        constant.add(1);
        PolynomialModP one = new PolynomialModP(constant, modP, true);
        
        // xa + yd = gcd(a,d) where a = poly and d = this.polynomial
        if(one.equals(poly.polyGCD(this.polynomial))) { // gcd (a,d) == 1
            result = poly.ExtEuclid(this.polynomial).get(0); // return x
        } // else: a = poly is not invertible
        return result;
    }
    
    public PolynomialModP sum(PolynomialModP a, PolynomialModP b) {
        PolynomialModP result = a.sum(b);
        return takeMod(result);
    }
    
    public PolynomialModP quotient(PolynomialModP a, PolynomialModP b) {
        return null;
    }
    
    public PolynomialModP product(PolynomialModP a, PolynomialModP b) {
        PolynomialModP result = a.product(b);
        return takeMod(result);
    }
    
    public boolean isIrreducible (PolynomialModP poly) {
        return false;
    }
    
    public PolynomialModP produceIrreduciblePoly(int deg) {
        return null;
    }
}
