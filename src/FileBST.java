// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than the instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.util.Scanner;
import java.io.IOException;

// -------------------------------------------------------------------------
/**
 * The main program that receives commands from users and execute them
 *
 * @author Danh Nguyen (danh0902)
 * @version Apr 25, 2014
 */
public class FileBST
{
    private static FileStruct binFile;
    private static Scanner    scan;
    private static String     command;
    private static long       root;        // the root of the BST
    private static long       head;        // the head of the Free List
    private static long       bytePosition;
    private static int        deletedValue;


    // ----------------------------------------------------------
    /**
     * Remove the top of the stack Free List and return it
     *
     * @return the top of the stack
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static long pop()
        throws IOException
    {
        long result = head;

        FreeListNode temp = binFile.readFreeListNode(head);
        head = temp.getNext();

        // Update the head pointer in the binary file
        binFile.writeHeadPtr(head);

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Insert a deleted record into the stack Free List
     *
     * @param offset
     *            the byte position from the beginning of the binary file
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void push(long offset)
        throws IOException
    {
        FreeListNode newFreeListNode = new FreeListNode(head);
        binFile.writeFreeListNode(offset, newFreeListNode);

        head = offset;

        // Update the head pointer in the binary file
        binFile.writeHeadPtr(head);
    }


    // ----------------------------------------------------------
    /**
     * Insert a node into the Binary Search Tree
     *
     * @param node
     *            the current node
     * @param key
     *            the given key
     * @param value
     *            the given value
     * @return the root of the subtree after insertion
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static long insert(long node, float key, int value)
        throws IOException
    {
        // The tree is empty or there's no more ways to go
        if (node == 0)
        {
            BSTNode newBSTNode = new BSTNode(key, value, 0, 0);

            long offset;

            if (head != 0)
            {
                offset = pop();
            }
            else
            {
                offset = binFile.getEOF();
            }

            binFile.writeBSTNode(offset, newBSTNode);

            bytePosition = offset;

            return offset;
        }
        else
        {
            BSTNode temp = binFile.readBSTNode(node);

            // Go left
            if (key < temp.getKey())
            {
                temp.setLeft(insert(temp.getLeft(), key, value));
            }
            // Go right
            else if (key > temp.getKey())
            {
                temp.setRight(insert(temp.getRight(), key, value));
            }
            else
            {
                bytePosition = -1;
                return node;
            }

            // Update the binary file
            binFile.writeBSTNode(node, temp);

            return node;
        }
    }


    // ----------------------------------------------------------
    /**
     * Find a node with the given key in the Binary Search Tree
     *
     * @param node
     *            the current node
     * @param key
     *            the desired key
     * @return -1 if not found, otherwise the byte position of the node found
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static long find(long node, float key)
        throws IOException
    {
        // The tree is empty or not found
        if (node == 0)
        {
            return -1;
        }
        else
        {
            BSTNode temp = binFile.readBSTNode(node);

            // Search in the left subtree
            if (key < temp.getKey())
            {

                return find(temp.getLeft(), key);
            }
            // Search in the right subtree
            else if (key > temp.getKey())
            {
                return find(temp.getRight(), key);
            }
            // Found
            else
            {
                return node;
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Print out the BST records that are sorted by their keys in increasing
     * order
     *
     * @param node
     *            the current node
     * @param depth
     *            the current depth
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void tree(long node, int depth)
        throws IOException
    {
        BSTNode temp = binFile.readBSTNode(node);

        // Go left
        if (temp.getLeft() != 0)
        {
            tree(temp.getLeft(), depth + 1);
        }

        // Print
        StringBuilder string = new StringBuilder("");
        for (int i = 0; i < depth; ++i)
        {
            string.append(">");
        }
        System.out.print(string);
        System.out.println(temp.getKey() + " " + temp.getValue() + " at "
            + node);

        // Go right
        if (temp.getRight() != 0)
        {
            tree(temp.getRight(), depth + 1);
        }
    }


    // ----------------------------------------------------------
    /**
     * Delete a node with the corresponding key in the Binary Search Tree
     *
     * @param node
     *            the current node
     * @param key
     *            the given key
     * @return the root of the subtree after deletion
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static long delete(long node, float key)
        throws IOException
    {
        // The tree is empty or not found
        if (node == 0)
        {
            return 0;
        }

        BSTNode temp = binFile.readBSTNode(node);

        // Search in the left subtree
        if (key < temp.getKey())
        {
            temp.setLeft(delete(temp.getLeft(), key));

            // Update the binary file
            binFile.writeBSTNode(node, temp);

            return node;
        }
        // Search in the right subtree
        else if (key > temp.getKey())
        {
            temp.setRight(delete(temp.getRight(), key));

            // Update the binary file
            binFile.writeBSTNode(node, temp);

            return node;
        }
        // Found
        else
        {
            bytePosition = node;
            deletedValue = temp.getValue();

            // Put the deleted node (the current node) to the Free List
            push(node);

            // If there are two children
            if (temp.getLeft() != 0 && temp.getRight() != 0)
            {
                BSTNode nodeSucc = binFile.readBSTNode(temp.getRight());
                BSTNode prev = null; // the parent of nodeSucc
                BSTNode prev2 = null; // the parent of prev

                while (nodeSucc.getLeft() != 0)
                {
                    prev2 = prev;
                    prev = nodeSucc;
                    nodeSucc = binFile.readBSTNode(nodeSucc.getLeft());
                }

                if (prev == null)
                {
                    nodeSucc.setLeft(temp.getLeft());
                    nodeSucc.setRight(0);

                    // Update the binary file
                    binFile.writeBSTNode(temp.getRight(), nodeSucc);

                    return temp.getRight();

                }
                else
                {
                    long save = prev.getLeft();
                    prev.setLeft(0);
                    nodeSucc.setLeft(temp.getLeft());
                    nodeSucc.setRight(temp.getRight());

                    // Update the binary file
                    binFile.writeBSTNode(prev2.getLeft(), prev);
                    binFile.writeBSTNode(save, nodeSucc);

                    return save;
                }
            }
            // If there is only one child on the left
            else if (temp.getLeft() != 0)
            {
                return temp.getLeft();
            }
            // If there is only one child on the right (this one also handles
            // the case that there's no child)
            else
            {
                return temp.getRight();
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Print out the numbered FreeList records in LIFO Stack order, starting
     * from index 1
     *
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void freelist()
        throws IOException
    {
        int i = 1;

        FreeListNode temp = binFile.readFreeListNode(head);
        System.out.println(i + " at " + head);

        while (temp.getNext() != 0)
        {
            ++i;

            System.out.println(i + " at " + temp.getNext());
            temp = binFile.readFreeListNode(temp.getNext());
        }
    }


    // ----------------------------------------------------------
    /**
     * A caller of the insert() function
     *
     * @param token
     *            the command
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void insertHelper(String[] token)
        throws IOException
    {
        float key = Float.parseFloat(token[1]);
        int value = Integer.parseInt(token[2]);

        // Do insert
        root = insert(root, key, value);

        // Update the root pointer in the binary file
        binFile.writeRootPtr(root);

        if (bytePosition == -1)
        {
            System.out.println(token[0] + " " + Float.parseFloat(token[1])
                + " " + token[2] + " duplicate");
        }
        else
        {
            System.out.println(token[0] + " " + Float.parseFloat(token[1])
                + " " + token[2] + " at " + bytePosition);
        }
    }


    // ----------------------------------------------------------
    /**
     * A caller of the find() method
     *
     * @param token
     *            the command
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void findHelper(String[] token)
        throws IOException
    {
        float key = Float.parseFloat(token[1]);
        bytePosition = find(root, key);

        if (bytePosition == -1)
        {
            System.out.println(token[0] + " " + Float.parseFloat(token[1])
                + " not found");
        }
        else
        {
            BSTNode temp = binFile.readBSTNode(bytePosition);

            System.out.println(token[0] + " " + Float.parseFloat(token[1])
                + " " + temp.getValue() + " at " + bytePosition);
        }
    }


    // ----------------------------------------------------------
    /**
     * A caller of the tree() function
     *
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void treeHelper()
        throws IOException
    {
        if (root == 0)
        {
            System.out.println("tree empty");
        }
        else
        {
            System.out.println("tree");
            tree(root, 0);
        }
    }


    // ----------------------------------------------------------
    /**
     * A caller of the delete() function
     *
     * @param token
     *            the command
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void deleteHelper(String[] token)
        throws IOException
    {
        float key = Float.parseFloat(token[1]);
        bytePosition = -1;

        root = delete(root, key);

        // Update the root pointer in the binary file
        binFile.writeRootPtr(root);

        if (bytePosition == -1)
        {
            System.out.println(token[0] + " " + Float.parseFloat(token[1])
                + " not found");
        }
        else
        {
            System.out.println(token[0] + " " + Float.parseFloat(token[1])
                + " " + deletedValue + " at " + bytePosition);
        }
    }


    // ----------------------------------------------------------
    /**
     * A caller of the freelist() function
     *
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void freelistHelper()
        throws IOException
    {
        if (head == 0)
        {
            System.out.println("freelist empty");
        }
        else
        {
            System.out.println("freelist");
            freelist();
        }
    }


    // ----------------------------------------------------------
    /**
     * Read commands and execute them
     *
     * @throws IOException
     */
    public static void readAndExecute()
        throws IOException
    {
        String[] token;

        while (scan.hasNextLine())
        {
            command = scan.nextLine();

            String delims = "\\s+";
            token = command.trim().split(delims);

            if (token[0].equals("insert"))
            {
                insertHelper(token);
            }

            if (token[0].equals("find"))
            {
                findHelper(token);
            }

            if (token[0].equals("tree"))
            {
                treeHelper();
            }

            if (token[0].equals("delete"))
            {
                deleteHelper(token);
            }

            if (token[0].equals("freelist"))
            {
                freelistHelper();
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a binary file with the given name and open an access to it
     *
     * @param fileName
     *            the given name
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void prepare(String fileName)
        throws IOException
    {
        scan = new Scanner(System.in);

        binFile = new FileStruct();

        if (binFile.createDatabase(fileName))
        {
            System.out.println("database new " + fileName);

            /* Initialize the root of the BST and the head of the Free List */
            root = 0;
            head = 0;

            /* Then, write them to the binary file */
            binFile.writeRootPtr(root);
            binFile.writeHeadPtr(head);
        }
        else
        {
            System.out.println("database existing " + fileName);

            root = binFile.readRootPtr();
            head = binFile.readHeadPtr();
        }
    }


    // ----------------------------------------------------------
    /**
     * Read commands from users and return results back to them. Compiler:
     * Eclipse - ADT Bundle, Operating System: Linux Ubuntu 12.04
     *
     * @version Apr 25, 2014
     * @param args
     *            program invocation and its parameters
     * @throws IOException
     *             exception for I/O-related errors
     */
    public static void main(String[] args)
        throws IOException
    {
        prepare(args[0]);
        // prepare("test_file.bin");

        readAndExecute();

        // Close the database when it's done
        binFile.closeDatabase();
    }
}
