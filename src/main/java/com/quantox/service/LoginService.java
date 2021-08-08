package com.quantox.service;

import com.quantox.data.User;
import com.quantox.dto.UserDto;
import com.quantox.resource.UserResource;
import com.quantox.util.HashingUtil;
import com.quantox.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger log = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private HashingUtil hashingUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    public String signIn(String userName, String password) throws Exception {
        String passwordHash = hashingUtil.generateHash(password,userName);
        log.info("paaswrod hash "+ passwordHash);
        User user = userService.getUserWithNameAndPassword(userName,passwordHash);
        if(user == null)
            throw new Exception("Invalid User and Password");
        return tokenUtil.getToken(user);
    }
}
