package com.edelwish;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdelwishApplicationTests
{
	@Test
	void generateQueryParams()
	{
		int id = 4;
		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		String result = "INSERT INTO `EDELWISH`.`PACKAGE_DETAIL_PACKAGE`(`package_id`,`detail_package_id`) VALUES (4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(4,8),(4,9),(4,10)";
		StringBuilder sql = new StringBuilder("INSERT INTO `EDELWISH`.`PACKAGE_DETAIL_PACKAGE`(`package_id`,`detail_package_id`) VALUES ");

		System.out.println("Length: " + data.length);

		for (int i = 0; i < data.length; i++)
		{
			System.out.println(i);

			sql.append("(").append(id).append(",").append(data[i]).append(")");

			if (i + 1 != data.length)
			{
				sql.append(",");
			}
		}

		System.out.println(sql.toString());

		assertEquals(result, sql.toString());
	}

	@Test
	void generateUrl()
	{
		System.out.println("https://osb.id00c1.id.infra/ID_Mobile/ContractServicesRestService_v2/insurance/?contractNumbers=:contractNumber".replaceAll(":contractNumber", "4000010992"));
	}
}