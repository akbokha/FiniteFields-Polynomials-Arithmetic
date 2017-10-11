
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdel K. Bokharouss
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String startInfo = "Enter a number between 1 and 7. The numbers corresponds to the following calculations:\n"
                + "1: Produces the sum, scalar multiple, difference and product of input polynomials \n"
                + "2: Produces the quotient and remainder of two polynomials\n"
                + "3: Executes the (Extended) Euclidean algorithm for polynomials\n"
                + "4: Checks whether tow polynomials in the mod p setting are equal modulo a third polynomial\n"
                + "5: Produces the addition and multiplication table of the field Z/p[X]/ q(X) of polynomial q(X)\n"
                + "6: Upon input of two field elements a and b, produces the sum (a+b), product (a*b) and the quotient (a*b^-1)\n"
                + "7: Checks primitivity of a field element; finds primitive elements in a field\n";
        
        String polynomialForm = "in the form : c_0 + c_1*x + ....+ c_(n-1)*x^(n-1) + c_n*x^(e_n)";
        System.out.println(startInfo);

        String choice = sc.nextLine();
        if (choice.equals("1")) {
            //input: two polynlmials mod p
            //output: sum, difference, product and scalar multiple
            //polynomial calculations
            System.out.println("please enter a prime number");
            int prime = Integer.parseInt(sc.nextLine());
            System.out.println("Please enter two polynomials (mod "+prime+") "+  polynomialForm);
            String p1 = sc.nextLine();
            String p2 = sc.nextLine();
            //call polynomial calculations instance
        } else if (choice.equals("2")) {
            //input: two polynomials mod p
            //output: quotient and remainder
            //function:long division
            System.out.println("please enter a prime number");
            int prime = Integer.parseInt(sc.nextLine());
            System.out.println("Please enter two polynomials (mod "+prime+") "+  polynomialForm);
            String p1 = sc.nextLine();
            String p2 = sc.nextLine();
        } else if (choice.equals("3")) {
            //input: two polynomials (x,y) mod p
            //output: x*a + y*b = gcd(x,y)
            //function: extended euclidian algorithm
            System.out.println("please enter a prime number");
            int prime = Integer.parseInt(sc.nextLine());
            System.out.println("Please enter two polynomials (mod "+prime+") "+  polynomialForm);
            String p1 = sc.nextLine();
            String p2 = sc.nextLine();
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
            System.out.println("please enter a prime number");
            int prime = Integer.parseInt(sc.nextLine());
            System.out.println("Please enter two polynomials (mod "+prime+") "+  polynomialForm);
            String p1 = sc.nextLine();
            String p2 = sc.nextLine();
            System.out.println("Please enter a third polynomial (the modulo) in the form: c_0 + c_1*x + .... + c_(n-1)*x^(n-1) + c_n*x^(e_n)");
            String p3 = sc.nextLine();
        } else if (choice.equals("5")) {
            //input: prime p and irreducible polynomail q(x)
            //output: addition and multiplication table of the field 
            //Z/pZ[x] / q(x)
            System.out.println("Please enter a prime number");
            int prime = Integer.parseInt(sc.nextLine());
            System.out.println("Please enter an irreducible polynomial in the form: c_0 + c_1*x + .... + c_(n-1)*x^(n-1) + c_n*x^(e_n)");
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

    }

}
