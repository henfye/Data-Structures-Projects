package p1_package;

/**
 * Class manages two dimensional array of sticks or bones; 
 * includes one dimensional array of sticks
 * and one dimensional array of bones
 * <p>
 * The limit on number of sticks is 25 per cent of array height
 * and the limit on number of bones is 25 per cent of array width
 * 
 * @author Michael Leverington
 *
 */
public class SticksBonesClass
   {
    /**
     * Constant, default two dimensional array sides
     */
    private static final int TWO_DIM_ARRAY_SIDE = 50;
       
    /**
     * constant, stick and bone percentage of side length
     */
    private static final double STICK_BONE_PERCENTAGE = 0.25;

    /**
     * constant, horizontal line character
     */
    private static final char HORIZ_LINE = '-';
    
    /**
     * constant, vertical line character
     */
    private static final char VERTICAL_LINE = '|';
    
    /**
     * Master array of sticks and bones
     */
    private CellClass[][] fieldArray;
    
    /**
     * Array of sticks
     */
    private ArtifactClass[] stickArray;
    
    /**
     * height of cell array
     */
    private int fieldArrayHeight;
    
    /**
     * width of cell array
     */
    private int fieldArrayWidth;
    
    /**
     * Number of sticks
     */
    private int numSticks;
    
    /**
     * Array of bones
     */
    private ArtifactClass[] boneArray;
    
    /**
     * Number of bones
     */
    private int numBones;
    
    /**
     * Default constructor
     * <p>
     * Initializes class with random sticks and bones in square array;
     * arrays use default capacities
     */
    public SticksBonesClass()
       {
        this( TWO_DIM_ARRAY_SIDE, TWO_DIM_ARRAY_SIDE );
       }
    
    /**
     * Initialization constructor
     * <p>
     * Initializes class with random sticks and bones;
     * arrays use parameter capacities
     * 
     * @param height integer number of rows in two dimensional array
     * 
     * @param width integer number of columns in two dimensional array
     */
    public SticksBonesClass( int height, int width )
       {
        fieldArrayHeight = height;
        
        fieldArrayWidth = width;
        
        // generate random array
        createRandomStickBoneArray( height, width );
        
        // find sticks, store in array
        findAllSticks();
        
        // find bones, store in array
        findAllBones();
       }
    
    /**
     * Creates a random stick/bone array
     * <p>
     * Random x, y location, with random length of stick or bone
     * between 3 and 6
     * 
     * @param height integer value representing number of rows in array
     * 
     * @param width integer value representing number of columns in array
     */
    // create random stick/bone array
    private void createRandomStickBoneArray( int height, int width )
       {
        int rowIndex, colIndex, locIndex, index = 0;
        int yLoc, xLoc, startLoc = 0;
        int itemLength;
        int minItemLength = 3;
        int maxItemLength = 6;
        int stickCount, boneCount;
        
        fieldArrayHeight = height;
        fieldArrayWidth = width;
        
        // create array of cells
        fieldArray = new CellClass[ fieldArrayHeight ][ fieldArrayWidth ];
        
        for( rowIndex = 0; rowIndex < fieldArrayHeight; rowIndex++ )
           {
            for( colIndex = 0; colIndex < fieldArrayWidth; colIndex++ )
               {
                fieldArray[ rowIndex ][ colIndex ] = new CellClass();
               }
           }
        
        // generate between 1/3 of height and full height sticks and bones
        stickCount = generateRandBetween( height / 3, height );
        boneCount = generateRandBetween( height / 3, height );

        boolean createItemSuccess;
        
        while( index < stickCount )
           {
            yLoc = generateRandBetween( startLoc, height - 1 );
            xLoc = generateRandBetween( startLoc, width - 1 );
            itemLength = generateRandBetween( minItemLength, maxItemLength );

            createItemSuccess = true;
            
            for( locIndex = 0; 
                  locIndex < itemLength && createItemSuccess; locIndex++ )
               {
                if( yLoc + locIndex >= height
                      || xLoc >= width
                           || fieldArray[ yLoc + locIndex ][ xLoc ].isUsed() )
                   {
                    createItemSuccess = false;
                   }
               }
            
            if( createItemSuccess )
               {
                for( locIndex = 0; locIndex < itemLength; locIndex++ )
                   {
                    fieldArray[ yLoc + locIndex ][ xLoc ]
                                .setCellCharacter( CellClass.STICK_BONE_CHAR );
                   }

                index++;
               }
           }
        
        index = 0;
        
        while( index < boneCount )
           {
            yLoc = generateRandBetween( startLoc, height - 1 );
            xLoc = generateRandBetween( startLoc, width - 1 );
            itemLength = generateRandBetween( minItemLength, maxItemLength );
            
            createItemSuccess = true;
            
            for( locIndex = 0; 
                  locIndex < itemLength && createItemSuccess; locIndex++ )
               {
                if( xLoc + locIndex >= width
                    || yLoc >= height
                          || fieldArray[ yLoc ][ xLoc + locIndex ].isUsed() )

                   {
                    createItemSuccess = false;
                   }
               }
            
            if( createItemSuccess )
               {
                for( locIndex = 0; locIndex < itemLength; locIndex++ )
                   {
                    fieldArray[ yLoc ][ xLoc + locIndex ]
                                .setCellCharacter( CellClass.STICK_BONE_CHAR );
                   }

                index++;
               }
           }

       }
    
    /**
     * Finds all bones, assigns to bone array
     * <p>
     * May find adjacent crossing stick and count as bone
     */
    public void findAllBones()
       {
        // initialize method/variables
          int colIndex = 0;
          int rowIndex = 0;
          int boneLengthCounter = 0;
          int boneArrayCounter = 0;
          int xPos = 0;
          numBones = 0;
          
          // allocate memory for the bone array
          boneArray = new ArtifactClass[(int) (fieldArrayHeight * 
                fieldArrayWidth * STICK_BONE_PERCENTAGE)];
          
          // loop through all rows
          while (rowIndex < fieldArrayHeight)
             {
                // loop through all columns besides the final to stop out of 
                // bounds errors
                while (colIndex < fieldArrayWidth - 1)
                   {
                      // if the current character is an asterisk and the next 
                      // character is an asterisk continue
                      if (fieldArray[rowIndex][colIndex].getCellCharacter() == 
                            CellClass.STICK_BONE_CHAR && 
                            fieldArray[rowIndex][colIndex+1].getCellCharacter()
                            == CellClass.STICK_BONE_CHAR)
                         {
                            // set a length counter to zero
                            boneLengthCounter = 0;
                          
                            
                            // set the xPos to column index as the column index
                            // will increment in the upcoming while loop
                            xPos = colIndex;
                            
                            // while loop makes sure the current index is an 
                            // asterisk and checks to make sure that the next 
                            // index is still within the scope of the array
                            while (fieldArray[rowIndex][colIndex]
                                  .getCellCharacter() == 
                                  CellClass.STICK_BONE_CHAR && colIndex + 1 != 
                                  fieldArrayWidth)
                               {
                                  // increment the bone length counter to help
                                  // find the total bone length
                                  boneLengthCounter++;
                                  
                                  // increment the column index so the while 
                                  // loop eventually quits
                                  colIndex++;
                               }
                            
                            // at each index of the boneArray starting at zero,
                            // store an artifact class object containing the 
                            // xPos, yPos, bone or stick, and bone length
                            boneArray[boneArrayCounter] = new ArtifactClass
                                  (xPos, rowIndex, ArtifactClass.BONE, 
                                        boneLengthCounter);
                            boneArrayCounter++;
                            numBones++;
                            
                         }
                    
                      // increment the column index
                      colIndex++;
                   }
                
                // increment the row index and reset the column 
                // index back to zero for its next time through the while loop
                rowIndex++;
                colIndex = 0;
             }
       }
    
    /**
     * Finds all sticks, assigns to sticks array, 
     * <p>
     * May find adjacent crossing bone and count as stick
     */
    public void findAllSticks()
       {
       // initialize method
          int colIndex = 0;
          int rowIndex = 0;
          int stickLengthCounter = 0;
          int stickArrayCounter = 0;
          int yPos = 0;
          
          // allocate memory for the stick array
          stickArray = new ArtifactClass[(int) (fieldArrayHeight * 
                fieldArrayWidth * STICK_BONE_PERCENTAGE)];
          
          // loop through all rows
          while (colIndex < fieldArrayWidth)
             {
                // loop through all rows besides the final to stop out of 
                // bounds errors
                while (rowIndex < fieldArrayHeight - 1)
                   {
                      // if the current character is an asterisk and the next 
                      // character is an asterisk continue
                      if (fieldArray[rowIndex][colIndex].getCellCharacter() == 
                            CellClass.STICK_BONE_CHAR && 
                            fieldArray[rowIndex+1][colIndex].getCellCharacter()
                            == CellClass.STICK_BONE_CHAR)
                         {
                            // set a length counter to zero
                            stickLengthCounter = 0;
                            
                            // add on to the stick count
                            numSticks++;
                            
                            // set the yPos to row index as the row index
                            // will increment in the upcoming while loop
                            yPos = rowIndex;
                            
                            // while loop makes sure the current index is an 
                            // asterisk and checks to make sure that the next 
                            // index is still within the scope of the array
                            while (fieldArray[rowIndex][colIndex]
                                  .getCellCharacter() == 
                                  CellClass.STICK_BONE_CHAR && rowIndex + 1 != 
                                  fieldArrayHeight)
                               {
                                  // increment the stick length counter to help
                                  // find the total stick length
                                  stickLengthCounter++;
                                  
                                  // increment the row index so the while 
                                  // loop eventually quits
                                  rowIndex++;
                               }
                            
                            // at each index of the sticArray starting at zero,
                            // store an artifact class object containing the 
                            // xPos, yPos, bone or stick, and stick length
                            stickArray[stickArrayCounter] = new ArtifactClass
                                  (colIndex, yPos, ArtifactClass.BONE, 
                                        stickLengthCounter);
                            stickArrayCounter++;
                            
                         }
                    
                      // increment the row index
                      rowIndex++;
                   }
                
                // increment the column index and reset the row 
                // index back to zero for its next time through the while loop
                colIndex++;
                rowIndex = 0;
             }
       }

    /**
     * Generates random values between given low, high, inclusive
     * 
     * @param low integer value representing low end of random output
     * 
     * @param high integer value representing high end of random output
     * 
     * @return integer random value returned between low and high
     * parameters, inclusive
     */
    public int generateRandBetween( int low, int high )
       {
        int range = high - low + 1;
        
        return (int)( Math.random() * range ) + low;          
       }    

    /**
     * Shows field of sticks and bones, has frame around perimeter
     */
    public void showField()
       {
          // initialize variables
          int frameCounter;
          int colIndex;
          int rowIndex;
          
          System.out.println("Field Display: ");
          System.out.println();
          
          // print out vertical lines on either side of the array field, 
          // loop through the field array width to create the top of the field.
          System.out.print(VERTICAL_LINE);
          for (frameCounter = 0; frameCounter < fieldArrayWidth; frameCounter++)
             {
                System.out.print(HORIZ_LINE);
             }
          System.out.println(VERTICAL_LINE);
          
          // begin a loop for all columns
          for (colIndex = 0; colIndex < fieldArrayHeight; colIndex++)
             {
                // prints out a vertical line for each column
                System.out.print(VERTICAL_LINE);
                
                // loop through all of the rows as well
                for (rowIndex = 0; rowIndex < fieldArrayWidth; rowIndex++)
                   {
                      
                      // using the rows and column indices, loop through each
                      // object in the field array, printing its character.
                      System.out.print(fieldArray[colIndex][rowIndex].
                            getCellCharacter());
                   }
                
                // print another vertical line with spacing
                System.out.println(VERTICAL_LINE);
             }
          
          // print out vertical lines on either side of the array field, 
          // loop through the field array width to create the top of the field.
          System.out.print(VERTICAL_LINE);
          for (frameCounter = 0; frameCounter < fieldArrayWidth; frameCounter++)
             {
                System.out.print(HORIZ_LINE);
             }
          System.out.println(VERTICAL_LINE);
          System.out.println();
          
          
       }
    
    /**
     * Shows bone list, provides results in formatted, aligned output
     * Hint: Google System.out.format
     *  
     */
    public void showBoneList()
       {
          // initialize method/variables
          int boneListCounter;
          
          // print out the amount of bones found
          System.out.print("Found ");
          System.out.print(numBones);
          System.out.println(" bones:");
        
          // loop through every object in the boneArray
          for (boneListCounter = 0; boneListCounter < numBones; 
                boneListCounter++)
           {
              // output xPos, yPos, and length in a neatly formatted way
              System.out.format("Bone at ( %2d, %2d ), "
                    + "length is: %2d", boneArray[boneListCounter].xPos, 
                    boneArray[boneListCounter].yPos, 
                    boneArray[boneListCounter].length);
              System.out.println();
              
           }
        // print a line for spacing
        System.out.println();
       }

    /**
     * Shows list of sticks, provides results in formatted, aligned output
     * Hint: Google System.out.format 
     *    
     */
    public void showStickList()
       {
          // initialize method/variables
          int stickListCounter;
          
          // print out the total number of sticks found
          System.out.print("Found ");
          System.out.print(numSticks);
          System.out.println(" sticks:");
        
          // loop through every object in the stick array
          for (stickListCounter = 0; stickListCounter < numSticks; 
                stickListCounter++)
             {
                // output xPos, yPos, and length in a neatly formatted way
                System.out.format("Stick at ( %2d, %2d ), "
                      + "length is: %2d", stickArray[stickListCounter].xPos, 
                      stickArray[stickListCounter].yPos, 
                      stickArray[stickListCounter].length);
                System.out.println();
              
             }
          
          // print an extra line for spacing
          System.out.println();
       }
    
   }

