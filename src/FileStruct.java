import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;

// -------------------------------------------------------------------------
/**
 * Navigate binary file manipulation
 *
 * @author danh0902
 * @version Apr 26, 2014
 */
public class FileStruct
{
    private RandomAccessFile raf;
    private File             file;


    // ----------------------------------------------------------
    /**
     * Create a new database if there's NOT any
     *
     * @param fileName
     *            the given file name
     * @return a new database (if not existing)
     * @throws IOException
     *             exception for I/O-related errors
     */
    public boolean createDatabase(String fileName)
        throws IOException
    {
        file = new File("./" + fileName);

        /* If the file doesn't exist, then create a new one */
        boolean notExisting = file.createNewFile();
        raf = new RandomAccessFile(file, "rw");

        return notExisting;
    }


    /* Root pointer - BST */
    // ----------------------------------------------------------
    /**
     * Read the root pointer value from the binary file
     *
     * @return the root pointer's value
     * @throws IOException
     *             exception for I/O-related errors
     */
    public long readRootPtr()
        throws IOException
    {
        raf.seek(0);

        return raf.readLong();
    }


    // ----------------------------------------------------------
    /**
     * Modify the value of the root pointer in the binary file
     *
     * @param root
     *            the given value
     * @throws IOException
     *             exception for I/O-related errors
     */
    public void writeRootPtr(long root)
        throws IOException
    {
        raf.seek(0);
        raf.writeLong(root);
    }


    /* Head pointer - Free List */
    // ----------------------------------------------------------
    /**
     * Read the head pointer's value in the binary file
     *
     * @return the head pointer's value
     * @throws IOException
     *             exception for I/O-related errors
     */
    public long readHeadPtr()
        throws IOException
    {
        raf.seek(8);

        return raf.readLong();
    }


    // ----------------------------------------------------------
    /**
     * Modify the head pointer's value
     *
     * @param head
     *            the given value
     * @throws IOException
     *             exception for I/O-related errors
     */
    public void writeHeadPtr(long head)
        throws IOException
    {
        raf.seek(8);
        raf.writeLong(head);
    }


    /* BST node */
    // ----------------------------------------------------------
    /**
     * Read the data stored in a BST node
     *
     * @param offset
     *            the byte position of that node in the binary file
     * @return BST node's data
     * @throws IOException
     *             exception for I/O-related errors
     */
    public BSTNode readBSTNode(long offset)
        throws IOException
    {
        raf.seek(0);
        raf.seek(offset);
        float key = raf.readFloat();
        int value = raf.readInt();
        long left = raf.readLong();
        long right = raf.readLong();

        BSTNode temp = new BSTNode(key, value, left, right);

        return temp;
    }


    // ----------------------------------------------------------
    /**
     * Modify the data of a BST node
     *
     * @param offset
     *            the byte position
     * @param newBSTNode
     *            the new BST node
     * @throws IOException
     *             exception for I/O-related errors
     */
    public void writeBSTNode(long offset, BSTNode newBSTNode)
        throws IOException
    {
        raf.seek(0);
        raf.seek(offset);
        raf.writeFloat(newBSTNode.getKey());
        raf.writeInt(newBSTNode.getValue());
        raf.writeLong(newBSTNode.getLeft());
        raf.writeLong(newBSTNode.getRight());
    }


    /* Free List node */
    // ----------------------------------------------------------
    /**
     * Read the data of a Free List node
     *
     * @param offset
     *            the byte position
     * @return a FreeList node
     * @throws IOException
     *             exception for I/O-related errors
     */
    public FreeListNode readFreeListNode(long offset)
        throws IOException
    {
        raf.seek(0);
        raf.seek(offset);
        long next = raf.readLong();

        FreeListNode temp = new FreeListNode(next);

        return temp;
    }


    // ----------------------------------------------------------
    /**
     * Modify a Free List node's data
     *
     * @param offset
     *            the byte position of that node
     * @param newFreeListNode
     *            the given Free List node
     * @throws IOException
     *             exception for I/O-related errors
     */
    public void writeFreeListNode(long offset, FreeListNode newFreeListNode)
        throws IOException
    {
        raf.seek(0);
        raf.seek(offset);
        raf.writeLong(newFreeListNode.getNext());
        raf.writeLong(newFreeListNode.getRemain1());
        raf.writeLong(newFreeListNode.getRemain2());
    }


    /* Other functions */
    // ----------------------------------------------------------
    /**
     * Get the byte position of the end of the binary file
     *
     * @return the found byte position
     * @throws IOException
     */
    public long getEOF()
        throws IOException
    {
        raf.seek(0);
        raf.seek(file.length());
        long temp = raf.getFilePointer();

        return temp;
    }


    // ----------------------------------------------------------
    /**
     * Close the database (the binary file) after everything is done
     *
     * @throws IOException
     *             exception for I/O-related errors
     */
    public void closeDatabase()
        throws IOException
    {
        raf.close();
    }
}
