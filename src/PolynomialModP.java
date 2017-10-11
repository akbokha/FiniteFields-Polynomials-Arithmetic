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
        int maxLength = Math.max(this.terms.size(), to_be_added.getTerms().size());
        int minLength = Math.min(this.terms.size(), to_be_added.getTerms().size());
        
        boolean thisPolynomialHasHigherDeg;
        if (maxLength == this.terms.size()) {
            thisPolynomialHasHigherDeg = true;
        } else {
            thisPolynomialHasHigherDeg = false;
        }
        
        ArrayList sum_terms = new ArrayList<>(maxLength);
        for (int i = 0; i < minLength; i++) {
            sum_terms.add(i, this.terms.get(i).add(to_be_added.getTerms().get(i)));
        }
        ArrayList longest;
        if (thisPolynomialHasHigherDeg) {
            longest = this.terms;
        } else {
            longest = to_be_added.getTerms();
        }
        for (int j = minLength; j < (maxLength - minLength); j++) {
            sum_terms.add(j, longest.get(j));
        }
        return new PolynomialModP(sum_terms, mod_prime);
    }
    
    public ArrayList<IntegerModP> getTerms() {
        return this.terms;
    }
}
