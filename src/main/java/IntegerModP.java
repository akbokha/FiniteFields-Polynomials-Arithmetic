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
     * Creates a new main.java.IntegerModP
     *
     * @param number The value
     * @param mod    The modulus
     */
    public IntegerModP(int number, int mod) {
        this.mod = mod;
        this.number = number % mod;
    }


    @Override
    public boolean equals(Object object) {
        IntegerModP that = (IntegerModP) object;
        if(number != that.getNumber() || mod != that.getMod()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number;
        result = 31 * result + mod;
        return result;
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
    
    public IntegerModP add (IntegerModP add) {
        return new IntegerModP((this.number + add.getNumber()) % mod, mod);
    }
    
    public IntegerModP multiply (IntegerModP add) {
        return new IntegerModP((this.number * add.getNumber()) % mod, mod);
    }
    
    public IntegerModP subtract (IntegerModP add) {
        return new IntegerModP((this.number - add.getNumber()) % mod, mod);
    }
}
