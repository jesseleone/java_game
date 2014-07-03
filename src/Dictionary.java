import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: dustin
 * Date: 9/1/13
 * Time: 12:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Dictionary {
    private ArrayList<String> words = new ArrayList<String>();
    private static Dictionary dictionary = null;

    public static Dictionary getDictionary(){
        if (dictionary == null){dictionary = new Dictionary();};
        return dictionary;
    }

    public Dictionary(){
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/words"));
            while((line = br.readLine()) != null){
                words.add(line.toUpperCase());
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkWord(String word){
        if (words.indexOf(word) != -1){
            return true;
        }
        else{
            return false;
        }
    }
}