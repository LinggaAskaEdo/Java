package string;

/**
 * Created by Lingga on 06/07/17.
 */

public class EnumSample
{
    public enum Company
    {
        EBAY, PAYPAL, GOOGLE, YAHOO, ATT
    }

    private Company company;

    private EnumSample(Company company)
    {
        this.company = company;
    }

    private void companyDetails()
    {
        switch (company)
        {
            case EBAY:
                System.out.println("Biggest Market Place in the World");
                break;
            case PAYPAL:
                System.out.println("Simplest way to manage money");
                break;
            case GOOGLE:
            case YAHOO:
                System.out.println("1st Web 2.0 Company.");
                break;
            default:
                System.out.println("Google - biggest search giant.. ATT - my carrier provider..");
                break;
        }
    }

    public static void main(String[] args)
    {
        EnumSample ebay = new EnumSample(Company.EBAY);
        ebay.companyDetails();

        EnumSample paypal = new EnumSample(Company.PAYPAL);
        paypal.companyDetails();

        EnumSample google = new EnumSample(Company.GOOGLE);
        google.companyDetails();

        EnumSample yahoo = new EnumSample(Company.YAHOO);
        yahoo.companyDetails();

        EnumSample att = new EnumSample(Company.ATT);
        att.companyDetails();
    }
}