import cn.hutool.core.io.FileUtil;
import com.lb.user.UserApplication;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

@SpringBootTest()
public class createPassword {

    @Test
    public void test() {
        String password = "adm_1";
        String newPassword = DigestUtils.md5Hex(password);
        System.out.println(newPassword);
    }

    public static void main(String[] args) throws Exception {
        //自定义 随机密码,  请修改这里
        String password = "user_1";
        String newPassword = DigestUtils.md5Hex(password);
        System.out.println(newPassword);
    }
}
