import java.io.IOException;
import java.util.*;

public class AVLTree<T extends Comparable<T>> implements AVLInterface<T> {

    /*
    The AVL tree inserts data from the csv file through the details and name methods from the
    Details and Music class. The data in the details and name methods is already provided through the repository class
     */


    private Node<T> root; //sets the upmost node in which the tree begins to derive from.


    /*
    Add method allows a user to add a song through a map that allows contains a K and V values.
    The key(K) in this case is the name of the song which is labeled as "String" while the values(V)
    is labeled as "Details" which contains the artist, year, bpm, etc.
     */
    public AVLInterface<T> add(Map.Entry<String, Details> data) { // key and value
        root = insert((T) data.getKey(), (Details) data.getValue(), root); //The key and values are inserted from the top which is the root
        return this;
    }


    /*
    insert method creates new nodes using the data within the name and details object.
    the nodes are then inserted into the tree based on which node is greater than or less than the root
    node. The comparison is made only using the title of the song.
     */
    private Node<T> insert(T data, Details details, Node<T> node) {
        if (node == null) {
            return new Node<>(data, details); // node contains the name and details of the song
        }
        if (data.compareTo(node.getName()) < 0) { // compares nodes based on the title of the song "Name"
            node.setLeftChild(insert(data, details, node.getLeftChild())); // if node is less than root than it adds node to the left along with details of the song
        } else if (data.compareTo(node.getName()) > 0) {
            node.setRightChild(insert(data, details, node.getRightChild())); // if node is greater than root than it adds node to the right along with details
        } else {
            return node;
        }
        updateHeight(node);  //Everytime a node is added it updates the height of the tree
        return applyRotation(node); //rotation is applied when nodes are imbalanced.
    }

    @Override
    public void traverseInOrder() { // traverses tree in order
        traverseInOrder(root);
    }

    @Override
    public void traversePostOrder() {
        traversePostOrder(root);  // traverses tree in post order
    }

    @Override
    public void traversePreOrder() {  // traverses tree in preorder
        traversePreOrder(root);
    }

    @Override
    public String getMyName() { // returns my name
        return "My name is " + "Norman Marroquin-Monge";
    }


