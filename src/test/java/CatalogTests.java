package java;

import lnu.models.Catalog;
import lnu.models.book;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CatalogTests {
    book book1,book2,book3,book4,book5,book6,oneUp;
    ArrayList<book> booksList, filterList;
    Catalog books, filter;
    String jsonString;

    @Before
    public void setUp(){

        book1 = new book("title:book1","author:book1","genre:book1","publishDate:book1","1","description:book1","price:book1");
        book2 = new book("title:book2","author:book2","genre:book2","publishDate:book2","2","description:book2","price:book2");
        book3 = new book("title:book3","author:book3","genre:book3","publishDate:book3","3","description:book3","price:book3");
        book4 = new book("title:book4","author:book4","genre:book4","publishDate:book4","4","description:book4","price:book4");
        book5 = new book("title:book5","author:book5","genre:book5","publishDate:book5","5","description:book5","price:book5");
        book6 = new book("title:book6","author:book6","genre:book6","publishDate:book6","6","description:book6","price:book6");
        oneUp = new book("title:oneUp","author:oneUp","genre:oneUp","publishDate:oneUp","1","description:oneUp","price:oneUp");

        booksList = new ArrayList<book>();
        booksList.add(book1);
        booksList.add(book2);
        booksList.add(book3);
        booksList.add(book4);
        booksList.add(book5);
        books = new Catalog();
        filter = new Catalog();
        filterList = new ArrayList<book>();
        books.setBooks(booksList);
    }

    @Test
    public void testGetBooks(){
        //Test that the method getBooks() returns the right list.
        assertEquals(books.getListOfBooks(), booksList);
    }

    @Test
    public void testDeleteBook(){
        //Test that the deleteBook() method doesnt remove anything if the id is not found.
        books.deleteBook("7");
        assertEquals(books.getListOfBooks(), booksList);
        //Show that the deleteBook() method actually removes elements if matched with the id.
        booksList.remove(0);
        books.deleteBook("1");
        assertEquals(books.getListOfBooks(), booksList);
        booksList.add(book1);
    }

    @Test
    public void testAddBook(){
        books.addBook(book1);
        assertEquals(books.getListOfBooks(), booksList);
    }

    @Test
    public void testGetBook(){
        assertEquals(books.getBook("2"),booksList.get(1));
        assertNull(books.getBook("7"));
    }

    @Test
    public void testUpdateBook(){
        //Test that if no id is matched to a book then nothing is changed.
        books.deleteBook("1");
        books.updateBook(book1,"1");
        assertEquals(books.getListOfBooks(), booksList);
        books.addBook(book1);

        //Show that the book is actually updated.
        books.updateBook(oneUp,"1");
        booksList.set(4, oneUp);
        assertEquals(books.getListOfBooks(), booksList);
    }

    @Test
    public void testFilterBook(){
        books.filterBooks(filter,"book1");
        filterList.add(book1);
        assertEquals(filter.getListOfBooks(), filterList);
        books.filterBooks(filter,"title");
        filterList.add(book1);
        filterList.add(book2);
        filterList.add(book3);
        filterList.add(book4);
        filterList.add(book5);
        assertEquals(filter.getListOfBooks(), filterList);
    }

    @Test
    public void testtoJson() throws Exception{
        //Show that the toJson method works.
        jsonString = books.toJson();
        assertThat(jsonString).isEqualTo(fixture("fixtures/books.json"));
    }

    @Test
    public void testToBook() throws Exception{
        assertThat(books.toBook(fixture("fixtures/bookOne.json")).toJson()).isEqualTo(book1.toJson());
    }
}