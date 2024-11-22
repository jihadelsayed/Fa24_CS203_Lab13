
public class EncryptionTester {

    public static void main(String[] args) throws Exception {
        String encryptedFilePath = "src/message.txt";
        String decryptedFilePath = "src/decrypted.txt";
        String inputFilePath = "src/encryptMe.txt";
        String outputFilePath = "src/newEncrypted.txt";

        int shift = 5; // Says how much you would like to shift

        Encrypter enc = new Encrypter(shift);

        enc.encrypt(inputFilePath, outputFilePath);
        enc.decrypt(encryptedFilePath, decryptedFilePath);
    }
}

// reference
// https://docs.oracle.com/cd/E68505_01/wls/WLAPI/weblogic/apache/xml/serialize/LineSeparator.html
// https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#join-java.lang.CharSequence-java.lang.Iterable-
// https://docs.oracle.com/javase/8/docs/api///?java/io/FileWriter.html=