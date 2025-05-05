package com.entreprise.assurance.identityservice.controller;

import com.entreprise.assurance.identityservice.model.Conseiller;
import com.entreprise.assurance.identityservice.repository.ConseillerRepository;
import com.entreprise.assurance.identityservice.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private ConseillerRepository repo;
    @Autowired private JwtUtils jwtUtils;
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Conseiller c = repo.findByUsername(username);
        if (c != null && c.getPassword().equals(password)) {
            return jwtUtils.generateJwtToken(username, c.getEntreprise().getId());
        }
        throw new RuntimeException("Bad credentials");
    }
}