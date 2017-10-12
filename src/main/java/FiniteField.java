import java.util.ArrayList;

/**
 *
 * @author Abdel K. Bokharouss
 * @author Remco Surtel
 */
public class FiniteField {
    
    private int modP;
    private int expN;
    private PolynomialModP polynomial;
    ArrayList<PolynomialModP> elements;
    
    public FiniteField(PolynomialModP poly, int modP){
        this.polynomial = poly;
        this.modP = modP;
        findElements();
    }

    public FiniteField(int modP, int expN){
        this.modP = modP;
        this.expN = expN;
        findElements2();
    }

    private void findElements() {
        ArrayList field_elements = new ArrayList<>();
        // find elements and add them to field_elements
        this.elements = field_elements;
    }

    private void findElements2(){
        ArrayList field_elements = new ArrayList<>();
        //create a polynomial, and find elements to add to the field
        this.elements = field_elements;
    }
    
    // needed for 2.4.2
    
    public PolynomialModP takeMod(PolynomialModP poly) {
        return null;
    }
    
    public PolynomialModP inverse(PolynomialModP poly) {
        return null;
    }
    
    public PolynomialModP sum(PolynomialModP a, PolynomialModP b) {
        return null;
    }
    
    public PolynomialModP quotient(PolynomialModP a, PolynomialModP b) {
        return null;
    }
    
    public PolynomialModP product(PolynomialModP a, PolynomialModP b) {
        return null;
    }
    
    public boolean isIrreducible (PolynomialModP poly) {
        return false;
    }
    
    public PolynomialModP produceIrreduciblePoly(int deg) {
        return null;
    }
}
