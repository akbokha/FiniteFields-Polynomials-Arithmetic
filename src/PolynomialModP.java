import java.util.ArrayList;
/**
 *
 * @author Abdel K. Bokharouss
 */
public class PolynomialModP {
    
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
    
    public PolynomialModP sum(PolynomialModP to_be_added) {
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), to_be_added.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(to_be_added, modPrime, (degree - to_be_added.getDegree()));
        } else {
            a = new PolynomialModP(this, modPrime, (to_be_added.getDegree() - degree));
            b = to_be_added;
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
    
    public PolynomialModP difference (PolynomialModP poly) {
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
    
    public PolynomialModP product (PolynomialModP poly) {
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
