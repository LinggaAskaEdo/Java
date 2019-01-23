package id.co.homecredit.cc.pin.util;

import id.co.homecredit.cc.pin.preference.ConstantPreference;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;

@Component
public class CreditCardUtil
{
    public String parseDate(XMLGregorianCalendar calendar)
    {
        return new SimpleDateFormat(ConstantPreference.DOB_DATE_FORMAT).format(calendar.toGregorianCalendar().getTime());
    }
}