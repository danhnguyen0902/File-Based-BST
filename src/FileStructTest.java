import java.io.IOException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for the FileStruct class
 *
 * @author danh0902
 * @version Apr 26, 2014
 */
public class FileStructTest
    extends TestCase
{
    private FileStruct   binFile;
    private BSTNode      bstNode;
    private FreeListNode freeListNode;


    // ----------------------------------------------------------
    /**
     * Sets up a beginning for each test case.
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void setUp()
        throws IOException
    // throws IOException
    {
        binFile = new FileStruct();
        binFile.createDatabase("test_file4.bin");
        bstNode = new BSTNode(2.5f, 8, 16, 40);
        freeListNode = new FreeListNode(88);
    }


    // ----------------------------------------------------------
    /**
     * Tests the readRootPtr() function
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void testReadRootPtr()
        throws IOException
    {
        binFile.writeRootPtr(0);
        assertEquals(binFile.readRootPtr(), 0L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the writeRootPtr() function
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void testWriteRootPtr()
        throws IOException
    {
        binFile.writeRootPtr(7);
        assertEquals(binFile.readRootPtr(), 7L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the readHeadPtr() function
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void testReadHeadPtr()
        throws IOException
    {
        binFile.writeHeadPtr(0);
        assertEquals(binFile.readHeadPtr(), 0L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the writeHeadPtr() function
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void testWriteHeadPtr()
        throws IOException
    {
        binFile.writeHeadPtr(7);
        assertEquals(binFile.readHeadPtr(), 7L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the readBSTNode() function
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void testReadBSTNode()
        throws IOException
    {
        binFile.writeBSTNode(16, bstNode);
        assertEquals(binFile.readBSTNode(16).getKey(), 2.5f, 0.01);
    }


    // ----------------------------------------------------------
    /**
     * Tests the writeBSTNode() function
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void testWriteBSTNode()
        throws IOException
    {
        binFile.writeBSTNode(16, bstNode);
        assertEquals(binFile.readBSTNode(16).getValue(), 8);
    }


    // ----------------------------------------------------------
    /**
     * Tests the readFreeListNode() function
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void testReadFreeListNode()
        throws IOException
    {
        binFile.writeFreeListNode(40, freeListNode);
        assertEquals(binFile.readFreeListNode(40).getNext(), 88L);
    }


    // ----------------------------------------------------------
    /**
     * Tests the writeFreeListNode() function
     *
     * @throws IOException
     *             exception for I/O errors
     */
    public void testWriteFreeListNode()
        throws IOException
    {
        binFile.writeFreeListNode(40, freeListNode);
        assertEquals(binFile.readFreeListNode(40).getRemain1(), 0L);
        assertEquals(binFile.readFreeListNode(40).getRemain2(), 0L);
    }


//    // ----------------------------------------------------------
//    /**
//     * Tests the getEOF() function
//     *
//     * @throws IOException
//     *             exception for I/O errors
//     */
//    public void testGetEOF()
//        throws IOException
//    {
//        assertEquals(binFile.getEOF(), 0L);
//    }
//
// // ----------------------------------------------------------
//    /**
//     * Tests the closeDataBase() function
//     *
//     * @throws IOException
//     *             exception for I/O errors
//     */
//    public void testCloseDatabase()
//        throws IOException
//    {
//        assertEquals(binFile.getEOF(), 64L);
//        binFile.closeDatabase();
//    }
}
