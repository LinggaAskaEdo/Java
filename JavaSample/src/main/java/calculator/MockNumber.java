package calculator;

/**
 * Created by Lingga on 20/02/17.
 */

public class MockNumber
{
    private String strNumberOne;
    private String strNumberTwo;
    private String strResult;

    String getStrNumberOne()
    {
        return strNumberOne;
    }

    void setStrNumberOne(String strNumberOne)
    {
        this.strNumberOne = strNumberOne;
    }

    String getStrNumberTwo()
    {
        return strNumberTwo;
    }

    void setStrNumberTwo(String strNumberTwo)
    {
        this.strNumberTwo = strNumberTwo;
    }

    String getStrResult()
    {
        return strResult;
    }

    void setStrResult(String strResult)
    {
        this.strResult = strResult;
    }

    @Override
    public String toString()
    {
        return "MockNumber{" + "strNumberOne='" + strNumberOne + '\'' +
                ", strNumberTwo='" + strNumberTwo + '\'' +
                ", strResult='" + strResult + '\'' +
                '}';
    }
}
