package p6_package;
/**
 * Description: Simple iterator class that manages integers
 * <p>
 * Note: Automatically resizes as needed
 * 
 * @author Samuel Fye
 *
 */

public class IteratorClass
   {
      /**
       * constant for default size of iterator
       */
      private static final int DEFAULT_CAPACITY = 10;
      
      /**
       * constant use for not found
       */
      private static final int NOT_FOUND = -1;
      
      /**
       * constant use for left bracket
       */
      private static final char LEFT_BRACKET = '[';
      
      /**
       * constant use for right bracket
       */
      private static final char RIGHT_BRACKET = ']';
      
      /**
       * constant use for space
       */
      private static final char SPACE = ' ';
      
      /**
       * array holding items
       */
      private int[] iteratorArray;
      
      /**
       * iterator size and capacity
       */
      private int iteratorSize;
      
      /**
       * iterator size and capacity
       */
      private int iteratorCapacity;
      
      /**
       * index of current index of iterator
       */
      private int currentIndex;
      
      /**
       * Default constructor, initializes iterator to default capacity (10)
       */
      public IteratorClass()
      {
         this(DEFAULT_CAPACITY);
      }
      
      /**
       * Initializing constructor, initializes iterator to specified capacity
       * 
       * @param capacity initial capacity specification for the iterator
       */
      public IteratorClass(int capacity)
      {
         iteratorArray = new int[capacity];
         iteratorSize = 0;
         iteratorCapacity = capacity;
         currentIndex = 0;
      }
      
      /**
       * Clears iterator of all valid values by setting array size and current 
       * index to zero, values remain in array but are not accessible
       */
      public void clear()
      {
         currentIndex = 0;
         iteratorSize = 0;
      }
      
      /**
       * Description: shows iterator from beginning to end with brackets around current index
       * <p>
       * Displays "Empty" if iterator is empty
       */
      public void displayIterator()
      {
         // initialize method/variables
         int iteratorIndex; 
         
         System.out.print("Iterator: ");
         
         // check if iterator size is empty
         if (isEmpty())
            {
               System.out.println("Empty");
            }
         
         // otherwise loop through the entire iterator from zero to the end
         else
            {
               for (iteratorIndex = 0; iteratorIndex < iteratorSize; 
                     iteratorIndex++)
                  {
                     
                     // check if the current number needs to have brackets
                     // around it
                     if (iteratorIndex == currentIndex)
                        {
                           System.out.print(LEFT_BRACKET);
                           System.out.print(iteratorArray[iteratorIndex]);
                           System.out.print(RIGHT_BRACKET);
                           System.out.print(SPACE);
                        }
                     
                     // otherwise simply print out the number with a space
                     else
                        {
                           System.out.print(iteratorArray[iteratorIndex]);
                           System.out.print(SPACE);
                        }
                  }
               System.out.println();
            }
      }
      
      /**
       * returns value at current index
       * 
       * @return integer value at current index
       */
      public int getValueAtCurrent()
      {
         int currentVal;
         
         currentVal = iteratorArray[currentIndex];
         
         return currentVal;
      }
      
      /**
       * checks for next element available in iterator
       * <p>
       * Note: Uses one line of code
       * 
       * @return Boolean result of test
       */
      public boolean hasNext()
      {
         return (currentIndex < iteratorSize - 1);
      }
      
      /**
       * checks for previous element available in iterator
       * <p>
       * Note: Uses one line of code
       * 
       * @return Boolean result of test
       */
      public boolean hasPrev()
      {
         return (currentIndex > 0);
      }
      
      /**
       * inserts item into iterator at current index
       * <p>
       * Note: Must check for resize after operation
       * 
       * @param newVal integer value to be inserted
       */
      public void insertAtCurrent(int newVal)
      {
         int index;
         
         // increment size up one to allow all elements to be pushed up
         // to create space for the inserted element
         iteratorSize++;
         
         // loop from end of array to just before current index
         for (index = iteratorSize - 1; index > currentIndex; index-- )
            {
               // bring up all items
               iteratorArray[index] = iteratorArray[index - 1];
            }
         
         // place the new value at the current index
         iteratorArray[currentIndex] = newVal;
         
         // check if the iterator array needs to be resized
         resize();
      }
      
      /**
       * inserts item into iterator at end of iterator
       * <p>
       * Note: Does not affect current index
       * <p>
       * Note: Must check for resize after operation
       * 
       * @param newVal integer value to be inserted
       */
      public void insertAtEnd(int newVal)
      {
         
         iteratorArray[iteratorSize] = newVal;
         resize();
         iteratorSize++;
      }
      
      /**
       * inserts item into iterator at beginning of iterator
       * <p>
       * Note: Does not affect current index
       * <p>
       * Note: Must check for resize after operation
       * 
       * @param newVal integer value to be inserted
       */
      public void insertAtFront(int newVal)
      {
         int index;
         
         // increment size for upcoming loop
         iteratorSize++;
         
         // 
         for (index = iteratorSize - 1; index > 0; index--)
            {
               iteratorArray[index] = iteratorArray[index - 1];
            }
         
         iteratorArray[0] = newVal;
         
         resize();
      }
      
      /**
       * moves current index to the right, if not at end
       * 
       * @return Boolean result of action
       */
      public boolean moveNext()
      {
         if (currentIndex < iteratorSize - 1)
            {
               currentIndex++;
               return true;
            }
         return false;
      }
      
      /**
       * moves current index to the left, if not at beginning
       * 
       * @return Boolean result of action
       */
      public boolean movePrev()
      {
         if ( currentIndex > 0 )
            {
               currentIndex--;
               return true;
            }
         return false;
      }
      
      /**
       * Tests for size of array equal to zero, no valid values stored in array
       * 
       * @return Boolean result of test for empty
       */
      public boolean isEmpty()
      {
         if (iteratorSize == 0)
            {
               return true;
            }
         return false;
      }
      
      /**
       * removes item from iterator at current index
       * <p>
       * Must resolve condition if last item removed
       * 
       * @return integer value if successful, NOT_FOUND if not
       */
      public int removeAtCurrent()
      {
         // initialize method/variables
         int index; 
         int holderVal = iteratorArray[currentIndex];
         
         
         if (isEmpty())
            {
               return NOT_FOUND;
            }
         
         
         // loop from the current to the end, overwriting the deleted
         // value
         for (index = currentIndex; index < iteratorSize - 1; index++)
            {
               iteratorArray[index] = iteratorArray[index+1];
            }
         
         // check if the current index is at the end and decrement if so
         if (currentIndex == iteratorSize - 1)
            {
               currentIndex--;
            }
         
         iteratorSize--;
         
         
         return holderVal;
      }
      
      /**
       * Description: Resets array capacity to twice the current capacity only 
       * as needed
       */
      public void resize()
      {
         // initialize method/variables
         int index;
         int[] tempArray;
         
         // check if resizing is required
         if (iteratorSize == iteratorCapacity)
            {
               iteratorCapacity *= 2;
               tempArray = new int[iteratorCapacity];
               // loop through the entire array and place it all into
               // a temp array
               for (index = 0; index < iteratorSize; index++)
                  {
                     tempArray[index] = iteratorArray[index];
                  }
               
               // alias iteratorArray to tempArray, tempArray then goes out of
               // scope
               iteratorArray = tempArray;
            }
      }
      
      /**
       * sets current index to beginning
       * 
       * @return Boolean result of action
       */
      public boolean setToFirst()
      {
         if (isEmpty())
            {
               return false;
            }
         
         currentIndex = 0;
         return true;
      }
      
      /**
       * sets current index to end
       * 
       * @return Boolean result of action
       */
      public boolean setToLast()
      {
         if (isEmpty())
            {
               return false;
            }
         
         currentIndex = iteratorSize - 1;
         return true;
      }
   }
