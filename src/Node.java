public class Node<T extends Comparable<T>> {
     /*
    The node class creates a new node which will contain the details and name of the song
     */

    //attributes for the node
    private T name; //reference to the node
    private Details details; // details object is referenced
    private int height = 1; //starting height of each node
    private Node<T> leftChild; //left child node is set
    private Node<T> rightChild;// right child node is set


    //blueprint for the node object
    public Node(T name, Details details) { // node constructor

        //attributes are initialized.
        this.name = name;
        this.details = details;
    }



    /*
    The bottom half of the class contains getters and setters that sets data to node and retrieves the data when called
    upon in a different class
     */
    public T getName() {
        return name;
    }

    public String getDetails() {
        return details.returnAllDetails();
    }

    // When the method result is called then it will return the name of the song along with the details from the details object
    public String getSearchResult() {
        return name.toString() + " is a song by " + details.artist + ". it is a " + details.genre + " song that was popular in " + details.year + ".";
    }

    public void setName(T name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }//
}
