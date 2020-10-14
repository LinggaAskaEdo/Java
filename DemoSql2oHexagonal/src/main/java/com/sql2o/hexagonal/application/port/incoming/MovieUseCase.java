package com.sql2o.hexagonal.application.port.incoming;

import com.sql2o.hexagonal.application.model.Response;

public interface MovieUseCase
{
    Response detailMovie(String titleKey);
}