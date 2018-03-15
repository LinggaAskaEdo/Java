package bbw.pulsa.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lingga on 12/03/18.
 */

public class Voucher
{
    private Integer voucherId;
    private Integer operatorId;

    @SerializedName("pulsa")
    private String voucherPulsa;

    @SerializedName("harga")
    private Integer voucherHarga;

    public Integer getVoucherId()
    {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId)
    {
        this.voucherId = voucherId;
    }

    public Integer getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getVoucherPulsa()
    {
        return voucherPulsa;
    }

    public void setVoucherPulsa(String voucherPulsa)
    {
        this.voucherPulsa = voucherPulsa;
    }

    public Integer getVoucherHarga()
    {
        return voucherHarga;
    }

    public void setVoucherHarga(Integer voucherHarga)
    {
        this.voucherHarga = voucherHarga;
    }

    @Override
    public String toString()
    {
        return "Voucher{" +
                "voucherId=" + voucherId +
                ", operatorId=" + operatorId +
                ", voucherPulsa=" + voucherPulsa +
                ", voucherHarga=" + voucherHarga +
                '}';
    }
}