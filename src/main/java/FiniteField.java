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
    
    /** private main.java.PolynomialModP takeMod (main.java.PolynomialModP poly)
     *  needed for other operations
     */
    // 
    
    // 2.4 first bullet point
    
    // public *toBeDecidedFormat/Collection* getAdditionTable() 
    
    // public *toBeDecidedFormat/Collection* getMultiplicationTable()
    
    // 2.4 second bullet point
    
    // public main.java.PolynomialModP sumElements(main.java.PolynomialModP a, main.java.PolynomialModP)
    
    // public main.java.PolynomialModP productElements(main.java.PolynomialModP a, main.java.PolynomialModP)
    
    /**  private main.java.PolynomialModP inversePoly(main.java.PolynomialModP)
     *   needed for other operations
     *   return null object if the inverse does not exist
     */
    
    // 2.4 thrid bullet point
    
    // One of the following options:
   
    // check primitivity of a field element / find primitive elements
    
    // test irreducibility of a polynomial mod p + produce irreducible polynomials of a prescribed degree
    
    
    
}
