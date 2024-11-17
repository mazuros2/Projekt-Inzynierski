package dev.projekt_inzynierski.configurationJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static final String SECRET_KEY = "8FqRGfyxcvCswkl1XOp0fCcJoR3PfYLe";

    public String extractLogin(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }


    public <T> T extractClaim (String jwtToken, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    public String tokenGenerator(UserDetails userDetails){
        return tokenGenerator(new HashMap<>(),userDetails);
    }

    public String tokenGenerator(
            Map<String,Object> extraClaims,
            UserDetails detailsUser
    ){
        return Jwts.builder().setClaims(extraClaims).setSubject(detailsUser.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 *60 *24)).signWith(getKeyToSignIn(), SignatureAlgorithm.HS256).compact();
    }

    public boolean tokenValidation(String jwtToken, UserDetails userDetails){
        final String login = extractLogin(jwtToken);
        return (login.equals(userDetails.getUsername())) && !isJwtTokenExpired(jwtToken);
    }

    private boolean isJwtTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    private Claims extractAllClaims(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(getKeyToSignIn()).build().parseClaimsJws(jwtToken).getBody();
    }

    private Key getKeyToSignIn() {
        byte[] keybyt = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keybyt);
    }
}