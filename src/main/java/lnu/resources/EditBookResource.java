package lnu.resources;

import lnu.dao.booksDAO;
import lnu.models.Catalog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class EditBookResource {
    private booksDAO read = new booksDAO();


    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") String id, String json){

        Catalog books = read.BooksFromFile();

        if(id == null || id.trim().length() == 0){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(books.updateBook(books.toBook(json), id)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        read.BooksToFile(books);
        return Response.ok().build();
    }

}