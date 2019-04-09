package test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * 密码加密测试类
 *
 * @author 廿二月的天
 */
public class PasswordEncryptTest {

    @Test
    public void testPass() {
        String pass = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String hashPass = encode.encode(pass);
        System.out.println(hashPass);
    }
}
