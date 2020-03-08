package com.springboot.vertx.models;

import com.springboot.vertx.utils.TodoConverter;
import io.vertx.core.json.JsonObject;

public class Flight
{
    private String lastUpdatedAt;
    private String actualLandingTime;
    private String actualOffBlockTime;
    private String aircraftRegistration;
    private AircraftType aircraftType;
    private BaggageClaim baggageClaim;
//    private CheckinAllocations checkinAllocations;
    private CodeShares codeshares;
    private String estimatedLandingTime;
    private String expectedTimeBoarding;
    private String expectedTimeGateClosing;
    private String expectedTimeGateOpen;
    private String expectedTimeOnBelt;
    private String expectedSecurityFilter;
    private String flightDirection;
    private String flightName;
    private int flightNumber;
    private String gate;
    private String pier;
    private String id;
    private String mainFlight;
    private String prefixIATA;
    private String prefixICAO;
    private int airlineCode;
    private String publicEstimatedOffBlockTime;
    private PublicFlightState publicFlightState;
    private Route route;
    private String scheduleDateTime;
    private String scheduleDate;
    private String scheduleTime;
    private String serviceType;
    private int terminal;
    private TransferPositions transferPositions;
    private String schemaVersion;

    public Flight()
    {}

    public Flight(String jsonStr)
    {
        TodoConverter.fromJson(new JsonObject(jsonStr), this);
    }

    public Flight(String lastUpdatedAt, String actualLandingTime, String flightName)
    {
        this.lastUpdatedAt = lastUpdatedAt;
        this.actualLandingTime = actualLandingTime;
        this.flightName = flightName;
    }

