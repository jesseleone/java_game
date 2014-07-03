import java.util.*;

public class Board {
    //Constructor
    public Board() {
        //creates blank board
        initializeBoard();
    }

    private ArrayList<ArrayList> board = new ArrayList(15);
    private Tile blankTile = new Tile('_', 0);

    //Creates board with blank spaces
    public void initializeBoard(){
        for(int i = 0; i<15; i++){
            board.add(i,new ArrayList<Tile>(15));
        }
        for(int i = 0; i<15; i++){
            ArrayList arrayPlaceHolder = board.get(i);
            for(int j = 0; j < 15; j++){
                arrayPlaceHolder.add(j, blankTile);
            }
        }
    }

    public void printBoard(){
        System.out.println("   1    2    3    4    5    6    7    8    9    10   11   12   13   14   15");
        int rowNumber = 1;
        for (int i=0;i<15;i++){
            ArrayList<Tile> arrayPlaceHolder = board.get(i);
            if(rowNumber<=9){
                System.out.print(rowNumber + " ");
            }else{
                System.out.print(rowNumber);
            }
            rowNumber ++;
            for(int j=0; j<15;j++){
                System.out.print(" _");
                System.out.print(arrayPlaceHolder.get(j).getLetter());
                System.out.print("_ ");
            }
            System.out.println("\n");
        }
    }

    public void placeTiles(ArrayList<Tile> tiles, ArrayList<ArrayList<Integer>> spaces){
        for (int i = 0; i < tiles.size(); i++){
            ArrayList arrayPlaceHolder = board.get(spaces.get(i).get(0) - 1);
            tiles.get(i).setPlayedStatus();
            arrayPlaceHolder.set((spaces.get(i).get(1) - 1), tiles.get(i));
        }
    }

    public boolean checkLetter(char letter, int row, int column){
        ArrayList<Tile> arrayPlaceHolder = board.get(row-1);
        if(arrayPlaceHolder.get(column-1).getLetter() == letter){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<ArrayList> getBoard() {
        return board;
    }
}

