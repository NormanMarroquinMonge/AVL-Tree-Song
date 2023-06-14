public class Music implements Comparable<Music> {
     /*
    This Music class will create another object that contains the name and details of the song
    The attribute "name" will be used as the key in order to return the details for the song.
    The object is created in the repository class as the file is read.
     */

    // fields for the class Music
    String name;

    //the details object from the class Details is set to a field.
    Details details;


    // blueprint for the object "Music" is created using attributes.
    // The returned details and name are both used to create the music object.
    public Music(String name, Details details) { // Constructor
        this.name = name;
        this.details = details;
    }

    /*Everything within the details object is called with this method
     to construct the music object.
     */
    public Details getDetails() {
        return details;
    }

    /*
    getName method provides the name of the song from the array
     */
    public String getName() {
        return name;
    }


    /*
    the compareTo method compares two strings lexicographically. The two strings are your
    input string for the title of the song and the title of the song that is already
    stored within the tree node.
     */
    @Override
    public int compareTo(Music o) {
        return 0;
    }
}
