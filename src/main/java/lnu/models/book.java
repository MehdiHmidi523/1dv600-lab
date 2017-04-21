package lnu.models;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;


@XmlRootElement(name = "book")
@XmlType(propOrder = {"id", "author", "title", "genre", "price", "publishDate", "description"})
public class book {

    @XmlAttribute(name="id")
    public String id;
    public String title;
    public String author;
    public String genre;
    public String price;
    @XmlElement(name = "publish_date")
    public String publishDate;
    public String description;

    public book(String newTitle, String newAuthor, String newGenre, String newPublishDate, String newId, String newDescription, String newPrice){
        title = newTitle;
        id = newId;
        author = newAuthor;
        genre = newGenre;
        publishDate = newPublishDate;
        description = newDescription;
        price = newPrice;
    }

    public book() {

    }

    public String toJson(){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(this);
        }catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public String toString(){
        String output = "Author: " + author + "Title: " + title + "Genre: " + genre + "Price: " + price + "\n Published on "
                + publishDate + "About: " + description;
        return output;
    }
    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String genre) {
        this.price = genre;
    }


    public String getPublishDate() {
        return publishDate;
    }
    @XmlElement(name = "publishDate")
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}