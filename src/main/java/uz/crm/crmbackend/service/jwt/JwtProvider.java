package uz.crm.crmbackend.service.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.crm.crmbackend.dto.user.ResToken;
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
        try {
            ResToken resToken = new ResToken();
            boolean flag = false;
            String string = "";
            for (UserRole userRole : user.getUserRoleSet()) {
                if (userRole.getName().equals(Constant.SUPER_ADMIN)) {
                    flag = true;
                    string = userRole.getAuthority();
                    break;
                }
                string = userRole.getAuthority();
            }
            resToken.setUserRoleSet(string);
            resToken.setUserName(user.getFullName());
            resToken.setUserId(user.getId());
            if (flag) {
                if (user.getLogoFile() != null) {
                    resToken.setImgId(user.getLogoFile().getId());
                }
            } else {
                resToken.setEduCenterId(user.getEduCenter().getId());
                resToken.setEduCenterName(user.getEduCenter().getEdu_centerName());
            }
            claims.put("attributes", new ObjectMapper().writeValueAsString(resToken));
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
