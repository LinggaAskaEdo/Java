package org.otis.reactive.models;

import java.util.List;

public class Flights
{
    private List<Flight> flights;

    public Flights()
    {}

    public Flights(List<Flight> flights)
    {
        this.flights = flights;
    }

    public List<Flight> getFlights()
    {
        return flights;
    }

    public void setFlights(List<Flight> flights)
    {
        this.flights = flights;
    }
}
