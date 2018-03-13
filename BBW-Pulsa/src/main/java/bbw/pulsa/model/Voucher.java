package bbw.pulsa.model;

/**
 * Created by Lingga on 12/03/18.
 */

public class Voucher
{
    private int voucherId;
    private int operatorId;
    private int pulsa;
    private int harga;

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getPulsa() {
        return pulsa;
    }

    public void setPulsa(int pulsa) {
        this.pulsa = pulsa;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}