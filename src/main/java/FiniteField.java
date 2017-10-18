import java.util.ArrayList;
/**
 *
 * @author Abdel K. Bokharouss
 * @author Remco Surtel
 */
public class FiniteField implements Cloneable {
    
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
    
    public PolynomialModP takeMod(PolynomialModP poly){
        PolynomialModP[] longdivResult = null;
        try {
            longdivResult = poly.longDivision(polynomial);
        } catch (Exception ex) {
            System.err.println("IllegalArgumentException: " + ex.getMessage());
        }
        return longdivResult[1]; // return remainder
    }
    
    public PolynomialModP inverse(PolynomialModP poly) {
        return null;
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

    public int getMod() {
        return modP;
    }

    public PolynomialModP[][] AddMulTable (ArrayList<PolynomialModP> elements, PolynomialModP polMod, int degree) throws CloneNotSupportedException {
        FiniteField f = (FiniteField) this.clone();
        if(f == null){
            throw new IllegalArgumentException("The polynomials cannot be null.");
        }
        int modulus = f.getMod();
        //ArrayList<PolynomialModP> elements = field.findElements();

        int order = (int) Math.pow(modulus, degree);
        PolynomialModP[][] result = new PolynomialModP[order][order];

        //base case (0)
        for (int i = 0; i < order; i++) {
            ArrayList<Integer>  fieldElement = new ArrayList<>();
            fieldElement.add(0);
            result[i][0] = new PolynomialModP(fieldElement, modulus);
        }

        for (int j = 0; j < order; j++) {
            ArrayList<Integer>  fieldElement = new ArrayList<>();
            fieldElement.add(0);
            result[0][j] = new PolynomialModP(fieldElement, modulus);
        }
        /*
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                ArrayList<PolynomialModP> fieldElement = new ArrayList<>();
                fieldElement.add(elements.get(i).sum(elements.get(j)));
                result[i][j] = fieldElement.get(0);
            }
        }
        */
        //multiplication table
        for (int i = 1; i < order; i++) {
            for (int j = 1; j < order; j++) {
                ArrayList<FiniteField> fieldElement = new ArrayList<>();
                elements.get(i).product(elements.get(j))
                fieldElement.add();
                result[i][j] = fieldElement.get(0).takeMod(polMod);
            }
        }
        return result;
    }
}
