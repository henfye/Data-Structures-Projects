package p8_package;

/**
 * Binary search tree (BST) class that stores StudentClassNode
 * data using the student ID as the key
 * <p>
 * <p>
 * Note: Assumes all student ID keys are unique
 * @author Samuel Fye
 *
 */

public class BST_Class
   {
      /**
       * Root of BST
       */
      private StudentClassNode BST_Root;
      
      /**
       * Used for acquiring ordered tree visitations in String form
       */
      private String outputString;
      
      /**
       * Default class constructor, initializes BST
       */
      public BST_Class()
      {
         BST_Root = null;
         outputString = "";
      }
      
      /**
       * Copy constructor
       * <p>
       * Note: Uses copyConstHelper
       * 
       * @param copied BST_Class object to be copied
       */
      public BST_Class(BST_Class copied)
      {
         outputString = "";
         
         BST_Root = copyConstHelper(copied.BST_Root);
      }
      
      /**
       * Copy constructor helper, recursively adds nodes to duplicate tree
       * 
       * @param copiedRef StudentClassNode reference for accessing copied object
       *  data
       *  
       *  @return StudentClassNode reference to node added at current level of 
       *  recursion
       */
      private StudentClassNode copyConstHelper(StudentClassNode copiedRef)
      {
         StudentClassNode localRef = null;
         
         // recursively traverse the entire binary search tree inorder 
         // and return the top of the tree
         if (copiedRef != null)
            {
               localRef = new StudentClassNode(copiedRef);
               localRef.leftChildRef = copyConstHelper(copiedRef.leftChildRef);
               localRef.rightChildRef = copyConstHelper(copiedRef.rightChildRef);
               
            }
         
         return localRef;
      }
      
      /**
       * Clears tree
       */
      public void clearTree()
      {
         // simply set the BST_Root to null to rid of the tree
         BST_Root = null;
      }
      
      /**
       * Insert method for BST
       * <p>
       * Note: uses insert helper method to insert by student ID key
       * 
       * @param inData StudentClassNode data to be added to BST
       */
      public void insert(StudentClassNode inData)
      {
         // simply call the insert helper method here
         insertHelper(BST_Root, inData);
      }
      
      /**
       * Insert helper method for BST insert action
       * <p>
       * Note: Inserts by student ID key
       * <p>
       * Note: Uses look-down strategy and links to current node; handles 
       * special case of empty tree
       * 
       * @param localRoot - StudentClassNode tree root reference at the current 
       * recursion level
       * @param inData - StudentClassNode item to be added to BST
       */
      private void insertHelper(StudentClassNode localRoot, 
            StudentClassNode inData)
      {
         // check if the tree is empty, if so make the root the 
         // inserted data
         if (isEmpty())
            {
               BST_Root = new StudentClassNode(inData);
            }
      // check if search value is less than local data
         else if (inData.studentID < localRoot.studentID)
            {
               
               // check if left child does not exist
               if (localRoot.leftChildRef == null)
                  {
                  // assign the current node's left child to a new node with 
                     // the given data
                     localRoot.leftChildRef = new StudentClassNode(inData);
                  }
               else
                  {
                  // otherwise, assume left child does exist
                     // call method recursively to left
                     insertHelper(localRoot.leftChildRef, inData);
                  }
            }
         else
            {
               // check if right child does not exist
               if (localRoot.rightChildRef == null)
                  {
                     // assign right child to new node with given data
                     localRoot.rightChildRef = new StudentClassNode(inData);
                  }
               // otherwise, assume right child does exist
               else
                  {
                     // call method recursively to right
                     insertHelper(localRoot.rightChildRef, inData);
                  }
            }
      }
      
      /**
       * Test for empty tree
       * 
       * @return Boolean result of test
       */
      public boolean isEmpty()
      {
         // check if the root is null, if so then the tree is empty
         return BST_Root == null;
      }
      
      /**
       * Provides inOrder traversal for user as a string
       * 
       * @return String containing in order output
       */
      public String outputInOrder()
      {
         // reset output string
         outputString = "";
         
         // call the helper method and return the concatenated string
         outputInOrderHelper(BST_Root);
         
         return outputString;
      }
      
      /**
       * Provides inOrder traversal action helper
       * <p>
       * Note: Updates string with each call
       * 
       * @param localRoot StudentClassNode tree root reference at the current
       * recursion level
       */
      private void outputInOrderHelper(StudentClassNode localRoot)
      {
         if (localRoot != null)
            {
               outputInOrderHelper(localRoot.leftChildRef);
               outputString += localRoot.toString() + "\n";
               outputInOrderHelper(localRoot.rightChildRef);
            }
      }
      
      /**
       * Provides postOrder traversal for use as a string
       * 
       * @return String containing post order output
       */
      public String outputPostOrder()
      {
         outputString = "";
         
         outputPostOrderHelper(BST_Root);
         
         return outputString;
      }
      
      /**
       * Provides postOrder traversal action helper
       * <p>
       * Note: Updates string with each call
       * 
       * @param localRoot - StudentClassNode tree root reference at the current 
       * recursion level
       */
      private void outputPostOrderHelper(StudentClassNode localRoot)
      {
         if (localRoot != null)
            {
               outputPostOrderHelper(localRoot.leftChildRef);
               outputPostOrderHelper(localRoot.rightChildRef);
               outputString += localRoot.toString() + "\n";
            }
      }
      
      /**
       * Provides preOrder traversal for user as a string
       * 
       * @return String containing pre order output
       */
      public String outputPreOrder()
      {
         outputString = "";
         
         outputPreOrderHelper(BST_Root);
         
         return outputString;
      }
      
      /**
       * Provides preOrder traversal action helper
       * <p>
       * Note: Updates string with each call
       * 
       * @param localRoot - StudentClassNode tree root reference at the current
       *  recursion level
       */
      private void outputPreOrderHelper(StudentClassNode localRoot)
      {
         if (localRoot != null)
            {
               outputString += localRoot.toString() + "\n";
               outputPreOrderHelper(localRoot.leftChildRef);
               outputPreOrderHelper(localRoot.rightChildRef);
            }
      }
      
      /**
       * Searches tree from given node to maximum value node below it, unlinks 
       * and returns found node
       * <p>
       * @param parent - StudentClassNode reference to current node
       * 
       * @param child - StudentClassNode reference to child node to be tested
       * 
       * @return StudentClassNode reference containing removed node
       */
      private StudentClassNode removeFromMax(StudentClassNode parent, 
            StudentClassNode child)
      {
         // recursively move down to the right until null is reached 
         // by the child reference
         if (child.rightChildRef != null)
            {
               parent = child;
               child = child.rightChildRef;
               
               // return the value that will be replacing the deleted value
               return removeFromMax(parent, child);
            }
         
         // loop around the found value to delete it from the tree
         parent.rightChildRef = child.leftChildRef;
         
         // return back up to the deleted location
         return child;
      }
      
      /**
       * Removes data node from tree using student ID key
       * <p>
       * Note: uses remove helper method
       * <p>
       * Note: Verifies if data is available with search method, then if found,
       *  calls remove
       * 
       * @param inData - StudentClassNode that includes the student ID key
       * 
       * @return StudentClassNode result of remove action
       */
      public StudentClassNode removeNode(StudentClassNode inData)
      {
         // find the location for the removed node, this is used for returning
         // the value at the end and for checking that it's not null
         StudentClassNode foundNode = search(inData);
         StudentClassNode removedNode;
         
         // if foundNode not null, then it exists somewhere in the tree
         if (foundNode != null)
            {
                removedNode = new StudentClassNode(foundNode);
                removeNodeHelper(BST_Root, inData);
                return removedNode;
            }
         return null;
      }
      
      /**
       * Remove helper for BST remove action for removing by student ID key
       * <p>
       * Note: uses removeFromMax method
       * <p>
       * Note: Assumes removed node is available since it was previously found 
       * by search in removeNode
       * 
       * @param localRoot - StudentClassNode tree root reference at the current 
       * recursion level
       * 
       * @param outData - StudentClassNode item that includes the student ID key
       * 
       * @return StudentClassNode reference result of remove helper action
       */
      private StudentClassNode removeNodeHelper(StudentClassNode localRoot, 
            StudentClassNode outData)
      {
         StudentClassNode temp;
         // search part of the removeNodeHelper, checks the outData against
         // the localRoot and then recursively finds the node to be removed
         if (outData.studentID < localRoot.studentID)
            {
               localRoot.leftChildRef = removeNodeHelper(localRoot.leftChildRef,
                     outData);
            }
         else if (outData.studentID > localRoot.studentID)
            {
               localRoot.rightChildRef = removeNodeHelper(localRoot.rightChildRef,
                     outData);
            }
         
         // check if there is not a left child, set localRoot to right child
         // and return it back up
         else if (localRoot.leftChildRef == null)
            {
               localRoot = localRoot.rightChildRef;
            }
         
         // check if there is not a right child, set localRoot to the left child
         // and return it back up
         else if (localRoot.rightChildRef == null)
            {
               localRoot = localRoot.leftChildRef;
            }
         
         // if the left child has a right child, find the greatest value that
         // will replace the localRoot
         else if (localRoot.leftChildRef.rightChildRef != null)
            {
               temp = removeFromMax(localRoot, localRoot.leftChildRef);
               localRoot.setStudentClassData(temp);
            }
         
         // assume that the left child does not have a right child, assign to
         // left child's left child to pass over it
         else
            {
               localRoot.setStudentClassData(localRoot.leftChildRef);
               
               localRoot.leftChildRef = localRoot.leftChildRef.leftChildRef;
            }
         
         // pass the localRoot back up 
         return localRoot;
      }
      
      /**
       * Searches for data in BST given StudentClassNode with necessary key
       * 
       * @param searchData - StudentClassNode item containing key
       * 
       * @return StudentClassNode reference to found data
       */
      public StudentClassNode search(StudentClassNode searchData)
      {
         return searchHelper(BST_Root, searchData);
      }
      
      /**
       * Helper method for BST search action
       * 
       * @param localRoot - StudentClassNode tree root reference at the current
       *  recursion level
       *  
       *  @param searchData - StudentClassNode item containing key
       *  
       *  @return StudentClassNode item found
       */
      private StudentClassNode searchHelper(StudentClassNode localRoot, 
            StudentClassNode searchData)
      {
         // check to be sure that localRoot is not null
         if (!isEmpty())
            {
               // recurse left if less than the local root
               if (searchData.studentID < localRoot.studentID)
                  {
                     return searchHelper(localRoot.leftChildRef, searchData);
                  }
               
               // recurse right if more than the local root
               else if (searchData.studentID > localRoot.studentID)
               {
                  return searchHelper(localRoot.rightChildRef, searchData);
               }
               
               // if not more or less than, the number is found and can be 
               // returned
               return localRoot;
            }
         
         // return null if the tree is empty
         return null;
      }
      
   }
