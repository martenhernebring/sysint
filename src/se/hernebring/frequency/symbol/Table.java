package se.hernebring.frequency.symbol;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Table used by FileParser who does the error handling
class Table {

    private Map<Character, Integer> frequencyTable = new TreeMap<>(); //for sorted chars
    private final static Logger logger = LoggerFactory.getLogger(Table.class);

    public Table(Collection<String> textCollection) {
        logger.atInfo().log("New Table was created from a text collection.");
        add(textCollection);
    }

    public Table(String textUnit) {
        logger.atInfo().log("New Table was created from a text unit.");
        add(textUnit);
    }

    public Table(Map<Character, Integer> original) {
        logger.atInfo().log("New Table copy was created from internal method.");
        frequencyTable = original;
    }

    public void add(Collection<String> textCollection) {
        for (String textUnit : textCollection) {
            add(textUnit);
        }
    }

    public void add(String textUnit) {
        String[] words = textUnit.split("\\s+"); //removes White space chars
        if (words != null && words.length > 0) {
            add(words);
        } else {
            logger.atWarn().log("File will be deleted in LinesReader");
            throw new IllegalArgumentException("Text has no symbols and is blank!");
        }
    }


    private void add(String[] symbolWords) { //only non-White space is allowed other add
        for (String symbolWord : symbolWords) {
            add(symbolWord.toCharArray());
        }
    }

    private void add(char[] symbols) {
        for (char symbol : symbols) {
            add(symbol);
        }
    }

    private void add(char symbol) {
        add(symbol, 1);
    }

    private void add(char symbol, int value) { //for adding case-insensitivity
        if (frequencyTable.containsKey(symbol)) {
            frequencyTable.put(symbol, frequencyTable.get(symbol) + value);
        } else {
            frequencyTable.put(symbol, value);
        }
    }

    @Override //public is forced
    public String toString() {
        var values = new StringBuilder();
        String newLine = System.getProperty("line.separator"); //vary per OS
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            values.append(entry.getKey() + ": " + entry.getValue() + newLine);
        }
        return values.toString();
    }

    String getCaseInsensitive() {
        Table caseInsensitiveCopy = new Table(frequencyTable);
        for (Character ch = 'A'; ch <= 'Z'; ch++) {
            if (frequencyTable.containsKey(ch)) {
                int value = frequencyTable.get(ch);
                char lowerCase = Character.toLowerCase(ch);
                caseInsensitiveCopy.add(lowerCase, value); //e.g. a=a+A
                caseInsensitiveCopy.remove(ch); //e.g. A is removed
            }
        }
        if(frequencyTable.containsKey((char)197)){
            int value = frequencyTable.get((char)197);
            char lowerCase = ((char)229);
            caseInsensitiveCopy.add(lowerCase, value);
            caseInsensitiveCopy.remove((char)197);
        } else if(frequencyTable.containsKey((char)196)){
            int value = frequencyTable.get((char)196);
            char lowerCase = ((char)228);
            caseInsensitiveCopy.add(lowerCase, value);
            caseInsensitiveCopy.remove((char)196);
        } else if(frequencyTable.containsKey((char)214)){
            int value = frequencyTable.get((char)214);
            char lowerCase = ((char)246);
            caseInsensitiveCopy.add(lowerCase, value);
            caseInsensitiveCopy.remove((char)214);
        } 
        return caseInsensitiveCopy.toString();
    }

    private void remove(char symbol) { //for internal use
        frequencyTable.remove(symbol);
    }

}
