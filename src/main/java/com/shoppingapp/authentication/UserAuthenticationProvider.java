package com.shoppingapp.authentication;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.shoppingapp.dao.CommonDao;
import com.shoppingapp.dao.UserDao;
import com.shoppingapp.entity.LoginState;


@Component
public class UserAuthenticationProvider implements AuthenticationProvider
{
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException
		{
			
				String userName = authentication.getName();
				String password = authentication.getCredentials().toString();
				CommonDao.initialize();
				LoginState login = UserDao.getUser(userName, password);

				if (login.user != null)
				{
						List<GrantedAuthority> grantedAuths = new ArrayList<>();
						grantedAuths.add(()-> {return "AUTH_USER";});
						Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
						System.out.println(auth.getAuthorities());
						return auth;
				}
				else
				{
						throw new AuthenticationCredentialsNotFoundException(login.error);
				}
		}

		@Override
		public boolean supports(Class<?> authentication)
		{
				return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		}
}