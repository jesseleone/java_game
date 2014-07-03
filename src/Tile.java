/**
 * Created with IntelliJ IDEA.
 * User: dustin
 * Date: 8/19/13
 * Time: 9:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tile {
    private char letter;
    private Integer points;
    private boolean played;

    public Tile(char letterForTile, Integer pointsForTile){
        this.letter = letterForTile;
        this.points = pointsForTile;
    }

    public char getLetter(){
        return this.letter;
    }

    public Integer getPoints(){
        return this.points;
    }

    public boolean getPlayedStatus(){
        return played;
    }

    public void setPlayedStatus(){
        played = true;
    }
}
