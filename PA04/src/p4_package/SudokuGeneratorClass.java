package p4_package;

/**
 * 
 * @author Samuel Fye
 *
 */

public class SudokuGeneratorClass
   {
      
      /**
       * Private cell class to identify fixed (locked) cells and data (number)
       * @author Samuel Fye
       *
       */
      private class CellNode
      {
         /**
          * Boolean value indicating a fixed cell which may not be changed once
          *  it has been set
          */
         boolean fixedCell;
         
         /**
          * cell value for Sudoku game
          */
         int value;
         
         /**
          * Default constructor for cell node
          */
         public CellNode()
         {
            fixedCell = false;
            value = 0;
         }
         
         /**
          * Copy constructor for cell node
          * 
          * @param copied CellNode object to be copied
          */
         public CellNode(CellNode copied)
         {
            fixedCell = copied.fixedCell;
            value = copied.value;
         }
         
      }
      
      /**
       * Constant for side of grid
       */
      private final int GRID_SIDE = 9;
      
      /**
       * Constant for side of sub grid
       */
      private final int SUB_GRID_SIDE = 3;
      
      /**
       * Constant for range of numbers in Sudoku
       */
      private final int SUDOKU_RANGE = 9;
      
      /**
       * Two dimensional array for holding cell nodes with fixed/locked code 
       * and number
       */
      private CellNode[][] sudokuArray;
      
      
      /**
       * Default generator class array sets up and initializes the Sudoku array
       */
      public SudokuGeneratorClass()
      {
         // initialize indices for looping the array
         int colIndex, rowIndex;
         
         // initialize the capacity of the sudoku array
         sudokuArray = new CellNode[SUDOKU_RANGE][SUDOKU_RANGE];
         
         // begin two for loops to loop through entire array capacity
         for (rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++)
            {
               for (colIndex = 0; colIndex < GRID_SIDE; colIndex++)
                  {
                     // create a cell node object for each location in the
                     // array
                     sudokuArray[rowIndex][colIndex] = new CellNode();
                  }
            }
      }
      
      /**
       * Generator class copy constructor
       * <p>
       * Note: Must create new CellNode for each copied element to eliminate 
       * aliasing
       * 
       * @param copied SudokuGeneratorClass object to be copied
       */
      public SudokuGeneratorClass(SudokuGeneratorClass copied)
      {
         // initialize method/variables
         int rowIndex;
         int colIndex;
         
         // begin two for loops to loop through entire array
         for (rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++)
            {
               for (colIndex = 0; colIndex < GRID_SIDE; colIndex++)
                  {
                     
                     // create a new cell node from the copied cell node and 
                     // store it in this.sudokuArray
                     CellNode tempCell = new CellNode(copied.sudokuArray
                           [rowIndex][colIndex]);
                     this.sudokuArray[rowIndex][colIndex] = tempCell;
                  }
            }
      }
      
      /**
       * Method called to create the Sudoku game
       * <p>
       * Note: Sets up sub diagonal grids and calls helper
       * 
       * @param numEmpties integer value indicating how many Sudoku cells to 
       * leave for the game player to fill in
       * 
       * @param showGrid Boolean value that supports display of the transactions
       *  and the grids as the program runs
       */
      public void createSudoku(int numEmpties, boolean showGrid)
      {
         System.out.println("Starting Grid:");
         System.out.println();
         
         // call methods that create the sudoku game, including
         // setDiagonalSubGrids, and removeNumbers
         setDiagonalSubGrids();
         createSudokuHelper(0, 0, true);
         
         // after solution is found, remove numbers
         removeNumbers(25);
         
         // print out the final sudoku puzzle
         System.out.println();
         System.out.println("Solution Found: ");
         System.out.println();
         displayGrid();
      }
      
      /**
       * Sudoku creation method tries new numbers at each cell from left to 
       * right and top to bottom; backtracks if numbers don't work for given 
       * cell
       * 
       * @param rowPos integer row location of current element
       * 
       * @param colPos integer column location of current element
       * 
       * @param showGrid Boolean indicator that shows grids and transactions as
       *  the method progresses if true
       * 
       * @return Boolean value to indicate success or failure of the recursive 
       * process
       */
      private boolean createSudokuHelper(int rowPos, int colPos, 
            boolean showGrid)
      {
         // initialize method/variables
         int index;
         boolean tempBool;
        
         // check if the grid needs to be displayed
         if (showGrid == true && colPos == 0)
            {
               System.out.println();
               displayGrid();
               System.out.println();
            }
         
         // check if the current cell is either not filled with an object 
         // or it is not a fixed cell
         if (sudokuArray[rowPos][colPos] == null || 
               sudokuArray[rowPos][colPos].fixedCell == false)
            {
               // start a loop that goes from 1 to 9 to check all 
               // possibilities of numbers that fit
               for (index = 1; index < 10; index++)
                  {
                     
                     // if there is no conflict at all continue
                     if (hasConflict(rowPos, colPos, index) == false)
                        {
                           // create a new cellNode class object
                           CellNode cellNode = new CellNode();
                           
                           // set the object to the index
                           cellNode.value = index;
                           
                           // place the object at the correct position
                           sudokuArray[rowPos][colPos] = cellNode;
                           
                           System.out.println("\t\tTrying " + cellNode.value + 
                                 " at row: " + rowPos + " and column: " + 
                                 colPos);
                           
                           showGrid = true;
                           
                           // increment the column position
                           colPos++;
                           
                           
                           // check if the edge of the array has been reached. 
                           // If so, increment the rowPosition and set column 
                           // back to zero
                           if (colPos == GRID_SIDE)
                              {
                                 rowPos++;
                                 colPos = 0;
                              }

                           
                           // call recursively with the new values
                           tempBool = createSudokuHelper(rowPos, colPos, 
                                 showGrid);
                           
                           // if returns true, pass it on down the chain
                           if (tempBool == true)
                              {
                                 return true;
                              }
                           
                           // otherwise, return back to the colPos and rowPos
                           // values that the program was previously at
                           else
                              {
                                 showGrid = false;
                                 
                                 System.out.println("\tBacktracking from row: " 
                              + rowPos + " and column: " + colPos);
                                 if (colPos == 0)
                                    {
                                       rowPos--;
                                       colPos = 8;
                                    }
                                 else
                                    {
                                       colPos--;
                                    }
                                 
                                 // set the location equal to null to try again
                                 sudokuArray[rowPos][colPos] = null;
                              }


                        }
                  }
               // if no values work, return false
               return false;
            }
         
         else
            {
               showGrid = true;
               // if in a fixed cell, move the column right one
               colPos++;
               
               // if column reaches the end, reset to zero and increment
               // the row position
               if (colPos == GRID_SIDE)
                  {
                     rowPos++;
                     colPos = 0;
                     
                     // check if row has gotten to the edge, return true if so
                     if (rowPos == GRID_SIDE)
                        {
                           System.out.println();
                           displayGrid();
                           return true;
                        }
                     
                     // if it hasn't call createSudokuHelper again 
                     
                    
                     
                  }
               // if it hasn't call createSudokuHelper again
               tempBool = createSudokuHelper(rowPos, colPos, showGrid);
               
               // if a true comes back, pass true down as well
               if (tempBool == true)
                  {
                     return true;
                  }
               
               // otherwise, return false
               else
                  {
                     showGrid = false;
                     System.out.println("\tBacktracking from row: " + rowPos 
                           + " and column: " + colPos);
                     return false;
                  }
            }
      }
      
      /**
       * Displays grid as it is currently set up
       * <p>
       * Uses character formatting for grid display
       */
      public void displayGrid()
      {
         // initialize method/variables
         int rowIndex;
         int colIndex;
         
         // print out top of sudoku table
         System.out.println("\t#===|===|===#===|===|===#===|===|===#");
         
         // loop through the entirety of the rows and columns
         for (rowIndex = 0; rowIndex < 9; rowIndex ++)
            {
               // print out a # for each row
               System.out.print("\t# ");
          
               for (colIndex = 0; colIndex < 9; colIndex++)
                  {
                     
                     // check what is in the sudoku array at the given location
                     // to decide whether the number inside of the object
                     // should be printed, or a space
                     if (sudokuArray[rowIndex][colIndex] == null || 
                           sudokuArray[rowIndex][colIndex].value == 0)
                        {
                           System.out.print(" ");
                        }
                     else
                        {
                           System.out.print(sudokuArray[rowIndex][colIndex]
                                 .value);
                        }
                     
                     // use modulo 3 to know whether or not to print out a 
                     // pound sign or a pipe
                     if ((colIndex + 1) % SUB_GRID_SIDE == 0)
                        {
                           System.out.print(" # ");
                        }
                     else
                        {
                           System.out.print(" | ");
                        }
                  }
               
               // print out a line for formatting
               System.out.println();
               
               // use modulo 3 again to know whether or not to print out a 
               // line with double bars or single bars
               if ((rowIndex + 1) % SUB_GRID_SIDE == 0)
                  {
                     System.out.println("\t#===|===|===#===|===|===#===|===|==="
                           + "#");
                  }
               else
                  {
                     System.out.println("\t#---|---|---#---|---|---#---|---|---"
                           + "#");
                  }
            }
           
            
         
      }
      
      /**
       * Generates random value between 1 and 9
       * <p>
       * Note: Uses double stage process. Calls random to get number between 1 
       * and 9, then loops that many times generating random values. Finally 
       * takes the last value generated. Uses Math.random
       * 
       * @return integer random value generated
       */
      private int genRandSudokuValue()
      {
         // initialize method/variables
         int randValueOne;
         int randValueTwo = 0;
         int randLoopCounter;
         
         // find the first value that will decide how many times to loop 
         // the next random value
         randValueOne = (int)(Math.random() * 10);
         
         // loop from 0 until the random value previously found
         for (randLoopCounter = 0; randLoopCounter < randValueOne; 
               randLoopCounter++)
            {
               // the final run of the loop will decide the random number
               // to be returned
               randValueTwo = (int)(Math.random() * 10);
            }
         return randValueTwo;
      }
      
      /**
       * Checks for conflict of a given number in a given element
       * <p>
       * Note: Uses isInRow, isInCol, and isInSubGrid in one line of code to 
       * indicate if the number has already been used in the same row, the same 
       * column, or the same sub grid
       * 
       * @param rowLocIndex integer row index of element
       * 
       * @param colLocIndex integer column index of element
       * 
       * @param value integer value to be tested for conflict
       * 
       * @return Boolean result of test
       */
      private boolean hasConflict(int rowLocIndex, int colLocIndex, int value)
      {
         // check if there is any conflict at all with a certain location
         if (isInCol(colLocIndex, value) == false && 
               isInRow(rowLocIndex, value) == false && 
               isInSubGrid(rowLocIndex, colLocIndex, value) == false)
            {
               return false;
            }
         return true;
      }
      
      /**
       * Checks for conflict of value in the same column
       * 
       * @param colIndex integer column index
       * 
       * @param value integer value to be tested
       * 
       * @return Boolean result of test
       */
      private boolean isInCol(int colIndex, int value)
      {
         int rowIndex;
       
         // loop through an entire column, checking if there are any conflicts
         for (rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++)
            {
               if (sudokuArray[rowIndex][colIndex] != null && value == 
                     sudokuArray[rowIndex][colIndex].value)
                  {
                     return true;
                  }
            }
         
         return false;
      }
      
      /**
       * Checks for conflict of value in the same row
       * 
       * @param rowIndex integer row index
       * 
       * @param value integer value to be tested
       * 
       * @return Boolean result of test
       */
      private boolean isInRow(int rowIndex, int value)
      {
         int colIndex;
         
         // loop through an entire row, checking if there are any conflicts
         for (colIndex = 0; colIndex < GRID_SIDE; colIndex++)
            {
               if (sudokuArray[rowIndex][colIndex] != null && value == 
                     sudokuArray[rowIndex][colIndex].value)
                  {
                     return true;
                  }
            }
         
         return false;
      }
      
      /**
       * Checks for conflict of value in sub grid
       * <p>
       * Note: Must find upper left corner of sub grid from the row and column 
       * indices, then search the sub grid
       * 
       * @param rowLocIndex integer row index of test item
       * 
       * @param colLocIndex integer column index of test item
       * 
       * @param value integer value to be tested
       * 
       * @return Boolean result of test
       */
      private boolean isInSubGrid(int rowLocIndex, int colLocIndex, int value)
      {
         int rowIndex;
         int colIndex;
         
         // set start row and col to beginning of subgrid by dividing and
         // multiplying by 3
         int startRow = (rowLocIndex / SUB_GRID_SIDE) * SUB_GRID_SIDE;
         int startCol = (colLocIndex / SUB_GRID_SIDE) * SUB_GRID_SIDE;
         
         // run through the entire sub grid to check if there are any conflicts
         for (rowIndex = startRow; rowIndex < startRow + SUB_GRID_SIDE; 
               rowIndex++)
            {
               for (colIndex = startCol; colIndex < startCol + SUB_GRID_SIDE; 
                     colIndex++)
                  {
                     if (sudokuArray[rowIndex][colIndex] != null && value == 
                           sudokuArray[rowIndex][colIndex].value)
                        {
                           return true;
                        }
                  }
            }
            
         return false;
      }
      
      /**
       * Randomly removes the specified number of items from the Sudoku array 
       * for preparing the game
       * 
       * @param numbersToBeRemoved integer number of elements to be cleared
       */
      private void removeNumbers(int numbersToBeRemoved)
      {
         int counter;
         int rowPos, colPos;
         
         // initialize two random numbers to be tested
         rowPos = genRandSudokuValue();
         colPos = genRandSudokuValue();
        
         // start a for loop for all of the numbers to be removed
         for (counter = 0; counter < numbersToBeRemoved; counter++)
            {
               // check that the random numbers are within the array capacity
               // and that the location has not been set to zero already
               while (rowPos == 9 || colPos == 9 || 
                     sudokuArray[rowPos][colPos].value == 0)
                  {
                     // find new values if need be
                     rowPos = genRandSudokuValue();
                     colPos = genRandSudokuValue();
                  }
               
               // set the location equal to zero so that only a space appears
               // when printing out the final table
               sudokuArray[rowPos][colPos].value = 0;
            }
      }
      
      /**
       * Sets all three diagonal sub grids in preparation for setting other 
       * values
       * <p>
       * Note: Calls setInitialSubGrid for each grid to be set up
       */
      private void setDiagonalSubGrids()
      {
         // set up each of the three sub grids
         setInitialSubGrid(0, 0);
         setInitialSubGrid(3, 3);
         setInitialSubGrid(6, 6);
      }
      
      /**
       * Sets one sub grid with non-repeating values
       * 
       * @param startRow integer row of upper left corner of sub grid to set up
       * 
       * @param startCol integer column of upper left corner of sub grid to set 
       * up
       */
      private void setInitialSubGrid(int startRow, int startCol)
      {
         int rowIndex;
         int colIndex;
         int randNum = genRandSudokuValue();
         
         // loop through the entire sub grid, filling in each space with a 
         // unique random number using genRandSudokuValue and isInSubGrid
         for (rowIndex = startRow; rowIndex < startRow + SUB_GRID_SIDE; 
               rowIndex++)
            {
               for (colIndex = startCol; colIndex < startCol + SUB_GRID_SIDE; 
                     colIndex++)
                  {
                     while (isInSubGrid(rowIndex, colIndex, randNum) == true)
                        {
                           randNum = genRandSudokuValue();
                        }
                     sudokuArray[rowIndex][colIndex].fixedCell = true;
                     sudokuArray[rowIndex][colIndex].value = randNum;
                  }
            }
      }
   }


