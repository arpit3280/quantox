package com.quantox.util;

import com.quantox.resource.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class HashingUtil {

    private Logger log = LoggerFactory.getLogger(HashingUtil.class);

    public String generateHash(String key,String saltString) throws Exception {
        try{
            int iterations = 1000;
            char [] chars  = key.toCharArray();
            byte [] salt  = saltString.getBytes(StandardCharsets.UTF_8);

            PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 512);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new Exception("Error while generating hash of password");
        }

    }
}
