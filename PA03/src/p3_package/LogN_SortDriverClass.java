package p3_package;

/**
 * 
 * @author Samuel Fye
 *
 */

public class LogN_SortDriverClass
   {
      /**
       * Default constructor, takes no action for this static tool class
       */
      public LogN_SortDriverClass()
      {
         
      }
      
      /**
       * Compares two strings character by character set to lower case to see which is alphabetically greater than the other;
       * <p>
       * if all tested letters of a name are equal, then compares string lengths
       * <p>
       * Results are as follows:
       * <p>
       * Alphabetically, if strOne is greater than strTwo, returns value greater than zero (e.g., Susan is greater than Bill);
       * <p>
       * if strOne is less than strTwo, returns value less than zero (e.g., Roger is less than Zelda);
       * <p>
       * if strOne is equal to strTwo alphabetically but is different length, returns difference in length (e.g., Will is less than William)
       * <p>
       * if strOne is equal to strTwo both alphabetically and in length, returns zero (e.g., Susan is equal to Susan)
       * <p>
       * Note: .length utility method may used in this method
       * 
       * @param strOne first String value to be compared
       * 
       * @param strTwo second String value to be compared
       * 
       * @return integer difference as specified
       */
      public static int compareStrings(String strOne, String strTwo)
      {
         // initialize method/variables
         int strOneLength;
         int strTwoLength;
         int shorterLength;
         int index;
         char strOneChar, strTwoChar;
         
         // find and store the length of both strings
         strOneLength = strOne.length();
         strTwoLength = strTwo.length();
         
         
         // use an if and else statement to figure out which string has 
         // the shorter length for use in the upcoming for loop
         if (strOneLength < strTwoLength)
            {
               shorterLength = strOneLength;
            }
         else
            {
               shorterLength = strTwoLength;
            }
         
         // loop until the shorter length is reached to avoid index errors
         for (index = 0; index < shorterLength; index++)
            {
               
               // find the characters that will be compared
               strOneChar = strOne.charAt(index);
               strTwoChar = strTwo.charAt(index);
               
               // check if strOneChar is less than strTwoChar, return -1 if so
               if (toLowerCase(strOneChar) < toLowerCase
                     (strTwoChar))
                  {
                     return strOneChar - strTwoChar;
                  }
               
               // check if strOneChar is greater than strTwoChar, return 1 if so
               else if (toLowerCase(strOneChar) > toLowerCase
                     (strTwoChar))
                  {
                     return strOneChar - strTwoChar;
                  }
            }
         
         // if the for loop makes it to the end without returning anything,
         // then they are alphabetically similar and the difference between
         // the two lengths needs to be found
            return strOneLength - strTwoLength;
      }
      
      /**
       * Merges String values brought in between a low and high index segment
       *  (inclusive) of an array
       * <p>
       * Note: uses locally sized single array for temporary storage
       * 
       * @param localArray String array holding unsorted values
       * 
       * @param lowIndex lowest index of array segment to be managed
       * 
       * @param middleIndex middle index of array segment to be managed
       * 
       * @param highIndex high index of array segment to be managed
       */
      private static void runMerge(String[] localArray, int lowIndex, 
            int middleIndex, int highIndex)
      {
         // initialize method/variables
         int index;
         int lowIndexHolder = lowIndex;
         int highIndexHolder = highIndex;
         int middleIndexHolder = middleIndex;
         int tempIndex = 0;
         
         // create a temporary array to hold data
         String[] tempArray = new String[highIndex - lowIndex + 1];
         

         // begin a loop while both the left and right sides of the array
         // have not been completely looped over
         while(lowIndexHolder < middleIndex && middleIndexHolder 
               <= highIndexHolder)
            {
               // compare if the string in the lowerIndex is less than the
               // string in the higherIndex
               if (compareStrings(localArray[lowIndexHolder], 
                     localArray[middleIndexHolder]) < 0)
                  {
                     // if so, stack up the temporary array with the lower val
                     tempArray[tempIndex] = localArray[lowIndexHolder];
                     
                     // increment the lower index and temporary array index
                     lowIndexHolder++;
                     tempIndex++;
                  }
               
               // compare if the string in the lower index is greater than
               // the string in the higher index
               else if (compareStrings(localArray[lowIndexHolder], 
                     localArray[middleIndexHolder]) > 0)
                  {
                     // if no, stack up the temporary array with the lower val
                     tempArray[tempIndex] = localArray[middleIndexHolder];
                     
                     // increment the middle index and the temp array index
                     middleIndexHolder++;
                     tempIndex++;
                  }
               
               // otherwise, these are the same value and it does not matter
               // which one is added first
               else
                  {
                     // add on both the low index and middle index to the 
                     // temporary array
                     tempArray[tempIndex] = localArray[lowIndexHolder];
                     tempArray[tempIndex+1] = localArray[middleIndexHolder];
                     
                     // increment the temporary array index twice and 
                     // increment both low and middle index.
                     tempIndex += 2;
                     lowIndexHolder++;
                     middleIndexHolder++;
                  }
               
            }
         
         // while loop to check if there are any values left in the first
         // section of the array
         while (lowIndexHolder < middleIndex)
            {
               // populate the temp array with the rest of the values 
               tempArray[tempIndex] = localArray[lowIndexHolder];
               lowIndexHolder++;
               tempIndex++;
            }
         
         // while loop to check if there are any values left in the second
         // section of the array
         while (middleIndexHolder <= highIndexHolder)
            {
               // populate the temp array with the rest of the values
               tempArray[tempIndex] = localArray[middleIndexHolder];
               middleIndexHolder++;
               tempIndex++;
            }
         
         // use a for loop to take the entirety of the temp array and 
         // overwrite the local array with it
         for(index = 0; index < highIndex-lowIndex+1; index++)
            {
               localArray[index + lowIndex] = tempArray[index];
            }
            
         }
      
      /**
       * String data sorted using merge sort algorithm
       * <p>
       * Note: Calls runMergeSortHelper with lower and upper indices of array to be sorted
       * 
       * @param localArray String array holding unsorted values
       * 
       * @param size integer value holding number of values in array
       */
      public static void runMergeSort(String[] localArray, int size)
      {
         // call on runMergeSortHelper with correct values
         runMergeSortHelper(localArray, 0, size - 1);
      }
      
      /**
       * Merge sort helper, recursively breaks given array segment down to smaller segments between lowIndex and highIndex (inclusive), then sorts data using merge sort method
       * 
       * @param localArray String array holding unsorted values
       * 
       * @param lowIndex lowest index of array segment to be managed; this varies as the segments are broken down recursively
       * 
       * @param highIndex highest index of array segment to be managed; this varies as the segments are broken down recursively
       */
      private static void runMergeSortHelper(String[] localArray, int lowIndex, int highIndex)
      {
         // initialize method/variables
         int midPoint;
        
         // check that there is more than one element being tested
         if (lowIndex < highIndex)
            {
               // find the midpoint of the high and low indices
               midPoint = (highIndex + lowIndex) / 2;
               
               // recursively run this same method but this time with
               // low index to mid index and mid index to high index
               runMergeSortHelper(localArray, lowIndex, midPoint);
               runMergeSortHelper(localArray, midPoint+1, highIndex);
               
               // take both sections that need to be sorted and pass them
               // through to be merged back together
               runMerge(localArray, lowIndex, midPoint+1, highIndex);
            }
      }
      
      /**
       * Partitions array using the first value as the partition; when this method is complete the partition value is in the correct location in the array
       * 
       * @param localArray String array holding unsorted values
       * 
       * @param lowIndex low index of array segment to be partitioned
       * 
       * @param highIndex high index of array segment to be partitioned
       * 
       * @return integer index of partition pivot
       */
      private static int runPartition(String[] localArray, int lowIndex, int highIndex)
      {
         // initialize method/variables
         int partition = lowIndex;
         int wkgPartitionIndex = lowIndex;
         int wkgIndex = lowIndex+1;
         
         // begin a for loop that goes from low to high index
         for (wkgIndex = lowIndex+1; wkgIndex <= highIndex; wkgIndex++)
            {
               
               // check each wkgIndex against the partition at the beginning 
               // index
               if (compareStrings(localArray[wkgIndex], localArray[partition]) < 0)
                  {
                     // if they are lower than the partiton, increment the 
                     // wkgPartitionIndex and swap the values of the 
                     // wkgIndex and wkgPartitionIndex
                     wkgPartitionIndex++;
                     swapValues(localArray, wkgPartitionIndex, wkgIndex);
                  }
            }
            
         
         // swap wkgPartitionIndex with partition, partition is now 
         // in the correct location of the array
         swapValues(localArray, wkgPartitionIndex, partition);
         
         // return partition index
         return wkgPartitionIndex;
      }
      
      /**
       * Data sorted using quick sort algorithm
       * <p>
       * Note: Call runQuickSortHelper with lower and upper indices of array to be sorted
       * 
       * @param localArray String array holding unsorted values
       * 
       * @param size integer value holding the number of values in the array
       */
      public static void runQuickSort(String[] localArray, int size)
      {
         // runs the quick sort helper method with the correct values
         runQuickSortHelper(localArray, 0, size-1);
      }
      
      /**
       * Helper method run with parameters that support recursive access
       * 
       * @param localArray String array holding unsorted values
       * 
       * @param lowIndex low index of the segment of the array to be processed
       * 
       * @param highIndex high index of the segment of the array to be processed
       */
      private static void runQuickSortHelper(String[] localArray, int lowIndex, int highIndex)
      {
         // initialize method/variables
         int partition = 0;
         
         // check to make sure there is more than one element being tested
         if (lowIndex < highIndex)
            {
               // run the partition method and take in the int that it 
               // returns
                partition = runPartition(localArray, lowIndex, highIndex);
                
                // recursively run this same method to sort back together
                // all of the partitioned areas of the array
                runQuickSortHelper(localArray, lowIndex, partition-1);
                runQuickSortHelper(localArray, partition+1, highIndex);
            }
        
      }
      
      /**
       * Swaps values within given array
       * 
       * @param localArray array of Strings used for swapping
       * 
       * @param indexOne integer index for one of the two items to be swapped
       * 
       * @param indexOther integer index for the other of the two items to be swapped
       */
      private static void swapValues(String[] localArray, int indexOne, int indexOther)
      {
      // initialize variables
         String tempStorage;
         
         // create a temporary storage for the first index
         tempStorage = localArray[indexOne];
         
         // place the second index into the first
         localArray[indexOne] = localArray[indexOther];
         
         // place the temporary into the second index
         localArray[indexOther] = tempStorage;
      }
      
      /**
       * If character is upper case letter (i.e., 'A' - 'Z'), changes letter to lower case (i.e., 'a' - 'z'); otherwise, returns character unchanged
       * 
       * @param testChar character value to be tested and possibly modified
       * 
       * @return character value modified as specified
       */
      private static char toLowerCase(char testChar)
      {
         // check if the character is uppercase
         if (testChar >= 'A' && testChar <= 'Z')
            {
               // if so, use ASCII value to return the lowercase version 
               return (char)(testChar - 'A' + 'a');
            }
         // if not, just return the character
         return testChar;
         
      }
   }
