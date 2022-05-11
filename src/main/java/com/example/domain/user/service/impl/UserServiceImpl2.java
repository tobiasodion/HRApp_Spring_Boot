package com.example.domain.user.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@Primary
public class UserServiceImpl2 implements UserService {
    @Autowired
    private UserRepository repository ;
    @Autowired
    private PasswordEncoder encoder ;
    /** User signup */
    @Transactional
    @Override
    public void signup(MUser user ) {
// Existence check
        boolean exists = repository .existsById(user .getUserId());
        if (exists ) {
            throw new DataAccessException("User already exists" ){};
        }
        user .setDepartmentId(1);
        user .setRole("ROLE_GENERAL" );
// Password encryption
        String rawPassword = user .getPassword();
        user .setPassword(encoder .encode(rawPassword ));
// insert
        repository .save(user );
    }
    /** Get user */
    @Override
    public List<MUser> getUsers(MUser user ) {
        // Search conditions
        ExampleMatcher matcher = ExampleMatcher
                .matching() // and condition
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING ) // Like clause
                .withIgnoreCase(); // Both uppercase and lowercase
        return repository.findAll(Example.of(user , matcher ));
    }
    /** Get user(1record) */
    @Override
    public MUser getUserOne(String userId ) {
        Optional<MUser> option = repository .findById(userId );
        MUser user = option .orElse(null );
        return user ;
    }
    /** Update user */
    @Transactional
    @Override
    public void updateUserOne(String userId , String password , String userName ) {
// Password encryption
        String encryptPassword = encoder .encode(password );
// User update
        repository .updateUser(userId , encryptPassword , userName );
    }
    /** Delete user */
    @Transactional
    @Override
    public void deleteUserOne(String userId ) {
        repository .deleteById(userId );
    }
    /** Get login user */
    @Override
    public MUser getLoginUser(String userId ) {
        return repository .findLoginUser(userId );
    }
}