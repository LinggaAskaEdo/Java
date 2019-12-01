package org.o7planning.sbjdbctrans.mapper;

import org.o7planning.sbjdbctrans.model.BankAccountInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements RowMapper<BankAccountInfo>
{
    public static final String BASE_SQL = "SELECT ba.ID, ba.FULL_NAME, ba.BALANCE FROM BANK_ACCOUNT ba ";

    @Override
    public BankAccountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException
    {
        Long id = resultSet.getLong("ID");
        String fullName = resultSet.getString("FULL_NAME");
        double balance = resultSet.getDouble("BALANCE");

        return new BankAccountInfo(id, fullName, balance);
    }
}