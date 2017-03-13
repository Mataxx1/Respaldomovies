package erick.munoz.guerrero.models;

/**
 * Created by Lenovo-PC on 11/03/2017.
 */

public class Movie {
    private int id;
    private String title,description,posterPath;


    public Movie(int id, String title, String description, String posterPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
