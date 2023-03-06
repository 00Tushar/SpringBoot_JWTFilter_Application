package com.niit.bej.security;

import com.niit.bej.domain.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generateToken(Customer customer) {
        String generatedToken = Jwts.builder()
                .setSubject(customer.getName())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();
        return Map.of(
                "token", generatedToken,
                "message", "Token Generated and " + customer.getName() + " Logged in Successfully!"
        );
    }
}
