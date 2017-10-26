import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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


        String startInfo = "Enter a number between 1 and 8 or 0 to stop the program. The numbers corresponds to the following calculations:\n"
                + "0: Stops the program\n"
                + "1: Produces the sum, difference product of two polynomials and scalar multiple of one of the two" +
                " polynomials\n"
                + "2: Produces the quotient and remainder of two polynomials (Long division)\n"
                + "3: Executes the (Extended) Euclidean algorithm for polynomials\n"
                + "4: Checks whether two polynomials in the mod p setting are equal modulo a third polynomial\n"
                + "5: Produces the addition and multiplication table of the field Z/p[X]/ q(X) of polynomial q(X)\n"
                + "6: Upon input of two field elements a and b, produces the sum (a+b), " +
                "product (a*b) and the quotient (a*b^-1) in a field F\n"
                + "7: Checks irreducibility of a polynomial mod p\n"
                + "8: produces irreducible polynomials of a prescribed degree";

        String polynomialForm = "in the form : c_0 + c_1 x + ....+ c_(n-1) x^(n-1) + c_n x^(e_n)";
        System.out.println(startInfo);
        while (true) {
            System.out.println("Enter a number between 1 and 8 or 0 to stop the program");
            Scanner sc = new Scanner(System.in);
            try {
                String choice = sc.nextLine();
                if (choice.equals("")) {
                    throw new Exception("input = null");
                } else if (Integer.parseInt(choice) > 8) {
                    throw new NumberFormatException();
                } else if (choice.equals("0")) {
                    break;
                } if (choice.equals("1")) {
                    //input: two polynomials mod p
                    //output: sum, difference, product and scalar multiple
                    //polynomial calculations
                    while (true) {
                        try {
                            int prime = enterTwoPolynomials(sc, polynomialForm);
                            if (!isPrime(prime)) {
                                throw new NumberFormatException();
                            }
                            String p1 = sc.nextLine();
                            ArrayList<Integer> pol1 = extractPol(p1);
                            String p2 = sc.nextLine();
                            ArrayList<Integer> pol2 = extractPol(p2);
                            System.out.println("Please enter 1*(integer) for the scalar multiple of polynomial 1, " +
                                    "or 2*(integer) for the scalar multiple of polynomial 2 for a given (integer)");
                            String scalarChoice = sc.nextLine();
                            int numberScalarM = Integer.parseInt(scalarChoice.substring(scalarChoice.lastIndexOf("*")+1));
                            computeSum(pol1, pol2, prime);
                            computeDiff(pol1, pol2, prime);
                            computeProduct(pol1, pol2, prime);
                            if (scalarChoice.startsWith("1")) {
                                computeScalarM(pol1, prime, numberScalarM);
                            } else if (scalarChoice.startsWith("0")) {
                                computeScalarM(pol2, prime, numberScalarM);
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid prime number.");
                        }
                    }
                } else if (choice.equals("2")) {
                    //input: two polynomials mod p
                    //output: quotient and remainder
                    //function:long division
                    while (true) {
                        try {
                            System.out.println("This operation divides the first entered \n" +
                                    "polynomial by the second entered polynomial");
                            int prime = enterTwoPolynomials(sc, polynomialForm);
                            if (!isPrime(prime)) {
                                throw new NumberFormatException();
                            }
                            String p1 = sc.nextLine();
                            ArrayList<Integer> pol1 = extractPol(p1);
                            String p2 = sc.nextLine();
                            ArrayList<Integer> pol2 = extractPol(p2);
                            computeLongDivision(pol1, pol2, prime);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid prime number");
                        }
                    }
                } else if (choice.equals("3")) {
                    //input: two polynomials (x,y) mod p
                    //output: x*a + y*b = gcd(x,y)
                    //function: extended euclidean algorithm
                    while (true) {
                        try {
                            System.out.println("This operation determines the gcd between the first entered \n" +
                                    "polynomial and the second entered polynomial");
                            int prime = enterTwoPolynomials(sc, polynomialForm);
                            if (!isPrime(prime)) {
                                throw new NumberFormatException();
                            }
                            String p1 = sc.nextLine();
                            ArrayList<Integer> pol1 = extractPol(p1);
                            String p2 = sc.nextLine();
                            ArrayList<Integer> pol2 = extractPol(p2);

                            System.out.println("Type 0 for Euclid's algorithm for polynomials, " +
                                    "or type 1 for the Extended Euclidean Algorithm for polynomials ");
                            String euclid = sc.nextLine();
                            if (euclid.equals("0")) {
                                computeEuclidean(pol1, pol2, prime);
                            } else {
                                computeExtEuclidean(pol1, pol2, prime);
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("please enter a valid prime number");
                        }
                    }

                } else if (choice.equals("4")) {
                    //input: two polynomials (x,y) mod p, third polynomial z
                    //output: true if (x,y) mod z == 0; false otherwise
                    //function: congruent modulo
                    while (true) {
                        try {
                            System.out.println("This operation checks if the first polynomial (a) and second " +
                                    "polynomial (b) are congruent modulo d, notation: a = b mod d");
                            int prime = enterTwoPolynomials(sc, polynomialForm);
                            if (!isPrime(prime)) {
                                throw new NumberFormatException();
                            }
                            String p1 = sc.nextLine();
                            ArrayList<Integer> pol1 = extractPol(p1);
                            String p2 = sc.nextLine();
                            ArrayList<Integer> pol2 = extractPol(p2);
                            System.out.println("Please enter a third polynomial (the modulo)"+ polynomialForm);
                            String p3 = sc.nextLine();
                            ArrayList<Integer> pol3 = extractPol(p3);
                            computeCongruenceMod(pol1, pol2, pol3, prime);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid prime number");
                        }
                    }

                } else if (choice.equals("5")) {
                    //input: prime p and irreducible polynomial q(x)
                    //output: addition and multiplication table of the field
                    //Z/pZ[x] / q(x)
                    while (true) {
                        try {
                            System.out.println("Please enter a prime number");
                            int prime = Integer.parseInt(sc.nextLine());
                            if (!isPrime(prime)) {
                                throw new NumberFormatException();
                            }
                            System.out.println("Please enter an irreducible polynomial"+polynomialForm);
                            String p1 = sc.nextLine();
                            ArrayList<Integer> pol1 = extractPol(p1);
                            computeMulTable(pol1, prime);
                            computeAddTable(pol1, prime);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid prime number");
                        }
                    }

                    //produce addition and multiplication table of the field
                } else if (choice.equals("6")) {
                    //input: two field elements a and b
                    //output: a+b, a*b, a*b^-1 (if b!=0)
                    //function: ext. eucld. algo for b^-1, and ....
                    while (true) {
                        try {
                            System.out.println("Please enter a prime number");
                            int prime = Integer.parseInt(sc.nextLine());
                            if (!isPrime(prime)) {
                                throw new NumberFormatException();
                            }
                            System.out.println("Please enter a field F in the form:"+polynomialForm);
                            String field = sc.nextLine();
                            System.out.println("Please enter two field elements a and b in F in the form:"+polynomialForm);
                            String f1 = sc.nextLine();
                            String f2 = sc.nextLine();
                            ArrayList<Integer> fieldElement1 = extractPol(f1);
                            ArrayList<Integer> fieldElement2 = extractPol(f2);
                            ArrayList<Integer> fieldF = extractPol(field);
                            computeSumFields(fieldF, fieldElement1, fieldElement2, prime);
                            computeProductFields(fieldF, fieldElement1, fieldElement2, prime);
                            computeProductInverse(fieldF, fieldElement1, fieldElement2, prime);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid prime number");
                        }
                    }
                } else if (choice.equals("7")) {
                    //input: polynomial mod p in field
                    //output: check irreducibility of polynomial + produces
                    while (true) {
                        try {
                            System.out.println("Please enter a prime number");
                            int prime = Integer.parseInt(sc.nextLine());
                            if (!isPrime(prime)) {
                                throw new NumberFormatException();
                            }
                            System.out.println("Please enter a field F in the form:"+polynomialForm);
                            String f1 = sc.nextLine();
                            System.out.println("Please enter a polynomial in F in the form:"+polynomialForm +" with " +
                                    "degree > 1");
                            String p1 = sc.nextLine();
                            ArrayList<Integer> field1 = extractPol(f1);
                            ArrayList<Integer> poly1 = extractPol(p1);
                            checkIrreducibility(field1, poly1, prime);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid prime number");
                        }
                    }

                } else if (choice.equals("8")) {
                    //input: field and degree
                    //output: irreducible polynomial in field of degree n
                    try {
                        System.out.println("Please enter a prime number");
                        int prime = Integer.parseInt(sc.nextLine());
                        if (!isPrime(prime)) {
                            throw new NumberFormatException();
                        }
                        System.out.println("Please enter a field F in the form:"+polynomialForm);
                        String f1 = sc.nextLine();
                        System.out.println("Please enter a degree > 0");
                        int deg = Integer.parseInt(sc.nextLine());

                        ArrayList<Integer> field1 = extractPol(f1);
                        computeIrrPolynomial(field1, prime, deg);

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid prime number");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer between 1 and 7");
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    private static void checkIrreducibility(ArrayList<Integer> field1, ArrayList<Integer> poly1, int prime) throws CloneNotSupportedException {
        PolynomialModP polField = new PolynomialModP(field1, prime);
        PolynomialModP p1 = new PolynomialModP(poly1, prime);
        FiniteField field = new FiniteField(polField, prime);
        boolean isIrreducible = field.isIrreducible(p1);
        String reducible = "";
        if (!isIrreducible) {
            reducible = "not";
        }
        System.out.println(p1.toString() + " is "+reducible+" irreducible in F(x) = "+polField.toString());
    }

    private static void computeIrrPolynomial(ArrayList<Integer> field1, int prime, int deg) throws CloneNotSupportedException {
        PolynomialModP p2 = new PolynomialModP(field1, prime);
        FiniteField f1 = new FiniteField(p2, prime);
        System.out.println("An irreducible polynomial in "+p2+" is "+ f1.produceIrreduciblePoly(deg).toString());
    }

    private static void computeProductInverse(ArrayList<Integer> fieldF, ArrayList<Integer> fieldElement1, ArrayList<Integer> fieldElement2, int prime) throws CloneNotSupportedException {
        PolynomialModP p1 = new PolynomialModP(fieldElement1, prime);
        PolynomialModP p2 = new PolynomialModP(fieldElement2, prime);
        PolynomialModP polField = new PolynomialModP(fieldF, prime);
        FiniteField field = new FiniteField(polField, prime);
        PolynomialModP result = field.inverse(p2);
        if (result == null) {
            System.out.println("The inverse of "+p2.toString()+ " in "+ polField.toString() +"does not exist");
        } else {
            System.out.println("The product of "+p1.toString()+" and "+result.toString()+" (mod) "+prime+" = "+ field.quotient(p1, p2));
        }
    }

    private static void computeProductFields(ArrayList<Integer> fieldF, ArrayList<Integer> fieldElement1, ArrayList<Integer> fieldElement2, int prime) {
        PolynomialModP p1 = new PolynomialModP(fieldElement1, prime, false);
        PolynomialModP p2 = new PolynomialModP(fieldElement2, prime, false);
        PolynomialModP polField = new PolynomialModP(fieldF, prime);
        FiniteField field = new FiniteField(polField, prime);
        System.out.println("The product of "+p1.toString()+" and "+p2.toString()+" (mod) "+prime+" = "+ field.product(p1, p2).toString());
    }

    private static void computeSumFields(ArrayList<Integer> fieldF, ArrayList<Integer> fieldElement1, ArrayList<Integer> fieldElement2, int prime) {
        PolynomialModP p1 = new PolynomialModP(fieldElement1, prime, false);
        PolynomialModP p2 = new PolynomialModP(fieldElement2, prime, false);
        PolynomialModP polField = new PolynomialModP(fieldF, prime);
        FiniteField field = new FiniteField(polField, prime);
        System.out.println("The sum of "+p1.toString()+" and "+p2.toString()+" (mod) "+prime+" = "+ field.sum(p1, p2).toString());
    }


    private static void computeMulTable(ArrayList<Integer> field1, int prime) throws CloneNotSupportedException {
        PolynomialModP p1 = new PolynomialModP(field1, prime);
        FiniteField f1 = new FiniteField(p1, prime);
        System.out.println("The multiplication table of the field Z/"+prime+ "Z / "+p1+" is: ");

        FiniteField[][] result = f1.mulTable(field1, p1.getDegree());
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j].getPolynomial());
                System.out.print("\t\t");
            }
            System.out.println();
        }

    }

    private static void computeAddTable(ArrayList<Integer> field1, int prime) throws CloneNotSupportedException {
        PolynomialModP p1 = new PolynomialModP(field1, prime);
        FiniteField f1 = new FiniteField(p1, prime);
        System.out.println("The addition table of the field Z/"+prime+ "Z / "+p1+" is: ");
        FiniteField[][] result = f1.mulTable(field1, p1.getDegree());

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j].getPolynomial());
                System.out.print("\t\t");
            }
            System.out.println("");
        }
    }

    private static void computeCongruenceMod(ArrayList<Integer> pol1, ArrayList<Integer> pol2, ArrayList<Integer> pol3, int prime) throws CloneNotSupportedException {
        PolynomialModP p1 = new PolynomialModP(pol1, prime, true);
        PolynomialModP p2 = new PolynomialModP(pol2, prime, true);
        PolynomialModP p3 = new PolynomialModP(pol3, prime, true );
        boolean isCong = p1.isCongMod(p2, p3);
        String congruence = "";
        if (!isCong) {
            congruence = "not";
        }
        System.out.println(p1 +" and "+p2+" are "+congruence+" congruent modulus "+p3);
    }

    private static void computeExtEuclidean(ArrayList<Integer> pol1, ArrayList<Integer> pol2, int prime) throws CloneNotSupportedException {
        PolynomialModP p1 = new PolynomialModP(pol1, prime, true);
        PolynomialModP p2 = new PolynomialModP(pol2, prime, true);
        System.out.println("gcd("+p1+","+p2+") (mod) "+prime+" = "+p1+"*" +p1.ExtEuclid(p2).get(0).toString() + " " +
                "+ "+p2+"*"+ p1.ExtEuclid(p2).get(1).toString() +" = "+ p1.Euclid(p2).toString());
    }

    private static void computeEuclidean(ArrayList<Integer> pol1, ArrayList<Integer> pol2, int prime) throws CloneNotSupportedException {
        PolynomialModP p1 = new PolynomialModP(pol1, prime, true);
        PolynomialModP p2 = new PolynomialModP(pol2, prime, true);
        System.out.println("gcd("+p1+","+p2+") (mod) "+prime+" = "+p1.Euclid(p2).toString());
    }

    private static void computeLongDivision(ArrayList<Integer> pol1, ArrayList<Integer> pol2, int prime) throws CloneNotSupportedException {
        PolynomialModP p1 = new PolynomialModP(pol1, prime, true);
        PolynomialModP p2 = new PolynomialModP(pol2, prime, true );
        System.out.println("Long division: divide "+p1+" by "+p2+" (mod) "+prime+" results in quotient: "+
                (p1.longDivision(p2)[0]).toString()+ " and remainder: " + (p1.longDivision(p2)[1]).toString());
    }

    private static void computeScalarM(ArrayList<Integer> pol1, int prime, int numberScalarM) throws CloneNotSupportedException {
        PolynomialModP p1 = new PolynomialModP(pol1, prime, true);
        System.out.println("The scalar multiple of "+p1+" * "+numberScalarM+" (mod) "
                +prime+"= "+p1.product(numberScalarM).toString());
    }

    private static void computeProduct(ArrayList<Integer> pol1, ArrayList<Integer> pol2, int prime) {
        PolynomialModP p1 = new PolynomialModP(pol1, prime, true);
        PolynomialModP p2 = new PolynomialModP(pol2, prime, true);
        System.out.println("The product of "+p1+" and "+p2+" (mod) "+prime+" = "+p1.product(p2).toString());
    }

    private static void computeDiff(ArrayList<Integer> pol1, ArrayList<Integer> pol2, int prime) {
        PolynomialModP p1 = new PolynomialModP(pol1, prime, true);
        PolynomialModP p2 = new PolynomialModP(pol2, prime, true);
        System.out.println("The difference of "+p1+" and "+p2+" (mod) "+prime+" = "+p1.difference(p2).toString());
    }

    private static void computeSum(ArrayList<Integer> pol1, ArrayList<Integer> pol2, int prime) {
        PolynomialModP p1 = new PolynomialModP(pol1, prime, true);
        PolynomialModP p2 = new PolynomialModP(pol2, prime, true);
        System.out.println("The sum of "+p1+" and "+p2+" (mod) "+prime+" = "+p1.sum(p2).toString());
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
        input = input.toLowerCase();
        input = input.trim();
        input = input.replaceAll("\\s+","");
        input = input.replace("-", "+-");
        String[] terms = input.split("\\+");
        ArrayList<String> list = new ArrayList<>();
        for (String term : terms) {
            list.add(term);
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equals("")) {
                it.remove();
            }
        }
        String[] newTerms = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newTerms[i] = list.get(i);
        }
        return newTerms;
    }

    /**
     * Text for entering two polynomials + prime number
     * @param sc scanner for input
     * @param polynomialForm System.out text
     * @return modulo (prime) number
     */
    private static int enterTwoPolynomials(Scanner sc, String polynomialForm) {
        System.out.println("Please enter a prime number");
        int prime = Integer.parseInt(sc.nextLine());
        System.out.println("Please enter two polynomials (mod "+prime+") "+  polynomialForm);
        return prime;
    }

    /**
     * Checks whether a number is prime or not
     * @param n input number that has to be checked
     * @return true if number is prime, false otherwise
     */
    static boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        if (n%2==0 || n == 1) {
            return false;
        }
        for (int i = 3; i*i <= n; i+=2) {
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }
}
