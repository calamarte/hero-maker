package es.atsistemas.heromaker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTUtils {

    private static long expirationTime;
    private static String secret;
    private static String prefix;

    @Value("${token.expiration.time:#{15 * 60 * 1000}}")
    private void setExpirationTime(long expirationTime){
        JWTUtils.expirationTime = expirationTime;
    }

    @Value("${token.secret:atsistemas}")
    private void setSecret(String secret){
        JWTUtils.secret = secret;
    }

    @Value("${token.prefix:Bearer}")
    public void setPrefix(String prefix) {
        JWTUtils.prefix = prefix;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getToken(String username){
        List grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("user")
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ZonedDateTime dateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());

        String token = Jwts
                .builder()
                .setId("crud")
                .setSubject(username)
                .claim("authorities", grantedAuthorities)
                .setIssuedAt(Date.from(dateTime.toInstant()))
                .setExpiration(Date.from(dateTime.plusNanos(expirationTime).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

        return token;
    }

    public static Claims validateToken(String token){
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }
}
