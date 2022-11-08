package com.example.projekatglavni1.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.projekatglavni1.constants.SecurityConstants;
import com.example.projekatglavni1.domain.UserPrincipal;

@Component
public class JwtTokenProvider {

	
	private String secret;
	
	public JwtTokenProvider(@Value("${security.jwt.token.secret-key}")String secret) {
		this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}
	
	public String generateJwtToken(UserPrincipal userPrincipal) {
		String[] claims = getClaimsFromUser(userPrincipal);
		return JWT.create().withIssuer(SecurityConstants.GET_ARRAYS_LLC).withAudience(SecurityConstants.GET_ARRAYS_ADMINISTRATION)
				.withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
				.withArrayClaim(SecurityConstants.AUTHORITIES, claims).withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(secret.getBytes()));
	}
	
	public List<GrantedAuthority> getAuthorities(String token){
		String[] claims = getClaimsFromToken(token);
		return Arrays.stream(claims).map(SimpleGrantedAuthority::new ).collect(Collectors.toList());
	}

	private String[] getClaimsFromToken(String token) {
		JWTVerifier verifier = getJwtVerifier();
		return verifier.verify(token).getClaim(SecurityConstants.AUTHORITIES).asArray(String.class);
	}

	public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
				= new UsernamePasswordAuthenticationToken(username, null, authorities);
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return usernamePasswordAuthenticationToken;
	}
	
	public boolean isTokenValid(String username, String token) {
		JWTVerifier verifier = getJwtVerifier();
		return StringUtils.isNotEmpty(username) && isTokenExpired(verifier, token);
	}
	
	private boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date expiration = verifier.verify(token).getExpiresAt();
		return expiration.before(new Date());
	}
	
	public String getSubject(String token) {
		JWTVerifier verifier = getJwtVerifier();
		return verifier.verify(token).getSubject();
	}

	private JWTVerifier getJwtVerifier() {
		JWTVerifier verifier;
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret);
			verifier = JWT.require(algorithm).withIssuer(SecurityConstants.GET_ARRAYS_LLC).build();
		}catch(JWTVerificationException e) {
			throw new JWTVerificationException(SecurityConstants.TOKEN_CANNOT_BE_VERIFIED);
		}
		
		return verifier;
	}

	private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
		List<String> authorities = new ArrayList<>();
		for(GrantedAuthority grantedAuthority : userPrincipal.getAuthorities()) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return authorities.toArray(new String[0]);
	}
}
