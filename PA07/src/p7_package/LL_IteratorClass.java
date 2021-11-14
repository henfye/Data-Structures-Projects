package p7_package;
/**
 * Description: Simple iterator class that manages integers using a linked list
 *  engine
 * 
 * @author Samuel Fye
 *
 */

public class LL_IteratorClass
   {
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
       * head node for linked list
       */
      private NodeClass headRef;
      
      /**
       * reference to current node
       */
      private NodeClass currentRef;
      
      /**
       * Default constructor, initializes references to null 
       */
      public LL_IteratorClass()
      {
         headRef = null;
         currentRef = null;
      }
      
      /**
       * Copy constructor
       * 
       * @param copied LL_IteratorClass object to be copied
       */
      public LL_IteratorClass(LL_IteratorClass copied)
      {
         // declare working references
         NodeClass cpdWkgRef;
         NodeClass localWkgRef;
         
         // check that the copied iterator has data in it
         if (copied.headRef != null)
            {
               // create a new headRef, for the new linked list
               headRef = new NodeClass(copied.headRef);
               
               // set up two working refs as well
               cpdWkgRef = copied.headRef;
               localWkgRef = headRef;
               
               // set the copied working ref ahead by one
               cpdWkgRef = cpdWkgRef.nextRef;
               
               
               // loop through the entire linked list and copy it all over
               while (cpdWkgRef.nextRef != null)
                  {
                     localWkgRef.nextRef = new NodeClass(cpdWkgRef);
                     cpdWkgRef = cpdWkgRef.nextRef;
                     localWkgRef = localWkgRef.nextRef;
                  }
            }
         
      }
      
      /**
       * Clears iterator of all valid values by setting head reference to null
       */
      public void clear()
      {
         headRef = null;
         currentRef = null;
      }
      
      /**
       * Description: shows iterator from beginning to end with brackets around 
       * current index
       * <p>
       * Displays "Empty" if list is empty
       */
      public void displayIterator()
      {
         // initialize method/variables
         int index;
         NodeClass wkgRef = headRef;
         
         // check if the list is empty and print out empty if so
         if (isEmpty())
            {
               System.out.print("Empty");
            }
         
         
         else
            {
               // otherwise, loop until the wkgRef is null
               while (wkgRef != null)
                  {
                     // check if current ref, place brackets around if so
                     if (wkgRef == currentRef)
                        {
                           System.out.print(LEFT_BRACKET);
                           System.out.print(wkgRef.data);
                           System.out.print(RIGHT_BRACKET);
                           System.out.print(SPACE);
                        }
                     // otherwise, just print out the wkgref
                     else
                        {
                           System.out.print(wkgRef.data);
                           System.out.print(SPACE);
                        }
                     
                     // place the wkgRef at the next ref
                     wkgRef = wkgRef.nextRef;
                     
                  }
               System.out.println();
            }
      }
      
      /**
       * returns reference prior to current reference
       * <p>
       * Note: Returns null if current reference is at head
       * 
       * @return NodeClass object found at current
       */
      public NodeClass getCurrentPriorRef()
      {
         // initialize wkgRef at headRef
         NodeClass wkgRef = headRef;
         
         // check if the list is empty and return null if so
         // also check if currentRef is at headRef and return null if so
         // !hasPrev does both since it has a check for !isEmpty
         if (!hasPrev())
            {
               return null;
            }
         
         // loop until wkgRef is one before currentRef
         while (wkgRef.nextRef != currentRef)
            {
               wkgRef = wkgRef.nextRef;
            }
         // return the wkgRef
         return wkgRef;
      }
      
      /**
       * returns value at current location
       * 
       * @return integer value at current location
       */
      public int getValueAtCurrent()
      {
         // check if the list is empty and return not found if so
         if (isEmpty())
            {
               return NOT_FOUND;
            }
         
         // simply return current ref data
         return currentRef.data;
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
         return (!isEmpty() && currentRef.nextRef != null);
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
         return (!isEmpty() && currentRef != headRef);
      }
      
      /**
       * inserts item into iterator at current location
       * <p>
       * Note: Does not affect current location
       * 
       * @param newVal integer value to be inserted 
       */
      public void insertAtCurrent(int newVal)
      {
         // check if current is at front of list, simply call insertAtFront
         // if so. hasPrev also checks for empty 
         if (!hasPrev())
            {
               insertAtFront(newVal);
            }
         
         // ***else if needed here since hasPrev has a check for isEmpty
         // if it does have prev, link the prev to new item and link
         // the new item to currentRef
         else if (hasPrev())
            {
               NodeClass priorRef = getCurrentPriorRef();
               priorRef.nextRef = new NodeClass(newVal);
               priorRef.nextRef.nextRef = currentRef;
            }
         
         // This is in case the list is empty
         else
            {
               headRef = new NodeClass(newVal);
               currentRef = headRef;
            }
      }
      
      /**
       * inserts item into iterator at end of iterator
       * <p>
       * Note: Does not affect current location
       * 
       * @param newVal integer value to be inserted
       */
      public void insertAtEnd(int newVal)
      {
         // initialize working reference
         NodeClass wkgRef = headRef;
         
         // check if iterator is empty, set headRef to the newly created
         // item and current ref to head ref
         if (isEmpty())
            {
            headRef = new NodeClass(newVal);
            currentRef = headRef;
            }
         
         // if not empty, loop until the end of the list is reached 
         // and place it at the end
         else
            {
               while (wkgRef.nextRef != null)
                  {
                     wkgRef = wkgRef.nextRef;
                  }
               wkgRef.nextRef = new NodeClass(newVal);
            }
      }
      
      /**
       * inserts item into iterator at beginning of iterator
       * <p>
       * Note: Does not affect current location
       * 
       * @param newVal integer value to be inserted
       */
      public void insertAtFront(int newVal)
      {
         // initialize wkgRef to headRef
         NodeClass wkgRef = headRef;
         
         // Set the headRef to the item being inserted and link it back to
         // the list using wkgRef
         headRef = new NodeClass(newVal);
         headRef.nextRef = wkgRef;
         
         // check if the current reference didn't have a previous value 
         // and set it to the headRef if not
         if (currentRef == null)
            {
               currentRef = headRef;
            }
      }
      
      /**
       * moves current location to the right, if not at end
       * 
       * @return Boolean result of action
       */
      public boolean moveNext()
      {
         // check if there is space to move and move to the right if so
         if (hasNext())
            {
               currentRef = currentRef.nextRef;
               return true;
            }
         return false;
      }
      
      /**
       * moves current location to the left, if not at the beginning
       * 
       * @return Boolean result of action
       */
      public boolean movePrev()
      {
         
      // check if there is space to move and move to the left if so
         if (hasPrev())
            {
               currentRef = getCurrentPriorRef();
               return true;
            }
         return false;
      }
      
      /**
       * Tests for empty linked list
       * 
       * @return Boolean result of test for empty
       */
      public boolean isEmpty()
      {
         return (headRef == null);
      }
      
      /**
       * removes item from iterator at current location
       * <p>
       * Note: Must reset current location if last item removed
       * <p>
       * Note: Must set current reference to null if last item removed
       * 
       * @return integer value if successful, NOT_FOUND if not
       */
      public int removeAtCurrent()
      {
         // initialize method/variables
         int removedInt = currentRef.data;
         NodeClass prevNode = getCurrentPriorRef();
         
         // check if the iterator is empty
         if (isEmpty())
            {
               return NOT_FOUND;
            }
         
         // if not empty, check if item is last item, set everything to null
         else if (!hasPrev() && !hasNext())
            {
               clear();
            }
         
         // check if item is at the end of the list, drop current down and
         // null out the end
         else if (!hasNext())
            {
               currentRef = prevNode;
               currentRef.nextRef = null;
            }
         
         // check if item is at beginning of list, set headRef to next and 
         // current to next
         else if (!hasPrev())
            {
               headRef = headRef.nextRef;
               currentRef = currentRef.nextRef;
            }
         
         // otherwise, simply find previous and next values and link together
         // move current up to next
         else
            {
               prevNode.nextRef = currentRef.nextRef;
               currentRef = currentRef.nextRef;
            }
         
         return removedInt;
         
      }
      
      /**
       * sets current location to beginning
       * 
       * @return Boolean true if list is not empty, false otherwise
       */
      public boolean setToFirst()
      {
         // simply set current location to head ref if the list is not
         // empty
         if (isEmpty())
            {
               return false;
            }
         currentRef = headRef;
         return true;
      }
      
      /**
       * sets current index to end
       * 
       * @return Boolean true if list is not empty, false otherwise
       */
      public boolean setToLast()
      {
         // 
         if (isEmpty())
            {
               return false;
            }
         
         while (hasNext())
            {
               moveNext();
            }
         return true;
      }
      
      
      /**
       * Internal/Nested Node class for storing integers
       */
      private class NodeClass
      {
         /**
          * NodeClass data
          */
         int data;
         
         /**
          * NodeClass next reference
          */
         NodeClass nextRef;
         
         /**
          * NodeClass default constructor
          */
         @SuppressWarnings("unused")
         public NodeClass()
         {
            this(0);
         }
         
         /**
          * NodeClass initialization constructor
          * 
          * @param value integer value for initialization
          */
         public NodeClass(int value)
         {
            data = value;
            nextRef = null;
         }
         
         /**
          * NodeClass copy constructor
          * 
          * @param copied NodeClass object to be copied
          */
         public NodeClass(NodeClass copied)
         {
            this(copied.data);
         }
      }

   }
