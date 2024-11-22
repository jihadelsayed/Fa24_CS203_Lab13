import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;
    private String decrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
        this.decrypted = "";
        
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
        this.decrypted = "";

    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
        String data = readFile(inputFilePath);
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z'};

        //System.out.println("File content: " + data);

        char[] encryptedAlphabet = new char[data.length()];

        for (int i = 0; i < data.length(); i++) {
            if (Character.isLetter(data.charAt(i))){
                //data.charAt(i) = data.charAt(i);
                // for loop over the characters
                for (int j = 0; j < alphabet.length; j++) {
                     if (data.charAt(i) == alphabet[j]) {
                        // if (j + shift < alphabetLength) {
                        //     encryptedAlphabet[i] = alphabet[j + shift];
                        // } else {
                        //     encryptedAlphabet[i] = alphabet[j + shift - alphabetLength];
                        // }
                        int newIndex = (j + shift) % alphabet.length;
                        //Character.isUpperCase(alphabet[newIndex]);
                        encryptedAlphabet[i] = alphabet[newIndex];
                        encrypted = encrypted + (Character.toString(encryptedAlphabet[i]));

                        break;
                    }else if(data.charAt(i) == Character.toUpperCase(alphabet[j])){
                        int newIndex = (j + shift) % alphabet.length;
                        //Character.isUpperCase(alphabet[newIndex]);
                        encryptedAlphabet[i] = Character.toUpperCase(alphabet[newIndex]);
                        encrypted = encrypted + (Character.toString(encryptedAlphabet[i]));

                        break;
                    }
                }
            }else{
                encrypted = encrypted + (Character.toString(data.charAt(i)));
            }
        }
        writeFile(encrypted, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
        String data = readFile(messageFilePath);
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z'};

        System.out.println("File content: " + data);

        char[] decryptedAlphabet = new char[data.length()];

        for (int i = 0; i < data.length(); i++) {
            if (Character.isLetter(data.charAt(i))){
                //data.charAt(i) = data.charAt(i);
                // for loop over the characters
                for (int j = 0; j < alphabet.length; j++) {
                     if (data.charAt(i) == alphabet[j]) {

                        int newIndex = (j - shift+ alphabet.length) % alphabet.length;
                        newIndex = +Math.abs(newIndex); // 2.10 zybooks 

                        //Character.isUpperCase(alphabet[newIndex]);
                        decryptedAlphabet[i] = alphabet[newIndex];
                        decrypted = decrypted + (Character.toString(decryptedAlphabet[i]));

                        break;
                    }else if(data.charAt(i) == Character.toUpperCase(alphabet[j])){
                        int newIndex = (j - shift+ alphabet.length) % alphabet.length;
                        newIndex = +Math.abs(newIndex); // 2.10 zybooks 
                        //Character.isUpperCase(alphabet[newIndex]);
                        decryptedAlphabet[i] = Character.toUpperCase(alphabet[newIndex]);
                        decrypted = decrypted + (Character.toString(decryptedAlphabet[i]));

                        break;
                    }
                }
            }else{
                decrypted = decrypted + (Character.toString(data.charAt(i)));
            }
        }
        writeFile(decrypted, decryptedFilePath);

    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        //TODO: Read file from filePath
         List<String> lines = new ArrayList<>();
         try(Scanner fileScanner = new Scanner(Paths.get(filePath)))
        {
            // read lines by lines until there is no lines
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                // do something with this String
                //System.out.println(line);
                lines.add(line);
                 // add line to the scoreList
            }
            fileScanner.close();
        } catch (Exception e){
            // print the error if there is one
            System.out.println("Error: " + e.toString());
        }
        
        return String.join(System.lineSeparator(), lines);
        // https://docs.oracle.com/cd/E68505_01/wls/WLAPI/weblogic/apache/xml/serialize/LineSeparator.html
        // https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
        // https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#join-java.lang.CharSequence-java.lang.Iterable-
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
        try (FileWriter writer = new FileWriter(new File(filePath))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
