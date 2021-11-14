package p5_package;

/**
 * Description: Class wrapper for a Java stack of generic data (classes)
 * <p>
 * Note: Only works with class that extends Comparable, as shown in class declaration
 * <p>
 * Note: Maintains a capacity value for maximum number of items that can be stored, and a size value for the number of valid or viable data items in the stack
 * 
 * @author Samuel Fye
 *
 * @param <GenericData>
 */

public class GenericStackClass<GenericData extends Comparable<GenericData>>
   {
      /**
       * constant for default size of stack
       */
      private static final int DEFAULT_CAPACITY = 10;
      
      /**
       * Constant use for space
       */
      private static final char SPACE = ' ';
      
      /**
       * Array holding stack items
       */
      private Object[] stackArray;
      
      /*
       * Stack size and capacity
       */
      private int stackSize;
      
      /**
       * Stack size and capacity
       */
      private int stackCapacity;
      
      /**
       * Default constructor, initializes stack to default capacity (10)
       */
      public GenericStackClass()
      {
         stackArray = new Object[DEFAULT_CAPACITY];
         stackCapacity = DEFAULT_CAPACITY;
         stackSize = 0;
      }
      
      /**
       * Initializing constructor, initializes stack to specified capacity
       * 
       * @param capacity maximum capacity specification for the stack
       */
      public GenericStackClass(int capacity)
      {
         stackArray = new Object[capacity];
         stackCapacity = capacity;
         stackSize = 0;
      }
      
      /**
       * Clears stack of all valid values by setting array size to zero, values remain in array but are not accessible
       */
      public void clear()
      {
         // set size to 0
         stackSize = 0;
      }
      
      /**
       * Description: shows stack from top to bottom
       * <p>
       * No display if stack is empty
       */
      @SuppressWarnings("unchecked")
      public void displayStack()
      {
         // initialize method/variables
         int index;
         GenericData item;
         int numSpaces = 15;
         
         // check if stack is empty, don't display if so
         if ( !isEmpty() )
            {
               // print out front 
               System.out.print("Stack Front: ");
               
               // loop through entire stack besides final element
               for ( index = stackSize - 1; index > 0; index-- )
                  {
                     // store and print out each array element besides the final
                     item = (GenericData)stackArray[index];
                     System.out.println(item.toString());
                     
                     // print out spaces and increment each time
                     printChars(numSpaces, SPACE);
                     numSpaces += 3;
                  }
               
               // print out the final element in the stack
               System.out.print("Stack Rear: ");
               System.out.println((GenericData)stackArray[0]);
            }
         
      }
      
      /**
       * Tests for size of stack equal to zero, no valid values stored in array
       * 
       * @return Boolean result of test for empty
       */
      public boolean isEmpty()
      {
         
         // if stack equals zero return true else return false
         if (stackSize == 0)
            {
               return true;
            }
         return false;
      }
      
      /**
       * returns value at top of stack without popping
       * 
       * @return GenericData front value if successful, null if not
       */
      @SuppressWarnings("unchecked")
      public GenericData peekTop()
      {
         
         // check if the stack is empty, if not then simply return the 
         // element at index stackSize - 1
         if (isEmpty())
            {
               return null;
            }
         
         return (GenericData) stackArray[stackSize - 1];
      }
      
      /**
       * pops item from stack
       * 
       * @return GenericData popped value if successful, null if not
       */
      @SuppressWarnings("unchecked")
      public GenericData pop()
      {
         // check if not empty, decrement stack size and return the element
         // at index stack size, otherwise return null
         if (!isEmpty())
            {
               stackSize--;
               return (GenericData) stackArray[stackSize];
            }
         return null;
      }
      
      /**
       * Description: prints specified number of characters recursively
       * 
       * @param numChars integer value specifying number of characters
       * 
       * @param outChar character to be output
       */
      public void printChars(int numChars, char outChar)
      {
         
         // loop from numChars down to zero, decrement and recursively call
         // printChars, print out a char once the bottom is reached
         if (numChars > 0)
            {
               numChars--;
               printChars(numChars, outChar);
               System.out.print(outChar);
            }
      }
      
      /**
       * Description: pushes item onto array
       * <p>
       * Checks for full stack and resizes as needed prior to data push
       * 
       * @param newValue GenericData value to be pushed onto stack
       */
      public void push(GenericData newValue)
      {
         // check if capacity has been reached, resize if necessary
         if (stackSize == stackCapacity)
            {
               resize();
            }
         
         // place the value at index stackSize and increment
         stackArray[stackSize] = newValue;
         stackSize++;
      }
      
      /**
       * Description: Resets array capacity to twice the current capacity
       */
      public void resize()
      {
         // initialize method/variables
         int index;
         
         // initialize a temp array with double the capacity
         Object[] tempArray = new Object[stackCapacity * 2];
         
         // check if capacity equals queue size on whether or not to resize
         if ( stackCapacity == stackSize)
            {
               // loop through the entire array and empty into temp array
               for (index = 0; index < stackSize; index++)
                  {
                     tempArray[index] = stackArray[index];
                  }
               
               // set the stack array equal to the temp array
               stackArray = tempArray;
            }
      }
   }
