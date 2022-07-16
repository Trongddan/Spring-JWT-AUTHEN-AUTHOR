package com.example.jwt3.jwt;

import com.example.jwt3.entity.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 84600;
    private static  final Logger logger= LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${app.jwt.secret}")
    private String secretkey;

    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getId() + ","+ user.getEmail())
                .claim("roles",user.getRoles().toString())
                .setIssuer("CodeDanken")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512,secretkey)
                .compact();
    }
    public  boolean validateAccessToken(String token){
        try {
            Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            logger.error("JWT Expire",e);
        }catch (IllegalArgumentException e){
            logger.error("Token is null or whitespace",e);
        }catch (MalformedJwtException e){
            logger.error("jwt invalid",e);
        }catch (UnsupportedJwtException e){
            logger.error("JWT unsupport",e);
        }catch (SignatureException e){
            logger.error("sign vaidate faild",e);
        }
        return  false;
    }
    public String getSubject(String token){
        return  parsClaims(token).getSubject();
    }
    public   Claims parsClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretkey)
                .parseClaimsJws(token)
                .getBody();
    }


}
