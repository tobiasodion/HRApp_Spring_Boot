package com.example.domain.user.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.user.model.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import com.example.domain.login.model.MUser;
import com.example.domain.user.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService service ;
    @Override
    public UserDetails loadUserByUsername(String username )
            throws UsernameNotFoundException {
// Get user information
        MUser loginUser = service .getLoginUser(username );
// If the user does not exist
        if (loginUser == null ) {
            throw new UsernameNotFoundException("user not found" );
        }
// Create authority list
GrantedAuthority authority = new SimpleGrantedAuthority(loginUser .getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities .add(authority );
// Generate UserDetails
        UserDetails userDetails = (UserDetails) new User(loginUser .getUserId(),
                loginUser .getPassword(),
                authorities );
        return userDetails ;
    }
}