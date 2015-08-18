package sk.octopuss.test;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivan.dolezal.ext on 18.8.2015.
 */

public class UserDetailServiceImpl implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) throws UsernameNotFoundException {


        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if ((Boolean)preAuthenticatedAuthenticationToken.getPrincipal()) {
            return new User((String) preAuthenticatedAuthenticationToken.getCredentials(), "notused", true, true, true, true,
                    grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }

    }


}
