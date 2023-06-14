import java.io.IOException;
import java.util.Map;

public interface AVLInterface<T extends Comparable<T>> {
    /* ----------------------------------------------------------------   */
    /* The following methods should be implemented for this assignment.  */
    /* ----------------------------------------------------------------   */


    /** Runs project.  You will create an instance of your project in main
     *      and then have it invoke this method to begin running.
     */
    public void go() throws IOException;

    /* The following methods are commands that the user can run */
    public void find(String name); // find and print Record
    public AVLInterface<T> add(Map.Entry<String, Details> data); // add new item
    public int count();  // # of elements

    void traverseInOrder(); // traverse the tree InOrder
    void traversePreOrder();// traverse the tree PreOrder
    void traversePostOrder(); // traverses the tree PostOrder

    /* These are useful output methods */
    public void greeting();  // print your name, the class, date and other useful info
    public void help(); // explain how to use this program
    public void exit(); // exit the program in a cleann way

    /** Returns your name **/
    public String getMyName();
}
