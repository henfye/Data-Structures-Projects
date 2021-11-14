package p9_package;

/**
 * Driver class for testing TwoThreeTreeClass
 * 
 * @author MichaelL
 *
 */
public class TwoThreeTreeMainClass
   {

    /**
     * Main/Driver method for testing components of TwoThreeTreeClass
     * 
     * @param args String arguments not used
     */
    public static void main(String[] args)
      {
       File_Input_Class fileOps = new File_Input_Class();
       TwoThreeTreeClass tttc = new TwoThreeTreeClass();
       TwoThreeTreeClass tttcCopied = new TwoThreeTreeClass(tttc);
       int nameCount, numNames = 8;
       String newName;
       boolean doesItWork;
       
       fileOps.openInputFile( "./doc/inData.txt" );
       
       for( nameCount = 0; nameCount < numNames; nameCount++ )
          {
           newName = fileOps.getString();
           
           System.out.println( "\nUploaded name: " + newName );
           
           tttc.addItem( newName );
           
           System.out.println( tttc.inOrderTraversal() );
           
           
          }
       doesItWork = tttc.search("Shafer");
       System.out.print(doesItWork);
       
       
       
       for( nameCount = 0; nameCount < numNames; nameCount++ )
          {
           newName = fileOps.getString();
           
           System.out.println( "\nUploaded name: " + newName );
           
           tttcCopied.addItem( newName );
           
           System.out.println( tttcCopied.inOrderTraversal() );
           
           
          }
      }

   }
