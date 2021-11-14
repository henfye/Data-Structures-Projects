package p7_package;

/**
 * Class that is inherited from IteratorClass to implement a queue data 
 * structure
 * 
 * @author Samuel Fye
 *
 */

public class LL_QueueClass extends LL_IteratorClass
   {
      /**
       * Default constructor
       */
      public LL_QueueClass()
      {
         super();
      }
      
      /**
       * Copy constructor
       * 
       * @param copied LL_QueueClaass object to be copied
       */
      public LL_QueueClass(LL_QueueClass copied)
      {
         super(copied);
      }
      
      /**
       * Clears queue
       * 
       * @Overrides clear in class LL_IteratorClass
       */
      public void clear()
      {
         super.clear();
      }
      
      /**
       * Dequeues data
       * 
       * @return integer value from queue
       */
      public int dequeue()
      {
         int removedInt;
         
         super.setToLast();
         removedInt = super.removeAtCurrent();
         return removedInt;
      }
      
      /**
       * Enqueues data
       * 
       * @param value integer value to be enqueued
       */
      public void enqueue(int value)
      {
         super.setToFirst();
         super.insertAtCurrent(value);
      }
      
      /**
       * Checks for empty
       * 
       * @Overrides isEmpty in class LL_IteratorClass
       * 
       * @return Boolean result of test
       */
      public boolean isEmpty()
      {
         return super.isEmpty();
      }
      
      /**
       * Views value at front of queue
       * 
       * @return integer value at front of queue
       */
      public int peekFront()
      {
         int foundValue;
         
         super.setToLast();
         foundValue = super.getValueAtCurrent();
         
         return foundValue;
      }
   }
