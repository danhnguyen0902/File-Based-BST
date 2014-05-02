import java.io.IOException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * A test class for the FileBST file
 *
 * @author danh0902
 * @version Apr 26, 2014
 */
public class FileBSTTest
    extends TestCase
{
    // ----------------------------------------------------------
    /**
     * First test
     *
     * @throws IOException
     */
    public void testMain1()
        throws IOException
    {
        // 1
        FileBST obj = new FileBST();
        assertNotNull(obj);

        // 2
        setSystemIn("# sample input for Project4\n"
            + "# this sample is only to demonstrate formatting\n"
            + "# it does not test all cases\n"
            + "# you should write your own tests to check all cases\n"
            + "# run as:\n"
            + "# java FileBST p4-output.bin < p4-input.txt > p4-output.txt\n"
            + "# produces the new files p4-output.bin and p4-output.txt\n\n"
            + "delete 1000 2\n" + "freelist\n" + "tree\n\n"
            + "insert 10.1 10\n" + "insert 20.1 20\n" + "insert 15.1 15\n"
            + "insert 20.1 39\n" + "tree\n\n" + "find 20.1\n" + "find 20\n\n"
            + "delete 10.1\n" + "delete 15.1\n" + "tree\n" + "freelist\n"
            + "insert 5.9 23");

        String[] args = { "test_file.bin" };
        FileBST.main(args);
        assertTrue(systemOut().getHistory().contains(
            "database new test_file.bin\n" + "delete 1000.0 not found\n"
                + "freelist empty\n" + "tree empty\n"
                + "insert 10.1 10 at 16\n" + "insert 20.1 20 at 40\n"
                + "insert 15.1 15 at 64\n" + "insert 20.1 39 duplicate\n"
                + "tree\n" + "10.1 10 at 16\n" + ">>15.1 15 at 64\n"
                + ">20.1 20 at 40\n" + "find 20.1 20 at 40\n"
                + "find 20.0 not found\n" + "delete 10.1 10 at 16\n"
                + "delete 15.1 15 at 64\n" + "tree\n" + "20.1 20 at 40\n"
                + "freelist\n" + "1 at 64\n" + "2 at 16\n"
                + "insert 5.9 23 at 64\n"));

        FileBST.main(args);

        // 3
        setSystemIn("insert 20.1 3\n" + "insert 10.5 2\n" + "insert 50.8 4\n"
            + "insert 30.1 3\n" + "insert 25.2 7\n" + "insert 24 2\n"
            + "insert 40.5 4\n" + "insert 39.5 4\n" + "insert 37.8 4\n"
            + "insert 33.5 4\n" + "delete 30.1\n" + "delete 25.2\n");

        args[0] = "test_file2.bin";
        FileBST.main(args);
        assertTrue(systemOut().getHistory().contains(
            "database new test_file2.bin\n" + "insert 20.1 3 at 16\n"
                + "insert 10.5 2 at 40\n" + "insert 50.8 4 at 64\n"
                + "insert 30.1 3 at 88\n" + "insert 25.2 7 at 112\n"
                + "insert 24.0 2 at 136\n" + "insert 40.5 4 at 160\n"
                + "insert 39.5 4 at 184\n" + "insert 37.8 4 at 208\n"
                + "insert 33.5 4 at 232\n" + "delete 30.1 3 at 88\n"
                + "delete 25.2 7 at 112\n"));

        // 4
        setSystemIn("insert 20.1 3\n" + "insert 10.5 2\n" + "insert 50.8 4\n"
            + "insert 40.1 3\n" + "insert 60.2 7\n" + "delete 50.8\n");

        args[0] = "test_file3.bin";
        FileBST.main(args);
        assertTrue(systemOut().getHistory().contains(
            "database new test_file3.bin\n" + "insert 20.1 3 at 16\n"
                + "insert 10.5 2 at 40\n" + "insert 50.8 4 at 64\n"
                + "insert 40.1 3 at 88\n" + "insert 60.2 7 at 112\n"
                + "delete 50.8 4 at 64"));

    }
}
