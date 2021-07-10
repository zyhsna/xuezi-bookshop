package edu.zyh.utils;

import edu.zyh.domain.Purchaser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {
    /*expire time a week*/
    private static final long EXPIRE = 60000 * 60 * 24 * 7;

    private static final String SECRET = "xuezi-bookshop";

    private static final String TOKEN_PREFIX = "bookshop";

    private static final String subject = "bookshop";

    public static String generateJsonWebToken(Purchaser purchaser) {
        String token = Jwts.builder().setSubject(subject).
                claim("username", purchaser.getUsername()).
                claim("id", purchaser.getUserId()).
                claim("phone", purchaser.getTelephone()).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + EXPIRE)).
                signWith(SignatureAlgorithm.HS256, SECRET).compact();
        return TOKEN_PREFIX + token;
    }

    public static Claims checkJWT(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
}
