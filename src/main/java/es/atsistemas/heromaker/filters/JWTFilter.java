package es.atsistemas.heromaker.filters;

import com.google.common.base.Strings;
import es.atsistemas.heromaker.security.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {

            Claims claims = getClaims(httpServletRequest);

            if (claims == null) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }

            if (claims.get("authorities") != null) {
                setUpSpringAuthentication(claims);
            } else {
                SecurityContextHolder.clearContext();
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) httpServletResponse).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }

    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     *
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private Claims getClaims(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (!Strings.isNullOrEmpty(token) && token.startsWith(JWTUtils.getPrefix())) {
            String[] splitToken = token.split(" ");

            if(splitToken.length == 2){
                token = splitToken[1];
            }else {
                 return null;
            }

            return JWTUtils.validateToken(token);
        }

        return null;
    }
}