    /*
    The traverseInOrder method starts from the left most node in the tree. Everytime the method reaches a node
    it will print out the children nodes of that node.
     */
    private void traverseInOrder(Node<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            System.out.println(node.getName().toString() + " " + node.getDetails());

            if (node.getLeftChild() != null) { // if the node is not null then it keeps traversing

                System.out.println("Left Child:{ " + node.getLeftChild().getName().toString() + " } "); //prints out the parents nodes left child
            } else {

                System.out.println("Left Child:{ null } "); //if there is no left child it prints null

            }
            if (node.getRightChild() != null) {

                System.out.println("Right Child:{ " + node.getRightChild().getName().toString() + " } "); // prints out the parents node right child
            } else {

                System.out.println("Right Child:{ null } "); // if there is no right child it prints null

            }

            System.out.println("--------------------------------------------------");
            traverseInOrder(node.getRightChild());
        }
    }


    /*
    The traversePostOrder method traverses the tree by finding the subtrees from left nodes of a tree. It
    starts from the root and goes to the left. After the left subtrees are traversed the subtrees from the right
    nodes of th tree are traversed. All subtrees are traversed from left --> right --> root.
     */
    private void traversePostOrder(Node<T> node) {
        if (node != null) {
            traversePostOrder(node.getLeftChild()); // left child is read first

            traversePostOrder(node.getRightChild());// right child is read second

            System.out.println(node.getName().toString() + " " + node.getDetails());
            if (node.getLeftChild() != null) {
                System.out.println("Left Child:{ " + node.getLeftChild().getName().toString() + " } ");
            } else {
                System.out.println("Left Child:{ null } ");
            }
            if (node.getRightChild() != null) {
                System.out.println("Right Child:{ " + node.getRightChild().getName().toString() + " } ");
            } else {
                System.out.println("Right Child:{ null } ");
            }

            System.out.println("--------------------------------------------------");
        }
    }

    /*
    The traversePreOrder method starts from the root of the tree and goes down the left side of the tree. each
    subtree on the left side will be traversed in the root --> left --> right order. After all subtrees from the
    left side are finished then the right subtrees are traversed in the same order as the left subtrees.
     */
    private void traversePreOrder(Node<T> node) {
        if (node != null) {
            System.out.println(node.getName().toString() + " " + node.getDetails());
            if (node.getLeftChild() != null) {
                System.out.println("Left Child:{ " + node.getLeftChild().getName().toString() + " } ");
            } else
                System.out.println("Left Child:{ null } ");

            if (node.getRightChild() != null) {
                System.out.println("Right Child:{ " + node.getRightChild().getName().toString() + " } ");
            } else
                System.out.println("Right Child:{ null } ");

            System.out.println("--------------------------------------------------");
            traversePreOrder(node.getLeftChild());
            traversePreOrder(node.getRightChild());
        }
    }

    /*
    count method starts from the root of the tree and counts the nodes downwards.
     */
    public int count() {
        return count(root);
    }

    /*
    counts the left and right nodes of the subtrees then the roots. Everytime a left node is detected
    it adds one to the counter. Same rule applies to the right child
     */
    private int count(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return count(node.getLeftChild()) + 1 + count(node.getRightChild());
    }


    /*
    The applyRotation method applies a right or left rotation depending on the imbalance of the tree detected
     */
    private Node<T> applyRotation(Node<T> node) {
        int balance = balance(node); // node in which the balance will occur

        if (balance > 1) { // if the height of node has balance greater than 1 then it is "right-heavy"

            if (balance(node.getLeftChild()) < 0) {

                node.setLeftChild(rotateLeft(node.getLeftChild())); // if it is "right-heavy" we apply a left rotation
                // This still leaves tree in an unbalanced state

            }

            return rotateRight(node); // right rotation allows for the double rotation needed to balance the tree

        }
        if (balance < -1) { // if the height of the node is less than -1 then it is considered "left-heavy"

            if (balance(node.getRightChild()) > 0) {

                node.setRightChild(rotateRight(node.getRightChild()));// if it is "left-heavy" we apply a right rotation
                //This also leaves the tree in an unbalanced state

            }
            return rotateLeft(node); // a left rotation allows for double rotation balancing tree
        }
        return node;
    }


    /*
    The rotateRight method applies a right rotation to an imbalanced tree by making the middle node the root while the
    original root becomes the right node of the new root.
     */
    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild(); // "leftnode" is set by retrieving left node.

        Node<T> centerNode = leftNode.getRightChild(); // left node is set to the center node of the imbalanced subtree

        /*
        if a left-right rotaton is needed then the center node is set to the left of the subtree
        while the right node becomes the new center node.
        The tree is still unbalanced so the center node becomes the root node while the old root becomes
        the right child of the new root.
         */

        leftNode.setRightChild(node);
        node.setLeftChild(centerNode);
        updateHeight(node); //height of the subtree is updated
        updateHeight(leftNode); // height of the left node is updated
        return leftNode;
    }

    /*
    The rotateLeft method applies a left rotation to an imbalanced tree by making the middle node the root while the
    original root becomes the left child of the new root.
   */
    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild(); // rightNode is set to the right node of the subtree

        Node<T> centerNode = rightNode.getLeftChild(); // right node becomes the center of the imbalanced subtree


        /*
        if a right-left rotation is needed then the center node is set to the right of the subtree while the left node
        becomes the new center node.
        The tree is still unbalanced so the center node becomes the new root of the subtree while the old root is set
        to the left of the subtree
         */

        rightNode.setLeftChild(node);
        node.setRightChild(centerNode);
        updateHeight(node); // height of the subtree is updated
        updateHeight(rightNode);// height of the right node is updated
        return rightNode;
    }

    /*
    The UpdateHeight method adds 1 to the height of a left or right child is detected.
     */
    private void updateHeight(Node<T> node) {
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
    }

    /*
    The balance method detects of the height of a children node needs balancing.
     */
    private int balance(Node<T> node) {
        return node != null ? height(node.getLeftChild()) - height(node.getRightChild()) : 0;
    }

    private int height(Node<T> node) {
        return node != null ? node.getHeight() : -1;
    }


    /*
    The go method starts the program by initializing the repository class which contains the ArrayList that
    contains all the data from the csv file. The go method then creates your key and value pairs and inserts them into
    the AVL Tree
     */
    @Override
    public void go() throws IOException {
        Repository repo = new Repository(); //initializes repository class

        ArrayList<Music> musicArrayList = repo.createMusicArrayList(); //calls array lost from repository class


        //make key value pair
        Map<String, Details> musicMap = new TreeMap<>();
        for (Music music : musicArrayList) {
            musicMap.put(music.getName(), music.getDetails());
        }
        //insert into avl tree
        for (Map.Entry<String, Details> entry : musicMap.entrySet()) {
            this.add(entry);
        }


        // provides the list created from give options and detects input
        findOption();
    }



    //list of your options of the program
    public void giveOption() {
        System.out.println("Choose an option by entering the corresponding number: ");
        System.out.println("\t 1. Find a song");
        System.out.println("\t 2. Add a song");
        System.out.println("\t 3. Count the number of nodes");
        System.out.println("\t 4. Traverse the tree in inorder");
        System.out.println("\t 5. Traverse the tree in preorder");
        System.out.println("\t 6. Traverse the tree in postorder");
        System.out.println("\t 7. Greeting");
        System.out.println("\t 8. Help");
        System.out.println("\t 9. Exit");
    }


    /*
    findOption method contains the list above and detects what your input is with if-and-else statements.
     */
    public void findOption() {
        giveOption();// gives options list
        Scanner enter = new Scanner(System.in); // detects your input
        int chosen = enter.nextInt(); // sets your input into the value chosen


        if (chosen == 1) { // if one is chosen then you enter the key of the song which is the title
            System.out.println("Enter the name of the song: ");
            Scanner enterName = new Scanner(System.in);
            String name = enterName.nextLine();
            this.find(name.toLowerCase()); // ignores upper case of your string

        } else if (chosen == 2) { // if 2 is chosen then you add a song and details to the map.
            System.out.println("Enter the name of the song: ");
            Scanner enterName = new Scanner(System.in);
            String name = enterName.nextLine();

            System.out.println("Enter the artist of the song: ");
            Scanner enterDetails = new Scanner(System.in);
            String artist = enterDetails.nextLine();

            System.out.println("Enter the genre of the song: ");
            Scanner enterGenre = new Scanner(System.in);
            String genre = enterGenre.nextLine();

            System.out.println("Enter the year of the song: ");
            Scanner enterYear = new Scanner(System.in);
            String year = enterYear.nextLine();

            System.out.println("Enter the bpm of the song: ");
            Scanner enterBPM = new Scanner(System.in);
            String bpm = enterBPM.nextLine();

            System.out.println("Enter the nrgy of the song: ");
            Scanner enterNrgy = new Scanner(System.in);
            String nrgy = enterNrgy.nextLine();

            System.out.println("Enter the dnce of the song: ");
            Scanner enterDnce = new Scanner(System.in);
            String dnce = enterDnce.nextLine();

            // everything but the song name is used to create a new details object which is then inserted into the map
            Details details = new Details(artist, genre, year, bpm, nrgy, dnce);

            //the map contains the key(String) and the values(Details)
            Map<String, Details> musicMap = new TreeMap<>();

            //the details object and the name of the song is inserted into the map
            musicMap.put(name, details);

            //the name and details are then put into an entry which is referred too when searching for a specific song.
            Map.Entry<String, Details> entry = musicMap.entrySet().iterator().next();
            this.add(entry);


        } else if (chosen == 3) { // calls the count method which counts the nodes of in the tree
            System.out.println("The number of nodes is: " + this.count());

        } else if (chosen == 4) {// calls the traverseinorder method which traverses tree in order.
            System.out.println("Inorder traversal: ");
            this.traverseInOrder();

        } else if (chosen == 5) {// calls the traversepreorder method which traverses the tree in preorder
            System.out.println("Preorder traversal: ");
            this.traversePreOrder();

        } else if (chosen == 6) { // calls the traversepostorder method which traverses the tree in postorder
            System.out.println("Postorder traversal: ");
            this.traversePostOrder();

        } else if (chosen == 7) { // calls the greeting method
            greeting();

        } else if (chosen == 8) { // calls the help method.
            help();

        } else if (chosen == 9) { // terminates program
            exit();

        } else { // if anything else but the numbers listed above is entered then the message is printed

            System.out.println("Invalid option. Please try again.");
            findOption(); // allows you to reenter an option
        }

        findOption(); // if the system isn't terminated by selecting 9 then it allows you to keep choosing an option
    }

    /*
    finds the name of a song by using the name of the song along with the details.
     */
    @Override
    public void find(String name) {
        find(name, root);
    }



    /*
    The value string "name" is used to find a node that also contains the same name and retrieves the data that is
    associated to that name.
     */
    private void find(String name, Node<T> node) {
        if (node != null) {
            if (name.compareTo(node.getName().toString().toLowerCase(Locale.ROOT)) < 0) { // your input string is compared to a node

                find(name, node.getLeftChild()); // searches on the left side of the tree for the node with similar name as input

            } else if (name.compareTo(node.getName().toString().toLowerCase(Locale.ROOT)) > 0) {

                find(name, node.getRightChild());// searches on the right side of the tree for the node with similar name as input

            } else {
                System.out.println(node.getSearchResult());
            }
        }
    }

    /*
    Method contains a string that contains my name, current class that the project is due for, and the date.
     */
    @Override
    public void greeting() {
        String name = getMyName();
        System.out.println("Hello, " + name + ". Class is: COSC-310. Date is 11/6/2022 ");
    }

    /*
    method contains a small description of what each option does.
     */
    @Override
    public void help() {
        System.out.println("1. Find a song: Enter the name of the song and the program will return the details of the song. Song must be written exactly like in the CSV file. Cases are ignored but spaces matter");
        System.out.println("2. Add a song: Enter the name of the song, the artist, the genre, the year, the bpm, the nrgy and the dnce of the song and the program will add the song to the tree");
        System.out.println("3. Count the number of nodes: The program will return the number of nodes in the tree");
        System.out.println("4. Traverse the tree in inorder: The program will return the inorder traversal of the tree");
        System.out.println("5. Traverse the tree in preorder: The program will return the preorder traversal of the tree");
        System.out.println("6. Traverse the tree in postorder: The program will return the postorder traversal of the tree");
        System.out.println("7. Greeting: The program will return a greeting");
        System.out.println("8. Help: The program will return the help menu");
        System.out.println("9. Exit: The program will exit");
    }

    @Override
    public void exit() { // prints a neat little goodbye

        System.out.println("Goodbye!");
        System.exit(0); // terminates the program

    }
}
