// -------------------------------------------------------------------------
/**
 * Represents a node in the Free List (a LIFO stack)
 *
 * @author danh0902
 * @version Apr 26, 2014
 */
public class FreeListNode
{
    private long next;
    private long remain1;
    private long remain2;


    // ----------------------------------------------------------
    /**
     * Create a new FreeListNode object.
     *
     * @param next
     *            the given next node
     */
    public FreeListNode(long next)
    {
        this.next = next;
        remain1 = 0;
        remain2 = 0;
    }


    // ----------------------------------------------------------
    /**
     * Get the next node of the current node
     *
     * @return the next node
     */
    public long getNext()
    {
        return next;
    }


    // ----------------------------------------------------------
    /**
     * Get nothing
     *
     * @return 0
     */
    public long getRemain1()
    {
        return remain1;
    }


    // ----------------------------------------------------------
    /**
     * Get nothing
     *
     * @return 0
     */
    public long getRemain2()
    {
        return remain2;
    }
}
