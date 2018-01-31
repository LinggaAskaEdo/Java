package mls.lookupservice.model;

import java.util.Date;

/**
 * Created by Lingga on 23/03/17.
 */

public class CellDB
{
    private long id;
    private String radio;
    private int mcc;
    private int net;
    private int area;
    private int cell;
    private String unit;
    private double lon;
    private double lat;
    private int ranges;
    private String samples;
    private boolean changeable;
    private Date created;
    private Date updated;
    private String average_signal;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getRadio()
    {
        return radio;
    }

    public void setRadio(String radio)
    {
        this.radio = radio;
    }

    public int getMcc()
    {
        return mcc;
    }

    public void setMcc(int mcc)
    {
        this.mcc = mcc;
    }

    public int getNet()
    {
        return net;
    }

    public void setNet(int net)
    {
        this.net = net;
    }

    public int getArea()
    {
        return area;
    }

    public void setArea(int area)
    {
        this.area = area;
    }

    public int getCell()
    {
        return cell;
    }

    public void setCell(int cell)
    {
        this.cell = cell;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public int getRanges()
    {
        return ranges;
    }

    public void setRanges(int ranges)
    {
        this.ranges = ranges;
    }

    public String getSamples()
    {
        return samples;
    }

    public void setSamples(String samples)
    {
        this.samples = samples;
    }

    public boolean isChangeable()
    {
        return changeable;
    }

    public void setChangeable(boolean changeable)
    {
        this.changeable = changeable;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    public String getAverage_signal()
    {
        return average_signal;
    }

    public void setAverage_signal(String average_signal)
    {
        this.average_signal = average_signal;
    }

    @Override
    public String toString()
    {
        return "CellDB{" + "id=" + id +
                ", radio='" + radio + '\'' +
                ", mcc=" + mcc +
                ", net=" + net +
                ", area=" + area +
                ", cell=" + cell +
                ", unit='" + unit + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", ranges=" + ranges +
                ", samples='" + samples + '\'' +
                ", changeable=" + changeable +
                ", created=" + created +
                ", updated=" + updated +
                ", average_signal='" + average_signal + '\'' +
                '}';
    }
}