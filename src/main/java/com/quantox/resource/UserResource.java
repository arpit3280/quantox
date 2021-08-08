package com.quantox.resource;

import com.quantox.dto.AddUserDto;
import com.quantox.service.UserService;
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
@RequestMapping("/user")
public class UserResource {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody AddUserDto addUserDto) {
        log.info("ADD USER API WITH PARAM : " + addUserDto);
        try{
            return new ResponseEntity(userService.addUser(addUserDto),HttpStatus.OK);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return new ResponseEntity("", HttpStatus.BAD_REQUEST);
        }
    }

}
