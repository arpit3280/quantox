package com.quantox.resource;

import com.quantox.dto.LoginDto;
import com.quantox.dto.UserDto;
import com.quantox.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class LoginResource {

    private Logger log = LoggerFactory.getLogger(LoginResource.class);

    @Autowired
    private LoginService loginService;

    @PostMapping("/signIn")
    public ResponseEntity login (@RequestBody LoginDto loginDto ) {
        log.info("SIGNIN BY USER: " + loginDto.getUserName());
        try{
            return new ResponseEntity(loginService.signIn(loginDto.getUserName(),loginDto.getPassword()),HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
