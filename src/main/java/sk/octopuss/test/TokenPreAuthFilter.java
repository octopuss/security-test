package sk.octopuss.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ivan.dolezal.ext on 18.8.2015.
 */
public class TokenPreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Autowired
    TokenGenerator tokenGenerator;


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
           String token = httpServletRequest.getParameter("token");
            if(token!=null) {
                return true;
            }
       return false;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
       return "ivan";
    }
}
