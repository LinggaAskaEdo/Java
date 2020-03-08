package org.otis.reactive.models;

import java.util.List;

public class Transactions
{
    private List<Flight> flights;

    public List<Flight> getFlights()
    {
        return flights;
    }

    public void setFlights(List<Flight> flights)
    {
        this.flights = flights;
    }
}
