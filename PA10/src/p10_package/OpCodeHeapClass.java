package p10_package;

/**
 * Array-based OpCodeClass min heap class used as a priority queue
 * for OpCodeClass data
 * 
 * @author Samuel Fye
 *
 */

public class OpCodeHeapClass
   {
      /**
       * Initial array capacity
       */
      public final int DEFAULT_ARRAY_CAPACITY = 10;
      
      /**
       * Array for heap
       */
      private OpCodeClass[] heapArray;
      
      /**
       * Management data for array
       */
      private int arraySize;
      
      /**
       * Management data for capacity
       */
      private int arrayCapacity;
      
      /**
       * Display flag can be set to observe bubble up and trickle down operations
       */
      private boolean displayFlag;
      
      /**
       * Default constructor sets up array management conditions and default 
       * display flag setting
       */
      public OpCodeHeapClass()
      {
         // set everything to its defaults
         arraySize = 0;
         arrayCapacity = DEFAULT_ARRAY_CAPACITY;
         displayFlag = false;
         heapArray = new OpCodeClass[arrayCapacity];
      }
      
      /**
       * Copy constructor copies array and array management conditions and 
       * default display flag setting
       * 
       * @param copied OpCodeHeapClass object to be copied
       */
      public OpCodeHeapClass(OpCodeHeapClass copied)
      {
         int index;
         
         arraySize = copied.arraySize;
         arrayCapacity = copied.arrayCapacity;
         displayFlag = false;
         heapArray = new OpCodeClass[copied.arrayCapacity];
         
         // copy over the entire array in this manner to avoid aliasing
         for (index = 0; index < arraySize; index++)
            {
               heapArray[index] = new OpCodeClass(copied.heapArray[index]);
            }
         
         
      }
      
      /**
       * Accepts OpCodeData item and adds it to heap
       * <p>
       * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data 
       * addition
       * <p>
       * Note: must check for resize before attempting to add an item
       * 
       * @param newItem OpCodeClass data item to be added
       */
      public void addItem(OpCodeClass newItem)
      {
         // begin by checking if the array needs to be resized
         checkForResize();
         
         
         // add item to the index arraySize, since it will be 1 more than
         // the indices of the array
         heapArray[arraySize] = new OpCodeClass(newItem);
         
         // increment array size
         arraySize++;
         
         // call bubbleUpArrayHeap using arraySize - 1 since we don't want
         // the arraySize that was just updated
         bubbleUpArrayHeap(arraySize - 1);
         System.out.println();
      }
      
      /**
       * Recursive operation to reset data in the correct order for the min 
       * heap after new data addition
       * 
       * @param currentIndex index of current item being assessed, and moved up
       * as needed
       */
      private void bubbleUpArrayHeap(int currentIndex)
      {
         int parentIndex = (currentIndex - 1) / 2;
         OpCodeClass tempVal;
         
         // check if currentIndex > 0 so we know when we arrive at the top 
         if (currentIndex > 0)
            {
               if (heapArray[currentIndex].compareTo(heapArray[parentIndex]) < 0)
                  {
                  // if displayFlag is set to true then show the bubble up
                     // operations
                     if (displayFlag)
                        {
                           System.out.println("   - Bubble up:");
                           System.out.println("     - Swapping parent: " + 
                           heapArray[parentIndex] + " with child:  " + 
                                 heapArray[currentIndex]);
                        }
                     
                     // swap current index and parent using a temp 
                     // value to hold onto the current index for swap
                     tempVal = heapArray[currentIndex];
                     heapArray[currentIndex] = heapArray[parentIndex];
                     heapArray[parentIndex] = tempVal;
                     
                     // if displayFlag is set to true then show the bubble up
                     // operations
                     
                     // recursively call bubbleUpArrayHeap with the parent
                     bubbleUpArrayHeap(parentIndex);
                  }
            }
      }
      
      /**
       * Automatic resize operation used prior to any new data addition in the 
       * heap
       * <p>
       * Tests for full heap array, and resizes to twice the current capacity 
       * as required
       */
      private void checkForResize()
      {
         OpCodeClass[] tempArray;
         int index;
         
         // check that the array is full
         if (arraySize == arrayCapacity)
            {
               // double capacity of so
               arrayCapacity *= 2;
               
               // create a new array with the doubled capacity
               tempArray = new OpCodeClass[arrayCapacity];
               
               // loop through entire array and copy over everything
               for (index = 0; index < arraySize; index++)
                  {
                     tempArray[index] = new OpCodeClass(heapArray[index]);
                  }
               
               // alias heapArray to tempArray, tempArray goes out of scope
               heapArray = tempArray;
            }
      }
      
      /**
       * Tests for empty heap
       * 
       * @return boolean result of test
       */
      public boolean isEmpty()
      {
         return arraySize == 0;
      }
      
      /**
       * Removes OpCodeClass data item from top of min heap, thus being the 
       * operation with the lowest priority value
       * <p>
       * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data
       *  removal
       *  
       *  @return OpCodeClass item removed
       */
      public OpCodeClass removeItem()
      {
         // initialize method/variables
         OpCodeClass tempVal;
         int root = 0;
         
         // check that there are values that can be removed
         if (!isEmpty())
            {
               System.out.println("Removing process: " + heapArray[root]);
               
               // begin by decrementing arraySize due to removal
               arraySize--;
               // store the item to be removed in tempVal
               tempVal = heapArray[root];
               
               // store the bottom item at top of tree
               heapArray[root] = heapArray[arraySize];
               
               // store the tempVal at the bottom, outside of array size
               heapArray[arraySize] = tempVal;

               // call trickle down with the value at the top
               trickleDownArrayHeap(root);
               
               System.out.println();
               
               // return the deleted object
               return heapArray[arraySize];
            }
         
         // this means that the array is empty and nothing can be removed
         else
            {
               return null;
            }
      }
      
      /**
       * Utility method to set the display flag for displaying internal 
       * operations of the heap bubble and trickle operations
       * 
       * @param setState flag used to set the state to display or not
       */
      public void setDisplayFlag(boolean setState)
      {
         displayFlag = setState;
      }
      
      /**
       * Dumps array to screen as is, no filtering or management
       */
      public void showArray()
      {
         int index;
         
         for (index = 0; index < arraySize; index++)
            {
               System.out.print(heapArray[index] + "\n");
            }
      }
      
      /**
       * Recursive operation to reset data in the correct order for the min 
       * heap after data removal
       * 
       * @param currentIndex index of current item being assessed, and moved
       * down as required
       */
      private void trickleDownArrayHeap(int currentIndex)
      {
         // initialize method/variables
         OpCodeClass tempVal;
         int smallerIndex;
         Boolean leftChildPrint = false;
         int leftChild = currentIndex * 2 + 1;
         int rightChild = currentIndex * 2 + 2;
         
         
         // check that there is both a left and a right child
         if (leftChild < arraySize && rightChild < arraySize)
            {
               // find which child has the smaller index, record which
               // child we will potentially be swapping with
               if (heapArray[leftChild].compareTo(heapArray[rightChild]) > 0)
                  {
                     smallerIndex = rightChild;
                  }
               else
                  {
                     leftChildPrint = true;
                     smallerIndex = leftChild;
                  }
               
               
               // check that the parent is at least greater than one of the 
               // two children
               if (heapArray[currentIndex].compareTo(heapArray[leftChild]) > 0 ||
                  heapArray[currentIndex].compareTo(heapArray[rightChild]) > 0)
                  {
                     
                     // if so, and if display flag true, print out what is 
                     // being swapped and with what child
                     if (displayFlag)
                        {
                           System.out.println("   - Trickle Down:");
                           System.out.print("     - Swapping parent " 
                           + heapArray[currentIndex].toString() + " with ");
                           if (leftChildPrint)
                              {
                                 System.out.print("left child: ");
                              }
                           else
                              {
                                 System.out.print("right child: ");
                              }
                                 System.out.println(heapArray[smallerIndex]);
                        }
                     
                     
                     // swap the smaller child and the parent 
                     tempVal = heapArray[currentIndex];
                     heapArray[currentIndex] = heapArray[smallerIndex];
                     heapArray[smallerIndex] = tempVal;
                     
                     // call recursively with smaller child index
                     trickleDownArrayHeap(smallerIndex);
                     
                     
                  }
               
               // otherwise, parent is greater than neither, do nothing
            }
         
         // check that there is a left child and not a right child
         else if (leftChild < arraySize)
            {
               // if parent is greater than left child, swap parent and left child
               // call recursively with child
               if (heapArray[currentIndex].compareTo(heapArray[leftChild]) > 0)
                  {
                  // display process if displayFlag set to true
                     if (displayFlag)
                        {
                           System.out.println("   - Trickle Down:");
                           System.out.println("     - Swapping parent " + 
                           heapArray[currentIndex].toString() + 
                           " with left child: " + heapArray[leftChild]);
                        }
                     
                    // swap parent and left child
                     // tempVal = parent 
                     tempVal = heapArray[currentIndex];
                     // parent = left child
                     heapArray[currentIndex] = heapArray[leftChild];
                     
                     // left child = parent
                     heapArray[leftChild] = tempVal;
                     
                  
                     
                     // call recursively with child
                     trickleDownArrayHeap(leftChild);
                  }
               
               // otherwise, we are at the bottom and don't need to do anything
            }
         
         // otherwise, there is no child on either side. Do nothing
         
      }
   }
