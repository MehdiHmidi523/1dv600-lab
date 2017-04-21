package java;

import lnu.models.book;
import org.junit.Before;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookTests {
    book one;
    book two;
    String title = "TitleTestBook";
    String id = "1";
    String author = "AuthorTestBook";
    String genre = "genreTestBook";
    String publishDate = "publishDateTestBook";
    String description = "descriptionTestBook";
    String price = "priceTestBook";

    @Before
    public void setUp(){
        two = new book();
        one = new book();
        one.setTitle(title);
        one.setId(id);
        one.setAuthor(author);
        one.setGenre(genre);
        one.setPublishDate(publishDate);
        one.setDescription(description);
        one.setPrice(price);
    }

    @Test
    public void testBook(){
        assertEquals(one.getTitle(),title);
        assertEquals(one.getId(),id);
        assertEquals(one.getAuthor(),author);
        assertEquals(one.getGenre(),genre);
        assertEquals(one.getPublishDate(),publishDate);
        assertEquals(one.getDescription(),description);
        assertEquals(one.getPrice(),price);
    }

    @Test
    public void testEmpty(){
        assertNull(two.getTitle());
        assertNull(two.getId());
        assertNull(two.getAuthor());
        assertNull(two.getGenre());
        assertNull(two.getPublishDate());
        assertNull(two.getDescription());
        assertNull(two.getPrice());
    }

    @Test
    public void testToString(){
        assertEquals(one.toString(),toStringTest());
    }

    @Test
    public void testJson(){
        assertThat(one.toJson()).isEqualTo(fixture("fixtures/book.json"));
        assertThat(two.toJson()).isEqualTo(fixture("fixtures/bookNull.json"));
    }

    public String toStringTest(){
        String output = "Author: " + author + "Title: " + title + "Genre: " + genre + "Price: " + price + "\n Published on "
                + publishDate + "About: " + description;
        return output;
    }

}

