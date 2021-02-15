package se.hernebring.sysint;

import java.util.Comparator;

public class BookYearComparator implements Comparator<Book> {

    @Override
    public int compare(Book first, Book second) {
        int a = first.getYear();
        int b = second.getYear();
        if(a > b) {
            return 1;
        } else if (a == b) {
            return 0;
        } else {
            return -1;
        }
    }

}
