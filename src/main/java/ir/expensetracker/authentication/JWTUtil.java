package ir.expensetracker.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.function.Function;

public class JWTUtil {

    private static final Long JWT_TOKEN_EXPIRATION = 3600L;
    private static final String JWT_SECRET_KEY = "expensetracker";

    public static String generateToken(String username, String password, Integer userId) {
        return Jwts.builder()
                .setSubject("ExpenseTrackerApp")
                .claim("username", username)
                .claim("password", password)
                .claim("userId", userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();
    }

    public static Boolean validateToken(String token, String username, String password) {
        Claims claimsList = getAllClaimsFromToken(token);
        if (claimsList.size() > 0 && claimsList.get("username").equals(username) && claimsList.get("password").equals(password) && !isTokenExpired(token)) {
            return true;
        } else {
            return false;
        }
    }

    public static Integer getUserIdFromToken(String token) {
        Claims claimsList = getAllClaimsFromToken(token);
        if (claimsList.size() > 0 && claimsList.containsKey("userId")) {
            return Integer.valueOf(claimsList.get("userId").toString());
        }
        return null;
    }

    private static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}