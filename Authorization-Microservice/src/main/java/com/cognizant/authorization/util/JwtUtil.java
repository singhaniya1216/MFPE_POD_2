package com.cognizant.authorization.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This is a Utility class which is used to carry out token related operations
 * like creating tokens,validation, setting time limit of a token etc.
 */
@Service
public class JwtUtil {

	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	@Value("${jwt.secret}")
	private String secretkey;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * This method extracts the Expiry date from the token using getExpiration from
	 * Claims
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
	}

	@SuppressWarnings("unused")
	private Boolean isTokenExpired(String token) {
		logger.info("start");
		logger.info("end");
		boolean isTokenExpired = extractExpiration(token).before(new Date());
		return isTokenExpired;
	}

	/**
	 * Returns the claims of the token, like Issuer, Expiration, Subject, and the ID
	 */
	public String generateToken(UserDetails userDetails) {
		logger.info("start");
		Map<String, Object> claims = new HashMap<>();
		logger.info("end");
		return createToken(claims, userDetails.getUsername());
	}

	/**
	 * Token is generated for 30 mins. The signature is used to verify the message
	 * wasn't changed along the way.
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		logger.info("start");
		logger.info("end");
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(SignatureAlgorithm.HS512, secretkey).compact();
	}

	/**
	 * Validates the token
	 */
	public Boolean validateToken(String token) {
		logger.info("start");
		try {
			logger.info("end");
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();

			return true;
		} catch (Exception e) {
			return false;
		}

	}
}