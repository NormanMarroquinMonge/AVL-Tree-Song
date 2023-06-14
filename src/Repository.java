import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Repository {
    /*
    The repository class creates an array list in which each data read from the CSV file is stored in
    The CSV file is read through columns and the columns are referenced from 0 to 7 with 0 being the
    first column and 7 being the last column in the file.
    This arraylist is connected to
     */



    public ArrayList<Music> createMusicArrayList() throws IOException {
        StringBuilder fileContents = new StringBuilder();
        File file = new File("top10s_1.csv");
        ArrayList<Music> musicArrayList = new ArrayList<Music>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                //print title only

                //create music object using details and title
                Details details = new Details(

                        /*
                        The "(?=([^"]*"[^"]*")*[^"]*$)" segment ignores any commas within a string preventing
                        the buffer reader from separating at unnecessary points.
                        For example, in the song "Hey, soul sister" the buffer reader will ignore the comma in
                        the title string and instead read the comma at the end of the name which separates
                        the title column from the artist column.
                         */

                        line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")[2],
                        line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")[3],
                        line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")[4],
                        line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")[5],
                        line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")[6],
                        line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")[7]
                );
                String title = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")[1];

                // object Music is created
                musicArrayList.add(new Music(title, details));
            }

        }

        return musicArrayList;
    }
}
