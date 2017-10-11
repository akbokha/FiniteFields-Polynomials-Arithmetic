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
    
    /** private PolynomialModP takeMod (PolynomialModP poly)
     *  needed for other operations
     */
    // 
    
    // 2.4 first bullet point
    
    // public *toBeDecidedFormat/Collection* getAdditionTable() 
    
    // public *toBeDecidedFormat/Collection* getMultiplicationTable()
    
    // 2.4 second bullet point
    
    // public PolynomialModP sumElements(PolynomialModP a, PolynomialModP)
    
    // public PolynomialModP productElements(PolynomialModP a, PolynomialModP)
    
    /**  private PolynomialModP inversePoly(PolynomialModP)
     *   needed for other operations
     *   return null object if the inverse does not exist
     */
    
    // 2.4 third bullet point
    
    // One of the following options:
   
    // check primitivity of a field element / find primitive elements
    
    // test irreducibility of a polynomial mod p + produce irreducible polynomials of a prescribed degree
    
    
    
}
