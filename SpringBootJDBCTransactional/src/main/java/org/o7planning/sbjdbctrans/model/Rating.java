package org.o7planning.sbjdbctrans.model;

import com.google.gson.annotations.SerializedName;

public class Rating
{
    @SerializedName(value = "source", alternate = "Source")
    private String source;

    @SerializedName(value = "value", alternate = "Value")
    private String value;

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Rating{" +
                "source='" + source + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}