package uz.crm.crmbackend.service.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.crm.crmbackend.dto.user.TokenDto;
import uz.crm.crmbackend.dto.user.UserRolesForToken;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.entity.UserRole;
import uz.crm.crmbackend.tools.Constant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtProvider {
    private static final long expire = 1000 * 60 * 60 * 12;
    private static final String key = "AqishUsBAsusbaJs)a9s!s_-";

    public String generateToken(String username, User user) {

        Claims claims = Jwts.claims().setSubject(username);
        List<UserRolesForToken> roles = new ArrayList<>();
        for (UserRole userRole : user.getUserRoleSet()) {
            roles.add(new UserRolesForToken(userRole.getId(), userRole.getName()));
        }
        try {
                claims.put("userRole", new ObjectMapper().writeValueAsString(roles));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public String getUsername(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }


}
