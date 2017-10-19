import javafx.util.Pair;

import java.util.ArrayList;
/**
 * ADT that represents a polynomial with a modulus
 *
 * @author Abdel K. Bokharouss
 * @author Bart van Helvert
 * @author Remco Surtel
 */
public class PolynomialModP implements Cloneable {
    /**
     * The mod prime of the polynomial
     */
    private final int modPrime;

    /**
     * The terms of the polynomial. The degree of the terms are represented by its index.
     */
    private ArrayList<IntegerModP> terms;

    
    public PolynomialModP(PolynomialModP copy, int modPrime, int degree_difference) {
        this.modPrime = modPrime;
        ArrayList copyList = new ArrayList<>();
        for (IntegerModP intmodp : copy.getTerms()) {
            copyList.add(intmodp);
        }
        for (int i = 0; i < degree_difference; i++) {
            copyList.add(new IntegerModP(0, modPrime));
        }
        this.terms = copyList;
    }

    /**
     * Creates a new polynomial
     *
     * @param terms     The terms of the polynomial with index as degree
     * @param modPrime  The modulus associated with the polynomial
     */
    public PolynomialModP(ArrayList<Integer> terms, int modPrime, boolean removeLeading0s) {
        this.modPrime = modPrime;
        this.terms = takeMod(terms, modPrime, removeLeading0s);
    }

    public PolynomialModP(ArrayList<Integer> terms, int modPrime) {
        this.modPrime = modPrime;
        this.terms = takeMod(terms, modPrime, true);
    }

    /*public PolynomialModP(ArrayList<Integer> terms, int modPrime, int unneededArgument) {
        this.modPrime = modPrime;
        for(int i = this.terms.size() - 1; i > 0; i--){
            if( this.terms.get(i) != 0 ){
                break;
            }
            else{
                this.terms.remove(i);
            }
        }
        this.terms = takeMod2(terms, modPrime);
    }*/

    /**
     * Takes the modulus of all coefficients of an integer list and stores them in a {@Link IntegerModP}
     *
     * @param terms The terms to take the modulus of
     * @param mod   The modulus to mod the values with
     * @return A list of terms with a modulus
     */
    public ArrayList<IntegerModP> takeMod(ArrayList<Integer> terms, int mod, boolean removeLeading0s) {
        ArrayList<IntegerModP> coeffModP = new ArrayList<>();
        for (int i : terms) {
            coeffModP.add(new IntegerModP(i, mod));
        }
        if(removeLeading0s){ removeLC0(coeffModP); }
        return coeffModP;
    }

    /**
     * Removes all leading coefficients with a coefficient of 0
     *
     * @param coeffModP The terms to remove the coefficients of
     */
    public void removeLC0(ArrayList<IntegerModP> coeffModP) {
        if(coeffModP.size() == 0) {
            return;
        }
        if(coeffModP.get(coeffModP.size()-1).getNumber() == 0) {
            coeffModP.remove(coeffModP.size()-1);
            removeLC0(coeffModP);
        }
    }

    /**
     * Negates this polynomial
     *
     * @return {@code this} negated
     * @throws CloneNotSupportedException when {@code this} is not cloneable
     */
    public PolynomialModP negate() throws CloneNotSupportedException {
        PolynomialModP poly = (PolynomialModP) this.clone();
        for(IntegerModP i : poly.terms) {
            i.setNumber(i.getNumber() * -1);
        }
        return poly;
    }
    
