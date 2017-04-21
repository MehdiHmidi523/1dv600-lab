package lnu.resources;

import lnu.dao.booksDAO;
import lnu.models.Catalog;
import lnu.models.book;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public class AddBookResource {
    private booksDAO read = new booksDAO();

    /**
     * Accepts a String of Type Json from the Client
     * Converts that String into an Object of type book
     * Adds the new book to the database.
     * @param json
     * @return Response value, 200 for OK and 404 if the Json String raises and exception.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(String json){
        Catalog books = read.BooksFromFile();
        System.out.println("String " + json);
        book myAddedBook;
        try{
            myAddedBook = books.toBook(json);
        }catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(myAddedBook.getId() == null){
            myAddedBook.setId((books.getHighest() + 1)+"");
        }

        books.addBook(myAddedBook);
        read.BooksToFile(books);
        return Response.ok().build();
    }


}
