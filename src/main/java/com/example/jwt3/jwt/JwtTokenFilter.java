package com.example.jwt3.jwt;

import com.example.jwt3.entity.Role;
import com.example.jwt3.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!hasAuthorization(request)){
            filterChain.doFilter(request,response);
            return;
        }
        String Accesstoken = getAccessToken(request);
        if(!jwtTokenUtil.validateAccessToken(Accesstoken)){
            filterChain.doFilter(request,response);
            return;
        }
        setAuthenticationContext(Accesstoken,request);
        filterChain.doFilter(request,response);
    }

    private void setAuthenticationContext(String Accesstoken,HttpServletRequest request) {
        UserDetails userDetails = getUserDetail(Accesstoken);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,null,userDetails.getAuthorities()
        );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UserDetails getUserDetail(String Accesstoken) {
        User userDetail = new User();

        //claims ???? ????y chi??nh la?? chu????i token ????????c ma?? ho??a nh?? tr??n jwt.io
        Claims claims = jwtTokenUtil.parsClaims(Accesstoken);
        System.out.println(claims);

        String claimsRole = (String) claims.get("roles");

         // chu????i claimRole co?? da??ng : [ROLE_ADMIN,ROLE_EDITOR...] vi vay ta se?? bo?? ??i 2 ki?? t???? []
        claimsRole = claimsRole.replace("[","").replace("]","");

        //va?? du??ng split ?????? ????a chu????i v???? tha??nh 1 ma??ng d????a va??o d????u ,.
        String[] roleNames = claimsRole.split(",");

        for( String aRoleName : roleNames){
            userDetail.addRole(new Role(aRoleName));
        }

        String subject = (String) claims.get(Claims.SUBJECT);


        String[] subjectArray = subject.split(",");
        userDetail.setId(Integer.parseInt(subjectArray[0]));
        userDetail.setEmail(subjectArray[1]);
        return userDetail;
    }


    private boolean hasAuthorization(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")){
            return false;
        }
        return  true;
    }
    private String getAccessToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }
}
