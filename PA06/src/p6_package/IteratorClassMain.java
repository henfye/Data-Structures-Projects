package p6_package;

public class IteratorClassMain
   {
    public static final int TWO_DIGIT_LOW = 10;
    public static final int TWO_DIGIT_HIGH = 99;
    
    public static void main(String[] args)
       {
        IteratorClass itCls = new IteratorClass();
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


       }

    public static int getRandBetween( int lowVal, int highVal )
       {
        int range = highVal - lowVal + 1;
        
        return (int)( Math.random() * range + lowVal );
       }
   }
