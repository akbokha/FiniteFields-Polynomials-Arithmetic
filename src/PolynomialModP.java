import java.util.ArrayList;
import java.math.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdel K. Bokharouss
 */
public class PolynomialModP {
    
    private int prime;
    private ArrayList<Integer> terms;
    
    public PolynomialModP(ArrayList<Integer> terms, int prime) {
        this.prime = prime;
        this.terms = terms;
    }
    
    public PolynomialModP sum(PolynomialModP add) {
        int maxLength = Math.max(this.terms.size(), add.getTerms().size());
        int minLength = Math.min(this.terms.size(), add.getTerms().size());
        
        boolean thisPolynomialHasHigherDeg;
        if (maxLength == this.terms.size()) {
            thisPolynomialHasHigherDeg = true;
        } else {
            thisPolynomialHasHigherDeg = false;
        }
        
        ArrayList sum_terms = new ArrayList<>(maxLength);
        for (int i = 0; i < minLength; i++) {
            sum_terms.add(i, (this.terms.get(i) + add.getTerms().get(i)));
        }
        for (int j = minLength; j < (maxLength - minLength); j++) {
            ArrayList longest;
            if (thisPolynomialHasHigherDeg) {
                longest = this.terms;
            } else {
                longest = add.getTerms();
            }
            sum_terms.add(j, longest.get(j));
        }
        return new PolynomialModP(sum_terms, prime);
    }
    
    public ArrayList<Integer> getTerms() {
        return this.terms;
    }
}
