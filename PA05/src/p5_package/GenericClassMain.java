package p5_package;

/** Provides workbench for testing StudentClass and GenericArrayClass
 * 
 * @author MichaelL
 *
 */
public class GenericClassMain
   {
      /** main method for driving multiple tests on Generic queue and stack
       * 
       * @param args command-line string input arguments
       */
      public static void main(String[] args)
         {
          GenericQueueClass<StudentClass> genQueueClass;
          GenericStackClass<StudentClass> genStackClass;          
          StudentClass studentData;
          StudentClass[] testData = new StudentClass[ 10 ];
          int index;
          
          System.out.println( 
                           "\n --- Beginning of Stack/Queue Test Program--- " );                  

          // set up test data
          testData[ 0 ] = new StudentClass( "Casolari, Lyle", 
                                                     554535, 'M', 3.260673916 );
          testData[ 1 ] = new StudentClass( "Serate, Alexis", 
                                                     183761, 'F', 2.417047022 );
          testData[ 2 ] = new StudentClass( "Goldberg, Zachary", 
                                                     557631, 'M', 3.781838392 );
          testData[ 3 ] = new StudentClass( "Niday, Breanna",
                                                     898497, 'F', 3.964983416 );
          testData[ 4 ] = new StudentClass( "Andrieu, Andrew", 
                                                     295673, 'M', 3.800520395 );
          testData[ 5 ] = new StudentClass( "Ahmed, Alexandra", 
                                                     214029, 'F', 2.503090728 );
          testData[ 6 ] = new StudentClass( "Hunt, Stephanie", 
                                                       597706, 'F', 3.7864219 );
          testData[ 7 ] = new StudentClass( "Volkmar, Mason", 
                                                     641651, 'M', 3.325557771 );
          testData[ 8 ] = new StudentClass( "Goffinet, Jahshua", 
                                                     722457, 'M', 2.258524754 );
          testData[ 9 ] = new StudentClass( "Buck, Phoebe", 
                                                     729716, 'F', 3.701569389 );
          
////////////////////////////////////////////////////////////////////////////////
// Queue tests conducted
          
          System.out.println( "\nQueue Tests Conducted ---------------------" );
          
          genQueueClass = new GenericQueueClass<StudentClass>();
          
          for( index = 0; index < 5; index++ )
             {
              genQueueClass.enqueue( testData[ index ] );
              
              System.out.println( "\nStudent data " 
                                 + testData[ index ].toString() + " enqueued." );
              
              genQueueClass.displayQueue();
             }
          
          for( index = 0; index < 5; index++ )
             {
              studentData = genQueueClass.dequeue();
              
              System.out.println( "\nStudent data " 
                                                 + studentData + " dequeued." );
              
              genQueueClass.displayQueue();
             }
          
////////////////////////////////////////////////////////////////////////////////
// Stack tests conducted
          
          System.out.println( "\nStack Tests Conducted ---------------------" );
          
          genStackClass = new GenericStackClass<StudentClass>();
          
          for( index = 0; index < 5; index++ )
             {
              genStackClass.push( testData[ index ] );
              
              System.out.println( "\nStudent data " 
                                + testData[ index ].toString() + " pushed." );
              
              genStackClass.displayStack();
             }
          
          for( index = 0; index < 5; index++ )
             {
              studentData = genStackClass.pop();
              
              System.out.println( "\nStudent data " 
                                                 + studentData + " popped." );
              
              genStackClass.displayStack();
             }
                    
          System.out.println( "\n --- End of Stack/Queue Test Program--- " );                  
         }

   }

