package com.shoppingapp.authentication;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.shoppingapp.dao.CommonDao;
import com.shoppingapp.dao.UserDao;


@Component
public class UserAuthenticationProvider implements AuthenticationProvider
{
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException
		{
				String userName = authentication.getName();
				String password = authentication.getCredentials().toString();

				if (authorizedUser(userName, password))
				{
						List<GrantedAuthority> grantedAuths = new ArrayList<>();
						grantedAuths.add(()-> {return "AUTH_USER";});
						Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
						System.out.println(auth.getAuthorities());
						return auth;
				}
				else
				{
						throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
				}
		}

		private boolean authorizedUser(String userName, String password)
		{
			CommonDao.initialize();
				System.out.println("username is :" + userName+" and password is "+password );
				if(UserDao.getUser(userName, password) != null) {
					System.out.println("user: "+UserDao.getUser(userName, password));
					return true;
				}
				return false;
		}

		@Override
		public boolean supports(Class<?> authentication)
		{
				return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		}
}