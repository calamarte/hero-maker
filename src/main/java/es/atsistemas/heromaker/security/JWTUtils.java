package es.atsistemas.heromaker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTUtils {

    public static final String HEADER = "x-api-key";
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //Minutes
    private static long expirationTime;

    @Value("${token.expiration.time:15}")
    private void setExpirationTime(long expirationTime){
        JWTUtils.expirationTime = expirationTime;
    }

    public static String getToken(String username){
        List grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("user")
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ZonedDateTime dateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());

        Date now = Date.from(dateTime.toInstant());
        Date expire = Date.from(dateTime.plusMinutes(expirationTime).toInstant());

        String token = Jwts
                .builder()
                .setSubject(username)
                .claim("authorities", grantedAuthorities)
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SECRET_KEY)
                .compact();

        return token;
    }

    public static Claims validateToken(String token){
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }
}
