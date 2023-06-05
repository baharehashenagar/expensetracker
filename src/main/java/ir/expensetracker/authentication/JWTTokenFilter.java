package ir.expensetracker.authentication;

import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.service.facade.IValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    @Autowired
    IValidatorService validatorService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        List<String> whiteList=new ArrayList<>();
        whiteList.add("/v2/api-docs");
        whiteList.add("/configuration/ui");
        whiteList.add("/swagger-resources");
        whiteList.add("/configuration/security");
        whiteList.add("/swagger-ui.html");
        whiteList.add("/webjars/");
        whiteList.add("/api/users/login");
        whiteList.add("/api/users");
        whiteList.add("/api/users/forgetpassword");
        if(whiteList.stream().anyMatch(request.getRequestURI()::contains)){
            filterChain.doFilter(request,response);
            return;
        }
        if(!hasAuthorizationHeader(request)){
            throw new ServletException(" Invalid Token");
        }
        String token=request.getHeader("Authorization");
        Integer userId=JWTUtil.getUserIdFromToken(token);
        UserEntity user=validatorService.validateUserExistence(userId);
        if(!JWTUtil.validateToken(token,user.getUsername(),user.getPassword())){
            throw new ServletException(" Invalid Token");
        }
        filterChain.doFilter(request,response);
    }
    private boolean hasAuthorizationHeader(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token==null || token.equals("")){
            return false;
        }
        return true;
    }
}