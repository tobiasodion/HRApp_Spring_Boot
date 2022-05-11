package com.example.domain.user.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder encoder ;

    @Autowired
    private UserMapper mapper ;

    /** User signup */
    @Override
    public void signup(MUser user ) {
        user .setDepartmentId(1);
        user .setRole("ROLE_GENERAL" );

        // Password encryption
        String rawPassword = user .getPassword();
        user .setPassword(encoder .encode(rawPassword ));

        mapper .insertOne(user );
    }

    /** Get user */
    @Override
    public List<MUser> getUsers(MUser user ) {
        return mapper.findMany(user);
    }

    /** Get user(1record) */
    @Override
    public MUser getUserOne(String userId ) {
        return mapper.findOne(userId );
    }

    /** Update user */
    @Transactional
    @Override
    public void updateUserOne(String userId ,
                              String password ,
                              String userName ) {
        // Password encryption
        String encryptPassword = encoder .encode(password );
        mapper .updateOne(userId ,encryptPassword , userName );
        mapper .updateOne(userId , password , userName );

        // Raise an exception
        //int i = 1 / 0;
    }
    /** Delete user */
    @Override
    public void deleteUserOne(String userId ) {
        int count = mapper .deleteOne(userId );
    }

    /** Get login user information */
    @Override
    public MUser getLoginUser(String userId ) {
        return mapper .findLoginUser(userId );
    }
}