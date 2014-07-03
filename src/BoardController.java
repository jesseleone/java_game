import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BoardController {

    private static int userCount = 0;
    private static ArrayList<User> userList = new ArrayList();
    private static User activeUser = null;
    private static Dictionary dictionary = Dictionary.getDictionary();
    private static Board board = new Board();

    private static ArrayList<Tile> tilesToPlay = new ArrayList<Tile>();
    private static ArrayList<ArrayList<Integer>> spaces = new ArrayList<ArrayList<Integer>>();
    private static String word;

    private static TileBag tileBag = new TileBag();

    private static Integer tilesToReturnToUser = 0;


    //starts a new game
    public static void newGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?");
        userCount = scanner.nextInt();
        for(int i = 0; i<userCount; i++){
            Integer userCount = i + 1;
            User user = new User("Player " + userCount.toString(), tileBag);
            userList.add(i, user);
        }
        activeUser = userList.get(0);
        userPrompt();
    }

    public static void userPrompt(){
        Tile tileToPlay;
        Scanner scanner = new Scanner(System.in);
        while (tileBag.getNumberOfTiles() > 0){
            board.printBoard();
            System.out.println("Here are your remaining tiles:");
            System.out.println(activeUser.showTiles());
            System.out.println(activeUser.name + ", enter a letter to be played or 'done' when complete");
            String inputString = scanner.next();
            if(!inputString.equals("done")){
                char charPlay = inputString.charAt(0);
                System.out.println(activeUser.name + ", enter a row # to play");
                int row = scanner.nextInt();
                System.out.println(activeUser.name + ", enter a column # to play");
                int column = scanner.nextInt();
                ArrayList<Tile> placeHolderArray = board.getBoard().get(row-1);
                if(board.checkLetter(charPlay, row, column)){
                    tileToPlay = placeHolderArray.get(column-1);
                    setTile(tileToPlay, row, column);
                }else if(activeUser.checkLetter(charPlay)){
                    tileToPlay = activeUser.getTile(charPlay);
                    setTile(tileToPlay, row, column);
                    tilesToReturnToUser++;
                }
                else {
                    System.out.println("That's not a valid move");
                }

                userPrompt();
            }else{
                if(finalizePlay()){
                    changeTurn();
                } else {
                    System.out.println("That's not a valid play. Try again.");
                }
                userPrompt();
            }
        }
        finishGame();
    }

    //changes turn after play, if user is last user, go to user 1
    public static void changeTurn(){
        if(activeUser == userList.get(userList.size()-1)){
            activeUser = userList.get(0);
        }
        else {
            activeUser = userList.get(userList.indexOf(activeUser)+1);
        }
        spaces.clear();
        tilesToReturnToUser = 0;
    }

    // Are these next three used?
    public static int getUserCount() {
        return userCount;
    }

    public static ArrayList getUserList() {
        return userList;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setTile(Tile tileToBePlayed, int rowToPlay, int columnToPlay){
        tilesToPlay.add(tileToBePlayed);
        ArrayList<Integer> space = new ArrayList<Integer>();
        space.add(rowToPlay);
        space.add(columnToPlay);
        spaces.add(space);
    }

    public static void giveTilesBack(){
        ArrayList<Tile> tilesToBeReturned = new ArrayList<Tile>();
        for (int i = 0; i < tilesToPlay.size(); i++){
            if (!tilesToPlay.get(i).getPlayedStatus()){
                tilesToBeReturned.add(tilesToPlay.get(i));
            }
        }
        activeUser.addTiles(tilesToBeReturned);
        tilesToPlay.clear();
    }

    public static boolean finalizePlay(){
        for (int i = 0; i < tilesToPlay.size(); i++){
            word = new StringBuilder().append(tilesToPlay.get(i).getLetter()).toString();
        }
        if(dictionary.checkWord(word)){
            providePointsToUser();
            board.placeTiles(tilesToPlay, spaces);
            activeUser.resetTiles(tilesToReturnToUser,tileBag);
            tilesToPlay.clear();
            return true;
        }
        else{
            giveTilesBack();
            tilesToPlay.clear();
            return false;
        }
    }

    private static void providePointsToUser(){
        Integer points = 0;
        for (int i = 0; i < tilesToPlay.size(); i++){
            points += tilesToPlay.get(i).getPoints();
        }
        activeUser.addPoints(points);
    }

    private static void finishGame(){
        System.out.println("Game over!");
        for (int i = 0; i < userCount; i++){
            System.out.println(userList.get(i).name);
            System.out.println(userList.get(i).getPoints());
        }
    }
}