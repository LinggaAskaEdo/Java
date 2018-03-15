package calculator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lingga on 16/02/17.
 */

public class MainCalculatorTest
{
    private MainCalculator calculator = new MainCalculator();
    private List<MockNumber> mockNumbers = new ArrayList<>();

    @BeforeClass
    public void setUp()
    {
        //MockitoAnnotations.initMocks(this);
        mockNumbers = generateMock();
    }

    private List<MockNumber> generateMock()
    {
        MockNumber number1 = new MockNumber();
        number1.setStrNumberOne("aska");
        number1.setStrNumberTwo("cool");
        number1.setStrResult("");

        MockNumber number2 = new MockNumber();
        number2.setStrNumberOne("1");
        number2.setStrNumberTwo("1.75");
        number2.setStrResult("2.75");

        MockNumber number3 = new MockNumber();
        number3.setStrNumberOne("1");
        number3.setStrNumberTwo("a");
        number3.setStrResult("");

        MockNumber number4 = new MockNumber();
        number4.setStrNumberOne("1");
        number4.setStrNumberTwo("1.23");
        number4.setStrResult("2.23");

        MockNumber number5 = new MockNumber();
        number5.setStrNumberOne("1*2");
        number5.setStrNumberTwo("2/2");
        number5.setStrResult("");

        MockNumber number6 = new MockNumber();
        number6.setStrNumberOne("1");
        number6.setStrNumberTwo("2");
        number6.setStrResult("3");

        mockNumbers.add(number1);
        mockNumbers.add(number2);
        mockNumbers.add(number3);
        mockNumbers.add(number4);
        mockNumbers.add(number5);
        mockNumbers.add(number6);

        return mockNumbers;
    }

    @Test
    public void testAdd() throws Exception
    {
        Assert.assertEquals(calculator.add(1,2), 3);
    }

    //Input with many type (int, long, double) with string format
    @Test
    public void testAddString() throws Exception
    {
        for (MockNumber mn : mockNumbers)
        {
            Assert.assertEquals(calculator.add(mn.getStrNumberOne(),mn.getStrNumberTwo()), mn.getStrResult());
        }

        //Assert.assertTrue(calculator.add("3", "2").equals("7"), "Sorry !!!");

        /*Assert.assertEquals(calculator.add("aska","cool"), "");
        Assert.assertEquals(calculator.add("1"," 1.75   "), "2.75");
        Assert.assertEquals(calculator.add("1","a"), "");
        Assert.assertEquals(calculator.add("1","1.23"), "2.23");
        Assert.assertEquals(calculator.add("1*2","2/2"), "");
        Assert.assertEquals(calculator.add(" 1 "," 2 "), "3");*/
    }

    @Test
    public void testSubtract() throws Exception
    {
        Assert.assertEquals(calculator.subtract(5 ,3), 2);
    }

    @Test
    public void testMultiply() throws Exception
    {
        Assert.assertEquals(calculator.multiply(4,5), 20);
    }

    @Test
    public void testDivide() throws Exception
    {
        Assert.assertEquals(calculator.divide(20,5), 4);
    }
}