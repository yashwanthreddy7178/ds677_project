import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BorrowerTest {
    Borrower borrower;
    Book book;

    @Before
    public void before(){
        borrower = new Borrower();
        book = new Book("The Slipery Slope", "Lemony Snicket", "Mystery");

    }
    @Test
    public void collectionCount(){
        assertEquals(0, borrower.collectonCount());
    }

    @Test
    public void addBook(){
        borrower.addBook(book);
        assertEquals(1, borrower.collectonCount() );

    }
    @Test
    public void removeBookFromCollection(){
        borrower.addBook(book);
        assertEquals(1,borrower.collectonCount());
        borrower.removeBookFromCollection(book);
        assertEquals(0, borrower.collectonCount());
    }
}
