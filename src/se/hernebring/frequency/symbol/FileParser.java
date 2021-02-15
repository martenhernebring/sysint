package se.hernebring.frequency.symbol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A class for parsing files to count the frequency of symbols. String table is produced.
 * @author Marten Hernebring, Java20 @version 1*/
public class FileParser {

    private Table textSymbolFrequency = null;
    private final static Logger logger = LoggerFactory.getLogger(FileParser.class);

    /** Check all symbols in the file paths and saves them by frequency.
     * @param filePaths path to text file to examined
     * */
    public FileParser(String[] filePaths) {
        for (String filePath : filePaths) {
            open(Paths.get(filePath));
        }
    }
    
    /** Check all symbols in the file path and saves the frequency by char value.
     * @param textFile path to the text file to examined
     * */
    public void open(Path textFile) {
        try {
            read(textFile);
        } catch (IllegalArgumentException iae) {
            delete(textFile, iae);
        } catch (IOException ioe) {
            if (!Files.exists(textFile)) {
                doesNotExist(textFile, ioe);
            } else {
                isNot(textFile,ioe);
            }
        }
    }

    private void read(Path textFile) throws IOException {
        logger.atInfo().log("File " + textFile.getFileName() + " was opened.");
        List<String> extractedLines = Files.readAllLines(textFile);
        if (textSymbolFrequency == null) {
            textSymbolFrequency = new Table(extractedLines);
        } else {
            textSymbolFrequency.add(extractedLines);
        }
    }
    
    //Text files with only White-space should be deleted
    private void delete(Path textFile, IllegalArgumentException iae) {
        System.err.printf("%s: %s. File will be deleted.%n", textFile, iae.getMessage());
        try {
            Files.delete(textFile);
        } catch (IOException ioe) {
            doesNotExist(textFile, ioe);
        }
    }
    
    private void doesNotExist(Path textFile, IOException ioe) {
        logger.atWarn().log("File " + textFile.getFileName() + " didnt't exist!");
        System.err.printf("File %s does not exist.%n", ioe.getMessage());
    }
    
    private void isNot(Path textFile, IOException ioe) {
        logger.atInfo().log("File " + textFile.getFileName() + " was not a text-file.");
        System.err.printf("File %s is not a text-file.%n", textFile.getFileName());   
    }
    
    /** To print out the symbol frequency table case sensitive
     * @return String representation of the symbol frequency table with case.
     * */
    @Override
    public String toString() {
        if (textSymbolFrequency != null) {
            return textSymbolFrequency.toString();
        } else {
            logger.atError().log("Class implementation is useless.");
            throw new NullPointerException("There was no text-file containing symbols.");
        }
    }

    /** To print out the symbol frequency table case insensitive
     * @return String representation of the symbol frequency table ignoring case.
     * */
    public String toCaseInsensitive() {
        if (textSymbolFrequency != null) {
            return textSymbolFrequency.getCaseInsensitive();
        } else {
            logger.atError().log("Class implementation is useless.");
            throw new NullPointerException("There was no text-file containing symbols.");
        }
    }

}