    public String getLastUpdatedAt()
    {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt)
    {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getActualLandingTime()
    {
        return actualLandingTime;
    }

    public void setActualLandingTime(String actualLandingTime)
    {
        this.actualLandingTime = actualLandingTime;
    }

    public String getActualOffBlockTime()
    {
        return actualOffBlockTime;
    }

    public void setActualOffBlockTime(String actualOffBlockTime)
    {
        this.actualOffBlockTime = actualOffBlockTime;
    }

    public String getAircraftRegistration()
    {
        return aircraftRegistration;
    }

    public void setAircraftRegistration(String aircraftRegistration)
    {
        this.aircraftRegistration = aircraftRegistration;
    }

    public AircraftType getAircraftType()
    {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType)
    {
        this.aircraftType = aircraftType;
    }

    public BaggageClaim getBaggageClaim()
    {
        return baggageClaim;
    }

    public void setBaggageClaim(BaggageClaim baggageClaim)
    {
        this.baggageClaim = baggageClaim;
    }

//    public CheckinAllocations getCheckinAllocations()
//    {
//        return checkinAllocations;
//    }

//    public void setCheckinAllocations(CheckinAllocations checkinAllocations)
//    {
//        this.checkinAllocations = checkinAllocations;
//    }

    public CodeShares getCodeshares()
    {
        return codeshares;
    }

    public void setCodeshares(CodeShares codeshares)
    {
        this.codeshares = codeshares;
    }

    public String getEstimatedLandingTime()
    {
        return estimatedLandingTime;
    }

    public void setEstimatedLandingTime(String estimatedLandingTime)
    {
        this.estimatedLandingTime = estimatedLandingTime;
    }

    public String getExpectedTimeBoarding()
    {
        return expectedTimeBoarding;
    }

    public void setExpectedTimeBoarding(String expectedTimeBoarding)
    {
        this.expectedTimeBoarding = expectedTimeBoarding;
    }

    public String getExpectedTimeGateClosing()
    {
        return expectedTimeGateClosing;
    }

    public void setExpectedTimeGateClosing(String expectedTimeGateClosing)
    {
        this.expectedTimeGateClosing = expectedTimeGateClosing;
    }

    public String getExpectedTimeGateOpen()
    {
        return expectedTimeGateOpen;
    }

    public void setExpectedTimeGateOpen(String expectedTimeGateOpen)
    {
        this.expectedTimeGateOpen = expectedTimeGateOpen;
    }

    public String getExpectedTimeOnBelt()
    {
        return expectedTimeOnBelt;
    }

    public void setExpectedTimeOnBelt(String expectedTimeOnBelt)
    {
        this.expectedTimeOnBelt = expectedTimeOnBelt;
    }

    public String getExpectedSecurityFilter()
    {
        return expectedSecurityFilter;
    }

    public void setExpectedSecurityFilter(String expectedSecurityFilter)
    {
        this.expectedSecurityFilter = expectedSecurityFilter;
    }

    public String getFlightDirection()
    {
        return flightDirection;
    }

    public void setFlightDirection(String flightDirection)
    {
        this.flightDirection = flightDirection;
    }

    public String getFlightName()
    {
        return flightName;
    }

    public void setFlightName(String flightName)
    {
        this.flightName = flightName;
    }

    public int getFlightNumber()
    {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    public String getGate()
    {
        return gate;
    }

    public void setGate(String gate)
    {
        this.gate = gate;
    }

    public String getPier()
    {
        return pier;
    }

    public void setPier(String pier)
    {
        this.pier = pier;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getMainFlight()
    {
        return mainFlight;
    }

    public void setMainFlight(String mainFlight)
    {
        this.mainFlight = mainFlight;
    }

    public String getPrefixIATA()
    {
        return prefixIATA;
    }

    public void setPrefixIATA(String prefixIATA)
    {
        this.prefixIATA = prefixIATA;
    }

    public String getPrefixICAO()
    {
        return prefixICAO;
    }

    public void setPrefixICAO(String prefixICAO)
    {
        this.prefixICAO = prefixICAO;
    }

    public int getAirlineCode()
    {
        return airlineCode;
    }

    public void setAirlineCode(int airlineCode)
    {
        this.airlineCode = airlineCode;
    }

    public String getPublicEstimatedOffBlockTime()
    {
        return publicEstimatedOffBlockTime;
    }

    public void setPublicEstimatedOffBlockTime(String publicEstimatedOffBlockTime)
    {
        this.publicEstimatedOffBlockTime = publicEstimatedOffBlockTime;
    }

    public PublicFlightState getPublicFlightState()
    {
        return publicFlightState;
    }

    public void setPublicFlightState(PublicFlightState publicFlightState)
    {
        this.publicFlightState = publicFlightState;
    }

    public Route getRoute()
    {
        return route;
    }

    public void setRoute(Route route)
    {
        this.route = route;
    }

    public String getScheduleDateTime()
    {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(String scheduleDateTime)
    {
        this.scheduleDateTime = scheduleDateTime;
    }

    public String getScheduleDate()
    {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate)
    {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime()
    {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime)
    {
        this.scheduleTime = scheduleTime;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }

    public int getTerminal()
    {
        return terminal;
    }

    public void setTerminal(int terminal)
    {
        this.terminal = terminal;
    }

    public TransferPositions getTransferPositions()
    {
        return transferPositions;
    }

    public void setTransferPositions(TransferPositions transferPositions)
    {
        this.transferPositions = transferPositions;
    }

    public String getSchemaVersion()
    {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion)
    {
        this.schemaVersion = schemaVersion;
    }

    @Override
    public String toString()
    {
        return "Flight{" +
                "lastUpdatedAt='" + lastUpdatedAt + '\'' +
                ", actualLandingTime='" + actualLandingTime + '\'' +
                ", actualOffBlockTime='" + actualOffBlockTime + '\'' +
                ", aircraftRegistration='" + aircraftRegistration + '\'' +
                ", aircraftType=" + aircraftType +
                ", baggageClaim=" + baggageClaim +
                ", codeshares=" + codeshares +
                ", estimatedLandingTime='" + estimatedLandingTime + '\'' +
                ", expectedTimeBoarding='" + expectedTimeBoarding + '\'' +
                ", expectedTimeGateClosing='" + expectedTimeGateClosing + '\'' +
                ", expectedTimeGateOpen='" + expectedTimeGateOpen + '\'' +
                ", expectedTimeOnBelt='" + expectedTimeOnBelt + '\'' +
                ", expectedSecurityFilter='" + expectedSecurityFilter + '\'' +
                ", flightDirection='" + flightDirection + '\'' +
                ", flightName='" + flightName + '\'' +
                ", flightNumber=" + flightNumber +
                ", gate='" + gate + '\'' +
                ", pier='" + pier + '\'' +
                ", id='" + id + '\'' +
                ", mainFlight='" + mainFlight + '\'' +
                ", prefixIATA='" + prefixIATA + '\'' +
                ", prefixICAO='" + prefixICAO + '\'' +
                ", airlineCode=" + airlineCode +
                ", publicEstimatedOffBlockTime='" + publicEstimatedOffBlockTime + '\'' +
                ", publicFlightState=" + publicFlightState +
                ", route=" + route +
                ", scheduleDateTime='" + scheduleDateTime + '\'' +
                ", scheduleDate='" + scheduleDate + '\'' +
                ", scheduleTime='" + scheduleTime + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", terminal=" + terminal +
                ", transferPositions=" + transferPositions +
                ", schemaVersion='" + schemaVersion + '\'' +
                '}';
    }
}