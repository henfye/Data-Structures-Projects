package p9_package;

/**
 * 2-3 Tree class that stores name strings
 * 
 * @author Samuel Fye
 *
 */

public class TwoThreeTreeClass
   {
      /**
       * constant used for identifying one data item stored
       */
      private final int ONE_DATA_ITEM = 1;
      
      /**
       * constant used for identifying two data items stored
       */
      private final int TWO_DATA_ITEMS = 2;
      
      /**
       * constant used for identifying three data items stored
       */
      private final int THREE_DATA_ITEMS = 3;
      
      /**
       * Used for acquiring ordered tree visitations in String form
       */
      private String outputString;
      
      /**
       * root of tree
       */
      private TwoThreeNodeClass root;
      
      /**
       * Default 2-3 tree constructor
       */
      public TwoThreeTreeClass()
      {
         outputString = "";
         root = null;
      }
      
      /**
       * Copy 2-3 tree constructor
       * 
       * @param copied - TwoThreeTreeClass object to be duplicated; data is 
       * copied, but new nodes and references must be created
       */
      public TwoThreeTreeClass(TwoThreeTreeClass copied)
      {
         outputString = copied.outputString;
         root = copyConstructorHelper(copied.root);
      }
      
      /**
       * Implements tree duplication effort with recursive method; copies data 
       * into newly created nodes and creates all new references to child nodes
       * 
       * @param workingCopiedRef - TwoThreeNodeClass reference that is updated 
       * to lower level children with each recursive call
       * 
       * @return TwoThreeNodeClass reference to next higher level node; 
       * last return is to root node of THIS object
       */
      private TwoThreeNodeClass copyConstructorHelper(TwoThreeNodeClass 
                                                               workingCopiedRef)
      {
         TwoThreeNodeClass localRef = null;
         
         if (workingCopiedRef != null)
            {
               // if number of items in current node is 1
               if (workingCopiedRef.numItems == ONE_DATA_ITEM)
                  {
                     // set local ref to 1 item and create a new node with
                     // the center data of the copied working ref
                     localRef = new TwoThreeNodeClass
                                                 (workingCopiedRef.centerData);
                     localRef.numItems = ONE_DATA_ITEM;
                  }
               // otherwise, assume node is 2
               else
                  {
                     // need to create a new node with left data, move the left
                     // data over to the left from center, and then copy over 
                     // the right 
                     localRef = new TwoThreeNodeClass(workingCopiedRef.leftData);
                     localRef.leftData = localRef.centerData;
                     localRef.rightData = workingCopiedRef.rightData;
                     localRef.numItems = TWO_DATA_ITEMS;
                  }
               
               
               // assign left child ref to recursive call of copy const help
               // with copied ref 
               localRef.leftChildRef = copyConstructorHelper(workingCopiedRef
                                                              .leftChildRef);
               
               // check if there is an actual left child, connect the left
               // child back up if so
               if (localRef.leftChildRef != null)
                  {
                     localRef.leftChildRef.parentRef = localRef;
                  }
               
               // check if num items is 2 to go down the center 
               if (workingCopiedRef.numItems == TWO_DATA_ITEMS)
                  {
                     // set the local center child to copy const center child
                     localRef.centerChildRef = copyConstructorHelper
                                              (workingCopiedRef.centerChildRef);
                     
                     // check if there is a center child and connect it back
                     // up if so
                     if (localRef.centerChildRef != null)
                        {
                           localRef.centerChildRef.parentRef = localRef;
                        }
                  }
               
               // call right child ref with copy const copied right child ref
               localRef.rightChildRef = copyConstructorHelper(workingCopiedRef
                                                               .rightChildRef);
               
               // if there is a right child, connect it back up 
               if (localRef.rightChildRef != null)
                  {
                     localRef.rightChildRef.parentRef = localRef;
                  }
               
               // return localRef back up 
               return localRef;
            }
         return null;
      }
      
      /**
       * Method is called when addItemHelper arrives at the bottom of the 2-3 
       * search tree (i.e., all node's children are null);
       * <p>
       * Assumes one- or two- value nodes and adds one more to the appropriate 
       * one resulting in a two- or three- value node
       * 
       * @param localRef - TwoThreeNodeClass reference has original node data 
       * and contains added value when method completes; method does not manage 
       * any node links
       * 
       * @param itemStr - String value to be added to 2-3 node
       */
      private void addAndOrganizeData(TwoThreeNodeClass localRef, String itemStr)
      {
         if (localRef.numItems == ONE_DATA_ITEM) 
            {
               localRef.numItems = TWO_DATA_ITEMS;
               // if itemString is less than the centerData
               if (compareStrings(itemStr, localRef.centerData) < 0)
                  {
                     // set left data to item string and right data to center
                     localRef.leftData = itemStr;
                     localRef.rightData = localRef.centerData;
                     
                  }
               // otherwise, assume that itemString greater than
               else
                  {
                     // set right data to item string and left data to center
                     localRef.rightData = itemStr;
                     localRef.leftData = localRef.centerData;
                  }
            }
            // otherwise, assume that the number of items is equal to 2
            else
               {
                  localRef.numItems = THREE_DATA_ITEMS;
                  // check the item against the left data, move left
                  if (compareStrings(itemStr, localRef.leftData) < 0 )
                     {
                        localRef.centerData = localRef.leftData;
                        localRef.leftData = itemStr;
                     }
                  
                  // check the data against the right data, move right
                  else if (compareStrings(itemStr, localRef.rightData) > 0)
                     {
                        localRef.centerData = localRef.rightData;
                        localRef.rightData = itemStr;
                     }
                  // assume it is in between the two numbers, move middle
                  else
                     {
                        localRef.centerData = itemStr;
                     }
               }
      }
      
      /**
       * Adds item to 2-3 tree using addItemHelper as needed
       * 
       * @param itemStr - String value to be added to the tree
       */
      public void addItem(String itemStr)
      {
         addItemHelper(root, itemStr);
      }
      
      /**
       * Helper method searches from top of tree to bottom using divide and 
       * conquer strategy to find correct location (node) for new added value; 
       * once location is found, item is added to node using addAndOrganizeData
       *  and then fixUpInsert is called in case the updated node has become a 
       *  three-value node
       *  
       *  @param localRef - TwoThreeNodeClass reference to the current item at 
       *  the same given point in the recursion process
       *  
       *  @param itemStr - String value to be added to the tree
       */
      private void addItemHelper(TwoThreeNodeClass localRef, String itemStr)
      {
         // check that the tree is not already empty
         if (root == null)
            {
               root = new TwoThreeNodeClass(itemStr);
            }
         
         // loop until local refs next child is null since the tree is 
         // flat on the bottom
         else if (localRef.numItems == ONE_DATA_ITEM && localRef.leftChildRef 
                                                                      != null) 
         {
            // if itemString is less than the centerData
            if (compareStrings(itemStr, localRef.centerData) < 0)
               {
                  // move down to the left
                  addItemHelper(localRef.leftChildRef, itemStr);
               }
            // otherwise, assume that itemString greater than
            else
               {
                  // move down to the right
                  addItemHelper(localRef.rightChildRef, itemStr);
               }
         }
         // otherwise, assume that the number of items is equal to 2
         else if (localRef.numItems == TWO_DATA_ITEMS && localRef.leftChildRef 
                                                                       != null)
            {
               // check the item against the left data, move left
               if (compareStrings(itemStr, localRef.leftData) < 0 )
                  {
                     addItemHelper(localRef.leftChildRef, itemStr);
                  }
               
               // check the data against the right data, move right
               else if (compareStrings(itemStr, localRef.rightData) > 0)
                  {
                     addItemHelper(localRef.rightChildRef, itemStr);
                  }
               // assume it is in between the two numbers, move middle
               else
                  {
                     addItemHelper(localRef.centerChildRef, itemStr);
                  }
            }
         else 
            {
               // the bottom has been reached and the data can be added
               // and fixed up 
               addAndOrganizeData(localRef, itemStr);
               fixUpInsert(localRef);
            }
         
      }
      
      /**
       * Method clears tree so that new items can be added again
       */
      public void clear()
      {
         root = null;
      }
      
      /**
       * Compares two strings
       * <p>
       * Returns value greater than zero if left string greater than right string
       * <p>
       * Returns value less than zero if left string less than right string
       * <p>
       * Returns zero if strings are equal
       * 
       * @param leftStr - String to be compared
       * 
       * @param rightStr - String to be compared
       * 
       * @return integer result of test as specified
       */
      public int compareStrings(String leftStr, String rightStr)
      {
         // initialize method/variables
         int leftStrLength, rightStrLength, shorterLength, index;
         char leftStrChar, rightStrChar;
         
         // find the length of both strings
         leftStrLength = leftStr.length();
         rightStrLength = rightStr.length();
         
         // find the shorter length for the upcoming loop to avoid index
         // errors
         if (leftStrLength < rightStrLength)
            {
               shorterLength = leftStrLength;
            }
         else
            {
               shorterLength = rightStrLength;
            }
         
         // loop through each character, checking if they are not equal
         // returns left character minus right character if not
         for (index = 0; index < shorterLength; index++)
            {
               leftStrChar = leftStr.charAt(index);
               rightStrChar = rightStr.charAt(index);
               
               if (leftStrChar != rightStrChar)
                  {
                     return leftStrChar - rightStrChar;
                  }
            }
         
         // if strings are the same up until the shorter length, return
         // the difference in lengths
         return leftStrLength - rightStrLength;
      }
      
      /**
       * Method used to fix tree any time a three-value node has been added to 
       * the bottom of the tree; it is always called but decides to act only if 
       * it finds a three-value node
       * <p>
       * Resolves current three-value node which may add a value to the node 
       * above; if the node above becomes a three-value node, then this is 
       * resolved with the next recursive call
       * <p>
       * Recursively climbs from bottom to top of tree resolving any three-value
       *  nodes found
       *  
       *  @param localRef - TwoThreeNodeClas reference initially given the 
       *  currently updated node, then is given parent node recursively each 
       *  time a three-value node was resolved
       */
      private void fixUpInsert(TwoThreeNodeClass localRef)
      {
         // FIXME num items could potentially be off here on the local ref
         // but am unsure as constructor might fix that
         
         // begin by checking if the node is full of 3 items and needs fixing
         if (localRef.numItems == THREE_DATA_ITEMS)
            {
               // check if parent ref is null
               if (localRef.parentRef == null)
                  {
                     // create a parent ref with local ref mid string
                     localRef.parentRef = new TwoThreeNodeClass
                                                          (localRef.centerData);
                    
                     // set root equal to the parent ref
                     root = localRef.parentRef;
                     
                     // fix up the left and right child references 
                     root.leftChildRef = new TwoThreeNodeClass
                           (TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
                     root.rightChildRef = new TwoThreeNodeClass
                           (TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
                  }
               
               
               // check if parent is 1 node
               else if (localRef.parentRef.numItems == ONE_DATA_ITEM)
                  {
                  // set parent num items equal to 2
                     localRef.parentRef.numItems = TWO_DATA_ITEMS;
                     
                  // check if parent.leftChildRef is equal to localRef
                     if (localRef.parentRef.leftChildRef == localRef)
                        {
                           
                        // move middle up to parent right data
                           localRef.parentRef.leftData = localRef.centerData;
                           
                           // move parent middle data to left
                           localRef.parentRef.rightData = 
                                                  localRef.parentRef.centerData;
                           
                           // call the special constructor for center and left
                           // children of the parent
                           localRef.parentRef.centerChildRef = 
                                 new TwoThreeNodeClass(TwoThreeNodeClass.
                                       RIGHT_CHILD_SELECT, localRef);
                           
                           localRef.parentRef.leftChildRef = new TwoThreeNodeClass
                                 (TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
                        }
                     
                     // otherwise, assume parent.rightChildRef equal to local
                     else 
                        {
                           // move middle up to parent right data
                           localRef.parentRef.rightData = localRef.centerData;
                           // move parent middle data to left
                           localRef.parentRef.leftData = localRef.parentRef
                                                                    .centerData;
                           // call the special constructor with current node and 
                           // right
                           localRef.parentRef.centerChildRef = new 
                                 TwoThreeNodeClass(TwoThreeNodeClass.
                                       LEFT_CHILD_SELECT, localRef);
                           
                           localRef.parentRef.rightChildRef = new 
                                 TwoThreeNodeClass(TwoThreeNodeClass.
                                       RIGHT_CHILD_SELECT, localRef);
                        }
                     
                  }
               
               // otherwise, check if parent is 2 node
               else
                  {
                     // set parent num items to 3
                     localRef.parentRef.numItems = THREE_DATA_ITEMS;
                     
                     // check if parent.leftChildRef equal to localRef
                     if (localRef.parentRef.leftChildRef == localRef)
                        {
                           // move parent left data to middle
                           localRef.parentRef.centerData = localRef.parentRef
                                                                      .leftData;
                           // push up center data to parent left data
                           localRef.parentRef.leftData = localRef.centerData;
                           
                           // move the center child data of parent to auxRight
                           // of parent
                           localRef.parentRef.auxRightRef = localRef.parentRef
                                                                .centerChildRef;
                           
                           // call special constructor for left child and
                           // aux left ref
                           localRef.parentRef.leftChildRef = 
                                 new TwoThreeNodeClass(TwoThreeNodeClass.
                                       LEFT_CHILD_SELECT, localRef);
                           
                           localRef.parentRef.auxLeftRef = new TwoThreeNodeClass
                                 (TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
                        }
                     
                     // check if parent.rightChildRef equal to localRef
                     else if (localRef.parentRef.rightChildRef == localRef)
                        {
                           // move parent right data to middle
                           localRef.parentRef.centerData = localRef.parentRef
                                                                     .rightData;
                           
                           // push up center data to parent right data
                           localRef.parentRef.rightData = localRef.centerData;
                           
                           // move center child data of parent to aux left of 
                           // parent
                           localRef.parentRef.auxLeftRef = localRef.parentRef
                                                                .centerChildRef;
                           
                           // call special constructor for right child and aux
                           // right ref
                           localRef.parentRef.rightChildRef = 
                                 new TwoThreeNodeClass(TwoThreeNodeClass.
                                       RIGHT_CHILD_SELECT, localRef);
                           localRef.parentRef.auxRightRef =
                                 new TwoThreeNodeClass(TwoThreeNodeClass.
                                       LEFT_CHILD_SELECT, localRef);
                           
                        }
                     
                     // otherwise, assume local ref is parent's middle child
                     else
                        {
                           // push local ref center up to parent ref center
                           localRef.parentRef.centerData = localRef.centerData;
                           
                           // call special constructor for aux left and right
                           localRef.parentRef.auxLeftRef = new TwoThreeNodeClass
                                 (TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
                           localRef.parentRef.auxRightRef = new TwoThreeNodeClass
                                 (TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
                        }
                  }
                     
               // recursively call fix up insert to fix up entire tree
               fixUpInsert(localRef.parentRef);
            }
      }
      
      /**
       * Tests center value if single node, tests left and right values if 
       * two-value node; returns true if specified data is found in any given node
       * <p>
       * Note: Method does not use any branching operations such as if/else/etc.
       * 
       * @param localRef - TwoThreeNodeClass reference to node with one or two 
       * data items in it
       * 
       * @param searchStr - String value to be found in given node
       * 
       * @return boolean result of test
       */
      private boolean foundInNode(TwoThreeNodeClass localRef, String searchStr)
      {
         // checks center data for searchStr if single node
         // checks left and right data for searchStr if two node
         return ((localRef.numItems == ONE_DATA_ITEM && 
                            compareStrings(localRef.centerData, searchStr) == 0)
               || (localRef.numItems == TWO_DATA_ITEMS && 
                              compareStrings(localRef.leftData, searchStr) == 0)
               || (localRef.numItems == TWO_DATA_ITEMS && 
                           compareStrings(localRef.rightData, searchStr) == 0));
      }
      
      /**
       * Public method called by user to display data in order
       * 
       * @return String result to be displayed and/or analyzed
       */
      public String inOrderTraversal()
      {
         outputString = "";
         inOrderTraversalHelper(root);
         outputString += "\n";
         return outputString;
      }
      
      /**
       * Helper method conducts in order traversal with 2-3 tree
       * <p>
       * Shows location of each value in a node: "C" at center of single node 
       * "L" or "R" at left or right of two-value node
       * 
       * @param localRef - TwoThreeNodeClass reference to current location at 
       * any given point in the recursion process
       */
      private void inOrderTraversalHelper(TwoThreeNodeClass localRef)
      {
         // check that current node is not null
         if (localRef != null)
            {
               // fall down recursively all the way to the left
               inOrderTraversalHelper(localRef.leftChildRef);
               
               // check if num items is 1 or two to decide whether or not to
               // add on data from the left, right, or center
               if (localRef.numItems == ONE_DATA_ITEM)
                  {
                     outputString += " | C: " + localRef.centerData;
                     
                  }
               else
                  {
                     // assume there are two data items, traverse down center
                     outputString += " | L: " + localRef.leftData;
                     inOrderTraversalHelper(localRef.centerChildRef);
                     outputString += " | R: " + localRef.rightData;
                  }
               
               // finally, move down the right
               inOrderTraversalHelper(localRef.rightChildRef);
            }
      }
      
      /**
       * Search method used by programmer to find specified item in 2-3 tree
       * 
       * @param searchStr - String value to be found in tree
       * 
       * @return boolean result of search effort
       */
      public boolean search(String searchStr)
      {
         return searchHelper(root, searchStr);
      }
      
      /**
       * Search helper method that traverses through tree in a recursive divide
       *  and conquer search fashion to find given integer in 2-3 tree
       *  
       *  @param localRef - TwoThreeNodeClass reference to given node at any 
       *  point during the recursive process
       *  
       *  @param searchStr - String value to be found in tree
       *  
       *  @return boolean result of search effort
       */
      private boolean searchHelper(TwoThreeNodeClass localRef, String searchStr)
      {
         boolean isFound = false;
         
         if (localRef != null && isFound == false)
            {
               
               // check each node if the searchStr is in it, set boolean to 
               // true and pass all the way back up 
               if (foundInNode(localRef, searchStr))
                  {
                     isFound = true;
                     return isFound;
                  }
               
               // check if number of items is 1
               if (localRef.numItems == ONE_DATA_ITEM)
                  {
                     
                     // recurse down each branch of the current node
                     isFound = searchHelper(localRef.leftChildRef, searchStr);
                     isFound = searchHelper(localRef.rightChildRef, searchStr);
                  }
               
               // otherwise, assume two node
               else
                  {
                     // recurse down each branch of the current node
                     isFound = searchHelper(localRef.leftChildRef, searchStr);
                     isFound = searchHelper(localRef.centerChildRef, searchStr);
                     isFound = searchHelper(localRef.rightChildRef, searchStr);
                  }
            }
         
         // if true is never returned, method will recurse back up and
         // finally return false, or true if true
         return isFound;
      }
   }
