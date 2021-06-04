package org.o7planning.sbjdbctrans.service;

import com.google.gson.Gson;
import org.o7planning.sbjdbctrans.dao.BankAccountDAO;
import org.o7planning.sbjdbctrans.model.BankAccountInfo;
import org.o7planning.sbjdbctrans.model.MovieInfo;
import org.o7planning.sbjdbctrans.model.Response;
import org.o7planning.sbjdbctrans.ws.MovieREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class WSService
{
    private final MovieREST movieREST;
    private final BankAccountDAO accountDAO;

    @Autowired
    public WSService(MovieREST movieREST, BankAccountDAO accountDAO)
    {
        this.movieREST = movieREST;
        this.accountDAO = accountDAO;
    }

    public ResponseEntity<String> getDetailMovie(String titleKey)
    {
        System.out.println("GetDetailMovie");

        Response response = new Response();
        response.setStatus("200");
        response.setMessage("Request was successful !!!");

        MovieInfo movieInfo = movieREST.getMovieDetails(titleKey);

        response.setMovieInfo(movieInfo);

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public String getAsyncDetailMovie(String titleKey)
    {
        System.out.println("GetDetailMovie");
        Response response = new Response();

        try
        {
            response.setStatus("200");
            response.setMessage("Request was successful !!!");
            throw new NullPointerException();
//            MovieInfo movieInfo = movieREST.getMovieDetails(titleKey);
//
//            response.setMovieInfo(movieInfo);


        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        return new Gson().toJson(response);
    }

    public ResponseEntity<String> getDetailAccount(Long accountId)
    {
        System.out.println("GetDetailAccount");

        Response response = new Response();
        response.setStatus("200");
        response.setMessage("Request was successful !!!");

        BankAccountInfo bankAccountInfo = accountDAO.findBankAccount(accountId);

        response.setAccountInfo(bankAccountInfo);

        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    public ListenableFuture<Double> bestQuotation(Double loanAmount)
    {
        return null;
    }
}