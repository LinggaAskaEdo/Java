package com.hexagonal.sample.application.port.incoming;

import java.math.BigDecimal;

public interface DepositUseCase
{
    void deposit(Long id, BigDecimal amount);
}