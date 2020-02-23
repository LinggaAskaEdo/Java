package org.otis.reactive.models;

import java.time.LocalDateTime;

public class ResponseModel
{
    private String flightName;
    private LocalDateTime timeToBoard;
    private LocalDateTime timeToBeAtSchiphol;
    private LocalDateTime timeToLeave;

    public String getFlightName()
    {
        return flightName;
    }

    public void setFlightName(String flightName)
    {
        this.flightName = flightName;
    }

    public LocalDateTime getTimeToBoard()
    {
        return timeToBoard;
    }

    public void setTimeToBoard(LocalDateTime timeToBoard)
    {
        this.timeToBoard = timeToBoard;
    }

    public LocalDateTime getTimeToBeAtSchiphol()
    {
        return timeToBeAtSchiphol;
    }

    public void setTimeToBeAtSchiphol(LocalDateTime timeToBeAtSchiphol)
    {
        this.timeToBeAtSchiphol = timeToBeAtSchiphol;
    }

    public LocalDateTime getTimeToLeave()
    {
        return timeToLeave;
    }

    public void setTimeToLeave(LocalDateTime timeToLeave)
    {
        this.timeToLeave = timeToLeave;
    }
}