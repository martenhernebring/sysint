package se.hernebring.frequency.symbol;
/*
Mårten Hernebrings Java-files for oop Autumn 2020
Necessary libraries: 
1. org.slf4j (logger [version 2]) slf4j-api-2.0.0-alpha0 and slf4j-simple-2.0.0-alpha0
2. org.junit.jupiter.api (tester [version 5])

Test run results:
1. 100 % coverage of Table.java from TableTest.java
2. 89.2 % coverage of FileParser.java from FileParserTest.java
3. FileApp.java was tested by using the terminal covering almost all applications.

FileParserTest.java necessary personal changes:
1. Change String prefix from ("/home/marten/eclipse-workspace/oop/src/") to this folder.
2. Create a temp.txt in this folder containing only White space (\n \r \t " ")
3. Add text-files paavo.txt and spraken.txt to this folder.

FileParserTest.java Important notes:
1. Logger must be added before running FileParserTest.java with at least info-level messages.
2. Temp.txt need to be re-created every time FileParserTest.java is run
*/