package p7_package;

/**
 * Class uses IteratorClass as data for Stack class
 * 
 * @author Samuel Fye
 *
 */

public class LL_StackClass
   {
      /**
       * IteratorClass object used as data for class
       */
      LL_IteratorClass stackData;
      
      /**
       * Default constructor
       */
      public LL_StackClass()
      {
         stackData = new LL_IteratorClass();
      }
      
      /**
       * Copy constructor
       * 
       * @param copied LL_StackClass object to be copied
       */
      public LL_StackClass(LL_StackClass copied)
      {
         stackData = new LL_IteratorClass(copied.stackData);
         
      }
      
      /**
       * Clears all data
       */
      public void clear()
      {
         stackData.clear();
      }
      
      /**
       * checks for empty stack
       * 
       * @return Boolean result of test
       */
      public boolean isEmpty()
      {
         return stackData.isEmpty();
      }
      
      /**
       * Returns data from top of stack without removing
       * 
       * @return integer value at top of stack
       */
      public int peekTop()
      {
         int foundVal;
         
         stackData.setToFirst();
         
         foundVal = stackData.getValueAtCurrent();
         
         return foundVal;
      }
      
      /**
       * Removes data from top of stack
       * 
       * @return integer value at top of stack
       */
      public int pop()
      {
         stackData.setToFirst();
         
         return stackData.removeAtCurrent();
      }
      
      /**
       * Places data at top of stack
       * 
       * @param value integer value to be pushed onto stack
       */
      public void push(int value)
      {
         stackData.setToFirst();
         stackData.insertAtCurrent(value);
         
      }
   }
