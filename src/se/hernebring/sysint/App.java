package se.hernebring.sysint;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        Book[] books = {new Book("Gamma", 2000), new Book("Alfa", 2010), new Book("Beta", 1990)};
        System.out.println("Before sort "+ Arrays.toString(books));
        Arrays.sort(books);
        System.out.println("After name sort "+ Arrays.toString(books));
        Arrays.sort(books, new BookYearComparator());
        System.out.println("After year sort "+ Arrays.toString(books));
    }

}
