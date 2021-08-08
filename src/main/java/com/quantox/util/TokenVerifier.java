package com.quantox.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Component
public class TokenVerifier {

    private Logger log = LoggerFactory.getLogger(TokenVerifier.class);

    public long verify(String token) throws Exception {
        log.info("veryfying token: " + token);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("quantox")).withIssuer("quantox").build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("Unable to verify "+e.getLocalizedMessage());
            throw new Exception("Unable to verify token: " + token);
        } catch (Exception e){
            log.error("Unable to verify token: " + token);
            throw new Exception("Unable to verify token: " + token);
        }

       long uid = jwt.getClaim("UID").asLong();
        return uid;
    }
}
