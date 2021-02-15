package se.hernebring.frequency.symbol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileParserTest {
    private String prefix = "/mnt/c/Users/HP/Documents/Yrgo/Java/GitHub/oop";
    private String[] temp = {prefix+"temp.txt"};
    private String[] notTextFile = {prefix+"."};
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test @SuppressWarnings("unused")
    void notATextFile() {
        FileParser textSymbolFrequency = new FileParser(notTextFile);
        String output = String.format("[main] INFO se.hernebring.frequency.symbol.FileParser - File . was opened.%n"+
            "[main] INFO se.hernebring.frequency.symbol.FileParser - File . was not a text-file.%n"+
            "File . is not a text-file.%n");
        assertEquals(output, errContent.toString());
    }
    
    @Test @SuppressWarnings("unused")
    void deleteWorthlessFile() {
        FileParser textSymbolFrequency = new FileParser(temp);
        String output = String.format("[main] INFO se.hernebring.frequency.symbol.FileParser - File temp.txt was opened.%n"
            +"[main] INFO se.hernebring.frequency.symbol.Table - New Table was created from a text collection.%n"
            +"[main] WARN se.hernebring.frequency.symbol.Table - File will be deleted in LinesReader%n"
            +prefix+"temp.txt: Text has no symbols and is blank!. File will be deleted.%n");
        assertEquals(output, errContent.toString());
    }
    
    @Test
    void readPaavoAndSpraken(){
        String[] textFilePaths = {prefix+"paavo.txt",prefix+"spraken.txt"};
        FileParser textSymbolFrequency = new FileParser(textFilePaths);
        assertNotNull(textSymbolFrequency);
        System.out.println(textSymbolFrequency);
    }
    
    @Test
    void nullPointerTest() {
        String[] newLines = {"\n","\n","\n"};
        FileParser fileParser = null;
        try {
            fileParser = new FileParser(newLines);
            System.out.println(fileParser);
            fail("Unexpected counting non-symbol character");
        } catch (NullPointerException ex) {
            System.out.println("This is ok: " + ex.getMessage());
        }
    }
    
}
