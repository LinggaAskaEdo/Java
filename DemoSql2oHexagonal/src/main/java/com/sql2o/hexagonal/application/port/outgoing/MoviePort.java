package com.sql2o.hexagonal.application.port.outgoing;

import com.sql2o.hexagonal.application.model.MovieInfo;

public interface MoviePort
{
    MovieInfo findMovieByTitle(String titleKey);
}
