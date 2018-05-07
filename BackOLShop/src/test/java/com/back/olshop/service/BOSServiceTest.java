/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.service;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BOSServiceTest
{
    @Test
    public void testGenerateTransactionNumber()
    {
        BOSService bosService = new BOSService();
        assertThat(bosService.generateTransactionNumber()).isEqualTo("T110000");
    }
}