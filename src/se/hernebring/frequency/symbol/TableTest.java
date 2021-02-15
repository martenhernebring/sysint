package se.hernebring.frequency.symbol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class TableTest {

    Table symbolFrequency;

    @Test
    void add3NewLines() {
        String newLinesText = "\n\n\n";
        try {
            symbolFrequency = new Table(newLinesText);
            fail("Unexpected counting non-symbol character");
        } catch (IllegalArgumentException ex) {
            System.out.println("This is ok: " + ex.getMessage());
        }
        assertThrows(NullPointerException.class, () -> symbolFrequency.toString());
    }

    @Test
    void addExclamationMark() {
        String exclamationMark = "!";
        try {
            symbolFrequency = new Table(exclamationMark);
        } catch (IllegalArgumentException ex) {
            fail("Unexpected exception thrown");
            System.out.println(ex.getMessage());
        }
        String newLine = System.getProperty("line.separator");
        assertEquals("!: 1" + newLine, symbolFrequency.toString());
    }
    
    @Test
    void addTextCollection() {
        Collection<String> uppgift = new ArrayList<>();
        uppgift.add("Du skall skapa ett program som skapar en frekvenstabell över tecknen i en given uppsättning textfiler. Det vill\n" + 
                "säga att den skall räkna hur många gånger varje tecken förekommer och skriva ut detta antal.");
        uppgift.add("De filer programmet skall undersöka anges på kommandoraden.");
        uppgift.add("Om programmet inte kan öppna vissa av filerna skall användaren informeras om detta (på System.err) men\n" + 
                "frekvensinformation skall skrivas ut som normalt för de filer som gick att läsa.");
        uppgift.add("Räkna inte blanktecken som t.ex. nyrader och mellanslag.");
        uppgift.add("Extra bonusfunktion (inga extra poäng): Om flaggan -i anges som första argument skall programmet inte\n" + 
                "skilja på stora och små bokstäver.");
        symbolFrequency = new Table(uppgift);
        assertNotNull(symbolFrequency);
        System.out.println(symbolFrequency.getCaseInsensitive());
    }
    
    @Test
    void swedishLetters() {
    	String aa = "Åå";
    	System.out.println(aa+": "+(int)'Å'+" "+(int)'å');
    	String ae = "Ää";
    	System.out.println(ae+": "+(int)'Ä'+" "+(int)'ä');
    	String oe = "Öö";
    	System.out.println(oe+": "+(int)'Ö'+" "+(int)'ö');
    	List<String> swedish = new ArrayList<>(Arrays.asList(aa, ae, oe));
    	Table sf = new Table(swedish);
    	System.out.printf("Case sensitive%n%s%nCase insensitive%n%s%n ", sf, sf.getCaseInsensitive());
    }
}
