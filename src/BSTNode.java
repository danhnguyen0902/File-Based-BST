// -------------------------------------------------------------------------
/**
 * Represents a BST node in the tree
 *
 * @author danh0902
 * @version Apr 26, 2014
 */
public class BSTNode
{
    private float key;
    private int   value;
    private long  left;
    private long  right;


    // ----------------------------------------------------------
    /**
     * Create a new BSTNode object.
     *
     * @param key
     *            the given key
     * @param value
     *            the given value
     * @param left
     *            the given left pointer
     * @param right
     *            the given right pointer
     */
    public BSTNode(float key, int value, long left, long right)
    {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }


    // ----------------------------------------------------------
    /**
     * Get the left pointer of the node
     *
     * @return the left pointer
     */
    public long getLeft()
    {
        return left;
    }


    // ----------------------------------------------------------
    /**
     * Modify the left pointer of the current node
     *
     * @param left
     *            the given left pointer
     */
    public void setLeft(long left)
    {
        this.left = left;
    }


    // ----------------------------------------------------------
    /**
     * Get the right pointer of the node
     *
     * @return the right pointer
     */
    public long getRight()
    {
        return right;
    }


    // ----------------------------------------------------------
    /**
     * Modify the right pointer of the current node
     *
     * @param right
     *            the given right pointer
     */
    public void setRight(long right)
    {
        this.right = right;
    }


    // ----------------------------------------------------------
    /**
     * Get the key of the node
     *
     * @return the value of the key
     */
    public float getKey()
    {
        return key;
    }


    // ----------------------------------------------------------
    /**
     * Get the value of the node
     *
     * @return the desired value
     */
    public int getValue()
    {
        return value;
    }
}
