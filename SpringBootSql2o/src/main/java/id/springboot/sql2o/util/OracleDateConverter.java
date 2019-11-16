package id.springboot.sql2o.util;

import oracle.sql.TIMESTAMP;
import org.springframework.stereotype.Component;
import org.sql2o.converters.Converter;
import org.sql2o.converters.ConverterException;
import org.sql2o.converters.ConvertersProvider;
import org.sql2o.converters.DateConverter;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

@Component
public class OracleDateConverter extends DateConverter implements ConvertersProvider
{
    @Override
    public Date convert(Object val) throws ConverterException
    {
        if (val instanceof TIMESTAMP)
        {
            try
            {
                TIMESTAMP ts = (TIMESTAMP) val;

                return ts.dateValue();
            }
            catch (SQLException e)
            {
                throw new ConverterException("Error converting from " + val.getClass() + " to java.util.Date", e);
            }
        }
        else
        {
            // fallback to default impl
            return super.convert(val);
        }
    }

    @Override
    public void fill(Map<Class<?>, Converter<?>> mapToFill)
    {
        mapToFill.put(Date.class, new OracleDateConverter());
    }
}