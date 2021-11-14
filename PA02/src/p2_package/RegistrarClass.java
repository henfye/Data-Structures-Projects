package p2_package;

/**
 * 
 * @author Samuel Fye
 *
 */

public class RegistrarClass
   {
      /**
       * Constant default capacity
       */
      private int DEFAULT_CAPACITY;
      
      /**
       * Constant used if item not found in array
       */
      private int NOT_FOUND;
      
      /**
       * Private array holding student data
       */
      private StudentClass[] studentArr;
      
      /**
       * Private capacity and size data
       */
      private int capacity;
      
      /**
       * Private capacity and size data
       */
      private int size;
      
      /**
       * Default constructor
       */
      public RegistrarClass()
      {
         // set all values to default values
         this.studentArr = new StudentClass[DEFAULT_CAPACITY];
         this.capacity = DEFAULT_CAPACITY;
         this.size = 0;
      }
      
      /**
       * Initialization constructor
       * 
       * @param initialCapacity integer value to set class initial capacity
       */
      public RegistrarClass(int initialCapacity)
      {
         // set student array size and capacity to initial capacity
         this.studentArr = new StudentClass[initialCapacity];
         this.capacity = initialCapacity;
      }
      
      /**
       * Copy constructor
       * 
       * @param copied RegistrarClass object to be copied
       */
      public RegistrarClass(RegistrarClass copied)
      {
         // initialize index
         int index = 0;
         
         // copy size
         this.size = copied.size;
         
         // copy capacity
         this.capacity = copied.capacity;
         
         // loop through the entire capacity, transfer over every element
         for (index = 0; index < size; index++ )
            {
               StudentClass copiedStudent = new StudentClass(copied.studentArr
                     [index]);
               this.studentArr[index] = copiedStudent;
            }
      }
      
      /**
       * Adds a StudentClass item to list
       * <p>
       * Note: Overloaded method
       * 
       * @param newStudent StudentClass object to be added to array
       * 
       * @return Boolean result of item addition to array
       */
      public boolean addStudent(StudentClass newStudent)
      {
         // check if there is space for a student to be added
         if (size < capacity)
            {
               // increase the size by one and store the new student in 
               // that index location
               studentArr[size] = newStudent;
               size += 1;
               return true;
            }
         
         // if not enough space, return false
         return false;
      }
      
      /**
       * Creates a StudentClass item, then adds to list using other method
       * <p>
       * Note: Uses anonymous StudentClass instantiation in call to other 
       * method; one line of code
       * <p>
       * Note: Overloaded method
       * 
       * @param stdName String name of student
       * @param stdID integer student ID of student
       * @param stdGender character gender of student
       * @param stdGPA GPA of student 
       * 
       * @return Boolean result of adding student
       */
      public boolean addStudent(String stdName, int stdID, char stdGender, 
            double stdGPA)
      {
         // create a new StudentClass object containing all parameter info
         StudentClass newStudent = new StudentClass(stdName, stdID, stdGender, 
               stdGPA);
         
         // returns true or false if addition was successful
         return addStudent(newStudent);
      }
      
      /**
       * Copies student list from one array to other
       * <p>
       * Note: must create new StudentClass object to assign to each element 
       * to destination array to eliminate aliasing
       * 
       * @param dest StudentClass array to which data is copied
       * @param source StudentClass array from which data is copied
       */
      private void copyArrayData(StudentClass[] dest, StudentClass[] source)
      {
         // initialize index variable
         int index;
         
         // loop through the entire array
         for (index = 0; index < size; index++)
            {
               // create a new StudentClass object that stores the 
               // source object, then pass it into the dest object
               StudentClass copyStudent = new StudentClass(source[index]);
               dest[index] = copyStudent;   
            }
         
      }
      
      /**
       * Optional method, local array dump for diagnostics
       */
      public void diagnosticArrayDump()
      {
         
      }
      
      /**
       * Finds student in array, returns data
       * <p>
       * Note: Uses findStudentIndex
       * 
       * @param student StudentClass object to be found
       * @return StudentClass object found or null if not found
       */
      public StudentClass findStudent(StudentClass student)
      {
         // initialize index variable
         int index;
         
         // find the index that the student object is found at
         index = findStudentIndex(student);
         
         // make sure that the values are equal and that the objects are the 
         // same, then return that student
         if (index != NOT_FOUND)
            {
               return student;
            }
         
         // otherwise return null
         return null;
      }
      
      /**
       * Finds student's index in array, returns index, or returns NOT_FOUND if 
       * item is not in the array
       * <p>
       * Note: Must use appropriate comparison method for class
       * 
       * @param student StudentClass object to be found
       * 
       * @return index of StudentClass object, or NOT_FOUND
       */
      public int findStudentIndex(StudentClass student)
      {
         // initialize index variables
         int index = 0;
         
         // loop through the entire array
         for (index = 0; index < size; index++)
            {
               
               // compare the value of the passed in object and each element
               // in the array, if they equal zero they are the same
               if (studentArr[index].compareTo(student) == 0)
                  {
                     return index;
                  }
            }
         
         // otherwise return NOT_FOUND
         return NOT_FOUND;
      }
      
      /**
       * Removes student from array, shifts elements down to keep array 
       * contiguous
       * <p>
       * Note: Uses findStudentIndex
       * 
       * @param student StudentClass object to be removed
       * 
       * @return StudentClass object that was removed, or null if not found
       */
      public StudentClass removeStudent(StudentClass student)
      {
         // initialize method/variables
         int studentFoundIndex;
         int index;
         
         // if the student object is not found, return null
         if ( findStudent(student) == null)
            {
               return null;
            }
         
         // otherwise, continue on to delete the object
         else
            {
               // find the index at which the student is found
               studentFoundIndex = findStudentIndex(student);
               
               // loop from the index the student was found at until the end
               // of the array
               for (index = studentFoundIndex; index < size; index++)
                  {
                     studentArr[index] = studentArr[index+1];
                  }
               
               // decrement the size to account for the one less student
               size--;
               
               // return the found student
               return student;
            }
      }
      
      /**
       * Description: Sorts elements using the bubble sort algorithm
       * <p>
       * Note: Creates new StudentClass array, sorts contents of array, and 
       * returns the sorted result; does not modify (this) object student array
       * 
       * @return new StudentClass array with sorted items
       */
      public StudentClass[] runBubbleSort()
      {
         // initialize variables
         int outerIndex;
         int innerIndex;
         
         // initialize a new array object and set it to size capacity
         StudentClass[] copiedStdArray = new StudentClass[capacity];
         
         // copy over the entire student array to avoid aliasing
         copyArrayData(copiedStdArray, studentArr);
         
         // begin outer loop
         for (outerIndex = 0; outerIndex < size; outerIndex++)
            {
               // begin inner loop
               for (innerIndex = 0; innerIndex < size-1; innerIndex++)
                  {
                     
                     // compare each value to the next value in the sequence
                     // if the first value is larger, swap the two
                     if (copiedStdArray[innerIndex].compareTo(copiedStdArray
                           [innerIndex+1]) > 0)
                              {
                                 swapValues(copiedStdArray, innerIndex, 
                                       innerIndex+1);
                              }
                  } // end inner loop
            } // end outer loop
         
         // return the sorted array
         return copiedStdArray;
         
      }
      
      /**
       * Description: Sorts character elements using the insertion sort 
       * algorithm
       * <p>
       * Note: Creates new StudentClass array, sorts contents of array, and 
       * returns the sorted result; does not modify (this) object student array
       * 
       * @return new StudentClass array with sorted items
       */
      public StudentClass[] runInsertionSort()
      {
         // initialize variables
         int listIndex = 1;
         int searchIndex;
         StudentClass tempObject;
         
         // initialize a new array object and set it to size capacity
         StudentClass[] copiedStdArray = new StudentClass[capacity];
         
         // copy over all array data to the new array created
         copyArrayData(copiedStdArray, studentArr);
         
         // loop until listIndex meets the size of the array
         while (listIndex < size)
            {
               // set a temporary object to the object being tested
               tempObject = copiedStdArray[listIndex];
               
               // set an inner loop index equal to the outer loop index
               searchIndex = listIndex;
               
               // loop while above zero and while the search index is less than
               // the index one below it
               while ( searchIndex > 0 && tempObject.compareTo(
                     copiedStdArray[searchIndex-1]) < 0)
                  {
                     // place the object in the index below into the current
                     // and decrement the searchIndex
                     copiedStdArray[searchIndex] = copiedStdArray
                           [searchIndex-1];
                     searchIndex--;
                     
                  } // end inner loop
               
               // take the temporary object that we grabbbed at the beginning
               // and place it into the search index
               copiedStdArray[searchIndex] = tempObject;
               
               // increment listIndex for next loop
               listIndex++;
               
            } // end outer loop
         
         // return the sorted array
         return copiedStdArray;
         
      }
      
      /**
       * Description: Sorts character elements using the selection sort 
       * algorithm
       * <p>
       * Note: Creates new StudentClass array, sorts contents of array, and 
       * returns the sorted result; does not modify (this) object student array
       * 
       * @return new StudentClass array with sorted items
       */
      public StudentClass[] runSelectionSort()
      {
         // initialize method/variables
         int outerIndex;
         int innerIndex;
         int smallValueIndex = 0;
         
         // initialize a smallValueObject for temporary storage later
         StudentClass smallValueObject;
         
         // initialize a new array object and set it to size capacity
         StudentClass[] copiedStdArray = new StudentClass[capacity];
         
         // copy over all array data to avoid aliasing
         copyArrayData(copiedStdArray, studentArr);
         
         // begin an outer loop that will replace every index
         for (outerIndex = 0; outerIndex < size-1; outerIndex++)
            {
               // temporarily store the outerIndex object into a variable
               smallValueObject = copiedStdArray[outerIndex];
               
               // loop through the array starting at outerindex, increment
               // each time
               for (innerIndex = outerIndex; innerIndex < size; innerIndex++)
                  {
                     // check if the temporarily held object is greater than
                     // or equal to the index being tested. 
                     if (smallValueObject.compareTo(copiedStdArray[innerIndex]) 
                           >= 0)
                              {
                                 // Use this loop to find the smallest value
                                 // in the array. Set the temp small value
                                 // equal to each newly found small value
                                 smallValueObject = copiedStdArray[innerIndex];
                                 
                                 // set the smallValueIndex equal to the inner
                                 // index at which this small value was found
                                 smallValueIndex = innerIndex;
                                 
                              }
                     
                     
                  } // end inner loop
               
               // swap the smallest number with the outerIndex, this will place
               // all of the smallest numbers at the beginning of the array
               swapValues(copiedStdArray, smallValueIndex, outerIndex);
               
            } // end outer loop
         
         // return the sorted array
         return copiedStdArray;
         
      }
      
      /**
       * Uses Shell's sorting algorithm to sort an array of integers
       * <p>
       * Shell's sorting algorithm is an optimized insertion algorithm
       * <p>
       * Note: Creates new StudentClass array, sorts contents of array, and 
       * returns the sorted result; does not modify (this) object student array
       * 
       * @return new StudentClass array with sorted items
       */
      public StudentClass[] runShellSort()
      {
         int gap, gapPassIndex, insertionIndex;
         StudentClass tempItem;
         
         StudentClass[] stdArr = new StudentClass[ capacity ];
         
         copyArrayData( stdArr, studentArr );
         
         for( gap = size / 2; gap > 0; gap /= 2)
            {
               for( gapPassIndex = gap; gapPassIndex < size; gapPassIndex++ )
                  {
                     tempItem = stdArr[ gapPassIndex ];
                     
                     insertionIndex = gapPassIndex;
                     
                     while( insertionIndex >= gap && 
                           stdArr[ insertionIndex - gap ].compareTo( 
                                 tempItem) > 0 )
                        {
                           stdArr[ insertionIndex ] = 
                                 stdArr[ insertionIndex - gap ];
                           
                           insertionIndex -= gap;
                        } // end search loop
                     
                     stdArr[ insertionIndex ] = tempItem;
                  } // end list loop
               
            } // end gap size setting loop
         
         return stdArr;
      }
      
      /**
       * Swaps values within given array
       * 
       * @param stdArray StudentClass array used for swapping
       * @param indexOne integer index for one of the two items to be swapped
       * @param indexOther integer index for the other of the two items to be 
       * swapped
       */
      private void swapValues(StudentClass[] stdArray, int indexOne, 
            int indexOther)
      {
         // initialize variables
         StudentClass tempStorage;
         
         // create a temporary storage for the first index
         tempStorage = stdArray[indexOne];
         
         // place the second index into the first
         stdArray[indexOne] = stdArray[indexOther];
         
         // place the temporary into the second index
         stdArray[indexOther] = tempStorage;
      }

   }
