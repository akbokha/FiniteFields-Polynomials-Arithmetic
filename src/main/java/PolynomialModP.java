package main.java;

import java.util.ArrayList;
/**
 * ADT that represents a polynomial with a modulus
 *
 * @author Abdel K. Bokharouss
 * @author Bart van Helvert
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
        ArrayList<IntegerModP> coeff_modP = new ArrayList<>();
        for (int i = 0; i < terms.size(); i++) {
            coeff_modP.add(i, new IntegerModP(terms.get(i), modPrime));
        }
        return coeff_modP;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PolynomialModP)) return false;

        PolynomialModP that = (PolynomialModP) o;

        if (modPrime != that.modPrime) return false;
        if (degree != that.degree) return false;
        return terms.equals(that.terms);
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
}
