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

@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    @Autowired
    IValidatorService validatorService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(!hasAuthorizationHeader(request)){
            filterChain.doFilter(request,response);
            return;
        }
        String token=request.getHeader("Authorization");
        Integer userId=JWTUtil.getUserIdFromToken(token);
        UserEntity user=validatorService.validateUserExistence(userId);
        if(!JWTUtil.validateToken(token,user.getUsername(),user.getPassword())){
            filterChain.doFilter(request,response);
            return;
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