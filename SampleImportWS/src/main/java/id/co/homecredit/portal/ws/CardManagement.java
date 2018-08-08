//package id.co.homecredit.portal.ws;
//
//import ua.in.smartjava.*;
//
//import javax.xml.namespace.QName;
//import javax.xml.ws.BindingProvider;
//import java.net.*;
//
//public class CardManagement
//{
//    //private static final Logger logger = LogManager.getLogger();
//
//    private String endpoint;
//    private String username;
//    private String password;
//
//    private CardManagementWS cardManagementWS;
//
//    public CardManagement(String endpoint, String username, String password)
//    {
//        this.endpoint = endpoint;
//        this.username = username;
//        this.password = password;
//    }
//
//    public GetCardsResponse getCardsResponse(GetCardsRequest getCardsRequest)
//    {
//        cardManagementWS = getCardManagementWS();
//
//        return cardManagementWS.getCards(getCardsRequest);
//    }
//
//    public ActivateCardResponse activateCardResponse(ActivateCardRequest activateCardRequest)
//    {
//        cardManagementWS = getCardManagementWS();
//
//        return cardManagementWS.activateCard(activateCardRequest);
//    }
//
//    private CardManagementWS getCardManagementWS()
//    {
//        //logger.debug("getCardManagementWS, endpoint: {}, username: {}, password: {}", endpoint, username, password);
//
//        try
//        {
//            if (cardManagementWS != null)
//            {
//                return cardManagementWS;
//            }
//
//            /* this can be uncommented if there's "server redirected too many times*/
//            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
//            Authenticator.setDefault(new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password.toCharArray());
//                }
//            });
//
//            CardManagementWSSoap11QSService service = new CardManagementWSSoap11QSService(
//                    new URL(endpoint),
//                    new QName("http://homecredit.net/homerselect/ws/card/management/v2", "CardManagementWSSoap11QSService")
//            );
//            CardManagementWS cardManagementWS = service.getCardManagementWSSoap11QSPort();
//
//            BindingProvider bp = (BindingProvider) cardManagementWS;
//            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
//            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
//            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
//            bp.getRequestContext().put("javax.xml.ws.client.connectionTimeout", 3000);
//            bp.getRequestContext().put("javax.xml.ws.client.receiveTimeout", 3000);
//
//            this.cardManagementWS = cardManagementWS;
//
//            return cardManagementWS;
//        }
//        catch (Exception e)
//        {
//            //logger.error("Error when getCardManagementWS(): {} ", e);
//
//            return null;
//        }
//    }
//}