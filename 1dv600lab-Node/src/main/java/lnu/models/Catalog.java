
package lnu.models;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.ArrayList;

@XmlRootElement(name = "catalog")
public class Catalog {

    private ArrayList<book> listOfBooks;
    public Catalog(){
        listOfBooks = new ArrayList<book>();
    }

    public ArrayList<book> getListOfBooks() {
        return listOfBooks;
    }

    @XmlElement(name = "book")
    public void setListOfBooks(ArrayList<book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    public ArrayList<book> getBooks(){
        return listOfBooks;
    }

    public book getBook(String id){
        for(int i = 0; i < listOfBooks.size(); i++){
            if(listOfBooks.get(i).getId().equals(id)){
                return listOfBooks.get(i);
            }
        }
        return null;
    }

    public int getHighest(){
        int highest = 0;
        for(int i = 0; i < listOfBooks.size(); i++ ){
            int current = Integer.parseInt(listOfBooks.get(i).getId());
            if(current > highest){
                 highest = current;
            }
        }
        return highest;
    }

    @XmlElement(name = "book")
    public void setBooks(ArrayList<book> Books){
        this.listOfBooks = Books;
    }

    public String toJson(){
        int length = 0;
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            for(int i = length;i < listOfBooks.size();i++){
                jsonString += mapper.writeValueAsString(listOfBooks.get(i));
                if(i + 1 != listOfBooks.size()){
                    jsonString += ",";
                }
            }

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(jsonString);
        return jsonString;
    }

    public Catalog filterBooks(Catalog filter, String title){
        for(int i = 0; i < listOfBooks.size(); i++){
            if(listOfBooks.get(i).getTitle().contains(title)){
                filter.addBook(listOfBooks.get(i));
            }
        }
        return filter;
    }

    public void addBook(book book){
        listOfBooks.add(book);
    }

    public boolean deleteBook(String id){
        for(int i = 0; i < listOfBooks.size(); i++){
            if(listOfBooks.get(i).getId().equals(id)){
                listOfBooks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateBook(book book, String id){
        for(int i = 0; i < listOfBooks.size(); i++){
            if(listOfBooks.get(i).getId().equals(id)){
                listOfBooks.set(i, book);
                return false;
            }
        }
        return true;
    }

    public book toBook(String json){
        ObjectMapper mapper = new ObjectMapper();
        book book = null;
        try {
            ObjectMapper configure = mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            book = mapper.readValue(json, book.class);
        }catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }
}