package com.msi.services;

import com.msi.models.AuthentificationProvider;
import com.msi.models.Product;
import com.msi.models.User;
import com.msi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findUsersByUsername(s);
    }

    public User loadUserByEmail(String s){
        return userRepo.findUsersByEmail(s);
    }
    public void addProduct(Product product){

    }
    public void createNewUserAfterOAuthLogSuccess(String email,
                  String name, AuthentificationProvider provider){
        User user = new User(name,null,email);
        user.setActive(true);
        user.setAuthentificationProvider(provider);
        userRepo.save(user);
    }

    public void updateUserAfterOAuthLogSuccess(User user, String name, AuthentificationProvider AuthProvider) {
        user.setUsername(name);
        user.setAuthentificationProvider(AuthProvider);

        userRepo.save(user);
    }


    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if(user==null){
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }
}
