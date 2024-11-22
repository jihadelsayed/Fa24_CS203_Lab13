
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class GameUtils {
        public static String convertWordToStar(String word) {
            StringBuilder secretWord = new StringBuilder();
            for (int n = 0; n < word.length(); n++) {
                secretWord.append("*");  
            }
            return secretWord.toString();
        }
        public static String GenerateRandomWord() {
            // list of simple words 
            //String[] words = {"apple", "banana", "cherry", "jihad", "hello", "computer"};
        
            // generate random word from words
            int randomInt = (int) (Math.random() * readWordList().size());
            String randomWord = readWordList().get(randomInt);
            System.out.println("Random word: " + randomWord);
            return randomWord;
        }
    public static ArrayList<String> readWordList()
    {
        ArrayList<String> wordsList = new ArrayList<>();

        // scan or read words from the dictionary file (get the dictionary from the current path of the project)
        try(Scanner fileScanner = new Scanner(Paths.get("dictionary.txt")))
        {
            // read lines by lines until there is no lines
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                // do something with this String
                //System.out.println(line);
                wordsList.add(line); // add line to the wordsList 
            }
            fileScanner.close();
        } catch (Exception e){
            // print the error if there is one
            System.out.println("Error: " + e.toString());
        }
        return wordsList;
    }
    public static ArrayList<String> readScoreList()
    {
        ArrayList<String> scoreList = new ArrayList<>();

        // scan or read scores from the score file (get the score from the current path of the project)
        try(Scanner fileScanner = new Scanner(Paths.get("score.txt")))
        {
            // read lines by lines until there is no lines
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                // do something with this String
                //System.out.println(line);
                scoreList.add(line);
                // add line to the scoreList
            }
            fileScanner.close();
        } catch (Exception e){
            // print the error if there is one
            System.out.println("Error: " + e.toString());
        }
        return scoreList;
    }
    public static ArrayList<String> topThreeScores(){
        ArrayList<String> sortScoreList = new ArrayList<String>();
        ArrayList<String> sortList = GameUtils.readScoreList();
        ArrayList<Score> scores = new ArrayList<>();

        
        for(String word : sortList)
        {
            String[] parts = word.split(" Score: ");
            String scoreGet =parts[1].trim();
            String nameGet = parts[0].trim();
            int score = Integer.parseInt(scoreGet);
            scores.add(new Score(nameGet, score));
        }
        scores.sort((a, b) -> b.score - a.score);
        for(Score scoree : scores)
        {
            String scoreAndName = scoree.name+  " Score: " + Integer.toString(scoree.score);
            sortScoreList.add(scoreAndName);
        }
        return sortScoreList;

    }
    public static String savePlayerNameAndScore(String playerNameAndScore, boolean updateScore) {
        ArrayList<String> scoreList = GameUtils.readScoreList();
        boolean isExistedUser = false;

        // Print over the file score.txt line by line from the start
        try(PrintWriter outPut = new PrintWriter("score.txt"))
        {
            // get the score list and print it to the file to make sure that the info there
            for(String word : scoreList)
            {
                //System.out.println(word);
                if(word.startsWith(playerNameAndScore)) // check if the player exists
                {
                    String scoreGet = "0";
                    String nameGet = "player1";
                    // check if the player has a score
                    if(word.contains(" Score: ") || playerNameAndScore.contains(" Score: "))
                    {
                        // get the score and the name separately 
                        String[] parts = word.split(" Score: ");
                        scoreGet =parts[1].trim();
                        nameGet = parts[0].trim();
                        
                    }else{
                        nameGet = playerNameAndScore;
                    }
                    
                    isExistedUser = true;
                    // convert score to int
                    int score = Integer.parseInt(scoreGet);
                    // if update score increase it by one
                    if(updateScore){
                        score++;
                        

                    }
                    outPut.println(nameGet + " Score: " + score);
                    playerNameAndScore = nameGet + " Score: " + score;
                    System.out.println(playerNameAndScore);
                }else{ 
                    // else save it in the file 
                    outPut.println(word);
                    
                }
            }
            if(!isExistedUser){
                // if the player does not exist, add them to the file with score 0
                playerNameAndScore = playerNameAndScore + " Score: 0";
                outPut.println(playerNameAndScore);
            }
            // close the file
            outPut.close();
        } catch (Exception e){
            // print the error if there is one
            System.out.println("Error: " + e.toString());
        }
        return playerNameAndScore;
    }
}
