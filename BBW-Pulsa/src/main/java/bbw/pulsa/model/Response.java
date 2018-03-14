package bbw.pulsa.model;

import java.util.List;

/**
 * Created by Lingga on 12/03/18.
 */

public class Response
{
    private String status;
    private String msg;
    private Integer userId;
    private List<String> operators;
    private List<Voucher> vouchers;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public List<String> getOperators()
    {
        return operators;
    }

    public void setOperators(List<String> operators)
    {
        this.operators = operators;
    }

    public List<Voucher> getVouchers()
    {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers)
    {
        this.vouchers = vouchers;
    }

    @Override
    public String toString()
    {
        return "Response{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", userId=" + userId +
                ", operators=" + operators +
                ", vouchers=" + vouchers +
                '}';
    }
}