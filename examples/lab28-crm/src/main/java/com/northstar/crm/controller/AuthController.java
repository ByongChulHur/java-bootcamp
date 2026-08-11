package com.northstar.crm.controller;

import com.northstar.crm.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;

  public AuthController(AuthenticationManager authenticationManager,
                        UserDetailsService userDetailsService,
                        JwtService jwtService) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody Map<String, String> body) {
    String username = body.getOrDefault("username", "");
    String password = body.getOrDefault("password", "");

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

    UserDetails user = userDetailsService.loadUserByUsername(username);
    String role = user.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

    String token = jwtService.issueToken(user.getUsername(), role);
    return Map.of("accessToken", token, "tokenType", "Bearer");
  }
}