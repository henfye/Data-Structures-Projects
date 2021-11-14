package p1_package;

/**
 * Cell class holds one cell with a Boolean characteristic
 *  
 * @author Michael Leverington
 *
 */
public class CellClass
   {
      /**
       * constant, empty character for array
       */
      public static final char EMPTY_CHAR = ' ';
        
    /**
     * constant, stick and bone character for array
     */
    public static final char STICK_BONE_CHAR = '*';
      
    /**
     * value in CellClass, character in cell
     */
    private char cellChar;
    
    /**
     * Default constructor
     * 
     */
    public CellClass()
       {
        cellChar = EMPTY_CHAR;      
       }
    
    /**
     * Copy constructor
     * <p>
     * Copies other cell class object
     * @param copied CellClass object to be copied
     */
    public CellClass( CellClass copied )
       {
        cellChar = copied.cellChar;
       }
    
    /**
     * accessor for character
     * <p>
     * Gets state of counted flag
     * @return Boolean state of counted flag
     */
    public char getCellCharacter()
       {
        return cellChar;
       }

    /**
     * accessor
     * <p>
     * Gets state of counted flag
     * @return Boolean state of counted flag
     */
    public boolean isUsed()
       {
        return cellChar == STICK_BONE_CHAR;
       }

    /**
     * mutator/modifier
     * <p>
     * Sets Boolean characteristic
     * 
     * @param newCellChar character value to set in object
     */
    public void setCellCharacter( char newCellChar )
       {
        cellChar = newCellChar;
       }
    
    /**
     * toString overload
     */
    public String toString()
       {
        return "" + cellChar; 
       }
    
   }


