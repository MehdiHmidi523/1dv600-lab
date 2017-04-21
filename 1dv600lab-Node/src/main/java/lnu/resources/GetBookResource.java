package lnu.resources;

import lnu.dao.booksDAO;
import lnu.models.Catalog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public class GetBookResource {
    private booksDAO read = new booksDAO();
    Catalog book = read.BooksFromFile();

    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") String id){

        if(id == null || id.trim().length() == 0){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(book.getBook(id) == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok("[" + book.getBook(id).toJson() + "]",MediaType.APPLICATION_JSON).build();
    }


}