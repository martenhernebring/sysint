package se.hernebring.sysint;

public class Book implements Comparable<Book> {
    
    private String name;
    private int year;
    
    public Book(String name, int year) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        else if (year < -3000 || year > 3000) {
            throw new IllegalArgumentException("Impossible year for book");
        }
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }
    
    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Book other) {
        if (this == other)
            return 0;
        else if (other == null)
            throw new IllegalArgumentException("Cannot compare with null");
        else {
            return name.compareTo(other.getName());
        }
    }

    

    @Override
    public String toString() {
        return "(" + name + " from " + year + ")";
    }

}