    public PolynomialModP sum(PolynomialModP added) {
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), added.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(added, modPrime, (getDegree() - added.getDegree()));
        } else {
            a = new PolynomialModP(this, modPrime, (added.getDegree() - getDegree()));
            b = added;
        }
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, (a.getTerms().get(i).add(b.getTerms().get(i))).getNumber());
        }
        return new PolynomialModP(result, modPrime, true);
    }
    
    public PolynomialModP difference(PolynomialModP poly) {
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), poly.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(poly, modPrime, (getDegree() - poly.getDegree()));
        } else {
            a = new PolynomialModP(this, modPrime, (poly.getDegree() - getDegree()));
            b = poly;
        }
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, a.getTerms().get(i).subtract(b.getTerms().get(i)).getNumber());
        }
        return new PolynomialModP(result, modPrime, true);
    }
    
    public PolynomialModP product(PolynomialModP poly) {
        int [] new_coefficients = new int[2* (this.terms.size() + poly.getTerms().size()) + 1];
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), poly.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(poly, modPrime, (getDegree() - poly.getDegree()));
        } else {
            a = new PolynomialModP(this, modPrime, (poly.getDegree() - getDegree()));
            b = poly;
        }
        ArrayList result = new ArrayList<>();
        int newDegree = 0;
        for (int i = 0; i < a.getTerms().size(); i++) {
            for(int j = 0; j < b.getTerms().size(); j++) {
                int degree = i + j;
                if (degree > newDegree) {
                    newDegree = degree;
                }
                int coeff = a.getTerms().get(i).getNumber() * b.getTerms().get(j).getNumber();
                new_coefficients[degree] = coeff + new_coefficients[degree];
            }
        }
        for (int k = 0; k <= newDegree; k++) {
            result.add(k, new_coefficients[k]);
        }
        return new PolynomialModP(result, modPrime, true);
    }

    /**
     * Multiplies a polynomial with a number
     *
     * @param number the number to multiple
     * @return {@code this*number}
     * @throws CloneNotSupportedException when {@code this} is not cloneable
     */
    public PolynomialModP product(int number) throws CloneNotSupportedException {
        ArrayList<Integer> list = new ArrayList<>();
        for(IntegerModP i : terms) {
            list.add(i.getNumber() * number);
        }
        return new PolynomialModP(list, modPrime, true);
    }

    /**
     * Does long division on 2 polynomials
     *
     * @param b polynomial to do longdivision upon
     * @return a quotient and remainder
     * @throws IllegalArgumentException when {@code b == null}
     * @throws CloneNotSupportedException when {@code b} is not cloneable
     */
    public PolynomialModP[] longDivision(PolynomialModP b) throws IllegalArgumentException, CloneNotSupportedException {
        if(b == null) {
            throw new IllegalArgumentException("The polynomial parameter must not be null");
        }
        PolynomialModP q = new PolynomialModP(new ArrayList<Integer>(), modPrime, true);
        PolynomialModP r = (PolynomialModP) this.clone();
        while(r.getDegree() >= b.getDegree()) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < r.getDegree() - b.getDegree(); i++) {
                list.add(0);
            }
            list.add(1);
            PolynomialModP xrminb = new PolynomialModP(list, modPrime, true);
            int lcrDIVlcb = r.terms.get(r.terms.size()-1).getNumber() / b.terms.get(b.terms.size()-1).getNumber();
            q = q.sum(xrminb.product(lcrDIVlcb));
            r = r.sum((xrminb.product(lcrDIVlcb)).product(b).negate());
        }
        return new PolynomialModP[]{q, r};
    }

    @Override
    public String toString() {
        PolynomialModP poly = new PolynomialModP(this.getTermsInt(), modPrime, true);
        StringBuilder str = new StringBuilder("");
        if(poly.getTerms().size() != 0) {
            str.append(termToString(poly.getTerms().get(poly.getTerms().size() - 1).getNumber(), poly.getTerms().size() - 1, true));
            for (int i = poly.getTerms().size() - 2; i >= 0; i--) {
                str.append(termToString(poly.getTerms().get(i).getNumber(), i, false));
            }
        }
        else{ return "0"; }

        return str.toString();
    }

    /**
     * Makes a string of a term in the form of ax^b
     *
     * @param coef  The coefficient of the term
     * @param pow   The power of the term
     * @return A string that represents this term
     */
    public String termToString(int coef, int pow, boolean first) {
        if(coef == 0) {
            return "";
        }else if(coef == 1) {
            if(first) {
                if(pow == 0) {
                    return "1";
                } else {
                    return termToNoCoefString(pow);
                }
            } else {
                if(pow == 0) {
                    return "+1";
                } else {
                    return "+" + termToNoCoefString(pow);
                }
            }
        } else if (coef == -1) {
            if(pow == 0) {
                return "-1";
            } else {
                return "-" + termToNoCoefString(pow);
            }
        } else {
            if(coef > 0 && !first) {
                return "+" + coef + termToNoCoefString(pow);
            } else {
                return coef + termToNoCoefString(pow);
            }

        }
    }

    /**
     * Makes a string of a term without the coefficient. The input has a for of x^b
     *
     * @param pow   The power of the term
     * @return A string that represents the term without the coefficient
     */
    public String termToNoCoefString(int pow) {
        if(pow == 0) {
            return "";
        } else if(pow == 1) {
            return "X";
        } else {
            return "X^" + pow;
        }
    }

    @Override
    public boolean equals(Object o) {
        PolynomialModP that = (PolynomialModP) o;
        if(modPrime != that.getModPrime() || !terms.equals(that.getTerms()) || getDegree() != that.getDegree()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = modPrime;
        result = 31 * result + terms.hashCode();
        return result;
    }

    /**
     * Gets the terms of the polynomial
     *
     * @return The terms of the polynomial
     */
    public ArrayList<IntegerModP> getTerms() {
        return this.terms;
    }

    public ArrayList<Integer> getTermsInt() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < terms.size(); i++){
            list.add(terms.get(i).getNumber());
        }
        return list;
    }

    /**
     * Gets the degree of the polynomial (highest power)
     *
     * @return The degree of the polynomial
     */
    public int getDegree() {
        return terms.size() - 1;
    }

    /**
     * Gets the prime mod of the polynomial
     *
     * @return The prime mod of the polynomial
     */
    public int getModPrime() {
        return this.modPrime;
    }

    /**
     * Given two polynomials mod p, a and b, this algorithm outputs two polynomials x and y with gcd(a, b) = xa + yb.
     * See algorithm 1.2.11 from lecture notes.
     *
     * @param b Polynomial mod p
     * @throws IllegalArgumentException if {@code a == null || b == null || a.modP != b.modP}
     * @return Polynomials x, y, such that gcd(a, b) = xa + yb
     */
    public ArrayList<PolynomialModP> ExtEuclid(PolynomialModP b) throws CloneNotSupportedException {

        PolynomialModP a = (PolynomialModP) this.clone();

        if(a == null || b == null){
            throw new IllegalArgumentException("The polynomials cannot be null.");
        }
        else if(a.getModPrime() != b.getModPrime()){
            throw new IllegalArgumentException("The polynomials must have the same modulus.");
        }

        ArrayList<Integer> l = new ArrayList();
        l.add(0,1);
        PolynomialModP x = new PolynomialModP(l, a.getModPrime(), true);
        PolynomialModP v = new PolynomialModP(l, a.getModPrime(), true);
        l.set(0,0);
        PolynomialModP y = new PolynomialModP(l, a.getModPrime(), true);
        PolynomialModP u = new PolynomialModP(l, a.getModPrime(),true);
        PolynomialModP zero = new PolynomialModP(l, a.getModPrime(), true);

        while(!(b.equals(zero))){
            PolynomialModP[] longDiv = a.longDivision(b); //index 0 is quotient, index 1 is remainder
            PolynomialModP q = longDiv[0]; // q = quot(a,b)
            a = (PolynomialModP) b.clone();
            b = longDiv[1]; // b = rem(a,b)
            PolynomialModP x2 = (PolynomialModP) x.clone();
            PolynomialModP y2 = (PolynomialModP) y.clone();
            x = (PolynomialModP) u.clone();
            y = (PolynomialModP) v.clone();
            u = x2.difference(q.product(u));
            v = y2.difference(q.product(v));
        }

        ArrayList<PolynomialModP> result = new ArrayList<>();
        result.add(0, x);
        result.add(1, y);
        return result;

    }
    
    public PolynomialModP polyGCD (PolynomialModP y) throws CloneNotSupportedException {
        PolynomialModP x = this;
        ArrayList<PolynomialModP> result = x.ExtEuclid(y);
        PolynomialModP a = result.get(0);
        PolynomialModP b = result.get(1);
        // return gcd(x, y) = ax + by
        return (a.product(x)).sum(b.product(y));
    }

    public int Euclid(PolynomialModP a) throws IllegalArgumentException, CloneNotSupportedException {
        return 0;
    }

    public PolynomialModP getClone() throws CloneNotSupportedException {
        PolynomialModP poly = (PolynomialModP) this.clone();
        return poly;
    }
}
