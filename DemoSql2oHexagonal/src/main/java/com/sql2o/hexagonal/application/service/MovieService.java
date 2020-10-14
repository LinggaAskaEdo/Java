package com.sql2o.hexagonal.application.service;

import com.sql2o.hexagonal.application.model.MovieInfo;
import com.sql2o.hexagonal.application.model.Response;
import com.sql2o.hexagonal.application.model.exception.NoContentException;
import com.sql2o.hexagonal.application.port.incoming.MovieUseCase;
import com.sql2o.hexagonal.application.port.outgoing.MoviePort;

public class MovieService implements MovieUseCase
{
    private final MoviePort moviePort;

    public MovieService(MoviePort moviePort)
    {
        this.moviePort = moviePort;
    }

    @Override
    public Response detailMovie(String titleKey)
    {
        Response response = new Response();

        MovieInfo movieInfo = moviePort.findMovieByTitle(titleKey);

        if (null == movieInfo)
            throw new NoContentException();

        response.setMovieInfo(movieInfo);

        return response;
    }
}
