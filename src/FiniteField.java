import java.util.ArrayList;

/**
 *
 * @author Abdel K. Bokharouss
 */
public class FiniteField {
    
    private int modP;
    private PolynomialModP polynomial;
    ArrayList<PolynomialModP> elements;
    
    public FiniteField(PolynomialModP poly, int modP){
        this.polynomial = poly;
        this.modP = modP;
        findElements();
    }
    
    private void findElements() {
        ArrayList field_elements = new ArrayList<>();
        // find elements and add them to field_elements
        this.elements = field_elements;
    }
    
    
    
}
