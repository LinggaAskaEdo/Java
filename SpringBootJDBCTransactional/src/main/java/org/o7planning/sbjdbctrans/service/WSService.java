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

@Service
public class WSService
{
    private MovieREST movieREST;
    private BankAccountDAO accountDAO;

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
}