package p7_package;

public class LL_IteratorClassMain
   {
      public static final int TWO_DIGIT_LOW = 10;
      public static final int TWO_DIGIT_HIGH = 99;
      
      public static void main(String[] args)
         {
          LL_IteratorClass itCls = new LL_IteratorClass();
          LL_QueueClass queueWorker = new LL_QueueClass();
          LL_StackClass stackWorker = new LL_StackClass();
          
          int index, value;

          for( index = 0; index < 10; index++ )
             {
              value = getRandBetween( TWO_DIGIT_LOW, TWO_DIGIT_HIGH );
              
              itCls.insertAtFront( value );
              
              itCls.displayIterator();
             }
          
          itCls.moveNext();
          itCls.moveNext();
          itCls.moveNext();
          itCls.moveNext();

          for( index = 0; index < 10; index++ )
             {
              value = getRandBetween( TWO_DIGIT_LOW, TWO_DIGIT_HIGH );
              
              itCls.insertAtEnd( value );
              
              itCls.displayIterator();
             }
          
          
          itCls.setToLast();
          itCls.displayIterator();

          itCls.movePrev();
          itCls.movePrev();
          itCls.movePrev();
          itCls.movePrev();
          itCls.movePrev();
          itCls.movePrev();
          itCls.movePrev();
          itCls.movePrev();
          itCls.movePrev();
          itCls.movePrev();
          
          itCls.displayIterator();

          itCls.removeAtCurrent();
          itCls.displayIterator();

          itCls.setToLast();

          itCls.displayIterator();

          itCls.removeAtCurrent();
          itCls.displayIterator();
          
          while( itCls.hasPrev() )
             {
              itCls.movePrev();
             }
          itCls.displayIterator();

          while( itCls.hasNext() )
             {
              itCls.moveNext();
             }
          itCls.displayIterator();
          
          for (index = 0; index < 30; index += 3)
             {
                queueWorker.enqueue(index);
                System.out.println("Value " + index + " enqueued");
                
             }
          
          System.out.println("Front value: " + queueWorker.peekFront());
          
          for (index = 0; index < 10; index++)
             {
                System.out.println("Value " + queueWorker.dequeue() + " dequeued");
             }
          
          for (index = 0; index < 50; index += 5)
             {
                stackWorker.push(index);
                System.out.println("Value " + index + " pushed");
             }
          
          System.out.println("Top Value: " + stackWorker.peekTop());
          
          for (index = 0; index < 10; index++)
             {
                System.out.println("Value " + stackWorker.pop() + " popped");
             }
          
          
          
         }

      public static int getRandBetween( int lowVal, int highVal )
         {
          int range = highVal - lowVal + 1;
          
          return (int)( Math.random() * range + lowVal );
         }
   }
