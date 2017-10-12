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
    
    private final int modPrime;
    private ArrayList<IntegerModP> terms;
    private int degree;
    
    public PolynomialModP(PolynomialModP copy) {
        this.modPrime = copy.getModPrime();
        this.degree = copy.getDegree();
        ArrayList copyList = new ArrayList<>();
        for (IntegerModP intmodp : copy.getTerms()) {
            copyList.add(new IntegerModP(intmodp.getNumber(), modPrime));
        }
        this.terms = copyList;
    }
    
    public PolynomialModP(PolynomialModP copy, int modPrime, int degree_difference) {
        this.modPrime = modPrime;
        this.degree = copy.getDegree();
        ArrayList copyList = new ArrayList<>();
        for (IntegerModP intmodp : copy.getTerms()) {
            copyList.add(intmodp);
        }
        for (int i = 0; i <= degree_difference; i++) {
            copyList.add(0);
        }
        this.terms = copyList;
    }

    public PolynomialModP(ArrayList<Integer> terms, int modPrime) {
        this.modPrime = modPrime;
        this.terms = takeMod(terms);
        this.degree = terms.size() - 1;
    }
    
    private ArrayList<IntegerModP> takeMod(ArrayList<Integer> terms) {
        ArrayList<IntegerModP> coeffModP = new ArrayList<>();
        for (int i : terms) {
            coeffModP.add(new IntegerModP(i, modPrime));
        }
        return coeffModP;
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
            b = new PolynomialModP(added, modPrime, (degree - added.getDegree()));
        } else {
            a = new PolynomialModP(this, modPrime, (added.getDegree() - degree));
            b = added;
        }
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, a.getTerms().get(i).add(b.getTerms().get(i)));
        }
        return new PolynomialModP(result, modPrime);
    }
    
    public PolynomialModP scalarMultiple(int b) {
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < this.terms.size(); i++) {
            result.add(i, new IntegerModP((this.terms.get(i).getNumber() * b), modPrime));
        }
        return new PolynomialModP(result, modPrime);
    }
    
    public PolynomialModP difference(PolynomialModP poly) {
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), poly.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(poly, modPrime, (degree - poly.getDegree()));
        } else {
            a = new PolynomialModP(this, modPrime, (poly.getDegree() - degree));
            b = poly;
        }
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, a.getTerms().get(i).subtract(b.getTerms().get(i)));
        }
        return new PolynomialModP(result, modPrime);
    }
    
    public PolynomialModP product(PolynomialModP poly) {
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), poly.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(poly, modPrime, (degree - poly.getDegree()));
        } else {
            a = new PolynomialModP(this, modPrime, (poly.getDegree() - degree));
            b = poly;
        }
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, a.getTerms().get(i).multiply(b.getTerms().get(i)));
        }
        return new PolynomialModP(result, modPrime);
    }

    /**
     * Multiplies a polynomial with a number
     *
     * @param number the number to multiple
     * @return {@code this*number}
     * @throws CloneNotSupportedException when {@code this} is not cloneable
     */
    public PolynomialModP product(int number) throws CloneNotSupportedException {
        PolynomialModP poly = (PolynomialModP) this.clone();
        for(IntegerModP i : poly.terms) {
            i.setNumber(i.getNumber() * number);
        }
        return poly;
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
        PolynomialModP q = new PolynomialModP(new ArrayList<Integer>(), modPrime);
        PolynomialModP r = (PolynomialModP) this.clone();
        while(r.getDegree() >= b.getDegree()) {
            PolynomialModP xrminb = new PolynomialModP(new ArrayList<Integer>(b.degree - r.degree), modPrime);
            int lcrdivlcb = r.terms.get(r.terms.size()-1).getNumber() / b.terms.get(b.terms.size()-1).getNumber();
            q.sum(xrminb.product(lcrdivlcb));
            q.sum((xrminb.product(lcrdivlcb)).product(b).negate());
        }
        return new PolynomialModP[]{q, r};
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for(int i = terms.size()-1; i >=0; i--) {
            str.append(terms.get(i).getNumber() + "X^" + i);
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        PolynomialModP that = (PolynomialModP) o;
        if(modPrime != that.getModPrime() || !terms.equals(that.getTerms()) || degree != that.getDegree()) {
            System.out.println(" prime was false");
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = modPrime;
        result = 31 * result + terms.hashCode();
        result = 31 * result + degree;
        return result;
    }

    public ArrayList<IntegerModP> getTerms() {
        return this.terms;
    }
    
    public int getDegree() {
        return this.degree;
    }
    
    public int getModPrime() {
        return this.modPrime;
    }

    /**
     * Given two polynomials mod p, a and b, this algorithm outputs two polynomials x and y with gcd(a, b) = xa + yb.
     * See algorithm 1.2.11 from lecture notes.
     *
     * @param a Polynomial mod p
     * @param b Polynomial mod p
     * @throws IllegalArgumentException if {@code a == null || b == null || a.modP != b.modP}
     * @return Polynomials x, y, such that gcd(a, b) = xa + yb
     */
    public Pair<PolynomialModP, PolynomialModP> ExtEuclid(PolynomialModP a, PolynomialModP b){

        if(a == null || b == null){
            throw new IllegalArgumentException("The polynomials cannot be null.");
        }
        else if(a.getModPrime() != b.getModPrime()){
            throw new IllegalArgumentException("The polynomials must have the same modulus.");
        }

        ArrayList<Integer> l = new ArrayList<>(1);
        l.set(0,1);
        PolynomialModP x = new PolynomialModP(l, a.getModPrime());
        PolynomialModP v = new PolynomialModP(l, a.getModPrime());
        l.set(0,0);
        PolynomialModP y = new PolynomialModP(l, a.getModPrime());
        PolynomialModP u = new PolynomialModP(l, a.getModPrime());
        PolynomialModP zero = new PolynomialModP(l, a.getModPrime());

        while(!(b.equals(zero))){
            //TODO: Calculate x, y
        }

        return new Pair<>(x, y);

    }

}
