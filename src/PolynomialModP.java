import java.util.ArrayList;
/**
 *
 * @author Abdel K. Bokharouss
 */
public class PolynomialModP {
    
    private final int mod_prime;
    private ArrayList<IntegerModP> terms;
    private int degree;
    
    public PolynomialModP(PolynomialModP copy) {
        this.mod_prime = copy.getModPrime();
        this.degree = copy.getDegree();
        ArrayList copyList = new ArrayList<>();
        for (IntegerModP intmodp : copy.getTerms()) {
            copyList.add(new IntegerModP(intmodp.getNumber(), mod_prime));
        }
        this.terms = copyList;
    }
    
    public PolynomialModP(PolynomialModP copy, int mod_prime, int degree_difference) {
        this.mod_prime = mod_prime;
        this.degree = copy.getDegree();
        ArrayList copyList = new ArrayList<>();
        for (IntegerModP intmodp : copy.getTerms()) {
            copyList.add(new IntegerModP(intmodp.getNumber(), mod_prime));
        }
        for (int i = 0; i <= degree_difference; i++) {
            copyList.add(0);
        }
        this.terms = copyList;
    }
    
    public PolynomialModP(ArrayList<Integer> terms, int mod_prime) {
        this.mod_prime = mod_prime;
        this.terms = takeMod(terms);
        this.degree = terms.size() - 1;
    }
    
    private ArrayList<IntegerModP> takeMod(ArrayList<Integer> terms) {
        ArrayList<IntegerModP> coeff_modP = new ArrayList<>();
        for (int i = 0; i < terms.size(); i++) {
            coeff_modP.add(i, new IntegerModP(terms.get(i), mod_prime));
        }
        return coeff_modP;
    }
    
    public PolynomialModP sum(PolynomialModP to_be_added) {
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), to_be_added.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(to_be_added, mod_prime, (degree - to_be_added.getDegree()));
        } else {
            a = new PolynomialModP(this, mod_prime, (to_be_added.getDegree() - degree));
            b = to_be_added;
        }
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, a.getTerms().get(i).add(b.getTerms().get(i)));
        }
        return new PolynomialModP(result, mod_prime);
    }
    
    public PolynomialModP scalarMultiple(int b) {
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < this.terms.size(); i++) {
            result.add(i, new IntegerModP((this.terms.get(i).getNumber() * b), mod_prime));
        }
        return new PolynomialModP(result, mod_prime);
    }
    
    public PolynomialModP difference (PolynomialModP poly) {
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), poly.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(poly, mod_prime, (degree - poly.getDegree()));
        } else {
            a = new PolynomialModP(this, mod_prime, (poly.getDegree() - degree));
            b = poly;
        }
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, a.getTerms().get(i).subtract(b.getTerms().get(i)));
        }
        return new PolynomialModP(result, mod_prime);
    }
    
    public PolynomialModP product (PolynomialModP poly) {
        PolynomialModP a;
        PolynomialModP b;
        int maxLength = Math.max(this.terms.size(), poly.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this;
            b = new PolynomialModP(poly, mod_prime, (degree - poly.getDegree()));
        } else {
            a = new PolynomialModP(this, mod_prime, (poly.getDegree() - degree));
            b = poly;
        }
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, a.getTerms().get(i).multiply(b.getTerms().get(i)));
        }
        return new PolynomialModP(result, mod_prime);
    }
    
    public ArrayList<IntegerModP> getTerms() {
        return this.terms;
    }
    
    public int getDegree() {
        return this.degree;
    }
    
    public int getModPrime() {
        return this.mod_prime;
    }
}
