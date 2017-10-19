import java.lang.reflect.Array;
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
    
    public PolynomialModP quotient(PolynomialModP a, PolynomialModP b) throws CloneNotSupportedException {
        PolynomialModP result = a.product(inverse(b));
        return takeMod(result);
    }
    
    public PolynomialModP product(PolynomialModP a, PolynomialModP b) {
        PolynomialModP result = a.product(b);
        return takeMod(result);
    }
    
    /**
     * Check the reducibility of a polynomial in this finite field
     * @param poly the polynomial of which the reducibility is checked
     * @return true if poly is irreducible in this, false otherwise
     * @throws CloneNotSupportedException 
     */
    public boolean isIrreducible (PolynomialModP poly) throws CloneNotSupportedException {
        boolean isIrreducible = true;
        int n = poly.getDegree();
        ArrayList<PolynomialModP> fieldElements = findElements();
        
        // zero polynomial
        ArrayList<Integer> zero = new ArrayList<>();
        zero.add(0);
        PolynomialModP polyZero = new PolynomialModP(zero, modP);
        
        if (poly.getDegree() > 1) { // constants / linear polynomails are irreducible 
            for (PolynomialModP fieldElement : fieldElements) {
                if (fieldElement.getDegree() > 0) { // consider only non constant polynomials
                    if (poly.longDivision(poly)[1].equals(polyZero)) { // divisible (no remainder)
                        if (n != fieldElement.getDegree()) {
                            isIrreducible = false;
                            break;
                        }
                    }
                }
            }
        } else {
            isIrreducible = false;
        }
        return isIrreducible;
    }
    
    public PolynomialModP produceIrreduciblePoly(int deg) {
        return null;
    }

    public PolynomialModP getPolynomial() {
        return this.polynomial;
    }

    public int getMod() {
        return this.modP;
    }

    public FiniteField[][] AddMulTable (ArrayList<PolynomialModP> elements, ArrayList<Integer> poly, int degree) throws CloneNotSupportedException {
        FiniteField f = (FiniteField) this.clone();
        if(f == null){
            throw new IllegalArgumentException("The polynomials cannot be null.");
        }
        int modulus = f.getMod();
        //ArrayList<PolynomialModP> elements = f.findElements();
        PolynomialModP p = new PolynomialModP(poly, modulus);
        ArrayList<Integer> reversePoly = new ArrayList<>();
        for (int i = 0; i < poly.size() - 1; i++) {
            reversePoly.add(-poly.get(i));
        }
        PolynomialModP reverseP = new PolynomialModP(reversePoly, modulus);
        int order = (int) Math.pow(modulus, degree);
        FiniteField[][] result = new FiniteField[order][order];

        //base case (0)
        for (int i = 0; i < order; i++) {
            ArrayList<Integer>  fieldElement = new ArrayList<>();
            fieldElement.add(0);
            PolynomialModP field = new PolynomialModP(fieldElement, modulus);
            result[i][0] = new FiniteField(field, modulus);
        }

        for (int j = 0; j < order; j++) {
            ArrayList<Integer>  fieldElement = new ArrayList<>();
            fieldElement.add(0);
            PolynomialModP field = new PolynomialModP(fieldElement, modulus);
            result[0][j] = new FiniteField(field, modulus);
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
                PolynomialModP product = elements.get(i).product(elements.get(j));
                fieldElement.add(new FiniteField(product, modulus));
                //PolynomialModP pResult = fieldElement.get(0).takeMod(p);
                //result[i][j] = new FiniteField(pResult, modulus);
                ArrayList<Integer> minusMaxDegree = new ArrayList<>();
                for (int z = 0; z < p.getDegree()+1; z++) {
                    if (z == p.getDegree()) {
                        minusMaxDegree.add(poly.get(z));
                    } else {
                        minusMaxDegree.add(0);
                    }
                }
                PolynomialModP mmD = new PolynomialModP(minusMaxDegree, modulus);
                if (fieldElement.get(0).getPolynomial().getDegree() == p.getDegree()) {
                    for (int k = 0; k < reverseP.getDegree(); k++) {
                        FiniteField newResult = new FiniteField((fieldElement.get(0).getPolynomial().sum(reverseP)).difference(mmD), modulus);
                        fieldElement.set(0, newResult);
                    }
                }
                result[i][j] = fieldElement.get(0);
            }
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j].getPolynomial());
                System.out.print("\t");
                //System.out.println("element ["+i+"]["+j+"]"+result[i][j].elements);
            }
            System.out.println();
        }
        return result;
    }
}
