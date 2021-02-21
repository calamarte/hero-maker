package es.atsistemas.heromaker.controllers.auth;

import com.google.common.base.Strings;
import es.atsistemas.heromaker.security.JWTUtils;
import es.atsistemas.heromaker.services.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserService service;

    @Operation(summary = "Login")
    @PostMapping("/auth")
    public AuthResponse login(@RequestBody LoginRequest request){

        String username = request.getUsername();
        String pass = request.getPassword();

        if(!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(pass)) {

            Optional<es.atsistemas.heromaker.model.User> result = service.findByUsername(username);

            if (result.isPresent() && result.get().getPassword().equals(pass)) {
                return new User(username, JWTUtils.HEADER, JWTUtils.getToken(username));
            }
        }

        return new Error(username);
    }

    // Login custom Request
    private static class LoginRequest{
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * Respuestas personalizadas para la auntenticación
     * Interface de marcado
     */
    @Hidden
    private interface AuthResponse{ }

    private class User implements AuthResponse{
        private String username;
        private String header;
        private String token;

        User(String username, String header, String token) {
            this.username = username;
            this.header = header;
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    private class Error implements AuthResponse{
        private String message;

        Error(String username) {
            this.message = "No ha sido posible loguear al usuario " + username;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
