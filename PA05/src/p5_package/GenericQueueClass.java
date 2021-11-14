package p5_package;

/**
 * Description: Java Queue class of generic data (classes)
 * <p>
 * Note: Only works with class that extends Comparable, as shown in class declaration
 * <p>
 * Note: Queue automatically resizes as needed
 * <p>
 * Note: Queue is maintained within array with front and rear indices rotated as needed to manage queue; array capacity is only increased when the queue array is full
 * 
 * @author Samuel Fye
 *
 * @param <GenericData>
 */

public class GenericQueueClass<GenericData extends Comparable<GenericData>>
   {
      /**
       * Constant for default size of queue
       */
      private static final int DEFAULT_CAPACITY = 10;
      
      /**
       * Constant use for space
       */
      private static final char SPACE = ' ';
      
      /**
       * Array holding queued items
       */
      private Object[] queueArray;
      
      /*
       * Queue size and capacity
       */
      private int queueSize;
      
      /**
       * Queue size and capacity
       */
      private int queueCapacity;
      
      /**
       * Index of front and rear of queue
       */
      private int frontIndex;
      
      /**
       * Index of front and rear of queue
       */
      private int rearIndex;
      
      /**
       * Default constructor, initializes queue to default capacity (10)
       */
      public GenericQueueClass()
      {
         queueArray = new Object[DEFAULT_CAPACITY];
         queueCapacity = DEFAULT_CAPACITY;
         frontIndex = 0;
         rearIndex = -1;
         queueSize = 0;
      }
      
      /**
       * Initializing constructor, initializes queue to specified capacity
       * 
       * @param capacity maximum capacity specification for the queue
       */
      public GenericQueueClass(int capacity)
      {
         queueArray = new Object[capacity];
         queueCapacity = capacity;
         frontIndex = 0;
         rearIndex = -1;
         queueSize = 0;
      }
      
      /**
       * Clears queue of all valid values by setting array size to zero, values remain in array but are not accessible
       */
      public void clear()
      {
         queueSize = 0;
         frontIndex = 0;
         rearIndex = 0;
      }
      
      /**
       * Dequeues item from queue
       * 
       * @return dequeued value if successful
       */
      @SuppressWarnings("unchecked")
      public GenericData dequeue()
      {
         // initialize method/variables
         GenericData tempValue;
         
         // check if the array is empty
         if (!isEmpty())
            {
               // use a temp value to store the value to be returned
               tempValue = (GenericData)queueArray[frontIndex];
               
               // increment the front as it is being dequeued
               frontIndex++;
               
               // if front exceeds capacity, place back at 0
               if (frontIndex % queueCapacity == 0)
                  {
                     frontIndex = 0;
                  }
               
               // decrement the size of the queue array
               queueSize--;
               
               // return the stored dequeued value
               return tempValue;
            }
         
         // return null if array is empty
         return null;
      }
      
      /**
       * Description: shows stack from front to rear
       * <p>
       * No display if stack is empty
       */
      @SuppressWarnings("unchecked")
      public void displayQueue()
      {
         // initialize method/variables
         GenericData item;
         int index;
         int numSpaces = 15;
         
         // check if the queue is empty, don't print if it is
         if ( !isEmpty() )
            {
               
               // manually print out queue front for the first entry
               System.out.print("Queue Front: ");
               
               // loop through all of the data besides the final
               for ( index = frontIndex; index <= rearIndex - 1; index++ )
                  {
                     
                     // set a variable equal to each piece of data
                     item = (GenericData)queueArray[index];
                     
                     // print out the data
                     System.out.println(item.toString());
                     
                     // print spaces for formatting and increment each time
                     printChars(numSpaces, SPACE);
                     numSpaces += 3;
                  }
               
               // print out queue rear and the final piece of data
               System.out.print("Queue Rear: ");
               System.out.println((GenericData)queueArray[rearIndex]);
            }
      }
      
      /**
       * Description: Enqueues item into array
       * <p>
       * Checks for full queue and resizes as needed prior to data enqueue
       * 
       * @param newValue GenericData value to be inserted into queue
       */
      public void enqueue(GenericData newValue)
      {
         
         // check if queueSize has reached queueCapacity and resize if need be
         if (queueSize == queueCapacity)
            {
               resize();
            }
         
         // increment the rear index as it starts at -1
         rearIndex++;
         
         // check if rear reaches capacity and reset back to zero if so
         if (rearIndex % queueCapacity == 0)
            {
               rearIndex = 0;
            }
         
         // add on the new value to rear index and increment the array size
         queueArray[rearIndex] = newValue;
         queueSize++;

      }
      
      /**
       * Tests for size of queue equal to zero, no valid values stored in array
       * 
       * @return Boolean result of test for empty
       */
      public boolean isEmpty()
      {
         // check if queue size is 0 and return true, meaning it is empty
         // otherwise return false
         if (queueSize == 0)
            {
               return true;
            }
         return false;
      }
      
      
      /**
       * returns value at front of queue without dequeuing
       * 
       * @return GenericData front value if successful, null if not
       */
      @SuppressWarnings("unchecked")
      public GenericData peekFront()
      {
         // if the array is empty then it is unsuccessful and returns null
         if (isEmpty())
            {
               return null;
            }
         
         // simply return the front index element in the array
         return (GenericData) queueArray[frontIndex];
         
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
         // loop until there are no more characters to be printed
         if (numChars > 0)
            {
               // decrement the num of chars left, recall recursively
               numChars--;
               printChars(numChars, outChar);
               
               // once all calls to printChars have been made, print out
               // the input character
               System.out.print(outChar);
            }
            
      }
      
      /**
       * Description: Resets array capacity to twice the current capacity
       */
      public void resize()
      {
         // initialize method/variables
         int index;
         
         // initialize a temp array with double the capacity
         Object[] tempArray = new Object[queueCapacity * 2];
         
         // check if capacity equals queue size on whether or not to resize
         if ( queueCapacity == queueSize)
            {
               // loop through the entire array and empty into temp array
               for (index = 0; index < queueSize; index++)
                  {
                     tempArray[index] = queueArray[index];
                  }
               
               // set queueArray to tempArray
               queueArray = tempArray;
            }
      }

   }
