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
        this.number = takeMod(number);
    }

    /**
     * Takes the modulus of an integer
     *
     * @param number The number to take the mod of
     * @return {@code number} modulus {@code mod}
     */
    private int takeMod(int number) {
        if(number < mod) {
            return number;
        }
        return number / mod;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        IntegerModP that = (IntegerModP) object;

        return number == that.number && mod == that.mod;
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
    
    public IntegerModP add (IntegerModP to_be_added) {
        return new IntegerModP(takeMod(this.number + to_be_added.getNumber()), mod);
    }
    
    public IntegerModP multiply (IntegerModP to_be_added) {
        return new IntegerModP(takeMod(this.number * to_be_added.getNumber()), mod);
    }
    
    public IntegerModP subtract (IntegerModP to_be_added) {
        return new IntegerModP(takeMod(this.number - to_be_added.getNumber()), mod);
    }
}
