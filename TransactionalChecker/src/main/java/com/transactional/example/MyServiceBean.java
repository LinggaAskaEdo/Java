package com.transactional.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class MyServiceBean
{
    public void doSomething()
    {
        doSomething3();
    }

    @Transactional
    public void doSomething2()
    {
        doSomething3();
    }

    @Transactional
    public void doSomething3()
    {
        TransactionStatus status = null;

        try
        {
            status = TransactionAspectSupport.currentTransactionStatus();
        }
        catch (NoTransactionException e)
        {
            System.err.println(e);
        }

        System.out.println(status != null ? "active transaction": "no transaction");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void bank()
    {
        bank1();
        bank2();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void bank1()
    {
        TransactionStatus status = null;

        try
        {
            status = TransactionAspectSupport.currentTransactionStatus();
        }
        catch (NoTransactionException e)
        {
            System.err.println(e);
        }

        System.out.println("bank1 " + (status != null ? "active transaction": "no transaction"));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void bank2()
    {
        TransactionStatus status = null;

        try
        {
            status = TransactionAspectSupport.currentTransactionStatus();
        }
        catch (NoTransactionException e)
        {
            System.err.println(e);
        }

        System.out.println("bank2 " + (status != null ? "active transaction": "no transaction"));
    }
}