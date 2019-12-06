package org.o7planning.sbjdbctrans.service;

import com.google.gson.Gson;
import org.o7planning.sbjdbctrans.model.MovieInfo;
import org.o7planning.sbjdbctrans.ws.MovieREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WSService
{
    private MovieREST movieREST;

    @Autowired
    public WSService(MovieREST movieREST)
    {
        this.movieREST = movieREST;
    }

    public ResponseEntity<String> getDetailMovie(String titleKey)
    {
        MovieInfo movieInfo = movieREST.getMovieDetails(titleKey);

//        movieInfo.setStatus("200");
//        movieInfo.setMessage("Request was successful !!!");

        return new ResponseEntity<>(new Gson().toJson(movieInfo), HttpStatus.OK);
    }
}