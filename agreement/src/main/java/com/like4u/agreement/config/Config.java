package com.like4u.agreement.config;





import com.like4u.agreement.protocol.Algorithm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/17 19:24
 */

public class Config {
    static Properties properties;
    static {
        InputStream in = Config.class.getResourceAsStream("/application.properties");

        properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  int getServerPort(){
        String value = properties.getProperty("server.port");

        if (value==null){
            return 8080;
        }else {
            return Integer.parseInt(value);
        }
    }
    public static Algorithm getAlgorithm(){
        String value = properties.getProperty("serializer.algorithm");
        if (value==null){
            return Algorithm.Java;
        }else {
            return Algorithm.valueOf(value);
        }
    }


}
