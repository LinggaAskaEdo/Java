package com.sql2o.hexagonal.application.port.incoming;

import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;

public interface BankUseCase
{
    Response checkBalances();
    Response checkBalanceById(long id);
    Response transferBalance(Request request);
    Response depositBalance(Request request);
    Response createAccount(Request request);
}
