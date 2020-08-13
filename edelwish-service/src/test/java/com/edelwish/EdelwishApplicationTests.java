package com.edelwish;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class EdelwishApplicationTests
{
	@Test
	void randomNumber()
	{
		String result = UUID.randomUUID().toString();
		System.out.println(result);

		assertThat(result, notNullValue());
	}
}