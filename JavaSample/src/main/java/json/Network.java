package json;

/**
 * Created by Lingga on 02/05/17.
 */

public class Network
{
    private String tadigCode;
    private String name;
    private Integer mccmnc;
    private Integer ccnc;
    private Country country;

    public String getTadigCode() {
        return tadigCode;
    }

    public void setTadigCode(String tadigCode) {
        this.tadigCode = tadigCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMccmnc() {
        return mccmnc;
    }

    public void setMccmnc(Integer mccmnc) {
        this.mccmnc = mccmnc;
    }

    public Integer getCcnc() {
        return ccnc;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Network{");
        sb.append("tadigCode='").append(tadigCode).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", mccmnc=").append(mccmnc);
        sb.append(", ccnc=").append(ccnc);
        sb.append(", country=").append(country);
        sb.append('}');
        return sb.toString();
    }

    public void setCcnc(Integer ccnc) {
        this.ccnc = ccnc;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


}