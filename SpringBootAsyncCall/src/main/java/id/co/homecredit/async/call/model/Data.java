package id.co.homecredit.async.call.model;

import java.util.List;

public class Data
{
    private List<Contract> contracts;

    public List<Contract> getContracts()
    {
        return contracts;
    }

    public void setContracts(List<Contract> contracts)
    {
        this.contracts = contracts;
    }

    @Override
    public String toString()
    {
        return "Data{" +
                "contracts=" + contracts +
                '}';
    }
}