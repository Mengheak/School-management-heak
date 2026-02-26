package com.example.school_mgnt_sb.services.auth;

import com.example.school_mgnt_sb.entities.auth.UserAccount;
import com.example.school_mgnt_sb.exception.StatusException;
import com.example.school_mgnt_sb.models.AuthUser;
import com.example.school_mgnt_sb.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByEmail(email).orElseThrow(() -> new StatusException(HttpStatus.NOT_FOUND, "Can't find user account with username = "+ email));
        return new AuthUser(user);
    }


}
