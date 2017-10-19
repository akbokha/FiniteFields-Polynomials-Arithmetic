import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
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
    
    /**
     * Produce an irreducible polynomial of a specified degree in the context of this finite field
     * @param deg the degree of the irreducible polynomial we want
     * @return a polynomial p of degree n such that isIrreducible(p)
     * @throws CloneNotSupportedException 
     */
    public PolynomialModP produceIrreduciblePoly(int deg) throws CloneNotSupportedException {
        Random random = new Random();
        
        // produce a random polynomial of degree deg
        PolynomialModP irrPoly = null;
        
        ArrayList<Integer> coefficients = new ArrayList<>();
        for (int i = 0; i <= deg; i++) {
            if (i == deg) { // if leading coefficient would become 0, then degree would be deg - 1 
                int coeff = random.nextInt(modP);
                if (coeff == 0) {
                    coeff++;
                }
                coefficients.add(coeff);
            }
            coefficients.add(random.nextInt(modP)); 
        }
        irrPoly = new PolynomialModP(coefficients, modP);
        
        // if this polynomial is not irreducbile, redo process until we find an irreducible polynomial of the specified degree 
        while (! isIrreducible(irrPoly)) {
            ArrayList<Integer> newCoefficients = new ArrayList<>();
            for (int i = 0; i <= deg; i++) {
                if (i == deg) { // if leading coefficient would become 0, then degree would be deg - 1 
                    int coeff = random.nextInt(modP);
                    if (coeff == 0) {
                        coeff++;
                    }
                    newCoefficients.add(coeff);
                }
            newCoefficients.add(random.nextInt(modP)); 
            }
            irrPoly = new PolynomialModP(newCoefficients, modP);
        }
        return irrPoly;
    }

    public PolynomialModP getPolynomial() {
        return this.polynomial;
    }

    public int getMod() {
        return this.modP;
    }

    public FiniteField[][] mulTable(ArrayList<Integer> poly, int degree) throws CloneNotSupportedException {
        FiniteField f = (FiniteField) this.clone();
        if(f == null){
            throw new IllegalArgumentException("The polynomials cannot be null.");
        }
        int modulus = f.getMod();
        ArrayList<PolynomialModP> elements = f.findElements();
        PolynomialModP modPolynomial = new PolynomialModP(poly, modulus);

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

        //multiplication table
        for (int i = 1; i < order; i++) {
            for (int j = 1; j < order; j++) {
                ArrayList<FiniteField> fieldElement = new ArrayList<>();
                PolynomialModP product = elements.get(i).product(elements.get(j));
                fieldElement.add(new FiniteField(product, modulus));

                FiniteField productField = new FiniteField(product, modulus);
                FiniteField modField = new FiniteField(modPolynomial, modulus);

                PolynomialModP finalResultP = modField.takeMod(product);

                fieldElement.set(0, new FiniteField(finalResultP, modulus));
                result[i][j] = fieldElement.get(0);
            }
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j].getPolynomial());
                System.out.print("\t\t");
            }
            System.out.println();
        }
        return result;
    }

    public FiniteField[][] addTable (ArrayList<Integer> poly, int degree) throws CloneNotSupportedException {
        FiniteField f = (FiniteField) this.clone();
        if(f == null){
            throw new IllegalArgumentException("The polynomials cannot be null.");
        }
        int modulus = f.getMod();
        ArrayList<PolynomialModP> elements = f.findElements();
        PolynomialModP modPolynomial = new PolynomialModP(poly, modulus);

        int order = (int) Math.pow(modulus, degree);
        FiniteField[][] result = new FiniteField[order][order];

        //addition table
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                ArrayList<FiniteField> fieldElement = new ArrayList<>();
                PolynomialModP sum = elements.get(i).sum(elements.get(j));
                fieldElement.add(new FiniteField(sum, modulus));

                FiniteField sumField = new FiniteField(sum, modulus);
                FiniteField modField = new FiniteField(modPolynomial, modulus);

                PolynomialModP finalResultP = modField.takeMod(sum);

                fieldElement.set(0, new FiniteField(finalResultP, modulus));
                result[i][j] = fieldElement.get(0);
            }
        }

        return result;
    }
}
