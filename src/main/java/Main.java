import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.max;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdel K. Bokharouss
 * @author Joris Rombouts
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String startInfo = "Enter a number between 1 and 7. The numbers corresponds to the following calculations:\n"
                + "1: Produces the sum, scalar multiple, difference and product of two polynomials \n"
                + "2: Produces the quotient and remainder of two polynomials\n"
                + "3: Executes the (Extended) Euclidean algorithm for polynomials\n"
                + "4: Checks whether tow polynomials in the mod p setting are equal modulo a third polynomial\n"
                + "5: Produces the addition and multiplication table of the field Z/p[X]/ q(X) of polynomial q(X)\n"
                + "6: Upon input of two field elements a and b, produces the sum (a+b), product (a*b) and the quotient (a*b^-1)\n"
                + "7: Checks primitivity of a field element; finds primitive elements in a field\n";

        String polynomialForm = "in the form : c_0 + c_1 x + ....+ c_(n-1) x^(n-1) + c_n x^(e_n)";
        System.out.println(startInfo);
        try {
            String choice = sc.nextLine();
            if (choice.equals("")) {
                throw new Exception("input = null");
            } else if (Integer.parseInt(choice) > 7 || Integer.parseInt(choice) < 1) {
                throw new NumberFormatException("please enter a valid integer between 1 and 7");
            } else if (choice.equals("1")) {
                //input: two polynomials mod p
                //output: sum, difference, product and scalar multiple
                //polynomial calculations
                try {
                    int prime = enterTwoPolynomials(sc, polynomialForm);

                    String p1 = sc.nextLine();
                    ArrayList<Integer> pol1 = extractPol(p1);
                    String p2 = sc.nextLine();
                    ArrayList<Integer> pol2 = extractPol(p2);

                } catch (NumberFormatException e) {
                    System.out.println("please enter a valid number");
                }

                //call polynomial calculations instance
            } else if (choice.equals("2")) {
                //input: two polynomials mod p
                //output: quotient and remainder
                //function:long division
                try {
                    int prime = enterTwoPolynomials(sc, polynomialForm);

                    String p1 = sc.nextLine();
                    ArrayList<Integer> pol1 = extractPol(p1);
                    String p2 = sc.nextLine();
                    ArrayList<Integer> pol2 = extractPol(p2);

                } catch (NumberFormatException e) {
                    System.out.println("please enter a valid number");
                }
            } else if (choice.equals("3")) {
                //input: two polynomials (x,y) mod p
                //output: x*a + y*b = gcd(x,y)
                //function: extended euclidian algorithm
                try {
                    int prime = enterTwoPolynomials(sc, polynomialForm);

                    String p1 = sc.nextLine();
                    ArrayList<Integer> pol1 = extractPol(p1);
                    String p2 = sc.nextLine();
                    ArrayList<Integer> pol2 = extractPol(p2);
                } catch (NumberFormatException e) {
                    System.out.println("please enter a valid number");
                }
                System.out.println("Type 0 for Euclid's algorithm for polynomials, or type 1 for the Extended Euclidian Algorithm for polynomials ");
                String euclid = sc.nextLine();
                if (euclid.equals("0")) {
                    //execute euclids algorithm for polynomials
                } else {
                    //execute extended euclids algorithm for polynomials
                }
            } else if (choice.equals("4")) {
                //input: two polynomials (x,y) mod p, third polynomial z
                //output: true if (x,y) mod z == 0; false otherwise
                //function: congruent modulo
                try {
                    int prime = enterTwoPolynomials(sc, polynomialForm);

                    String p1 = sc.nextLine();
                    ArrayList<Integer> pol1 = extractPol(p1);
                    String p2 = sc.nextLine();
                    ArrayList<Integer> pol2 = extractPol(p2);
                } catch (NumberFormatException e) {
                    System.out.println("please enter a valid number");
                }
                System.out.println("Please enter a third polynomial (the modulo)"+ polynomialForm);
                String p3 = sc.nextLine();
            } else if (choice.equals("5")) {
                //input: prime p and irreducible polynomail q(x)
                //output: addition and multiplication table of the field
                //Z/pZ[x] / q(x)
                try {
                    System.out.println("Please enter a prime number");
                    int prime = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("please enter a valid number");
                }
                System.out.println("Please enter an irreducible polynomial"+polynomialForm);
                //produce addition and multiplication table of the field
            } else if (choice.equals("6")) {
                //input: two field elements a and b
                //output: a+b, a*b, a*b^-1 (if b!=0)
                //function: ext. eucld. algo for b^-1, and ....
                System.out.println("Please enter two field elements a and b in the form: [tbd]");
                String field1 = sc.nextLine();
                String field2 = sc.nextLine();

            } else if (choice.equals("7")) {
                //input: field element
                //output: check primitivy of a field element
                //function: algo 3.4.3, 3.4.4
                System.out.println("Please enter a field F of order q and prime divisors p1,...,pk of q-e and a in F in the form: [tbd]");
                String field = sc.nextLine();
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static IntegerModP solve() {
        IntegerModP answer = null;
        return answer;
    }
    /**
     * Extracts input polynomial of the user into an ArrayList, where the index of the elements in the list
     * corresponds to the degree where that coefficients belongs to.
     * @param p1 input string of the user in the form: c_0 + c_1 x^(deg_1) + ... + c_n x^(deg_n)
     * @return ArrayList {@code polynomialList} where the degree of the terms are represented by its index.
     */
    private static ArrayList<Integer> extractPol(String p1) {
        String [] terms = stripInput(p1);
        int maxDeg = 0;
        maxDeg = getMaxDegree(terms, maxDeg);
        ArrayList<Integer> polynomialList = createArrayList(maxDeg);

        for (String term : terms) {
            int deg = 0;
            if (!term.contains("x")) {
                deg = 0;
            } else if (!term.contains("^")) {
                deg = 1;
                term = term.substring(0, term.indexOf("x"));
                if (term.equals("-")) {
                    term = "-1";
                } else if (term.equals("")) {
                    term = "1";
                }
            } else if(term.contains("x^")) {
                deg = Integer.parseInt(term.substring(term.lastIndexOf("^") + 1));
                term = term.substring(0, term.indexOf("x"));
                if (term.equals("-")) {
                    term = "-1";
                } else if (term.equals("")){
                    term = "1";
                }
            }
            polynomialList.set(deg, Integer.parseInt(term));
        }
        return polynomialList;
    }

    /**
     * Returns the maximum degree of the terms
     * @param elements Array with terms
     * @param maxDeg maximum degree (initial 0)
     * @return {@code \max(\forall i; elements.has(i); \old(elements[i]))}
     */
    private static int getMaxDegree(String[] elements , int maxDeg) {
        for (String term : elements) {
            if (!term.contains("x")) {
                maxDeg = max(0, maxDeg);
            } else if (!term.contains("^")) {
                maxDeg = max(1, maxDeg);
            } else if (term.contains("x^")) {
                if (Integer.parseInt(term.substring(term.lastIndexOf("^") + 1) ) > maxDeg) {
                    maxDeg = max(maxDeg, Integer.parseInt(term.substring(term.lastIndexOf("^") + 1)));
                }
            }
        }
        return maxDeg;
    }

    /**
     * Creates new ArrayList in which the polynomials are stored
     *
     * @param maxDeg integer that represents the maximum degree of the polynomial
     * @return ArrayList polynomialList with {@codepolynomialList.size() = max(degree) + 1};
     */
    private static ArrayList<Integer> createArrayList(int maxDeg) {
        ArrayList<Integer> polynomialList = new ArrayList<>();
        for (int i = 0; i < maxDeg + 1; i++) {
            polynomialList.add(0);
        }
        return polynomialList;
    }

    /**
     * Method that removes all the whitespaces between each character. Replaces all -signs by +-, signs so that
     * we can split on the +signs. Returns an array of terms (coefficients * x)
     * @param input user input in the form: c_0 + c_1 x^(deg_1) + ... + c_n x^(deg_n)
     * @return {@code String[] terms}, where each element represent a term (coefficient * x)
     */
    private static String[] stripInput (String input) {
        input = input.trim();
        input = input.replaceAll("\\s+","");
        input = input.replace("-", "+-");
        String[] terms = input.split("\\+");
        return terms;
    }

    /**
     * Text for entering two polynomials + prime number
     * @param sc scanner for input
     * @param polynomialForm System.out text
     * @return modulo (prime) number
     */
    private static int enterTwoPolynomials(Scanner sc, String polynomialForm) {
        System.out.println("please enter a prime number");
        int prime = Integer.parseInt(sc.nextLine());
        System.out.println("Please enter two polynomials (mod "+prime+") "+  polynomialForm);
        return prime;
    }
}
