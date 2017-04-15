package java;

import io.dropwizard.testing.junit.ResourceTestRule;
import lnu.dao.booksDAO;
import lnu.models.Catalog;
import lnu.models.book;
import lnu.resources.*;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import static org.fest.assertions.Assertions.assertThat;

// Look at this to find out how to create an api test.
// Info on how to test with dropwizard: http://www.dropwizard.io/0.8.0/docs/manual/testing.html
public class API_Tests {
    booksDAO read;
    Catalog backup,test,empty,filter;
    book one, oneUp, two,title;

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PingResource())
            .addResource(new AddBookResource())
            .addResource(new EditBookResource())
            .addResource(new GetBookResource())
            .addResource(new GetBooksResource())
            .addResource(new RemoveBookResource())
            .build();

    @Before
    public void setUp(){
        read = new booksDAO();
        backup = read.BooksFromFile();
        test = read.BooksFromFile();
        one = new book("title","author","genre","2008-05-01","0596517742","des","price");
        oneUp = new book("titleUp","author","genre","2008-05-01","0596517742","des","price");
        two = new book();
        empty = new Catalog();
        filter = new Catalog();
        title = test.getBook("1");
    }

    @Test
    public void testGetPing() {
        assertThat(resources.client().target("/ping").request().get(String.class))
                .isEqualTo("{\"answer\": \"pong\"}");
    }

    @Test
    public void testGetBooks(){
        assertThat(resources.client().target("/books").request().get(String.class))
                .isEqualTo("[" + test.toJson() + "]");
        assertThat(resources.client().target("/books").request().get().getStatus())
                .isEqualTo(200);
        read.BooksToFile(empty);
        assertThat(resources.client().target("/books").request().get().getStatus())
                .isEqualTo(404);
        read.BooksToFile(backup);
        test.addBook(oneUp);
        read.BooksToFile(test);
        assertThat(resources.client().target("/books?title=11").request().get().getStatus())
                .isEqualTo(404);
        assertThat(resources.client().target("/books?title=titleUp").request().get(String.class))
                .isEqualTo("[" + oneUp.toJson() + "]");
        assertThat(resources.client().target("/books?title=titleUp").request().get().getStatus())
                .isEqualTo(200);
    }

    @Test
    public void testAddBook(){
        assertThat(resources.client().target("/books/").request().put(Entity.entity(one.toJson(),MediaType.APPLICATION_JSON)).getStatus())
                .isEqualTo(200);
    }

    @Test
    public void testGetBook(){
		/*The method getBook will return the book with the given id as a Json string*/
        assertThat(resources.client().target("/books/1").request().get().getStatus())
                .isEqualTo(200);
        assertThat(resources.client().target("/books/1").request().get(String.class)).isEqualTo("[" + test.getBook("1").toJson() + "]");
        assertThat(resources.client().target("/books/   ").request().get().getStatus())
                .isEqualTo(404);
        assertThat(resources.client().target("/books/102b").request().get().getStatus())
                .isEqualTo(404);
    }

    @Test
    public void testEditBook(){
        test.addBook(one);
        read.BooksToFile(test);
        assertThat(resources.client().target("/books/0596517742").request().post(Entity.entity(one.toJson(),MediaType.APPLICATION_JSON)).getStatus())
                .isEqualTo(200);
        assertThat(resources.client().target("/books/   ").request().post(Entity.entity(one.toJson(),MediaType.APPLICATION_JSON)).getStatus())
                .isEqualTo(404);
        assertThat(resources.client().target("/books/102b").request().post(Entity.entity(one.toJson(),MediaType.APPLICATION_JSON)).getStatus())
                .isEqualTo(404);
        assertThat(resources.client().target("/books/0596517742").request().post(Entity.entity(oneUp.toJson(),MediaType.APPLICATION_JSON)).getStatus())
                .isEqualTo(200);
    }

    @Test
    public void testRemoveBook(){
        assertThat(resources.client().target("/books/1").request().delete().getStatus())
                .isEqualTo(200);
        assertThat(resources.client().target("/books/  ").request().delete().getStatus())
                .isEqualTo(404);
        assertThat(resources.client().target("/books/102b").request().delete().getStatus())
                .isEqualTo(404);
        assertThat(resources.client().target("/books/").request().delete().getStatus())
                .isEqualTo(405);
    }

    @After
    public void reset(){
        read.BooksToFile(backup);
    }
}