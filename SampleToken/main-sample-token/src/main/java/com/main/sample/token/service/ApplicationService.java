package com.main.sample.token.service;

import com.common.sample.token.security.Token;
import com.main.sample.token.model.AuthenticatedToken;
import com.main.sample.token.model.LoginRequest;
import com.main.sample.token.model.Response;
import com.main.sample.token.preference.ConstantPreference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApplicationService
{
    private static final Logger logger = LogManager.getLogger();

    public Response issuedToken(LoginRequest request)
    {
        Response response = new Response();

        try
        {
            String subject = request.getUserId().trim();

            long expired;
            long current = System.currentTimeMillis();

            if ("GUEST".equals(request.getLoginType()))
            {
                expired = ConstantPreference.TIMEOUT * 10000;
            }
            else
            {
                expired = ConstantPreference.TIMEOUT;
            }

            AuthenticatedToken authenticatedToken = new AuthenticatedToken();
            authenticatedToken.setAccessToken(generateToken(subject, expired, request));
            authenticatedToken.setRefreshToken(generateToken(subject, (expired * 10000), request), request); //refresh token will generated 10 times longer than access token
            authenticatedToken.setExpired(expired);
            authenticatedToken.setUsername(subject);
            authenticatedToken.setTokenCreated(new Date(current));
            authenticatedToken.setTokenExpired(getExpiredDate(expired, current));

            response.setStatus("200");
            response.setMessage("Request was successful");
            response.setToken(authenticatedToken);
        }
        catch (Exception e)
        {
            logger.error("Error when issuedToken: {}", e);

            response.setStatus("204");
            response.setMessage("We accepted your request but there is nothing to return (e.g. response is empty)");
        }

        return response;
    }

    private String generateToken(String subject, long expired, LoginRequest request)
    {
        String encryptedJwt = null;

        try
        {
            String jwkJson = "{\"kty\":\"oct\",\"k\":\"" + Token.MOBILE_TOKEN + "\"}";
            JsonWebKey jwKey = JsonWebKey.Factory.newJwk(jwkJson);

            //user should be defined in database because of user login validation has passed
//            User authenticatedUser = userService.getUserByUserId(subject);

            JwtClaims claims = new JwtClaims();
            claims.setIssuer(Token.MOBILE_ISSUER);
            claims.setExpirationTimeMinutesInTheFuture(expired);
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            claims.setNotBeforeMinutesInThePast(2);
            claims.setSubject(subject);
            claims.setClaim(Token.SECURITY_OBJECT, request);

            JsonWebSignature jws = new JsonWebSignature();
            jws.setPayload(claims.toJson());
            jws.setKeyIdHeaderValue(jwKey.getKeyId());
            jws.setKey(jwKey.getKey());
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);

            String plainToken = jws.getCompactSerialization();
            logger.info("jwt token before encryption and still plain metadata {}", plainToken);

            JsonWebEncryption jwe = new JsonWebEncryption();
            jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.DIRECT);
            jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
            jwe.setKey(jwKey.getKey());
            jwe.setKeyIdHeaderValue(jwKey.getKeyId());
            jwe.setContentTypeHeaderValue("JWT");
            jwe.setPayload(plainToken);

            encryptedJwt = jwe.getCompactSerialization();
            logger.info("jwt token after encryption and metadata has been protected {}", encryptedJwt);
        }
        catch (Exception e)
        {
            logger.error("Error when generateToken: {}", e);
        }

        return encryptedJwt;
    }

    private Date getExpiredDate(long timeout, long timestamp)
    {
        //set expiration and generate JWT Token
        long expired = timestamp + (timeout * 60 * 1000); //convert into millisecond

        return new Date(expired);
    }
}