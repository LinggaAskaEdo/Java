package springboot.lpe.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Preference
{
    @Value("${test.one}")
    private String noteOne;

    @Value("${test.username}")
    private String noteTwo;

    @Value("${test.password}")
    private String noteThree;

    @Value("${test.password2}")
    private String noteFour;

    @Value("${test.password3}")
    private String noteFive;

    public String getNoteOne()
    {
        return noteOne;
    }

    public String getNoteTwo()
    {
        return noteTwo;
    }

    public String getNoteThree()
    {
        return noteThree;
    }

    public String getNoteFour()
    {
        return noteFour;
    }

    public String getNoteFive()
    {
        return noteFive;
    }
}