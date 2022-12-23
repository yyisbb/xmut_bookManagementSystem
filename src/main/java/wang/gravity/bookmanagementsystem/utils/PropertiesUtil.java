package wang.gravity.bookmanagementsystem.utils;


import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class PropertiesUtil {

    private static String jwtSecret;
    private static String aesSecret;

    private static String projectPort;

    static {
        ClassPathResource resource = new ClassPathResource("application.properties");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            jwtSecret = properties.getProperty("jwt.secret");
            aesSecret = properties.getProperty("aes.secret");
            projectPort = properties.getProperty("server.port");
        } catch (IOException e) {
            System.out.println("application.properties属性文件读取异常" + e);
        }
    }

    public static String getJwtSecret() {
        return jwtSecret;
    }
    public static String getAesSecret() {
        return aesSecret;
    }

    public static String getProjectPort() {
        return projectPort;
    }

}