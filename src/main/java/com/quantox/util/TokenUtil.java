package com.quantox.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.quantox.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TokenUtil {

    private Logger log = LoggerFactory.getLogger(TokenUtil.class);

    public String getToken(User user) throws UnsupportedEncodingException {
        return JWT.create()
                .withIssuer("quantox")
                .withExpiresAt(Date.from(LocalDateTime.now().plusSeconds(5000L).atZone(ZoneId.systemDefault()).toInstant()))
                .withAudience("quantox")
                .withClaim("UID", user.getId())
                .sign(Algorithm.HMAC512("quantox"));
    }
}
