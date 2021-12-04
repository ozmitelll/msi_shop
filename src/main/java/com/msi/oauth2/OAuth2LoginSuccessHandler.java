package com.msi.oauth2;

import com.msi.models.AuthentificationProvider;
import com.msi.models.User;
import com.msi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException{

        DefaultOidcUser oAuth2User = (DefaultOidcUser) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String name = oAuth2User.getFullName();
        User user = userService.loadUserByEmail(email);

        if(user == null){
           userService.createNewUserAfterOAuthLogSuccess(email,name, AuthentificationProvider.GOOGLE);
        } else{
            userService.updateUserAfterOAuthLogSuccess(user,name,AuthentificationProvider.GOOGLE);
        }



        System.out.println(email);


        super.onAuthenticationSuccess(request,response,authentication);
    }


}
