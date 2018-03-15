package bbw.pulsa.dao;

import bbw.pulsa.model.User;
import bbw.pulsa.model.Voucher;

import java.util.List;

/**
 * Created by Lingga on 12/03/18.
 */

public interface PulsaDao
{
    User login(String username, String password);
    List<String> getOperator();
    List<Voucher> getVouchers(String operator);
    Integer getUserId(String username);
    Integer getOperatorId(String operator);
    boolean saveTransaction(Integer userId, Integer operatorId, Integer harga);
}