package lnu.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import lnu.models.book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Response will be JSON
@Produces(MediaType.APPLICATION_JSON)

// This is the class that will be called when "localhost:9090/api/books" get called by the browser.
// FOR THE CURIOUS: Take a look in the config.yml to find out why "/api" is present in the path.
@Path("/books")
public class GetBooksResource {

	private List<book> booklist = new ArrayList<>();

	@GET
	public String getBooks() {


			book book1 = new book(1, "The Metamorphosis", "Franz Kafka", "Philosophical novella", "$50", "1915", "The story begins with a traveling salesman, Gregor Samsa, waking to find himself transformed (metamorphosed) into a large, monstrous insect-like creature. The cause of Gregor's transformation is never revealed, and Kafka himself never gave an explanation. The rest of Kafka's novella deals with Gregor's attempts to adjust to his new condition as he deals with being burdensome to his parents and sister, who are repelled by the horrible, verminous creature Gregor has become.");
			booklist.add(book1);
			book book2 = new book(2, "Animal Farm", "George Orwell", "Satirical fable", "$50", "1945-08-17", "Mr Jones of Manor Farm is so lazy and drunken that one day he forgets to feed his livestock. The ensuing rebellion under the leadership of the pigs Napoleon and Snowball leads to the animals taking over the farm. Vowing to eliminate the terrible inequities of the farmyard, the renamed Animal Farm is organised to benefit all who walk on four legs. But as time passes, the ideals of the rebellion are corrupted, then forgotten. And something new and unexpected emerges...Animal Farm - the history of a revolution that went wrong - is George Orwell's brilliant satire on the corrupting influence of power.");
			booklist.add(book2);
			book book3 = new book(3, "The House of the Spirits", "Isabel Allende", "Magic realism", "$50", "1982", "The story details the life of the Trueba family, spanning four generations, and tracing the post-colonial social and political upheavals of Chile, though the country's name, and the names of figures closely paralleling historical ones, such as 'the President' or 'the Poet', are never explicitly given. The story is told mainly from the perspective of two protagonists Esteban and Alba.");
			booklist.add(book3);

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = "";
		try {
            jsonInString= mapper.writerWithDefaultPrettyPrinter().writeValueAsString(booklist);
			System.out.println(jsonInString);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonInString;
	}

}
