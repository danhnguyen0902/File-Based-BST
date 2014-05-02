import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for the FreeListNode class
 *
 * @author danh0902
 * @version Apr 26, 2014
 */
public class FreeListNodeTest
    extends TestCase
{
    private FreeListNode node;


    // ----------------------------------------------------------
    /**
     * Sets up a beginning for each test case.
     */
    public void setUp()
    {
        node = new FreeListNode(88);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getNext() function
     */
    public void testGetNext()
    {
        assertEquals(node.getNext(), 88L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getRemain1() function
     */
    public void testGetRemain1()
    {
        assertEquals(node.getRemain1(), 0L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getRemain2() function
     */
    public void testGetRemain2()
    {
        assertEquals(node.getRemain2(), 0L);
    }
}
