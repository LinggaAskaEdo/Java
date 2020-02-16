package io.vertx.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieInfo
{
    @SerializedName(value = "title", alternate = "Title")
    private String title;

    @SerializedName(value = "year", alternate = "Year")
    private String year;

    @SerializedName(value = "rated", alternate = "Rated")
    private String rated;

    @SerializedName(value = "released", alternate = "Released")
    private String released;

    @SerializedName(value = "runtime", alternate = "Runtime")
    private String runtime;

    @SerializedName(value = "genre", alternate ="Genre")
    private String genre;

    @SerializedName(value = "director", alternate ="Director")
    private String director;

    @SerializedName(value = "writer", alternate ="Writer")
    private String writer;

    @SerializedName(value = "actors", alternate ="Actors")
    private String actors;

    @SerializedName(value = "plot", alternate ="Plot")
    private String plot;

    @SerializedName(value = "language", alternate ="Language")
    private String language;

    @SerializedName(value = "country", alternate ="Country")
    private String country;

    @SerializedName(value = "awards", alternate ="Awards")
    private String awards;

    @SerializedName(value = "poster", alternate ="Poster")
    private String poster;

    @SerializedName(value = "ratings", alternate ="Ratings")
    private List<Rating> ratings;

    @SerializedName(value = "metascore", alternate = "Metascore")
    private String metascore;

    @SerializedName("imdbRating")
    private String imdbRating;

    @SerializedName("imdbVotes")
    private String imdbVotes;

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName(value = "type", alternate ="Type")
    private String type;

    @SerializedName(value = "dvd", alternate ="DVD")
    private String dvd;

    @SerializedName(value = "boxOffice", alternate ="BoxOffice")
    private String boxOffice;

    @SerializedName(value = "production", alternate ="Production")
    private String production;

    @SerializedName(value = "website", alternate ="Website")
    private String website;

    @SerializedName(value = "response", alternate ="Response")
    private String response;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getRated()
    {
        return rated;
    }

    public void setRated(String rated)
    {
        this.rated = rated;
    }

    public String getReleased()
    {
        return released;
    }

    public void setReleased(String released)
    {
        this.released = released;
    }

    public String getRuntime()
    {
        return runtime;
    }

    public void setRuntime(String runtime)
    {
        this.runtime = runtime;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public String getWriter()
    {
        return writer;
    }

    public void setWriter(String writer)
    {
        this.writer = writer;
    }

    public String getActors()
    {
        return actors;
    }

    public void setActors(String actors)
    {
        this.actors = actors;
    }

    public String getPlot()
    {
        return plot;
    }

    public void setPlot(String plot)
    {
        this.plot = plot;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getAwards()
    {
        return awards;
    }

    public void setAwards(String awards)
    {
        this.awards = awards;
    }

    public String getPoster()
    {
        return poster;
    }

    public void setPoster(String poster)
    {
        this.poster = poster;
    }

    public List<Rating> getRatings()
    {
        return ratings;
    }

    public void setRatings(List<Rating> ratings)
    {
        this.ratings = ratings;
    }

    public String getMetascore()
    {
        return metascore;
    }

    public void setMetascore(String metascore)
    {
        this.metascore = metascore;
    }

    public String getImdbRating()
    {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating)
    {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes()
    {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes)
    {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID()
    {
        return imdbID;
    }

    public void setImdbID(String imdbID)
    {
        this.imdbID = imdbID;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDVD()
    {
        return dvd;
    }

    public void setDVD(String dVD)
    {
        this.dvd = dVD;
    }

    public String getBoxOffice()
    {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice)
    {
        this.boxOffice = boxOffice;
    }

    public String getProduction()
    {
        return production;
    }

    public void setProduction(String production)
    {
        this.production = production;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "MovieInfo{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", ratings=" + ratings +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", dvd='" + dvd + '\'' +
                ", boxOffice='" + boxOffice + '\'' +
                ", production='" + production + '\'' +
                ", website='" + website + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}