/**
 * An integer with a modulus
 *
 * @author Bart van Helvert
 */
public class IntegerModP {
    /**
     * The value
     */
    private int number;

    /**
     * The modulus
     */
    private int mod;

    /**
     * Creates a new IntegerModP
     *
     * @param number The value
     * @param mod    The modulus
     */
    public IntegerModP(int number, int mod) {
        this.number = takeMod(number);
        this.mod = mod;
    }
    
    private int takeMod(int number) {
        return (number - ( mod * (int) Math.floor(number / mod)));
    }

    @Override
    public String toString() {
        return number + " mod " + mod;
    }

    /**
     * Gets the number of the {@link IntegerModP}
     *
     * @return The number of the {@link IntegerModP}
     */
    public int getNumber(){
        return number;
    }

    /**
     * Sets the number of the {@link IntegerModP}
     *
     * @param number The number of the {@link IntegerModP}
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets the modulus of the {@link IntegerModP}
     *
     * @return The modulus of the {@link IntegerModP}
     */
    public int getMod() {
        return mod;
    }

    /**
     * Sets the modulus of the {@link IntegerModP}
     *
     * @param mod The modulus of the {@link IntegerModP}
     */
    public void setMod(int mod) {
        this.mod = mod;
    }



}
