public class Details implements Comparable<Details> {
    /*
    The Details class will define the object "Details" that contains everything but the name of the
    song in the CSV file. All the attributes that will define the object "details" are initialized first.
     */

    // Attributes for the Details class.
    // Each attribute corresponds to each section in the CSV file besides the name of the song and the number.
    String artist;
    String genre;
    String year;
    String bpm;
    String nrgy;
    String dnce;


    // Create the blueprint for object by creating the objects' constructor.
    // The attributes are then referenced in the object.
    public Details(String artist, String genre, String year, String bpm, String nrgy, String dnce) { //constructor
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.bpm = bpm;
        this.nrgy = nrgy;
        this.dnce = dnce;
    }


    // Method will gather all the attributes together and return them when called.
    public String returnAllDetails() {
        return artist + " " + genre + " " + year + " " + bpm + " " + nrgy + " " + dnce;
    }

    /*
  the compareTo method compares two strings lexicographically. This method will be used for the "find" method
  which allows you to find a song by only typing the name of the song. Your string (name of the song) is compared
  to the name of the song that is already stored within the avl tree. This method retrieves the "details" object
  corresponding to the songs name.
   */
    @Override
    public int compareTo(Details o) {
        return 0;
    }
}
