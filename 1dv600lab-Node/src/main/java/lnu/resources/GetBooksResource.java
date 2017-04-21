package lnu.resources;

import lnu.dao.booksDAO;
import lnu.models.Catalog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// Response will be JSON
@Produces(MediaType.APPLICATION_JSON)

// This is the class that will be called when "localhost:9090/api/books" get called by the browser.
// FOR THE CURIOUS: Take a look in the config.yml to find out why "/api" is present in the path.
@Path("/books")
public class GetBooksResource {
	private booksDAO read = new booksDAO();

	@GET
	public Response getBooks(@DefaultValue("All") @QueryParam("title") String title) {
		Catalog books = read.BooksFromFile();
		if(books == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		if(books.getBooks().size() == 0){
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		if(title.equals("All")){
			return Response.ok("[" + books.toJson() + "]",MediaType.APPLICATION_JSON).build();
		}else {
			Catalog filter = new Catalog();
			books.filterBooks(filter, title);
			if(filter.getBooks().isEmpty()){
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			return Response.ok("[" + filter.toJson() + "]",MediaType.APPLICATION_JSON).build();
		}
	}

}
