package cn.com.goods.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class jwtUtil {
	public static String sercetKey = "settlement";
	public final static long keeptime = 1800000;

	/*
	 * @Value("${token.sercetKey}") public static String sercetKey;
	 * 
	 * @Value("${token.keeptime:30000}") public static long keeptime;
	 */

	public static String generToken(String id, String issuer, String subject) {
		long ttlMillis = keeptime;
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(sercetKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now);
		if (subject != null) {
			builder.setSubject(subject);
		}
		if (issuer != null) {
			builder.setIssuer(issuer);
		}
		builder.signWith(signatureAlgorithm, signingKey);

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	public String updateToken(String token) {
		try {
			Claims claims = verifyToken(token);
			String id = claims.getId();
			String subject = claims.getSubject();
			String issuer = claims.getIssuer();
			Date date = claims.getExpiration();
			return generToken(id, issuer, subject);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "0";
	}

	public String updateTokenBase64Code(String token) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			token = new String(decoder.decodeBuffer(token), "utf-8");
			Claims claims = verifyToken(token);
			String id = claims.getId();
			String subject = claims.getSubject();
			String issuer = claims.getIssuer();
			Date date = claims.getExpiration();
			String newToken = generToken(id, issuer, subject);
			return base64Encoder.encode(newToken.getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "0";
	}

	public static Claims verifyToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(sercetKey))
				.parseClaimsJws(token).getBody();
		return claims;
	}

	public static void main(String args[]) {
		// String str = generToken("0001",null,null);
		// System.out.println(str);

		Claims claims = verifyToken(
				"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwMDAxIiwiaWF0IjoxNTIxMDEyMDc5LCJleHAiOjE1MjEwMTIwNzl9.tm6SWLtg2v3vHOO1hHBet4X-3vBV0S__BpBRLVOqAws");
		System.out.println(claims.getId());
	}
}
