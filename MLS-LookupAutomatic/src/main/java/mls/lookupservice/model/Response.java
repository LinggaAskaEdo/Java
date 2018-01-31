package mls.lookupservice.model;

import java.util.Date;

/**
 * Created by Lingga on 23/03/17.
 */

public class Response
{
    private long id;
    private String radio;
    private int mcc;
    private int mnc;
    private int lac;
    private int cell_id;
    private String cell_ref;
    private String unit;
    private double longitude;
    private double latitude;
    private int ranges;
    private String samples;
    private boolean changeable;
    private Date created;
    private Date updated;
    private String average_signal;

    public void setId(long id)
    {
        this.id = id;
    }

    public void setRadio(String radio)
    {
        this.radio = radio;
    }

    public void setMcc(int mcc)
    {
        this.mcc = mcc;
    }

    public void setMnc(int mnc)
    {
        this.mnc = mnc;
    }

    public void setLac(int lac)
    {
        this.lac = lac;
    }

    public void setCell_id(int cell_id)
    {
        this.cell_id = cell_id;
    }

    public void setCell_ref(String cell_ref)
    {
        this.cell_ref = cell_ref;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public void setRanges(int ranges)
    {
        this.ranges = ranges;
    }

    public void setSamples(String samples)
    {
        this.samples = samples;
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

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    public void setAverage_signal(String average_signal)
    {
        this.average_signal = average_signal;
    }

    @Override
    public String toString()
    {
        return "Response{" + "id=" + id +
                ", radio='" + radio + '\'' +
                ", mcc=" + mcc +
                ", mnc=" + mnc +
                ", lac=" + lac +
                ", cell_id=" + cell_id +
                ", cell_ref=" + cell_ref +
                ", unit='" + unit + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", ranges=" + ranges +
                ", samples='" + samples + '\'' +
                ", changeable=" + changeable +
                ", created=" + created +
                ", updated=" + updated +
                ", average_signal='" + average_signal + '\'' +
                '}';
    }
}