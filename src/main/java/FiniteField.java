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
    
    // needed for addition/multiplication table @Joris
    private ArrayList<PolynomialModP> findElements() {
        ArrayList field_elements = new ArrayList<>();
        // find elements and add them to field_elements
        return field_elements;
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
