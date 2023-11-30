package app.controllers;

import app.DTOs.CreateAwardDTO;
import app.DTOs.LoginDTO;
import app.services.AuthJWT;
import app.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAdmin(@RequestBody LoginDTO loginDTO) {
        String token = authService.Auth(loginDTO);

        if (token.equals("")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } else {
            // Wrap the token in a JSON object
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
    }
}
