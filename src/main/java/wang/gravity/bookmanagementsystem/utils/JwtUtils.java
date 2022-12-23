package wang.gravity.bookmanagementsystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.pojo.User;

@Component
public class JwtUtils {
    /**
     * 签发对象：这个用户的用户名
     * 签发时间：现在
     * 有效时间：30分钟
     * 加密密钥：这个人的id加上一串字符串
     */
    public static String createToken(User user) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, 24);
        Date expiresDate = nowTime.getTime();

        String secret = PropertiesUtil.getJwtSecret();
        //签发对象
        return JWT.create().withAudience(user.getUsername())
                //发行时间
                .withIssuedAt(new Date())
                //有效时间
                .withExpiresAt(expiresDate)
                .withClaim("username", user.getUsername())
                //加密
                .sign(Algorithm.HMAC256(user.getUsername() + secret));
    }

    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     *
     * @param token
     */
    public static void verifyToken(String token, String userId) throws Exception {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(userId + PropertiesUtil.getJwtSecret())).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            throw new MyException(ErrorCode.TOKEN_USER_NOT_FOUND_ERROR);
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) throws Exception {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new MyException(ErrorCode.TOKEN_USER_NOT_FOUND_ERROR);
        }
        return audience;
    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name) {
        return JWT.decode(token).getClaim(name);
    }

}

