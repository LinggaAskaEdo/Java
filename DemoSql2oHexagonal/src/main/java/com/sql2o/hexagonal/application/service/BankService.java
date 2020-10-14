package com.sql2o.hexagonal.application.service;

import com.sql2o.hexagonal.adapter.preference.ConstantPreference;
import com.sql2o.hexagonal.application.model.BankAccount;
import com.sql2o.hexagonal.application.model.Request;
import com.sql2o.hexagonal.application.model.Response;
import com.sql2o.hexagonal.application.model.exception.BadRequestException;
import com.sql2o.hexagonal.application.model.exception.InternalServerErrorException;
import com.sql2o.hexagonal.application.model.exception.NotFoundException;
import com.sql2o.hexagonal.application.port.incoming.BankUseCase;
import com.sql2o.hexagonal.application.port.outgoing.BankPort;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class BankService implements BankUseCase
{
    private final BankPort bankPort;

    public BankService(BankPort bankPort)
    {
        this.bankPort = bankPort;
    }

    @Override
    public Response checkBalances()
    {
        Optional<List<BankAccount>> bankAccounts = Optional.ofNullable(bankPort.getAccounts());

        if (!bankAccounts.isPresent())
            throw new NotFoundException();

        Response response = new Response();
        response.setBankAccounts(bankAccounts.get());

        return response;
    }

    @Override
    public Response checkBalanceById(long id)
    {
        Optional<BankAccount> bankAccount = Optional.ofNullable(bankPort.findById(id));

        if (!bankAccount.isPresent())
            throw new NotFoundException("id: " + id);

        return new Response(bankAccount.get());
    }

    @Override
    public Response transferBalance(Request request)
    {
        Response response;

        try
        {
            bankPort.transferBalance(request.getSourceId(), request.getDestId(), request.getAmount());
            response = new Response(ConstantPreference.RESPONSE_CODE_OK, ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (InternalServerErrorException isee)
        {
            throw new InternalServerErrorException(isee.getMessage());
        }

        return response;
    }

    @Override
    public Response depositBalance(Request request)
    {
        Response response;

        try
        {
            bankPort.topUpBalance(request.getDestId(), request.getAmount());
            response = new Response(ConstantPreference.RESPONSE_CODE_OK, ConstantPreference.RESPONSE_MESSAGE_OK);
        }
        catch (InternalServerErrorException isee)
        {
            throw new InternalServerErrorException(isee.getMessage());
        }

        return response;
    }

    @Override
    public Response createAccount(Request request)
    {
        Response response = new Response();

        if (request.getAmount() < 1000)
            throw new BadRequestException("Minimum amount must be 1000");

        BigInteger resultId = bankPort.createAccount(request.getFullName(), request.getAmount());

        if (resultId.compareTo(BigInteger.ZERO) > 0)
            response = new Response(ConstantPreference.RESPONSE_CODE_CREATED, ConstantPreference.RESPONSE_MESSAGE_CREATED + ": " + resultId);

        return response;
    }
}
