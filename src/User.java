import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dustin
 * Date: 9/1/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    public String name = "";
    private HashMap<String, ArrayList<Tile>> tiles = new HashMap<String, ArrayList<Tile>>();
    private Integer points = 0;

    public User(String userName, TileBag tileBag){
        name = userName;

        for (int i = 0; i < 7; i++){
            ArrayList<Tile> tileHolder = new ArrayList<Tile>();
            Tile tile = tileBag.getTile();
            // Too much repetition with below. Combine/refactor
            if (tiles.get(String.valueOf(tile.getLetter())) != null){
                // The above and below can be combined -- refactor.
                tileHolder = tiles.get(String.valueOf(tile.getLetter()));
                tileHolder.add(tileHolder.size(),tile);
            } else {
                tileHolder.add(tile);
            }
            tiles.put(String.valueOf(tile.getLetter()),tileHolder);
        }
    }

    public String showTiles(){
        String tilesString = "| ";
        Iterator it = tiles.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            for (int i = 0; i < tiles.get(pairs.getKey()).size(); i++){
                tilesString += pairs.getKey();
                tilesString += " | ";
            }


        }
        return tilesString;
    };

    public void addTiles(ArrayList<Tile> tilesToAdd){
        for (int i = 0; i < tilesToAdd.size(); i++){
            ArrayList<Tile> tileHolder = new ArrayList<Tile>();
            Tile tile = tilesToAdd.get(i);
            if (tiles.get(String.valueOf(tile.getLetter())) != null){
                // The above and below can be combined -- refactor.
                tileHolder = tiles.get(String.valueOf(tile.getLetter()));
                tileHolder.add(tile);
            } else {
                tileHolder.add(tile);
            }
            tiles.put(String.valueOf(tile.getLetter()),tileHolder);
        }
    }

    public Tile getTile(char letter){
        if(checkLetter(letter)){
            Tile tile = tiles.get(String.valueOf(letter)).get(0);
            tiles.get(String.valueOf(letter)).remove(0);
            return tile;
        } else {
            return null;
        }
    }

    public boolean checkLetter(char letter){
        if(tiles.get(String.valueOf(letter)) != null){
            return true;
        } else {
            return false;
        }
    }


    public void addPoints(Integer newPoints){
        points += newPoints;
    }

    public Integer getPoints(){
        return points;
    }

    public void resetTiles(int numOfTiles, TileBag tileBag){
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for(int i = 0; i < numOfTiles; i++){
            tiles.add(tileBag.getTile());
        }
        addTiles(tiles);
    }
}