package es.unizar.aisolutions.aimovie.data;

import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by dbarelop on 27/04/15.
 */
public class IMDbMovie extends Movie {
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("d MMM yyyy");
    private String title;
    private int year;
    private String rated;
    private Date released;
    private String runtime;
    private List<String> genre;
    private String director;
    private String writer;
    private List<String> actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private URL poster;
    private Integer metascore;
    private Float imdbRating;
    private Integer imdbVotes;
    private String imdbID;

    public IMDbMovie(JsonObject jsonMovie) throws MovieParseException {
        String response = jsonMovie.get("Response").getAsString();
        if (!response.equals("False")) {
            String type = jsonMovie.get("Type").getAsString();
            if (type.equals("movie")) {
                title = jsonMovie.get("Title").getAsString();
                year = jsonMovie.get("Year").getAsInt();
                rated = jsonMovie.get("Rated").getAsString();
                try {
                    released = DATE_FORMATTER.parse(jsonMovie.get("Released").getAsString());
                } catch (ParseException e) {
                    released = null;
                }
                runtime = jsonMovie.get("Runtime").getAsString();
                genre = Arrays.asList(jsonMovie.get("Genre").getAsString().split(","));
                director = jsonMovie.get("Director").getAsString();
                writer = jsonMovie.get("Writer").getAsString();
                actors = Arrays.asList(jsonMovie.get("Actors").getAsString().split(","));
                plot = jsonMovie.get("Plot").getAsString();
                language = jsonMovie.get("Language").getAsString();
                country = jsonMovie.get("Country").getAsString();
                awards = jsonMovie.get("Awards").getAsString();
                try {
                    poster = new URL(jsonMovie.get("Poster").getAsString());
                } catch (MalformedURLException e) {
                    poster = null;
                }
                try {
                    metascore = Integer.parseInt(jsonMovie.get("Metascore").getAsString());
                } catch (NumberFormatException e) {
                    metascore = null;
                }
                try {
                    imdbRating = Float.parseFloat(jsonMovie.get("imdbRating").getAsString());
                } catch (NumberFormatException e) {
                    imdbRating = null;
                }
                try {
                    imdbVotes = Integer.parseInt(jsonMovie.get("imdbVotes").getAsString().replace(",", ""));
                } catch (NumberFormatException e) {
                    imdbVotes = null;
                }
                imdbID = jsonMovie.get("imdbID").getAsString();
            } else {
                throw new MovieParseException("Provided JSON doesn't belong to a movie");
            }
        } else {
            throw new MovieParseException("Error parsing IMDbMovie from JSON");
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public Date getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public List<String> getGenre() {
        return genre;
    }

    @Override
    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public List<String> getActors() {
        return actors;
    }

    @Override
    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public URL getPoster() {
        return poster;
    }

    public int getMetascore() {
        return metascore;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }
}
