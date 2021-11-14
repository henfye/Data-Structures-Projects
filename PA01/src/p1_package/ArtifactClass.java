package p1_package;

/**
 * Artifact class manages either a stick or a bone
 * 
 * Stick or bone must have a length of at least two
 *  
 * @author Michael Leverington
 *
 */
public class ArtifactClass
   {
    /**
     * constant for stick data
     */
    public static final int STICK = 1001;
    
    /**
     * constant for bone data
     */
    public static final int BONE = 2002;
    
    /**
     * member variable, kind of artifact, stick or bone
     */
    public int stickOrBone;
    
    /**
     * member variable, length of artifact (stick or bone)
     */
    public int length;
    
    /**
     * member variable, artifact x position
     */
    public int xPos;
    
    /**
     * member variable, artifact y position
     */
    public int yPos;
    
    /**
     * Initialization constructor
     * <p>
     * Initializes class data
     * <p>
     * Note: default constructor unnecessary
     * 
     * @param xPosIn integer x position
     * 
     * @param yPosIn integer y position
     * 
     * @param stickBone integer value representing either stick or bone
     * 
     * @param inLength integer value representing length of stick or bone
     */
    public ArtifactClass( int xPosIn, int yPosIn, int stickBone, int inLength )
       {
        xPos = xPosIn;
        
        yPos = yPosIn;
        
        stickOrBone = stickBone;
        
        length = inLength;
       }
    
    /**
     * copy constructor
     * <p>
     * Copies artifact object to this object
     * 
     * @param copied ArtifactClass object to be copied
     */
    public ArtifactClass( ArtifactClass copied )
       {
        xPos = copied.xPos;
        
        yPos = copied.yPos;
        
        stickOrBone = copied.stickOrBone;
        
        length = copied.length;
       }

    /**
     * accessor
     * 
     * @return ArtifactClass object
     */
    public ArtifactClass getArtifact()
       {
        return this;
       }
    
    /**
     * mutator/modifier
     * 
     * @param xPosIn integer x position
     * 
     * @param yPosIn integer y position
     * 
     * @param stickBone integer value representing either stick or bone
     * 
     * @param inLength integer value representing length of stick or bone
     */
    public void setArtifact( int xPosIn, int yPosIn, int stickBone, int inLength )
       {
        xPos = xPosIn;
        
        yPos = yPosIn;
        
        stickOrBone = stickBone;
          
        length = inLength;
       }
   }












