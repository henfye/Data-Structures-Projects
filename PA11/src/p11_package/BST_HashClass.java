package p11_package;

/**
 * Hash class that uses BST elements for data storage
 * @author Samuel Fye
 *
 */

public class BST_HashClass
   {
      /**
       * Table size default
       * <p>
       * Table size (capacity) is recommended to be a prime number
       */
      private static final int DEFAULT_TABLE_SIZE = 11;
      
      /**
       * Capacity (Size) of the base table
       */
      private int tableSize;
      
      /**
       * Array for hash table
       */
      private BST_Class[] tableArray;
      
      /**
       * Default constructor
       * <p>
       * Initializes array to default table size; initializes all BST elements
       * <p>
       * Note: One line of code
       */
      public BST_HashClass()
      {
         tableArray = new BST_Class[DEFAULT_TABLE_SIZE];
         tableSize = DEFAULT_TABLE_SIZE;
      }
      
      /**
       * Initialization constructor
       * <p>
       * initializes array to specified table size; intiailizes all BST elements
       * 
       * @param inTableSize sets table size
       */
      public BST_HashClass(int inTableSize)
      {
         tableArray = new BST_Class[inTableSize];
         tableSize = inTableSize;
      }
      
      /**
       * Copy constructor
       * 
       * @param copied BST_HashClass object to be copied
       */
      public BST_HashClass(BST_HashClass copied)
      {
         int index;
         
         tableSize = copied.tableSize;
         
         for (index = 0; index < tableSize; index++)
            {
               tableArray[index] = copied.tableArray[index];
            }
      }
      
      /**
       * Adds item to hash table
       * <p>
       * Uses overloaded addItem with object
       * 
       * @param inName name of student
       * 
       * @param inStudentID student ID
       * 
       * @param inGender gender of student
       * 
       * @param inGPA gpa of student
       */
      public void addItem(String inName, int inStudentID, char inGender, 
                                                                   double inGPA)
      {
         StudentClassNode newItem = new StudentClassNode(inName, inStudentID, inGender, inGPA);
         addItem(newItem);
      }
      
      /**
       * Adds item to has table
       * <p>
       * Overloaded method that accepts StudentClassNode object
       * 
       * @param newItem student class object
       */
      public void addItem(StudentClassNode newItem)
      {
         // initialize method/variables
         int hashIndex;
         
         // generate hash first and find the value of the index
         hashIndex = generateHash(newItem) % tableSize;
         
         // insert the new item into the BST at the index found
         if (tableArray[hashIndex] == null)
            {
               tableArray[hashIndex] = new BST_Class();
            }
         tableArray[hashIndex].insert(newItem);
      }
      
      /**
       * Clears hash table by clearing all BST elements
       */
      public void clearHashTable()
      {
         int index;
         
         for (index = 0; index < tableSize; index++)
            {
               tableArray[index] = null;
            }
      }
      
      /**
       * Searches for item in hash table using student ID as key
       * 
       * @param studentID used for requesting data
       * 
       * @return StudentClassNode object removed, or null if not found
       */
      public StudentClassNode findItem(int studentID)
      {
         // create a temporary student class node to store StudentID into 
         // for calling the generateHash method
         StudentClassNode tempID = new StudentClassNode(null, studentID, '0' , 0);
         StudentClassNode foundItem;
         
         // find the hash code from studentID and modulo it to find the index
         // of its location in the table
         int hashCode = generateHash(tempID);
         int hashIndex = hashCode % tableSize;
         
         // use this index to search for the item
         foundItem = tableArray[hashIndex].search(tempID);
         
         return foundItem;
      }
      
      /**
       * Method uses student ID to generate hash value for use as index in hash
       *  table
       * <p>
       * Process sums the Unicode values of each of the student ID digits
       *  converted to characters, and then creates a hash index
       * 
       * @param studentData StudentClassNode object from which hash value
       *  will be generated
       * 
       * @return integer hash value to be used as array index
       */
      public int generateHash(StudentClassNode studentData)
      {
         // initialize method/variables
         int studentIDHolder = studentData.studentID;
         int tempConverterInt;
         int sum = 0;
         
         while (studentIDHolder > 0)
            {
               // get the final digit farthest right in studentID
               tempConverterInt = studentIDHolder % 10;
               
               // add up a sum of the unicode values
               sum += (int) (tempConverterInt + '0');
               
               // divide studentID by 10 to get next value
               studentIDHolder = studentIDHolder / 10;
               
            }
         
         return sum;
      }
      
      /**
       * Removes item from hash table, using studentID as key
       * 
       * @param studentID used for requesting data
       * 
       * @return StudentClassNode object removed, or null if not found
       */
      public StudentClassNode removeItem(int studentID)
      {
         // create a temporary student class node to store StudentID into 
         // for calling the generateHash method
         StudentClassNode tempID = new StudentClassNode(null, studentID, '0' , 0);
         StudentClassNode deletedItem;
         
         // find the hash code from studentID and modulo it to find the index
         // of its location in the table
         int hashCode = generateHash(tempID);
         int hashIndex = hashCode % tableSize;
         
         deletedItem = tableArray[hashIndex].removeNode(tempID);
         
         
         return deletedItem;
      }
      
      /**
       * traverses through all array bins, finds counts from each BST, then 
       * displays as table
       * <p>
       * Shows table of list lengths, then shows table size, then shows the 
       * number of empty bins and the longest linked list of the set; adapts to any size array
       */
      public void showHashTableStatus()
      {
         int tableIndex;
         int emptyBins = 0;
         int largestBSTCount = 0;
         int smallestBSTCount = tableArray[0].countNodes();
         
         System.out.println("Array node report:");
         System.out.print("             ");
         
         for (tableIndex = 0; tableIndex < tableSize; tableIndex++)
            {
               System.out.printf( "%2d     ", tableArray[tableIndex].countNodes());
               if (tableArray[tableIndex].countNodes() == 0)
                  {
                     emptyBins++;
                  }
               
               
               
               
               
               if (tableArray[tableIndex].countNodes() > largestBSTCount)
                  {
                     largestBSTCount = tableArray[tableIndex].countNodes();
                  }
               
               if (tableArray[tableIndex].countNodes() < smallestBSTCount)
                  {
                     smallestBSTCount = tableArray[tableIndex].countNodes();
                  }
            }
         System.out.println("");
         System.out.print("           ");
         
         
         for (tableIndex = 0; tableIndex < tableSize; tableIndex++)
            {
               System.out.print("-----  ");
            }
         
         System.out.println("");
         System.out.print("  Index:     ");
         
         for (tableIndex = 0; tableIndex < tableSize; tableIndex++)
            {
               System.out.printf("%2d     ", tableIndex);
            }
         System.out.println();
         System.out.println();
         
         System.out.println("With a table size of " + tableSize + ",");
         System.out.println("The number of empty bins was " + emptyBins + ".");
         
         System.out.println("The largest BST count was " + largestBSTCount + " nodes(s).");
         System.out.println("The smallest BST count was " + smallestBSTCount + " nodes(s)");
         System.out.println();
      }
      
      
   }
