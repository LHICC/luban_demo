import com.lb.base.auth.utils.JwtHelper;
import com.lb.base.auth.utils.JwtUserInfo;
import com.lb.base.auth.utils.Token;

/**
 * jwt 生成和解析 测试类
 *
 */
public class JwtHelperTest {

    /**
     * 验证自己生成的 公钥私钥能否 成功生成token 解析token
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        JwtUserInfo jwtInfo = new JwtUserInfo(1L, "test", "测试", 1L, 1L);
        int expire = 7200;

        //生成Token  注意： 确保该模块 resources 目录下已经有了私钥
        Token token = JwtHelper.generateUserToken(jwtInfo, "pri.key", expire);
        System.out.println(token);

        //解析Token  注意： 确保该模块 resources 目录下已经有了公钥
        JwtUserInfo jwtFromToken = JwtHelper.getJwtFromToken(token.getToken(), "pub.key");
        System.out.println(jwtFromToken);

    }

}
