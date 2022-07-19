import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TestJwt {


    public static void main(String[] args) {
        String token = createJwt();
        System.out.println("生成的令牌: " + token);
        parseJwt(token);
    }
    
    /**
     * 生成jwt令牌 (即token)
     */
    public static String createJwt() {

        //当前时间(毫秒数)
        long now = System.currentTimeMillis();
        //过期时间 1000 * 10 （即10s后）
        long exp = now + 1000 * 10;

        JwtBuilder builder = Jwts.builder();
        builder.setId("1111")
                .setSubject("admin") //主题:用户名
                .setIssuedAt(new Date()) //签发时间
                .signWith(SignatureAlgorithm.HS256,"lanshuqian")
                .setExpiration(new Date(exp));  //过期时间
        return builder.compact(); //开始生成jwt令牌
    }

    /**
     * 解析jwt令牌
     * @return
     */
    public static void parseJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey("lanshuqian")
                .parseClaimsJws(token).getBody();
        System.out.println("id" + claims.getId());
        System.out.println("subject:" + claims.getSubject());

    }
}
