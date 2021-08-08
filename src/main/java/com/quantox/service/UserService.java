package com.quantox.service;

import com.quantox.data.User;
import com.quantox.dto.AddUserDto;
import com.quantox.dto.UserDto;
import com.quantox.repository.UserRepository;
import com.quantox.resource.UserResource;
import com.quantox.util.HashingUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private HashingUtil hashingUtil;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();


    public UserDto addUser(AddUserDto addUserDto) throws Exception {
        log.info("adding user: "+ addUserDto.getUserName());
        User user = new User(0L,addUserDto.getUserName(),hashingUtil.generateHash(addUserDto.getPassword(),addUserDto.getUserName()),addUserDto.getFirstName(),addUserDto.getLastName());
        user = userRepository.save(user);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto createUser = modelMapper.map(user, UserDto.class);
        return createUser;
    }

    public User getUserWithNameAndPassword(String userName, String passwordHash) {
        return userRepository.getByNameAndPass(userName,passwordHash);
    }
}
