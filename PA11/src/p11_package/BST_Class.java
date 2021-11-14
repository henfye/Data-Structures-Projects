package p11_package;


/**
 * Binary search tree (BST) class that stores StudentClassNode
 * data using the student ID as the key
 * <p>
 * Note: Assumes all student ID keys are unique
 *  
 * @author Michael Leverington
 *
 */
public class BST_Class
   {
    /**
     * Root of BST
     */
    private StudentClassNode BST_Root;

    /**
     * Used for acquiring node count from tree
     */
    private int nodeCount;
    
    /**
     * Default class constructor, initializes BST
     */
    public BST_Class()
       {
        BST_Root = null;
        
        nodeCount = 0;
       }
    
    /**
     * Copy constructor
     * <p>
     * Note: Uses copyConstHelper
     * 
     * @param copied BST_Class object to be copied
     */
    public BST_Class( BST_Class copied )
       {
        BST_Root = copyConstHelper( copied.BST_Root );
        
        nodeCount = 0;
       }
    
    /**
     * Copy constructor helper, recursively adds nodes
     * to duplicate tree
     * 
     * @param copiedRef StudentClassNode reference for accessing
     * copied object data
     * 
     * @return StudentClassNode reference to node added
     * at current level of recursion
     */
    private StudentClassNode copyConstHelper( StudentClassNode copiedRef )
       {
        StudentClassNode localTemp = null;
        
        if( copiedRef != null )
           {
            localTemp = new StudentClassNode( copiedRef );
            
            localTemp.leftChildRef = copyConstHelper( copiedRef.leftChildRef );
            
            localTemp.rightChildRef = copyConstHelper( copiedRef.rightChildRef );
           } 
        
        return localTemp;
       }
    
    /**
     * Clears tree 
     */
    public void clearTree()
       {
        BST_Root = null;
       }
    
    /**
     * counts the number of nodes in the tree
     * <p>
     * Calls recursive helper to conduct action
     * 
     * @return integer number of nodes in the BST
     */
    public int countNodes()
       {
        nodeCount = 0;
        
        countNodesHelper( BST_Root );
        
        return nodeCount;
       }
    
    /**
     * Provides inOrder traversal action helper
     * <p>
     * Note: Updates string with each call
     * 
     * @param localRoot StudentClassNode tree root reference 
     * at the current recursion level
     */
    private void countNodesHelper( StudentClassNode localRoot )
       {
        if( localRoot != null )
           {
            countNodesHelper( localRoot.leftChildRef );
            nodeCount++;
            countNodesHelper( localRoot.rightChildRef );
           }
       }

    /** 
     * Insert method for BST
     * <p>
     * Note: uses insert helper method to insert by student ID key
     * 
     * @param inData StudentClassNode data to be added to BST
     *  
     */
    public void insert( StudentClassNode inData )
       {
        insertHelper( BST_Root, inData );
       }
    
    /**
     * Insert helper method for BST insert action
     * <p>
     * Note: Inserts by student ID key
     * <p>
     * Note: Uses look-down strategy and links to current node;
     * handles special case of empty tree
     * 
     * @param localRoot StudentClassNode tree root reference 
     * at the current recursion level
     * 
     * @param inData StudentClassNode item to be added to BST
     * 
     */
    private void insertHelper( StudentClassNode localRoot, 
                                                    StudentClassNode inData )
       {
        if( localRoot != null )
           {        
            if( localRoot.studentID - inData.studentID > 0 )
               {
                if( localRoot.leftChildRef == null )
                   {
                    localRoot.leftChildRef = new StudentClassNode( inData );
                   }
                
                else 
                   {             
                    insertHelper( localRoot.leftChildRef, inData );
                   }
               }
           
            else if ( localRoot.studentID - inData.studentID < 0 )
               {
                if( localRoot.rightChildRef == null )
                   {
                    localRoot.rightChildRef = new StudentClassNode( inData );
                   }
                
                else
                   {
                    insertHelper( localRoot.rightChildRef, inData );
                   }
               }
           }
        
        else
           {           
            BST_Root = new StudentClassNode( inData );
           }
       }
    
    /**
     * Searches tree from given node to maximum value node below it,
     * unlinks and returns found node
     * 
     * @param parent StudentClassNode reference to current node
     * 
     * @param child StudentClassNode reference to child node to be tested
     * 
     * @return StudentClassNode reference containing removed node
     */
    private StudentClassNode removeFromMax( StudentClassNode parent, 
                                                    StudentClassNode child )
       {
        StudentClassNode foundMaxNode;
        
        if( child.rightChildRef != null )
           {
            return removeFromMax( child, child.rightChildRef );
           }

        foundMaxNode = child;
        
        parent.rightChildRef = child.leftChildRef;
        
        return foundMaxNode;
       }
    
    /** 
     * Removes data node from tree using student ID key
     * <p>
     * Note: uses remove helper method
     * <p>
     * Note: Verifies if data is available with search method,
     * then if found, calls remove
     * 
     * @param inData StudentClassNode that includes the student ID key
     * 
     * @return StudentClassNode result of remove action
     */
    public StudentClassNode removeNode( StudentClassNode inData )
       {        
        StudentClassNode removedData = search( inData );
        
        if( removedData != null )
           {
            removedData = new StudentClassNode( removedData );

            BST_Root = removeNodeHelper( BST_Root, inData );
           } 
        
        return removedData;
       }
    
    /** 
     * Remove helper for BST remove action for removing by student ID key
     * <p>
     * Note: uses removeFromMax method
     * <p>
     * Note: Assumes removed node is available since it was previously found
     * by search in removeNode
     * 
     * @param localRoot StudentClassNode tree root reference 
     * at the current recursion level
     * 
     * @param outData StudentClassNode item that includes the student ID key
     * 
     * @return StudentClassNode reference result of remove helper action
     */
    private StudentClassNode removeNodeHelper( StudentClassNode localRoot,
                                                   StudentClassNode outData )
       {
        int compareResult;
        StudentClassNode tempReplacementNode;
        
        compareResult = localRoot.studentID - outData.studentID;
        
        if( compareResult > 0 )  
           {
           // not found, must be left
            localRoot.leftChildRef 
                       = removeNodeHelper( localRoot.leftChildRef, outData );
           }
        
        else if( compareResult < 0 )  
           {
            // not found, must be right
            localRoot.rightChildRef 
                      = removeNodeHelper( localRoot.rightChildRef, outData );
           }
        
        else if( localRoot.leftChildRef == null )
           {
            // found, has right child
            localRoot = localRoot.rightChildRef;
           }
        
        else if( localRoot.rightChildRef == null )
           {
            // found, has left child
            localRoot = localRoot.leftChildRef;
           }
        
        else
           {
            // found, has two children
            
            // check for left node without right child
            if( localRoot.leftChildRef.rightChildRef == null )
               {
                localRoot.setStudentClassData( localRoot.leftChildRef );
                
                localRoot = localRoot.leftChildRef.leftChildRef;
               }
            
            // otherwise, assume left child has at least one right child
            else
               {
                tempReplacementNode = removeFromMax( localRoot, 
                                                    localRoot.leftChildRef );
            
                localRoot.setStudentClassData( tempReplacementNode );
               }
           }
        
        return localRoot;
       }
    
    /** 
     * Searches for data in BST given StudentClassNode with necessary key
     * 
     * @param searchData StudentClassNode item containing key
     * 
     * @return StudentClassNode reference to found data
     */
    public StudentClassNode search( StudentClassNode searchData )
       {
        return searchHelper( BST_Root, searchData );
       }
    
    /**
     * Helper method for BST search action
     *  
     * @param localRoot StudentClassNode tree root reference 
     * at the current recursion level
     * 
     * @param searchData StudentClassNode item containing key
     * 
     * @return StudentClassNode item found
     */
    private StudentClassNode searchHelper( StudentClassNode localRoot, 
                                                StudentClassNode searchData )
       {
        int compareResult;
        
        if( localRoot != null )
           {
            compareResult = localRoot.studentID - searchData.studentID;
            
            if( compareResult > 0 )
               {
                return searchHelper( localRoot.leftChildRef, searchData );
               }
            
            else if( compareResult < 0 )
               {
                return searchHelper( localRoot.rightChildRef, searchData );
               }
            
            return localRoot;
           }
                  
        return null;
       }
    
   }
