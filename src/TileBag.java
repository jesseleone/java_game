import javax.swing.text.Style;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: dustin
 * Date: 8/19/13
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class TileBag {
    private ArrayList<Tile> tileBag = new ArrayList<Tile>();

    public TileBag(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/letterFrequencies.txt"));
            String line;
            int numberOfTiles;
            int tilePoints;
            char tileLetter;
            tileBag.clear();
            // Read from file and create tiles one-by-one then put into the tileBag likeso:
            while((line = br.readLine()) != null){
                String[] lineInfo = line.split(" ");
                tileLetter = lineInfo[0].charAt(0);
                numberOfTiles = Integer.parseInt(lineInfo[1]);
                tilePoints = Integer.parseInt(lineInfo[2]);
                for (int i = numberOfTiles; i > 0; i--){
                    Tile tile = new Tile(tileLetter,tilePoints);
                    tileBag.add(tile);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Tile getTile(){
        if (tileBag.size() == 0){
            return null;
        }
        else{
            int i = (int)(Math.random() * (tileBag.size() ));
            Tile tile = tileBag.get(i);
            tileBag.remove(i);
            tileBag.trimToSize();
            return tile;
        }
    };

    public Integer getNumberOfTiles(){
        return tileBag.size();
    };

}
