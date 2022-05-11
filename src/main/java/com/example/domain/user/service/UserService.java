package com.example.domain.user.service;
import com.example.domain.user.model.MUser;

import java.util.List;

public interface UserService {
    /** User signup */
    public void signup(MUser user );

    /** Get user */
    public List<MUser> getUsers(MUser user );

    /** Get user(1record) */
    public MUser getUserOne(String userId );

    /** Update user */
    public void updateUserOne(String userId ,
                              String password ,
                              String userName );
    /** Delete user */
    public void deleteUserOne(String userId );

    /** Get login user information */
    public MUser getLoginUser(String userId );
}