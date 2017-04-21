package lnu.resources;

import lnu.dao.booksDAO;
import lnu.models.Catalog;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/books/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class RemoveBookResource {
    private booksDAO read = new booksDAO();

    @DELETE
    @Path("{id}")
    public Response removeBooks(@PathParam("id") String id) {
        Catalog oneFromFile = read.BooksFromFile();

        if(id == null || id.trim().length() == 0){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(!oneFromFile.deleteBook(id)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        read.BooksToFile(oneFromFile);
        return Response.ok().build();
    }

    }