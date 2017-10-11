import java.util.ArrayList;
/**
 *
 * @author Abdel K. Bokharouss
 */
public class PolynomialModP {
    
    private final int mod_prime;
    private ArrayList<IntegerModP> terms;
    
    public PolynomialModP(ArrayList<Integer> terms, int mod_prime) {
        this.mod_prime = mod_prime;
        this.terms = takeMod(terms);
    }
    
    private ArrayList<IntegerModP> takeMod(ArrayList<Integer> terms) {
        ArrayList<IntegerModP> coeff_modP = new ArrayList<>();
        for (int i = 0; i < terms.size(); i++) {
            coeff_modP.add(i, new IntegerModP(terms.get(i), mod_prime));
        }
        return coeff_modP;
    }
    
    public PolynomialModP sum(PolynomialModP to_be_added) {
        ArrayList a = new ArrayList<>();
        ArrayList b = new ArrayList<>();
        
        int maxLength = Math.max(this.terms.size(), to_be_added.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this.terms;
            b = sameLength(to_be_added.getTerms(), maxLength);
        } else {
            a = sameLength(this.terms, maxLength);
            b = to_be_added.getTerms();
        }
        
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, this.terms.get(i).add(to_be_added.getTerms().get(i)));
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
        ArrayList a = new ArrayList<>();
        ArrayList b = new ArrayList<>();
        
        int maxLength = Math.max(this.terms.size(), poly.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this.terms;
            b = sameLength(poly.getTerms(), maxLength);
        } else {
            a = sameLength(this.terms, maxLength);
            b = poly.getTerms();
        }
        
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, this.terms.get(i).subtract(poly.getTerms().get(i)));
        }
        return new PolynomialModP(result, mod_prime);
    }
    
    public PolynomialModP product (PolynomialModP poly) {
        ArrayList a = new ArrayList<>();
        ArrayList b = new ArrayList<>();
        
        int maxLength = Math.max(this.terms.size(), poly.getTerms().size());
        if (maxLength == this.terms.size()) {
            a = this.terms;
            b = sameLength(poly.getTerms(), maxLength);
        } else {
            a = sameLength(this.terms, maxLength);
            b = poly.getTerms();
        }
        
        ArrayList result = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            result.add(i, this.terms.get(i).multiply(poly.getTerms().get(i)));
        }
        return new PolynomialModP(result, mod_prime);
    }
    
    public ArrayList<IntegerModP> getTerms() {
        return this.terms;
    }
    
    private ArrayList<IntegerModP> sameLength(ArrayList<IntegerModP> poly, int len) {
        
    }
}
