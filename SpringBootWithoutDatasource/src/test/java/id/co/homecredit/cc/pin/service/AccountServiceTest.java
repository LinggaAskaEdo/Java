package id.co.homecredit.cc.pin.service;

import net.homecredit.party._2016_1.CustomerPerson;
import net.homecredit.party_ws._2016_1.CustomerPersonSearchResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest
{
    @Test
    public void testGetCuid_Null()
    {
        System.out.println("Test GetCuid - Null");

        String userId = "81244444444";

        final WSService wsService = mock(WSService.class);
        when(wsService.getCuid(userId)).thenReturn(null);

        final AccountService accountService = new AccountService(wsService);
        assertThat(accountService.getCuid(userId)).isEqualTo(null);
    }

    @Test
    public void testGetCuid_NotNull()
    {
        System.out.println("Test GetCuid - Not Null");

        String userId = "81244444444";
        Long externalId = 1234L;
        String externalIdStr = "1234";

        /*mock CustomerPersonSearch*/
        CustomerPersonSearchResponse customerPersonSearchResponse = new CustomerPersonSearchResponse();

        CustomerPerson customerPerson = new CustomerPerson();
        customerPerson.setExternalId(externalId);

        customerPersonSearchResponse.getItems().add(customerPerson);

        final WSService wsService = mock(WSService.class);
        when(wsService.getCuid(userId)).thenReturn(customerPersonSearchResponse);

        final AccountService accountService = new AccountService(wsService);
        assertThat(accountService.getCuid(userId)).isEqualTo(externalIdStr);
    }
}