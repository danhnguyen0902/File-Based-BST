import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for the BSTNode class
 *
 * @author danh0902
 * @version Apr 26, 2014
 */
public class BSTNodeTest
    extends TestCase
{
    private BSTNode node;


    // ----------------------------------------------------------
    /**
     * Sets up a beginning for each test case.
     */
    public void setUp()
    {
        node = new BSTNode(2.3f, 5, 112, 88);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getLeft() function
     */
    public void testGetLeft()
    {
        assertEquals(node.getLeft(), 112L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the setLeft() function
     */
    public void testSetLeft()
    {
        node.setLeft(16);
        assertEquals(node.getLeft(), 16L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getRight() function
     */
    public void testGetRight()
    {
        assertEquals(node.getRight(), 88L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the setRight() function
     */
    public void testSetRight()
    {
        node.setRight(40);
        assertEquals(node.getRight(), 40L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getKey() function
     */
    public void testGetKey()
    {
        assertEquals(node.getKey(), 2.3f, 0.01);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getValue() function
     */
    public void testGetValue()
    {
        assertEquals(node.getValue(), 5);
    }

}
